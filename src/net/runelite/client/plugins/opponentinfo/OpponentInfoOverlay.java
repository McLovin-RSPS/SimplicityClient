/*
 * Copyright (c) 2016-2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2018, Jordan Atwood <jordan.atwood423@gmail.com>
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
package net.runelite.client.plugins.opponentinfo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import javax.inject.Inject;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.Entity;

import com.simplicity.client.widget.settings.Setting;
import com.simplicity.client.widget.settings.Settings;
import net.runelite.api.GraphicsBufferType;
import net.runelite.client.ui.overlay.*;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.ProgressBarComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import net.runelite.client.util.Text;

class OpponentInfoOverlay extends OverlayPanel
{
	private static final Color HP_GREEN = new Color(0, 146, 54, 230);
	private static final Color HP_RED = new Color(102, 15, 16, 230);

	private final OpponentInfoPlugin opponentInfoPlugin;
	private final OpponentInfoConfig opponentInfoConfig;
	private String opponentName;
	private int currentHealth;
	private int maxHealth;

	@Inject
	private OpponentInfoOverlay(OpponentInfoPlugin opponentInfoPlugin, OpponentInfoConfig opponentInfoConfig)
	{
		this.opponentInfoPlugin = opponentInfoPlugin;
		this.opponentInfoConfig = opponentInfoConfig;

		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(OverlayPriority.HIGH);
		setLayer(OverlayLayer.ABOVE_SCENE);
		setGraphicsBuffer(GraphicsBufferType.ALL);

		panelComponent.setBorder(new Rectangle(2, 2, 2, 2));
		panelComponent.setGap(new Point(0, 2));
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		final Entity opponent = opponentInfoPlugin.getLastOpponent();

		if (opponent == null || opponent.getName() == null || opponent.getName().isEmpty() || opponent.maxHealth <= 0)
		{
			opponentName = null;
			return null;
		}

		if (opponent.getName().equals("Zalcano") || Client.getClient().getRegionId() == 15515) { // Nightmare
			return null;
		}

		boolean x10 = Settings.getBoolean(Setting.CONSTITUTION);
		
		opponentName = Text.removeTags(opponent.getName()).replaceAll("@cya@", "");
		currentHealth = !x10 ? opponent.currentHealth / 10 : opponent.currentHealth;
		maxHealth = !x10 ? opponent.maxHealth / 10 : opponent.maxHealth;

		final FontMetrics fontMetrics = graphics.getFontMetrics();

		// Opponent name
		int panelWidth = Math.max(ComponentConstants.STANDARD_WIDTH, fontMetrics.stringWidth(opponentName) + ComponentConstants.STANDARD_BORDER + ComponentConstants.STANDARD_BORDER);
		panelComponent.setPreferredSize(new Dimension(panelWidth, 0));
		panelComponent.getChildren().add(TitleComponent.builder()
			.text(opponentName)
			.build());

		// Health bar
		final ProgressBarComponent progressBarComponent = new ProgressBarComponent();
		progressBarComponent.setBackgroundColor(HP_RED);
		progressBarComponent.setForegroundColor(HP_GREEN);

		final HitpointsDisplayStyle displayStyle = opponentInfoConfig.hitpointsDisplayStyle();

		if ((displayStyle == HitpointsDisplayStyle.HITPOINTS || displayStyle == HitpointsDisplayStyle.BOTH))
		{

			// Show both the hitpoint and percentage values if enabled in the config
			final ProgressBarComponent.LabelDisplayMode progressBarDisplayMode = displayStyle == HitpointsDisplayStyle.BOTH ?
				ProgressBarComponent.LabelDisplayMode.BOTH : ProgressBarComponent.LabelDisplayMode.FULL;

			progressBarComponent.setLabelDisplayMode(progressBarDisplayMode);
			progressBarComponent.setMaximum(maxHealth);
			progressBarComponent.setValue(currentHealth);
		}
		else
		{
			float floatRatio = (float) currentHealth / (float) maxHealth;
			progressBarComponent.setValue(floatRatio * 100d);
		}

		panelComponent.getChildren().add(progressBarComponent);

		return super.render(graphics);
	}
}
