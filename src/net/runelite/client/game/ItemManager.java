/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.simplicity.client.Item;
import com.simplicity.client.Sprite;
import com.simplicity.client.cache.definitions.ItemDefinition;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.GameState;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.SpritePixels;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.RuneLite;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.util.AsyncBufferedImage;
import net.runelite.http.api.item.ItemClient;
import net.runelite.http.api.item.ItemPrice;
import net.runelite.http.api.item.SearchResult;
import static net.runelite.api.Constants.*;
import static net.runelite.api.ItemID.*;
import static net.runelite.api.ItemID.AGILITY_CAPE;

@Singleton
@Slf4j
public class ItemManager
{
	
	@Value
	private static class ImageKey
	{
		private final int itemId;
		private final int itemQuantity;
		private final boolean stackable;
	}

	@Value
	private static class OutlineKey
	{
		private final int itemId;
		private final int itemQuantity;
		private final Color outlineColor;
	}

	private final Client client = RuneLite.getClient();
	private final ScheduledExecutorService scheduledExecutorService;
	private final ClientThread clientThread;

	private final ItemClient itemClient = new ItemClient();
	private final LoadingCache<String, SearchResult> itemSearches;
	private final ConcurrentMap<Integer, ItemPrice> itemPrices = new ConcurrentHashMap<>();
	private final LoadingCache<ImageKey, AsyncBufferedImage> itemImages;
	private final LoadingCache<Integer, ItemComposition> itemCompositions;
	private final LoadingCache<OutlineKey, BufferedImage> itemOutlines;

	@Inject
	public ItemManager(ScheduledExecutorService executor, ClientThread clientThread)
	{
		this.scheduledExecutorService = executor;
		this.clientThread = clientThread;

		scheduledExecutorService.scheduleWithFixedDelay(this::loadPrices, 0, 30, TimeUnit.MINUTES);

		itemSearches = CacheBuilder.newBuilder()
			.maximumSize(512L)
			.expireAfterAccess(1, TimeUnit.HOURS)
			.build(new CacheLoader<String, SearchResult>()
			{
				@Override
				public SearchResult load(String key) throws Exception
				{
					return itemClient.search(key);
				}
			});

		itemImages = CacheBuilder.newBuilder()
			.maximumSize(128L)
			.expireAfterAccess(1, TimeUnit.HOURS)
			.build(new CacheLoader<ImageKey, AsyncBufferedImage>()
			{
				@Override
				public AsyncBufferedImage load(ImageKey key) throws Exception
				{
					return loadImage(key.itemId, key.itemQuantity, key.stackable);
				}
			});

		itemCompositions = CacheBuilder.newBuilder()
			.maximumSize(1024L)
			.expireAfterAccess(1, TimeUnit.HOURS)
			.build(new CacheLoader<Integer, ItemComposition>()
			{
				@Override
				public ItemComposition load(Integer key) throws Exception
				{
					return client.getItemDefinition(key);
				}
			});

		itemOutlines = CacheBuilder.newBuilder()
			.maximumSize(128L)
			.expireAfterAccess(1, TimeUnit.HOURS)
			.build(new CacheLoader<OutlineKey, BufferedImage>()
			{
				@Override
				public BufferedImage load(OutlineKey key) throws Exception
				{
					return loadItemOutline(key.itemId, key.itemQuantity, key.outlineColor);
				}
			});
	}

	private void loadPrices()
	{
		try
		{
			ItemPrice[] prices = itemClient.getPrices();
			if (prices != null)
			{
				itemPrices.clear();
				for (ItemPrice price : prices)
				{
					itemPrices.put(price.getItem().getId(), price);
				}
			}

			log.debug("Loaded {} prices", itemPrices.size());
		}
		catch (IOException e)
		{
			log.warn("error loading prices!", e);
		}
	}

	@Subscribe
	public void onGameStateChanged(final GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOGIN_SCREEN)
		{
			itemCompositions.invalidateAll();
		}
	}

	/**
	 * Look up an item's price
	 *
	 * @param itemID item id
	 * @return item price
	 */
	public int getItemPrice(int itemID)
	{
		return getItemPrice(itemID, false);
	}
	
	/**
	 * Look up an item's price
	 *
	 * @param itemID item id
	 * @param ignoreUntradeableMap should the price returned ignore the {@link }
	 * @return item price
	 */
	public int getItemPrice(int itemID, boolean ignoreUntradeableMap)
	{
		if (itemID == ItemID.COINS_995)
		{
			return 1;
		}
		
		if (itemID == ItemID.PLATINUM_TOKEN + 30_000)
		{
			return 1000;
		}

		ItemComposition itemComposition = getItemComposition(itemID);
		if (itemComposition.getNote() != -1)
		{
			itemID = itemComposition.getLinkedNoteId();
		}

		int price = 0;
		for (int mappedID : ItemMapping.map(itemID))
		{
			ItemPrice ip = itemPrices.get(mappedID);
			if (ip != null)
			{
				price += ip.getPrice();
			}
		}

		return price;
	}

	/**
	 * Look up an item's composition
	 *
	 * @param itemName item name
	 * @return item search result
	 * @throws java.util.concurrent.ExecutionException exception when item
	 * is not found
	 */
	public SearchResult searchForItem(String itemName) throws ExecutionException
	{
		return itemSearches.get(itemName);
	}

	/**
	 * Look up an item's composition
	 *
	 * @param itemId item id
	 * @return item composition
	 */
	public ItemComposition getItemComposition(int itemId)
	{
		return itemCompositions.getUnchecked(itemId);
	}
	
	/**
	 * Loads item sprite from game, makes transparent, and generates image
	 *
	 * @param itemId
	 * @return
	 */
	private AsyncBufferedImage loadImage(int itemId, int quantity, boolean stackable)
	{
		AsyncBufferedImage img = new AsyncBufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		clientThread.invoke(() ->
		{
			if (client.getGameState().ordinal() < GameState.LOGIN_SCREEN.ordinal())
			{
				return false;
			}
			
			Sprite sprite = ItemDefinition.getSprite(itemId, quantity, 0);
			
			/*SpritePixels sprite = client.createItemSprite(itemId, quantity, 1, SpritePixels.DEFAULT_SHADOW_COLOR,
				stackable ? 1 : 0, false, CLIENT_DEFAULT_ZOOM);*/
			if (sprite == null)
			{
				System.out.println("sprite still null");
				return false;
			}
			sprite.toBufferedImage(img);
			img.loaded();
			return true;
		});
		return img;
	}

	/**
	 * Get item sprite image as BufferedImage.
	 * <p>
	 * This method may return immediately with a blank image if not called on the game thread.
	 * The image will be filled in later. If this is used for a UI label/button, it should be added
	 * using AsyncBufferedImage::addTo to ensure it is painted properly
	 *
	 * @param itemId
	 * @return
	 */
	public AsyncBufferedImage getImage(int itemId)
	{
		return getImage(itemId, 1, false);
	}

	/**
	 * Get item sprite image as BufferedImage.
	 * <p>
	 * This method may return immediately with a blank image if not called on the game thread.
	 * The image will be filled in later. If this is used for a UI label/button, it should be added
	 * using AsyncBufferedImage::addTo to ensure it is painted properly
	 *
	 * @param itemId
	 * @param quantity
	 * @return
	 */
	public AsyncBufferedImage getImage(int itemId, int quantity, boolean stackable)
	{
		try
		{
			return itemImages.get(new ImageKey(itemId, quantity, stackable));
		}
		catch (ExecutionException ex)
		{
			return null;
		}
	}
	public int canonicalize(int itemID)
	{
		ItemComposition itemComposition = getItemComposition(itemID);

		if (itemComposition.getNote() != -1)
		{
			return itemComposition.getLinkedNoteId();
		}

		if (itemComposition.getPlaceholderTemplateId() != -1)
		{
			return itemComposition.getPlaceholderId();
		}
		int found = WORN_ITEMS.getOrDefault(itemID, itemID);
		return found > 0 ? found : itemID;
	}
	/**
	 * Create item sprite and applies an outline.
	 *
	 * @param itemId item id
	 * @param itemQuantity item quantity
	 * @param outlineColor outline color
	 * @return image
	 */
	private BufferedImage loadItemOutline(final int itemId, final int itemQuantity, final Color outlineColor)
	{
		final SpritePixels itemSprite = client.createItemSprite(itemId, itemQuantity, 1, 0, 0, true, 710);
		return itemSprite.toBufferedOutline(outlineColor);
	}

	/**
	 * Get item outline with a specific color.
	 *
	 * @param itemId item id
	 * @param itemQuantity item quantity
	 * @param outlineColor outline color
	 * @return image
	 */
	public BufferedImage getItemOutline(final int itemId, final int itemQuantity, final Color outlineColor)
	{
		try
		{
			return itemOutlines.get(new OutlineKey(itemId, itemQuantity, outlineColor));
		}
		catch (ExecutionException e)
		{
			return null;
		}
	}

	private static final ImmutableMap<Integer, Integer> WORN_ITEMS = ImmutableMap.<Integer, Integer>builder().
			put(BOOTS_OF_LIGHTNESS_89, BOOTS_OF_LIGHTNESS).
			put(PENANCE_GLOVES_10554, PENANCE_GLOVES).

			put(GRACEFUL_HOOD_11851, GRACEFUL_HOOD).
			put(GRACEFUL_CAPE_11853, GRACEFUL_CAPE).
			put(GRACEFUL_TOP_11855, GRACEFUL_TOP).
			put(GRACEFUL_LEGS_11857, GRACEFUL_LEGS).
			put(GRACEFUL_GLOVES_11859, GRACEFUL_GLOVES).
			put(GRACEFUL_BOOTS_11861, GRACEFUL_BOOTS).
			put(GRACEFUL_HOOD_13580, GRACEFUL_HOOD_13579).
			put(GRACEFUL_CAPE_13582, GRACEFUL_CAPE_13581).
			put(GRACEFUL_TOP_13584, GRACEFUL_TOP_13583).
			put(GRACEFUL_LEGS_13586, GRACEFUL_LEGS_13585).
			put(GRACEFUL_GLOVES_13588, GRACEFUL_GLOVES_13587).
			put(GRACEFUL_BOOTS_13590, GRACEFUL_BOOTS_13589).
			put(GRACEFUL_HOOD_13592, GRACEFUL_HOOD_13591).
			put(GRACEFUL_CAPE_13594, GRACEFUL_CAPE_13593).
			put(GRACEFUL_TOP_13596, GRACEFUL_TOP_13595).
			put(GRACEFUL_LEGS_13598, GRACEFUL_LEGS_13597).
			put(GRACEFUL_GLOVES_13600, GRACEFUL_GLOVES_13599).
			put(GRACEFUL_BOOTS_13602, GRACEFUL_BOOTS_13601).
			put(GRACEFUL_HOOD_13604, GRACEFUL_HOOD_13603).
			put(GRACEFUL_CAPE_13606, GRACEFUL_CAPE_13605).
			put(GRACEFUL_TOP_13608, GRACEFUL_TOP_13607).
			put(GRACEFUL_LEGS_13610, GRACEFUL_LEGS_13609).
			put(GRACEFUL_GLOVES_13612, GRACEFUL_GLOVES_13611).
			put(GRACEFUL_BOOTS_13614, GRACEFUL_BOOTS_13613).
			put(GRACEFUL_HOOD_13616, GRACEFUL_HOOD_13615).
			put(GRACEFUL_CAPE_13618, GRACEFUL_CAPE_13617).
			put(GRACEFUL_TOP_13620, GRACEFUL_TOP_13619).
			put(GRACEFUL_LEGS_13622, GRACEFUL_LEGS_13621).
			put(GRACEFUL_GLOVES_13624, GRACEFUL_GLOVES_13623).
			put(GRACEFUL_BOOTS_13626, GRACEFUL_BOOTS_13625).
			put(GRACEFUL_HOOD_13628, GRACEFUL_HOOD_13627).
			put(GRACEFUL_CAPE_13630, GRACEFUL_CAPE_13629).
			put(GRACEFUL_TOP_13632, GRACEFUL_TOP_13631).
			put(GRACEFUL_LEGS_13634, GRACEFUL_LEGS_13633).
			put(GRACEFUL_GLOVES_13636, GRACEFUL_GLOVES_13635).
			put(GRACEFUL_BOOTS_13638, GRACEFUL_BOOTS_13637).
			put(GRACEFUL_HOOD_13668, GRACEFUL_HOOD_13667).
			put(GRACEFUL_CAPE_13670, GRACEFUL_CAPE_13669).
			put(GRACEFUL_TOP_13672, GRACEFUL_TOP_13671).
			put(GRACEFUL_LEGS_13674, GRACEFUL_LEGS_13673).
			put(GRACEFUL_GLOVES_13676, GRACEFUL_GLOVES_13675).
			put(GRACEFUL_BOOTS_13678, GRACEFUL_BOOTS_13677).
			put(GRACEFUL_HOOD_21063, GRACEFUL_HOOD_21061).
			put(GRACEFUL_CAPE_21066, GRACEFUL_CAPE_21064).
			put(GRACEFUL_TOP_21069, GRACEFUL_TOP_21067).
			put(GRACEFUL_LEGS_21072, GRACEFUL_LEGS_21070).
			put(GRACEFUL_GLOVES_21075, GRACEFUL_GLOVES_21073).
			put(GRACEFUL_BOOTS_21078, GRACEFUL_BOOTS_21076).

			put(MAX_CAPE_13342, MAX_CAPE).

			put(SPOTTED_CAPE_10073, SPOTTED_CAPE).
			put(SPOTTIER_CAPE_10074, SPOTTIER_CAPE).

			put(AGILITY_CAPET_13341, AGILITY_CAPET).
			put(AGILITY_CAPE_13340, AGILITY_CAPE).
			build();
}
