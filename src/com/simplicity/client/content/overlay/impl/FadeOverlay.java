package com.simplicity.client.content.overlay.impl;

import java.awt.Color;
import java.awt.Graphics2D;

import com.simplicity.client.Client;
import com.simplicity.client.DrawingArea;
import com.simplicity.client.content.overlay.ScreenOverlay;
import com.simplicity.client.content.overlay.ScreenOverlayGroup;

public class FadeOverlay extends ScreenOverlay {
	
	/**
	 * Constructs a new {@link FadeOverlay}.
	 */
	public FadeOverlay() {
		super(0, 0, Client.instance.getGameAreaWidth(), Client.instance.getGameAreaHeight());
	}

	@Override
	public boolean draw(Client client, int x, int y) throws Exception {
		Graphics2D g2d = DrawingArea.createGraphics(true);
		g2d.setColor(getColor());
		g2d.fillRect(getX(), getY(), getWidth(), getHeight());
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT;
	}

	@Override
	public boolean enabled() {
		return Client.instance.fadeVisible;
	}
	
	/**
	 * Gets the color.
	 * 
	 * @return The color.
	 */
	private Color getColor() {
		int hex = Client.instance.fadeColor;
		int r = (hex & 0xFF0000) >> 16;
		int g = (hex & 0xFF00) >> 8;
		int b = (hex & 0xFF);

		return new Color(r, g, b, Client.instance.fadeAlpha);
	}

}
