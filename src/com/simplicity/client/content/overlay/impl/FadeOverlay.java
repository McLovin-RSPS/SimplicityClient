package com.simplicity.client.content.overlay.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;

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
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
        Paint oldPaint = g2d.getPaint();
		
		g2d.setColor(getColor());
		
		int hex = Client.instance.fadeColor;
		
		boolean fadeIn = Client.instance.fadeAlpha < Client.instance.fadeAlphaEnd;
		
		if (hex == 0x4c0b01 && fadeIn) {
			int alpha = Client.instance.fadeAlpha;
			
	        float cx = getWidth();
	        float cy = getHeight();
	        float radius = 100 + (Client.instance.fadeAlpha);
	        
			Color col1 = new Color(70, 18, 5, alpha);
			
			int bgAlpha = alpha > 200 ? alpha: alpha > 150 ? alpha - 150 : 0;
			
			Color col2 = new Color((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF), bgAlpha);
			
	        Paint paint = new RadialGradientPaint(cx / 2, cy / 2, radius, new float[] { 0.0f, 1.0f }, new Color[] { col1, col2 });
	        g2d.setPaint(paint);
	    	g2d.fillRect(0, 0, getWidth(), getHeight());
	    	
	    	hex = 0x200000;
	    	col1 = new Color((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF), 200);
	    	paint = new RadialGradientPaint(cx / 2, cy / 2, radius * 2, new float[] { 0.2f, 1.0f }, new Color[] { col1, col2 });
	        g2d.setPaint(paint);
	    	g2d.fillRect(0, 0, getWidth(), getHeight());
	    	
	    	if (radius < 270) {
	    		hex = 0x100000;
		    	col1 = new Color((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF), 100);
	    		paint = new RadialGradientPaint(cx / 2, cy / 2, radius / 4, new float[] { 0.2f, 1.0f }, new Color[] { col1, col2 });
	        	g2d.setPaint(paint);
	    		g2d.fillRect(0, 0, getWidth(), getHeight());
	    	}
		} else {
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
		
        g2d.setPaint(oldPaint);
		return true;
	}

	@Override
	public ScreenOverlayGroup getOverlayGroup() {
		return ScreenOverlayGroup.TOP_LEFT_VERTICAL;
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
