/*
 * Copyright (c) 2017, Aria <aria@ar1as.space>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package net.runelite.client.plugins.grounditems;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GraphicsBufferType;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import static net.runelite.client.plugins.grounditems.config.ItemHighlightMode.MENU;

import net.runelite.client.RuneLite;
import net.runelite.client.plugins.grounditems.config.PriceDisplayMode;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.components.BackgroundComponent;
import net.runelite.client.ui.overlay.components.ProgressPieComponent;
import net.runelite.client.ui.overlay.components.TextComponent;
import net.runelite.client.util.QuantityFormatter;
import org.apache.commons.lang3.ArrayUtils;

import com.simplicity.client.Player;

public class GroundItemsOverlay extends Overlay
{
	private static final int MAX_DISTANCE = 2500;
	// We must offset the text on the z-axis such that
	// it doesn't obscure the ground items below it.
	private static final int OFFSET_Z = 20;
	// The game won't send anything higher than this value to the plugin -
	// so we replace any item quantity higher with "Lots" instead.
	private static final int MAX_QUANTITY = 65535;
	// The 15 pixel gap between each drawn ground item.
	private static final int STRING_GAP = 15;
	// Size of the hidden/highlight boxes
	private static final int RECTANGLE_SIZE = 8;
	private static final Color PUBLIC_TIMER_COLOR = Color.YELLOW;
	private static final Color PRIVATE_TIMER_COLOR = Color.GREEN;
	private static final int TIMER_OVERLAY_DIAMETER = 10;
	private static final Duration DESPAWN_TIME_INSTANCE = Duration.ofMinutes(30);
	private static final Duration DESPAWN_TIME_LOOT = Duration.ofMinutes(2);
	private static final Duration DESPAWN_TIME_DROP = Duration.ofMinutes(3);
	private static final int KRAKEN_REGION = 9116;

	private final Client client = RuneLite.getClient();
	private final GroundItemsPlugin plugin;
	private final GroundItemsConfig config;
	private final StringBuilder itemStringBuilder = new StringBuilder();
	private final BackgroundComponent backgroundComponent = new BackgroundComponent();
	private final TextComponent textComponent = new TextComponent();
	private final ProgressPieComponent progressPieComponent = new ProgressPieComponent();
	private final Map<WorldPoint, Integer> offsetMap = new HashMap<>();

	@Inject
	private GroundItemsOverlay(GroundItemsPlugin plugin, GroundItemsConfig config)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		setGraphicsBuffer(GraphicsBufferType.MAIN_GAME);
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		final boolean dontShowOverlay = (config.itemHighlightMode() == MENU || plugin.isHideAll()) && !plugin.isHotKeyPressed();

		if (dontShowOverlay && !config.highlightTiles())
		{
			return null;
		}
		
		final FontMetrics fm = graphics.getFontMetrics();
		final Player player = client.getLocalPlayer();

		if (player == null)
		{
			return null;
		}
		
		offsetMap.clear();
		final LocalPoint localLocation = player.getLocalLocation();
		final Point mousePos = client.getMouseCanvasPosition();
		Collection<GroundItem> groundItemList = plugin.getCollectedGroundItems().values();
		GroundItem topGroundItem = null;
		
		if (!plugin.isHotKeyPressed())
		{
			// Make copy of ground items because we are going to modify them here, and the array list supports our
			// desired behaviour here
			groundItemList = new ArrayList<>(groundItemList);
			final java.awt.Point awtMousePos = new java.awt.Point(mousePos.getX(), mousePos.getY());
			GroundItem groundItem = null;
			
			for (GroundItem item : groundItemList)
			{
				item.setOffset(offsetMap.compute(item.getLocation(), (k, v) -> v != null ? v + 1 : 0));

				if (groundItem != null)
				{
					continue;
				}
				
				if (plugin.getTextBoxBounds() != null
					&& item.equals(plugin.getTextBoxBounds().getValue())
					&& plugin.getTextBoxBounds().getKey().contains(awtMousePos))
				{
					groundItem = item;
					continue;
				}

				if (plugin.getHiddenBoxBounds() != null
					&& item.equals(plugin.getHiddenBoxBounds().getValue())
					&& plugin.getHiddenBoxBounds().getKey().contains(awtMousePos))
				{
					groundItem = item;
					continue;
				}

				if (plugin.getHighlightBoxBounds() != null
					&& item.equals(plugin.getHighlightBoxBounds().getValue())
					&& plugin.getHighlightBoxBounds().getKey().contains(awtMousePos))
				{
					groundItem = item;
				}
				
				groundItem = item;
			}

			if (groundItem != null)
			{
				groundItemList.remove(groundItem);
				groundItemList.add(groundItem);
				topGroundItem = groundItem;
			}
		}

		plugin.setTextBoxBounds(null);
		plugin.setHiddenBoxBounds(null);
		plugin.setHighlightBoxBounds(null);

		final boolean onlyShowLoot = config.onlyShowLoot();
		final boolean groundItemTimers = config.groundItemTimers();
		final boolean outline = config.textOutline();
		
		for (GroundItem item : groundItemList)
		{
			final LocalPoint groundPoint = LocalPoint.fromWorld(client, item.getLocation());
			
			if (groundPoint == null || localLocation.distanceTo(groundPoint) > MAX_DISTANCE
				|| (onlyShowLoot && !item.isMine()))
			{
				continue;
			}
			
			final Color highlighted = plugin.getHighlighted(new NamedQuantity(item), item.getGePrice(), item.getHaPrice());
			final Color hidden = plugin.getHidden(new NamedQuantity(item), item.getGePrice(), item.getHaPrice(), item.isTradeable());

			if (highlighted == null && !plugin.isHotKeyPressed())
			{
				// Do not display hidden items
				if (hidden != null)
				{
					continue;
				}

				// Do not display non-highlighted items
				if (config.showHighlightedOnly())
				{
					continue;
				}
			}

			final Color color = plugin.getItemColor(highlighted, hidden);

			if (config.highlightTiles())
			{
				final Polygon poly = Perspective.getCanvasTilePoly(client, groundPoint);

				if (poly != null)
				{
					OverlayUtil.renderPolygon(graphics, poly, color);
				}
			}

			if (dontShowOverlay)
			{
				continue;
			}

			itemStringBuilder.append(item.getName());

			if (item.getQuantity() > 1)
			{
				if (item.getQuantity() >= MAX_QUANTITY)
				{
					itemStringBuilder.append(" (Lots!)");
				}
				else
				{
					itemStringBuilder.append(" (")
						.append(QuantityFormatter.quantityToStackSize(item.getQuantity()))
						.append(")");
				}
			}

			if (config.priceDisplayMode() == PriceDisplayMode.BOTH)
			{
				if (item.getGePrice() > 0)
				{
					itemStringBuilder.append(" (GE: ")
						.append(QuantityFormatter.quantityToStackSize(item.getGePrice()))
						.append(" gp)");
				}

				if (item.getHaPrice() > 0)
				{
					itemStringBuilder.append(" (HA: ")
						.append(QuantityFormatter.quantityToStackSize(item.getHaPrice()))
						.append(" gp)");
				}
			}
			else if (config.priceDisplayMode() != PriceDisplayMode.OFF)
			{
				final int price = config.priceDisplayMode() == PriceDisplayMode.GE
					? item.getGePrice()
					: item.getHaPrice();

				if (price > 0)
				{
					itemStringBuilder
						.append(" (")
						.append(QuantityFormatter.quantityToStackSize(price))
						.append(" gp)");
				}
			}

			final String itemString = itemStringBuilder.toString();
			itemStringBuilder.setLength(0);
			
			final Point textPoint = Perspective.getCanvasTextLocation(client,
				graphics,
				groundPoint,
				itemString,
				item.getHeight() + OFFSET_Z);

			if (textPoint == null)
			{
				continue;
			}

			final int offset = /*plugin.isHotKeyPressed()
				?*/ item.getOffset()
				/*: offsetMap.compute(item.getLocation(), (k, v) -> v != null ? v + 1 : 0)*/;

			final int textX = textPoint.getX();
			final int textY = textPoint.getY() - (STRING_GAP * offset);

			if (plugin.isHotKeyPressed())
			{
				final int stringWidth = fm.stringWidth(itemString);
				final int stringHeight = fm.getHeight();

				// Item bounds
				int x = textX - 2;
				int y = textY - stringHeight - 2;
				int width = stringWidth + 4;
				int height = stringHeight + 4;
				final Rectangle itemBounds = new Rectangle(x, y, width, height);

				// Hidden box
				x += width + 2;
				y = textY - (RECTANGLE_SIZE + stringHeight) / 2;
				width = height = RECTANGLE_SIZE;
				final Rectangle itemHiddenBox = new Rectangle(x, y, width, height);

				// Highlight box
				x += width + 2;
				final Rectangle itemHighlightBox = new Rectangle(x, y, width, height);

				boolean mouseInBox = itemBounds.contains(mousePos.getX(), mousePos.getY());
				boolean mouseInHiddenBox = itemHiddenBox.contains(mousePos.getX(), mousePos.getY());
				boolean mouseInHighlightBox = itemHighlightBox.contains(mousePos.getX(), mousePos.getY());

				if (mouseInBox)
				{
					plugin.setTextBoxBounds(new SimpleEntry<>(itemBounds, item));
				}
				else if (mouseInHiddenBox)
				{
					plugin.setHiddenBoxBounds(new SimpleEntry<>(itemHiddenBox, item));

				}
				else if (mouseInHighlightBox)
				{
					plugin.setHighlightBoxBounds(new SimpleEntry<>(itemHighlightBox, item));
				}

				boolean topItem = topGroundItem == item;

				// Draw background if hovering
				if (topItem && (mouseInBox || mouseInHiddenBox || mouseInHighlightBox))
				{
					backgroundComponent.setRectangle(itemBounds);
					backgroundComponent.render(graphics);
				}

				// Draw hidden box
				drawRectangle(graphics, itemHiddenBox, topItem && mouseInHiddenBox ? Color.RED : color, hidden != null, true);

				// Draw highlight box
				drawRectangle(graphics, itemHighlightBox, topItem && mouseInHighlightBox ? Color.GREEN : color, highlighted != null, false);
			}

			if (groundItemTimers || plugin.isHotKeyPressed())
			{
				drawTimerOverlay(graphics, textX, textY, item);
			}

			textComponent.setText(itemString);
			textComponent.setColor(color);
			textComponent.setOutline(outline);
			textComponent.setPosition(new java.awt.Point(textX, textY));
			textComponent.render(graphics);
		}

		return null;
	}

	private void drawTimerOverlay(Graphics2D graphics, int textX, int textY, GroundItem groundItem)
	{
		// We can only accurately guess despawn times for our own pvm loot and dropped items
		if (groundItem.getLootType() != LootType.PVM && groundItem.getLootType() != LootType.DROPPED)
		{
			return;
		}

		// Loot appears to others after 1 minute, and despawns after 2 minutes
		// Dropped items appear to others after 1 minute, and despawns after 3 minutes
		// Items in instances never appear to anyone and despawn after 30 minutes

		Instant spawnTime = groundItem.getSpawnTime();
		if (spawnTime == null)
		{
			return;
		}

		Instant despawnTime;
		Instant now = Instant.now();
		Color fillColor;
		if (client.isInInstancedRegion())
		{
			// Items in the Kraken instance appear to never despawn?
			if (isInKraken())
			{
				return;
			}

			despawnTime = spawnTime.plus(DESPAWN_TIME_INSTANCE);
			fillColor = PRIVATE_TIMER_COLOR;
		}
		else
		{
			if (groundItem.getLootType() == LootType.DROPPED)
			{
				despawnTime = spawnTime.plus(DESPAWN_TIME_DROP);
			}
			else
			{
				despawnTime = spawnTime.plus(DESPAWN_TIME_LOOT);
			}

			// If it has not yet been a minute, the item is private
			if (spawnTime.plus(1, ChronoUnit.MINUTES).isAfter(now))
			{
				fillColor = PRIVATE_TIMER_COLOR;
			}
			else
			{
				fillColor = PUBLIC_TIMER_COLOR;
			}
		}

		if (now.isBefore(spawnTime) || now.isAfter(despawnTime))
		{
			// that's weird
			return;
		}

		float percent = (float) (now.toEpochMilli() - spawnTime.toEpochMilli()) / (despawnTime.toEpochMilli() - spawnTime.toEpochMilli());

		progressPieComponent.setDiameter(TIMER_OVERLAY_DIAMETER);
		// Shift over to not be on top of the text
		int x = textX - TIMER_OVERLAY_DIAMETER;
		int y = textY - TIMER_OVERLAY_DIAMETER / 2;
		progressPieComponent.setPosition(new Point(x, y));
		progressPieComponent.setFill(fillColor);
		progressPieComponent.setBorderColor(fillColor);
		progressPieComponent.setProgress(1 - percent); // inverse so pie drains over time
		progressPieComponent.render(graphics);
	}

	private void drawRectangle(Graphics2D graphics, Rectangle rect, Color color, boolean inList, boolean hiddenBox)
	{
		graphics.setColor(Color.BLACK);
		graphics.drawRect(rect.x + 1, rect.y + 1, rect.width, rect.height);

		graphics.setColor(color);
		graphics.draw(rect);

		if (inList)
		{
			graphics.fill(rect);
		}

		graphics.setColor(Color.WHITE);
		// Minus symbol
		graphics.drawLine
			(
				rect.x + 2,
				rect.y + (rect.height / 2),
				rect.x + rect.width - 2,
				rect.y + (rect.height / 2)
			);

		if (!hiddenBox)
		{
			// Plus symbol
			graphics.drawLine
				(
					rect.x + (rect.width / 2),
					rect.y + 2,
					rect.x + (rect.width / 2),
					rect.y + rect.height - 2
				);
		}

	}

	private boolean isInKraken()
	{
		return ArrayUtils.contains(client.getMapRegions(), KRAKEN_REGION);
	}

}
