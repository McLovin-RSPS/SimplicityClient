/*
 * Copyright (c) 2018, Ethan <https://github.com/shmeeps>
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
package net.runelite.client.plugins.prayer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;

import com.simplicity.Configuration;
import lombok.AccessLevel;
import lombok.Setter;
import net.runelite.api.*;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import net.runelite.client.util.ColorUtil;

class PrayerDoseOverlay extends Overlay
{
	private static final float PULSE_TIME = 2f * Constants.GAME_TICK_LENGTH;

	private static final Color START_COLOR = new Color(0, 255, 255);
	private static final Color END_COLOR = new Color(0, 92, 92);

	private final Client client;
	private final PrayerPlugin plugin;
	private final PrayerConfig config;
	private final TooltipManager tooltipManager;
	private Instant startOfLastTick = Instant.now();
	private boolean trackTick = true;

	@Setter(AccessLevel.PACKAGE)
	private boolean hasPrayerRestore;
	@Setter(AccessLevel.PACKAGE)
	private int bonusPrayer;
	@Setter(AccessLevel.PACKAGE)
	private boolean hasHolyWrench;

	@Inject
	private PrayerDoseOverlay(final Client client, final TooltipManager tooltipManager, final PrayerPlugin plugin, final PrayerConfig config)
	{
		this.client = client;
		this.tooltipManager = tooltipManager;
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setGraphicsBuffer(GraphicsBufferType.MINIMAP);
	}

	void onTick()
	{
		// Only track the time on every other tick
		if (trackTick)
		{
			startOfLastTick = Instant.now(); //Reset the tick timer
			trackTick = false;
		}
		else
		{
			trackTick = true;
		}
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		int orbX = com.simplicity.client.Client.getClient().getOrbX(1) + 2;
		int orbY = com.simplicity.client.Client.getClient().getOrbY(1) + 3;
		if (!Configuration.enableOldschoolFrame && !client.isResized()) {
			orbX -= 24;
			orbY -= 1;
		}
		Rectangle2D bounds = new Rectangle(orbX, orbY, 27, 27);
		if (bounds.getX() < 0)
		{
			return null;
		}

		final Point mousePosition = client.getMouseCanvasPosition();

		/*if (config.showPrayerStatistics() && bounds.contains(mousePosition.getX(), mousePosition.getY()))
		{
			final StringBuilder sb = new StringBuilder();

			if (config.replaceOrbText())
			{
				sb.append("Prayer points remaining: ").append(client.getBoostedSkillLevel(Skill.PRAYER));
			}
			else
			{
				sb.append("Time Remaining: ").append(plugin.getEstimatedTimeRemaining(false));
			}

			sb.append("</br>").append("Prayer Bonus: ").append(plugin.getPrayerBonus());
			tooltipManager.add(new Tooltip(sb.toString()));
		}*/

		if (!config.showPrayerDoseIndicator() || !hasPrayerRestore)
		{
			return null;
		}

		final int currentPrayer = client.getBoostedSkillLevel(Skill.PRAYER);
		final int maxPrayer = client.getRealSkillLevel(Skill.PRAYER);

		final int prayerPointsMissing = maxPrayer - currentPrayer;
		if (prayerPointsMissing <= 0)
		{
			return null;
		}

		final double dosePercentage = hasHolyWrench ? .27 : .25;
		final int basePointsRestored = (int) Math.floor(maxPrayer * dosePercentage);

		final int pointsRestored = basePointsRestored + 7 + bonusPrayer;

		if (prayerPointsMissing < pointsRestored)
		{
			return null;
		}

		// Purposefully using height twice here as the bounds of the prayer orb includes the number sticking out the side
		final int orbInnerSize = (int) bounds.getHeight();

		final int orbInnerX = (int) (bounds.getX() + 24); // x pos of the inside of the prayer orb
		final int orbInnerY = (int) (bounds.getY()); // y pos of the inside of the prayer orb

		final long timeSinceLastTick = Duration.between(startOfLastTick, Instant.now()).toMillis();

		final float tickProgress = Math.min(timeSinceLastTick / PULSE_TIME, 1); // Cap between 0 and 1
		final double t = tickProgress * Math.PI; // Convert to 0 - pi

		graphics.setColor(ColorUtil.colorLerp(START_COLOR, END_COLOR, Math.sin(t)));
		graphics.setStroke(new BasicStroke(2));
		graphics.drawOval(orbInnerX, orbInnerY, orbInnerSize, orbInnerSize);

		return new Dimension((int) bounds.getWidth(), (int) bounds.getHeight());
	}

}
