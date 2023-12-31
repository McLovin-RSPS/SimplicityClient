/*
 * Copyright (c) 2019, Jos <Malevolentdev@gmail.com>
 * Copyright (c) 2019, Rheon <https://github.com/Rheon-D>
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
package net.runelite.client.plugins.statusbars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;

import net.runelite.api.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SkillIconManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.statusbars.Config.BarMode;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.util.ImageUtil;

class StatusBarsOverlay extends Overlay
{
	private static final Color PRAYER_COLOR = new Color(50, 200, 200, 175);
	private static final Color ACTIVE_PRAYER_COLOR = new Color(57, 255, 186, 225);
	private static final Color HEALTH_COLOR = new Color(225, 35, 0, 125);
	private static final Color POISONED_COLOR = new Color(0, 145, 0, 150);
	private static final Color VENOMED_COLOR = new Color(0, 65, 0, 150);
	private static final Color HEAL_COLOR = new Color(255, 112, 6, 150);
	private static final Color PRAYER_HEAL_COLOR = new Color(57, 255, 186, 75);
	private static final Color ENERGY_HEAL_COLOR = new Color (199,  118, 0, 218);
	private static final Color RUN_STAMINA_COLOR = new Color(160, 124, 72, 255);
	private static final Color SPECIAL_ATTACK_COLOR = new Color(3, 153, 0, 195);
	private static final Color ENERGY_COLOR = new Color(199, 174, 0, 220);
	private static final Color DISEASE_COLOR = new Color(255, 193, 75, 181);
	private static final int HEIGHT = 252;
	private static final int RESIZED_BOTTOM_HEIGHT = 272;
	private static final int IMAGE_SIZE = 17;
	private static final int ICON_DIMENSIONS = 26;
	private static final int RESIZED_BOTTOM_OFFSET_Y = 12;
	private static final int RESIZED_BOTTOM_OFFSET_X = 10;
	private static final int MAX_SPECIAL_ATTACK_VALUE = 100;
	private static final int MAX_RUN_ENERGY_VALUE = 100;

	private final Client client;
	private final StatusBarsConfig config;
	//private final ItemStatChangesService itemStatService;
	private final SpriteManager spriteManager;

	private final Image prayerIcon;
	private Image heartIcon;
	private Image heartDisease;
	private Image heartPoison;
	private Image heartVenom;
	private Image specialIcon;
	private Image energyIcon;
	private final Map<BarMode, BarRenderer> barRenderers = new EnumMap<>(BarMode.class);
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private ItemManager itemManager;

	@Inject
	private StatusBarsOverlay(Client client, StatusBarsConfig config, SkillIconManager skillIconManager, /*ItemStatChangesService itemstatservice,*/ SpriteManager spriteManager)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.TAB_AREA);
		this.client = client;
		this.config = config;
		//this.itemStatService = itemstatservice;
		this.spriteManager = spriteManager;
		setGraphicsBuffer(GraphicsBufferType.TAB_AREA);
		prayerIcon = ImageUtil.resizeCanvas(skillIconManager.getSkillImage(Skill.PRAYER, true), ICON_DIMENSIONS, ICON_DIMENSIONS);
		initRenderers();
	}

	private void initRenderers()
	{
		barRenderers.put(BarMode.DISABLED, null);
		barRenderers.put(BarMode.HITPOINTS, new BarRenderer(
			() -> client.getRealSkillLevel(Skill.HITPOINTS) / 100,
			() -> client.getBoostedSkillLevel(Skill.HITPOINTS) / 100,
			() -> getRestoreValue(Skill.HITPOINTS.getName()),
			() ->
			{
				final int poisonState = client.getVar(VarPlayer.IS_POISONED);

				if (poisonState == 2)
				{
					return VENOMED_COLOR;
				}

				if (poisonState == 1)
				{
					return POISONED_COLOR;
				}

				if (client.getVar(VarPlayer.DISEASE_VALUE) > 0)
				{
					return DISEASE_COLOR;
				}

				return HEALTH_COLOR;
			},
			() -> HEAL_COLOR,
			() ->
			{
				final int poisonState = client.getVar(VarPlayer.IS_POISONED);

				if (poisonState == 1)
				{
					return heartIcon;
				}

				if (poisonState == 2)
				{
					return heartIcon;
				}

				if (client.getVar(VarPlayer.DISEASE_VALUE) > 0)
				{
					return heartIcon;
				}

				return heartIcon;
			}
		));
		barRenderers.put(BarMode.PRAYER, new BarRenderer(
			() -> client.getRealSkillLevel(Skill.PRAYER) / 100,
			() -> client.getBoostedSkillLevel(Skill.PRAYER) / 100,
			() -> getRestoreValue(Skill.PRAYER.getName()),
			() ->
			{
				Color prayerColor = PRAYER_COLOR;

				for (Prayer pray : Prayer.values())
				{
					if (client.isPrayerActive(pray))
					{
						prayerColor = ACTIVE_PRAYER_COLOR;
						break;
					}
				}

				return prayerColor;
			},
			() -> PRAYER_HEAL_COLOR,
			() -> prayerIcon
		));
		barRenderers.put(BarMode.RUN_ENERGY, new BarRenderer(
			() -> MAX_RUN_ENERGY_VALUE,
			client::getEnergy,
			() -> getRestoreValue("Run Energy"),
			() ->
			{
				if (client.getVar(Varbits.RUN_SLOWED_DEPLETION_ACTIVE) != 0)
				{
					return RUN_STAMINA_COLOR;
				}
				else
				{
					return ENERGY_COLOR;
				}
			},
			() -> ENERGY_HEAL_COLOR,
			() -> energyIcon
		));
		barRenderers.put(BarMode.SPECIAL_ATTACK, new BarRenderer(
			() -> MAX_SPECIAL_ATTACK_VALUE,
			() -> com.simplicity.client.Client.instance.currentSpec,
			() -> 0,
			() -> SPECIAL_ATTACK_COLOR,
			() -> SPECIAL_ATTACK_COLOR,
			() -> specialIcon
		));
	}

	@Override
	public Dimension render(Graphics2D g)
	{
		
		int clientWidth = com.simplicity.client.Client.clientWidth;
		int clientHeight = com.simplicity.client.Client.clientHeight;
		boolean longTabs = clientWidth >= 1000;
		
		Viewport curViewport = Viewport.FIXED;
		final Point offsetLeft = curViewport.getOffsetLeft();
		final Point offsetRight = curViewport.getOffsetRight();
		boolean tabVis = com.simplicity.client.Client.instance.showTab;
		Point location = new Point(client.isResized() ? (clientWidth - (tabVis ? 236 : 35)) : 31, client.isResized() ? clientHeight - (longTabs ? 315 : 352) : 38);
		
		int curWidth = com.simplicity.client.Client.clientSize == 0 ? 190 : 8;
		final int height = client.isResized() ? RESIZED_BOTTOM_HEIGHT : HEIGHT;
		final int offsetLeftBarX = (location.getX() - offsetLeft.getX());
		final int offsetLeftBarY = (location.getY() - offsetLeft.getY());
		final int offsetRightBarX = (location.getX() - offsetRight.getX()) + curWidth;
		final int offsetRightBarY = (location.getY() - offsetRight.getY());

		buildIcons();

		BarRenderer left = barRenderers.get(config.leftBarMode());
		BarRenderer right = barRenderers.get(config.rightBarMode());

		if (left != null)
		{
			left.renderBar(config, g, offsetLeftBarX, offsetLeftBarY, height);
		}

		if (right != null)
		{
			right.renderBar(config, g, offsetRightBarX, offsetRightBarY, height);
		}

		return null;
	}

	private int getRestoreValue(String skill)
	{
		final MenuEntry[] menu = client.getMenuEntries();
		final int menuSize = menu.length;
		final MenuEntry entry = menuSize > 0 ? menu[menuSize - 1] : null;
		int restoreValue = 0;

		if (entry != null && entry.getParam1() == WidgetInfo.INVENTORY.getId())
		{
			/*final Effect change = itemStatService.getItemStatChanges(entry.getIdentifier());

			if (change != null)
			{
				for (final StatChange c : change.calculate(client).getStatChanges())
				{
					final int value = c.getTheoretical();

					if (value != 0 && c.getStat().getName().equals(skill))
					{
						restoreValue = value;
					}
				}
			}*/
		}

		return restoreValue;
	}

	private void buildIcons()
	{
		if (heartIcon != null && heartDisease != null && heartPoison != null && heartVenom != null && energyIcon != null && specialIcon != null)
		{
			return;
		}

		heartIcon = ImageUtil.resizeCanvas(Objects.requireNonNull(spriteManager.getSprite(0, SpriteID.MINIMAP_ORB_HITPOINTS_ICON)), ICON_DIMENSIONS, ICON_DIMENSIONS);
		heartDisease = ImageUtil.resizeCanvas(Objects.requireNonNull(spriteManager.getSprite(0, SpriteID.MINIMAP_ORB_HITPOINTS_DISEASE)), ICON_DIMENSIONS, ICON_DIMENSIONS);
		heartPoison = ImageUtil.resizeCanvas(Objects.requireNonNull(spriteManager.getSprite(0, SpriteID.MINIMAP_ORB_HITPOINTS_POISON)), ICON_DIMENSIONS, ICON_DIMENSIONS);
		heartVenom = ImageUtil.resizeCanvas(Objects.requireNonNull(spriteManager.getSprite(0, SpriteID.MINIMAP_ORB_HITPOINTS_VENOM)), ICON_DIMENSIONS, ICON_DIMENSIONS);
		energyIcon = ImageUtil.resizeCanvas(Objects.requireNonNull(spriteManager.getSprite(0, SpriteID.MINIMAP_ORB_RUN_ICON)), ICON_DIMENSIONS, ICON_DIMENSIONS);
		specialIcon = ImageUtil.resizeCanvas(Objects.requireNonNull(spriteManager.getSprite(0, SpriteID.MINIMAP_ORB_SPECIAL_ICON)), ICON_DIMENSIONS, ICON_DIMENSIONS);
	}
}
