/*
 * Copyright (c) 2018 Abex
 * Copyright (c) 2018, Sean Dewar <https://github.com/seandewar>
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
package net.runelite.client.plugins.statusorbs;

import com.simplicity.Configuration;
import com.simplicity.client.widget.settings.Setting;
import com.simplicity.client.widget.settings.Settings;
import net.runelite.api.Point;
import net.runelite.api.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.tooltip.Tooltip;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Arc2D;

public class StatusOrbsOverlay extends Overlay
{
	private static final Color HITPOINTS_COLOR = brighter(0x9B0703);
	private static final Color SPECIAL_COLOR = brighter(0x1E95B0);
	private static final Color RUN_COLOR = new Color(255, 215, 0);
	private static final Color OVERLAY_COLOR = new Color(255, 255, 255, 60);
	private static final double DIAMETER = 26D;
	private static final int OFFSET = 27;

	private final Client client;
	private final StatusOrbsPlugin plugin;
	private final TooltipManager tooltipManager;

	private long last = System.nanoTime();
	private double percentHp;
	private double lastHp;
	private double percentSpec;
	private double lastSpec;

	private static Color brighter(int color)
	{
		float[] hsv = new float[3];
		Color.RGBtoHSB(color >>> 16, (color >> 8) & 0xFF, color & 0xFF, hsv);
		return Color.getHSBColor(hsv[0], 1.f, 1.f);
	}

	@Inject
	public StatusOrbsOverlay(Client client, StatusOrbsPlugin plugin, TooltipManager tooltipManager)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setGraphicsBuffer(GraphicsBufferType.MINIMAP);
		this.client = client;
		this.plugin = plugin;
		this.tooltipManager = tooltipManager;
	}

	@Override
	public Dimension render(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		long current = System.nanoTime();
		double ms = (current - last) / (double) 1000000;

		if (plugin.isShowHitpoints())
		{
			if (lastHp == plugin.getHitpointsPercentage() && plugin.getHitpointsPercentage() != 0)
			{
				percentHp += ms * plugin.getHpPerMs();
			}
			else
			{
				percentHp = plugin.getHitpointsPercentage();
				lastHp = plugin.getHitpointsPercentage();
			}
			renderRegen(g, WidgetInfo.MINIMAP_HEALTH_ORB, percentHp, HITPOINTS_COLOR);
		}

		if (plugin.isShowSpecial())
		{
			if (lastSpec == plugin.getSpecialPercentage() && plugin.getSpecialPercentage() != 0)
			{
				percentSpec += ms * plugin.getSpecPerMs();
			}
			else
			{
				percentSpec = plugin.getSpecialPercentage();
				lastSpec = plugin.getSpecialPercentage();
			}

			renderRegen(g, WidgetInfo.MINIMAP_SPEC_ORB, percentSpec, SPECIAL_COLOR);
		}

		last = current;
		return null;
	}

	private void renderRegen(Graphics2D g, WidgetInfo widgetInfo, double percent, Color color)
	{
		Rectangle bounds = null;

		if (widgetInfo == WidgetInfo.MINIMAP_SPEC_ORB && Settings.getBoolean(Setting.SPECIAL_ORB)) {
			int orbX = com.simplicity.client.Client.getClient().getOrbX(4);
			int orbY = com.simplicity.client.Client.getClient().getOrbY(4);
			if (!Configuration.enableOldschoolFrame && !client.isResized()) {
				orbX -= 24;
			}
			bounds = new Rectangle(orbX, orbY, 32, 32);
		} else if (widgetInfo == WidgetInfo.MINIMAP_HEALTH_ORB) {

		}

		if (bounds == null) {
			return;
		}

		Arc2D.Double arc = new Arc2D.Double(bounds.x + OFFSET, bounds.y + (bounds.height / 2 - DIAMETER / 2), DIAMETER, DIAMETER, 90.d, -360.d * percent, Arc2D.OPEN);
		final Stroke STROKE = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		g.setStroke(STROKE);
		g.setColor(color);
		g.draw(arc);
	}
}
