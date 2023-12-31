/*
 * Copyright (c) 2017, Tomas Slusny <slusnucky@gmail.com>
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
package net.runelite.client.ui.overlay.tooltip;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageProducer;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.simplicity.client.RSImageProducer;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.config.TooltipPositionType;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LayoutableRenderableEntity;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TooltipComponent;

@Singleton
@Slf4j
public class TooltipOverlay extends Overlay
{
	private static final int UNDER_OFFSET = 24;
	private static final int PADDING = 2;
	private final TooltipManager tooltipManager;
	private final Client client;
	private final RuneLiteConfig runeLiteConfig;

	@Inject
	private TooltipOverlay(Client client, TooltipManager tooltipManager, final RuneLiteConfig runeLiteConfig)
	{
		this.client = client;
		this.tooltipManager = tooltipManager;
		this.runeLiteConfig = runeLiteConfig;
		setPosition(OverlayPosition.TOOLTIP);
		setPriority(OverlayPriority.HIGHEST);
		setLayer(OverlayLayer.ALWAYS_ON_TOP);
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		final List<Tooltip> tooltips = tooltipManager.getTooltips();

		if (tooltips.isEmpty() || client.getMouseCanvasPosition().getX() < 0 || client.getMouseCanvasPosition().getY() < 0)
		{
			return null;
		}

		try
		{
			return renderTooltips(graphics, tooltips);
		}
		finally
		{
			// Tooltips must always be cleared each frame
			tooltipManager.clear();
		}
	}

	private Dimension renderTooltips(Graphics2D graphics, List<Tooltip> tooltips)
	{
		int canvasWidth = client.getCanvasWidth();
		int canvasHeight = client.getCanvasHeight();

		final net.runelite.api.Point mouseCanvasPosition = client.getMouseCanvasPosition();
		final Rectangle prevBounds = getBounds();

		int tooltipX = Math.min(canvasWidth - prevBounds.width, mouseCanvasPosition.getX());
		int tooltipY = runeLiteConfig.tooltipPosition() == TooltipPositionType.ABOVE_CURSOR
				? Math.max(0, mouseCanvasPosition.getY() - prevBounds.height)
				: Math.min(canvasHeight - prevBounds.height, mouseCanvasPosition.getY() + UNDER_OFFSET);

		final Rectangle newBounds = new Rectangle(tooltipX, tooltipY, 0, 0);

		for (Tooltip tooltip : tooltips)
		{
			final LayoutableRenderableEntity entity;

			if (tooltip.getComponent() != null)
			{
				entity = tooltip.getComponent();
				if (entity instanceof PanelComponent)
				{
					((PanelComponent) entity).setBackgroundColor(runeLiteConfig.overlayBackgroundColor());
				}
			}
			else
			{
				final TooltipComponent tooltipComponent = new TooltipComponent();
				tooltipComponent.setModIcons(client.getModIcons());
				tooltipComponent.setText(tooltip.getText());
				tooltipComponent.setBackgroundColor(runeLiteConfig.overlayBackgroundColor());
				entity = tooltipComponent;
			}

			entity.setPreferredLocation(new Point(tooltipX, tooltipY + newBounds.height));
			final Dimension dimension = entity.render(graphics);

			// Create incremental tooltip newBounds
			newBounds.height += dimension.height + PADDING;
			newBounds.width = Math.max(newBounds.width, dimension.width);
		}

		return newBounds.getSize();
	}
}
