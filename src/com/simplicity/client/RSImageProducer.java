package com.simplicity.client;
// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.util.Hashtable;

public final class RSImageProducer
{
	public RSImageProducer(int width, int height, Component component)
	{
		this.width = width;
		this.height = height;
		this.component = component;
		int count = width * height;
		depthbuffer = new float[width * height];
		anIntArray315 = new int[count];
		DirectColorModel model;
		final boolean gpu = Client.drawCallbacks != null;
		if (gpu) {
			model = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, true, 3);
		} else {
			model = new DirectColorModel(32, 0xff0000, 0xff00, 0xff);
		}
		image = new BufferedImage(model, Raster.createWritableRaster(model.createCompatibleSampleModel(width, height), new DataBufferInt(anIntArray315, count), null), false, new Hashtable<Object, Object>());
		initDrawingArea();
	}

	public void drawGraphics(int y, Graphics gfx, int x)
	{
		draw(gfx, x, y);
	}

	public void draw(Graphics gfx, int x, int y)
	{
		if (Client.drawCallbacks != null) {
			return;
		}

		gfx.drawImage(image, x, y, component);
	}

	public void draw(Graphics gfx, int x, int y, int clipX, int clipY, int clipWidth, int clipHeight)
	{
		if (Client.drawCallbacks != null) {
			return;
		}

		Shape tmp = gfx.getClip();
		try
		{
			clip.x = clipX;
			clip.y = clipY;
			clip.width = clipWidth;
			clip.height = clipHeight;
			gfx.setClip(clip);
			gfx.drawImage(image, x, y, component);
		}
		finally
		{
			gfx.setClip(tmp);
		}
	}

	public void initDrawingArea()
	{
		DrawingArea.initDrawingArea(height, width, anIntArray315, depthbuffer);
	}

	private float[] depthbuffer;
	public final int[] anIntArray315;
	public final int width;
	public final int height;
	public final BufferedImage image;
	public final Component component;
	private final Rectangle clip = new Rectangle();
}
