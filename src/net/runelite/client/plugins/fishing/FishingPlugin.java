/*
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
 * Copyright (c) 2018, Levi <me@levischuck.com>
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
package net.runelite.client.plugins.fishing;

import com.google.inject.Provides;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.simplicity.client.Entity;
import com.simplicity.client.Item;
import com.simplicity.client.container.item.ItemContainer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemID;
import net.runelite.api.MenuAction;
import net.runelite.api.NPC;
import net.runelite.api.Varbits;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.InteractingChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.FishingSpot;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.xptracker.XpTrackerPlugin;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayMenuEntry;

@PluginDescriptor(
	name = "Fishing",
	description = "Show fishing stats and mark fishing spots",
	tags = {"overlay", "skilling"},
	loadWhenOutdated = true
)
@PluginDependency(XpTrackerPlugin.class)
@Singleton
@Slf4j
public class FishingPlugin extends Plugin
{
	private static final int TRAWLER_SHIP_REGION_NORMAL = 7499;
	private static final int TRAWLER_SHIP_REGION_SINKING = 8011;
	private static final int TRAWLER_TIME_LIMIT_IN_SECONDS = 314;

	private Instant trawlerStartTime;

	@Getter(AccessLevel.PACKAGE)
	private final FishingSession session = new FishingSession();

	@Getter(AccessLevel.PACKAGE)
	private final Map<Integer, MinnowSpot> minnowSpots = new HashMap<>();

	@Getter(AccessLevel.PACKAGE)
	private final List<com.simplicity.client.NPC> fishingSpots = new ArrayList<>();

	@Getter(AccessLevel.PACKAGE)
	private FishingSpot currentSpot;

	@Inject
	private Client client;

	@Inject
	private Notifier notifier;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private FishingConfig config;

	@Inject
	private FishingOverlay overlay;

	@Inject
	private FishingSpotOverlay spotOverlay;

	@Inject
	private FishingSpotMinimapOverlay fishingSpotMinimapOverlay;

	@Provides
	FishingConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FishingConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		overlayManager.add(spotOverlay);
		overlayManager.add(fishingSpotMinimapOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		spotOverlay.setHidden(true);
		fishingSpotMinimapOverlay.setHidden(true);
		overlayManager.remove(overlay);
		overlayManager.remove(spotOverlay);
		overlayManager.remove(fishingSpotMinimapOverlay);
		fishingSpots.clear();
		minnowSpots.clear();
		currentSpot = null;
		trawlerStartTime = null;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		GameState gameState = gameStateChanged.getGameState();
		if (gameState == GameState.CONNECTION_LOST || gameState == GameState.LOGIN_SCREEN)
		{
			fishingSpots.clear();
			minnowSpots.clear();
		}
	}



	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getItemContainer() != com.simplicity.client.Client.getClient().getInventory()
			&& event.getItemContainer() != com.simplicity.client.Client.getClient().getEquipment())
		{
			return;
		}

		final boolean showOverlays = session.getLastFishCaught() != null
			|| canPlayerFish(com.simplicity.client.Client.getClient().getInventory())
			|| canPlayerFish(com.simplicity.client.Client.getClient().getEquipment());

		if (!showOverlays)
		{
			currentSpot = null;
		}

		spotOverlay.setHidden(!showOverlays);
		fishingSpotMinimapOverlay.setHidden(!showOverlays);
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		if (event.getMessage().contains("You catch a") || event.getMessage().contains("You catch some") ||
			event.getMessage().equals("Your cormorant returns with its catch."))
		{
			session.setLastFishCaught(Instant.now());
			spotOverlay.setHidden(false);
			fishingSpotMinimapOverlay.setHidden(false);
		}
	}

	@Subscribe
	public void onInteractingChanged(InteractingChanged event)
	{
		if (event.getSource() != client.getLocalPlayer())
		{
			return;
		}

		final Entity target = event.getTarget();

		if (!(target instanceof NPC))
		{
			return;
		}

		final NPC npc = (NPC) target;
		FishingSpot spot = FishingSpot.findSpot(npc.getId());

		if (spot == null)
		{
			return;
		}

		currentSpot = spot;
	}

	private boolean canPlayerFish(final ItemContainer itemContainer)
	{
		if (itemContainer == null)
		{
			return false;
		}

		for (Item item : itemContainer.getItems())
		{
			switch (item.getDefinition().id)
			{
				case ItemID.DRAGON_HARPOON:
				case ItemID.INFERNAL_HARPOON:
				case ItemID.INFERNAL_HARPOON_UNCHARGED:
				case ItemID.HARPOON:
				case ItemID.BARBTAIL_HARPOON:
				case ItemID.BIG_FISHING_NET:
				case ItemID.SMALL_FISHING_NET:
				case ItemID.SMALL_FISHING_NET_6209:
				case ItemID.FISHING_ROD:
				case ItemID.FLY_FISHING_ROD:
				case ItemID.PEARL_BARBARIAN_ROD:
				case ItemID.PEARL_FISHING_ROD:
				case ItemID.PEARL_FLY_FISHING_ROD:
				case ItemID.BARBARIAN_ROD:
				case ItemID.OILY_FISHING_ROD:
				case ItemID.LOBSTER_POT:
				case ItemID.KARAMBWAN_VESSEL:
				case ItemID.KARAMBWAN_VESSEL_3159:
				case ItemID.CORMORANTS_GLOVE:
				case ItemID.CORMORANTS_GLOVE_22817:
				case ItemID.TRAILBLAZER_HARPOON:
				case ItemID.CRYSTAL_HARPOON:
				case ItemID.CRYSTAL_HARPOON_23864:
				case ItemID.CRYSTAL_HARPOON_INACTIVE:
					return true;
			}
		}

		return false;
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		// Reset fishing session
		if (session.getLastFishCaught() != null)
		{
			final Duration statTimeout = Duration.ofMinutes(config.statTimeout());
			final Duration sinceCaught = Duration.between(session.getLastFishCaught(), Instant.now());

			if (sinceCaught.compareTo(statTimeout) >= 0)
			{
				currentSpot = null;
				session.setLastFishCaught(null);
			}
		}

		inverseSortSpotDistanceFromPlayer();


		updateTrawlerTimer();
		updateTrawlerContribution();
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned event)
	{
		final com.simplicity.client.NPC npc = event.getNpc();
		if(npc.getName().toLowerCase().contains("spot")) {
			log.info("npc name: " + npc.getName() + " - " + npc.getId() + " - " + npc.getWorldLocation().toString());
		}
		if (FishingSpot.findSpot(npc.getId()) == null)
		{
			return;
		}

		fishingSpots.add(npc);
		inverseSortSpotDistanceFromPlayer();
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned npcDespawned)
	{
		final com.simplicity.client.NPC npc = npcDespawned.getNpc();

		fishingSpots.remove(npc);

		MinnowSpot minnowSpot = minnowSpots.remove(npc.index);
		if (minnowSpot != null)
		{
			log.debug("Minnow spot {} despawned", npc);
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		/*if (event.getGroupId() == WidgetID.FISHING_TRAWLER_GROUP_ID)
		{
			trawlerStartTime = Instant.now();
			log.debug("Trawler session started");
		}*/
	}

	/**
	 * Updates the trawler contribution value
	 */
	private void updateTrawlerContribution()
	{


		/*Widget trawlerContributionWidget = client.getWidget(WidgetInfo.FISHING_TRAWLER_CONTRIBUTION);
		if (trawlerContributionWidget == null)
		{
			return;
		}

		int trawlerContribution = client.getVar(Varbits.FISHING_TRAWLER_ACTIVITY);
		trawlerContributionWidget.setText("Contribution: " + trawlerContribution);*/
	}

	/**
	 * Changes the Fishing Trawler timer widget from minutes to minutes and seconds
	 */
	private void updateTrawlerTimer()
	{
		if (trawlerStartTime == null)
		{
			return;
		}


	}

	private void inverseSortSpotDistanceFromPlayer()
	{
		if (fishingSpots.isEmpty())
		{
			return;
		}

		final LocalPoint cameraPoint = new LocalPoint(client.getCameraX(), client.getCameraY());
		fishingSpots.sort(
			Comparator.comparingInt(
				// Negate to have the furthest first
				(com.simplicity.client.NPC npc) -> -npc.getLocalLocation().distanceTo(cameraPoint))
				// Order by position
				.thenComparing(com.simplicity.client.NPC::getLocalLocation, Comparator.comparingInt(LocalPoint::getX)
					.thenComparingInt(LocalPoint::getY))
				// And then by id
				.thenComparingInt(com.simplicity.client.NPC::getId)
		);
	}
}
