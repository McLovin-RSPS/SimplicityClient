package com.simplicity.client;

import net.runelite.client.plugins.hdnew.HdPlugin;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.util.Hashtable;

public class DrawingArea extends QueueNode {
	
	public static void drawHorizontalLineAlpha(int x, int y, int colour, int length, int alpha) {
		if (y < topY || y >= bottomY)
			return;
		if (x < topX) {
			length -= topX - x;
			x = topX;
		}
		if (x + length > bottomX)
			length = bottomX - x;
		int j1 = 256 - alpha;
		int k1 = (colour >> 16 & 0xff) * alpha;
		int l1 = (colour >> 8 & 0xff) * alpha;
		int i2 = (colour & 0xff) * alpha;
		int i3 = x + y * width;
		for (int j3 = 0; j3 < length; j3++) {
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			drawAlpha(pixels, i3++, k3, k3, alpha);
		}

	}

	public static void method335(int i, int j, int k, int l, int i1, int k1) {
		if (k1 < topX) {
			k -= topX - k1;
			k1 = topX;
		}
		if (j < topY) {
			l -= topY - j;
			j = topY;
		}
		if (k1 + k > bottomX)
			k = bottomX - k1;
		if (j + l > bottomY)
			l = bottomY - j;
		int l1 = 256 - i1;
		int i2 = (i >> 16 & 0xff) * i1;
		int j2 = (i >> 8 & 0xff) * i1;
		int k2 = (i & 0xff) * i1;
		int k3 = width - k;
		int l3 = k1 + j * width;
		for (int i4 = 0; i4 < l; i4++) {
			for (int j4 = -k; j4 < 0; j4++) {
				int l2 = (pixels[l3] >> 16 & 0xff) * l1;
				int i3 = (pixels[l3] >> 8 & 0xff) * l1;
				int j3 = (pixels[l3] & 0xff) * l1;
				int k4 = ((i2 + l2 >> 8) << 16) + ((j2 + i3 >> 8) << 8) + (k2 + j3 >> 8);
				drawAlpha(pixels, l3++, k4, k4, i1);
			}

			l3 += k3;
		}
	}

	static void drawVerticalLineAlpha(int x, int y, int colour, int length, int alpha) {
		if (x < topX || x >= bottomX)
			return;
		if (y < topY) {
			length -= topY - y;
			y = topY;
		}
		if (y + length > bottomY)
			length = bottomY - y;
		int j1 = 256 - alpha;
		int k1 = (colour >> 16 & 0xff) * alpha;
		int l1 = (colour >> 8 & 0xff) * alpha;
		int i2 = (colour & 0xff) * alpha;
		int i3 = x + y * width;
		for (int j3 = 0; j3 < length; j3++) {
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			drawAlpha(pixels, i3+=width, k3, k3, alpha);
		}

	}

	public static void initDrawingArea(int i, int j, int ai[], float depth[])
	{
		depthBuffer = depth;
		pixels = ai;
		width = j;
		height = i;
		setDrawingArea(i, 0, j, 0);
	}
	public static void method339(int i, int j, int k, int l)
	{
		if(i < topY || i >= bottomY)
			return;
		if(l < topX)
		{
			k -= topX - l;
			l = topX;
		}
		if(l + k > bottomX)
			k = bottomX - l;
		int i1 = l + i * width;
		for(int j1 = 0; j1 < k; j1++) {
			drawAlpha(pixels, i1 + j1, j, j, 255);
		}

	}

	public static void drawPixelsWithOpacity(int color, int yPos, int pixelWidth, int pixelHeight, int opacityLevel, int xPos) {
		if (xPos < topX) {
			pixelWidth -= topX - xPos;
			xPos = topX;
		}
		if (yPos < topY) {
			pixelHeight -= topY - yPos;
			yPos = topY;
		}
		if (xPos + pixelWidth > bottomX)
			pixelWidth = bottomX - xPos;
		if (yPos + pixelHeight > bottomY)
			pixelHeight = bottomY - yPos;
		int l1 = 256 - opacityLevel;
		int i2 = (color >> 16 & 0xff) * opacityLevel;
		int j2 = (color >> 8 & 0xff) * opacityLevel;
		int k2 = (color & 0xff) * opacityLevel;
		int k3 = width - pixelWidth;
		int l3 = xPos + yPos * width;
		if (l3 > pixels.length - 1) {
			l3 = pixels.length - 1;
		}
		for (int i4 = 0; i4 < pixelHeight; i4++) {
			for (int j4 = -pixelWidth; j4 < 0; j4++) {
				int l2 = (pixels[l3] >> 16 & 0xff) * l1;
				int i3 = (pixels[l3] >> 8 & 0xff) * l1;
				int j3 = (pixels[l3] & 0xff) * l1;
				int k4 = ((i2 + l2 >> 8) << 16) + ((j2 + i3 >> 8) << 8) + (k2 + j3 >> 8);
				drawAlpha(pixels, l3++, k4, k4, opacityLevel);
			}
			l3 += k3;
		}
	}
	public static void defaultDrawingAreaSize()
	{
		topX = 0;
		topY = 0;
		bottomX = width;
		bottomY = height;
		viewportRX = bottomX - 0;
		viewport_centerX = bottomX / 1;
	}

	public static void setDrawingArea(int yBottom, int xTop, int xBottom, int yTop)
	{
		if(xTop < 0)
			xTop = 0;
		if(yTop < 0)
			yTop = 0;
		if(xBottom > width)
			xBottom = width;
		if(yBottom > height)
			yBottom = height;
		topX = xTop;
		topY = yTop;
		bottomX = xBottom;
		bottomY = yBottom;
		viewportRX = bottomX - 0;
		viewport_centerX = bottomX / 2;
		viewport_centerY = bottomY / 2;
	}

	public static int clamp(int x, int a, int b) {
		return (x < a) ? a : (x > b) ? b : x;
	}
	public static void blur( int[] in, int[] out, int width, int height, int radius ) {
		int widthMinus1 = width-1;
		int tableSize = 2*radius+1;
		int divide[] = new int[256*tableSize];

		for ( int i = 0; i < 256*tableSize; i++ )
			divide[i] = i/tableSize;

		int inIndex = 0;

		for ( int y = 0; y < height; y++ ) {
			int outIndex = y;
			int ta = 0, tr = 0, tg = 0, tb = 0;

			for ( int i = -radius; i <= radius; i++ ) {
				int rgb = in[inIndex + clamp(i, 0, width-1)];
				ta += (rgb >> 24) & 0xff;
				tr += (rgb >> 16) & 0xff;
				tg += (rgb >> 8) & 0xff;
				tb += rgb & 0xff;
			}

			for ( int x = 0; x < width; x++ ) {
				out[ outIndex ] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb];

				int i1 = x+radius+1;
				if ( i1 > widthMinus1 )
					i1 = widthMinus1;
				int i2 = x-radius;
				if ( i2 < 0 )
					i2 = 0;
				int rgb1 = in[inIndex+i1];
				int rgb2 = in[inIndex+i2];

				ta += ((rgb1 >> 24) & 0xff)-((rgb2 >> 24) & 0xff);
				tr += ((rgb1 & 0xff0000)-(rgb2 & 0xff0000)) >> 16;
				tg += ((rgb1 & 0xff00)-(rgb2 & 0xff00)) >> 8;
			tb += (rgb1 & 0xff)-(rgb2 & 0xff);
			outIndex += height;
			}
			inIndex += width;
		}
	}

	public static void drawDiagonalLine(int x, int y, int areaWidth, int areaHeight, int color) {//method577
		areaWidth -= x;
	areaHeight -= y;
	if(areaHeight == 0)
		if(areaWidth >= 0) {
			drawHorizontalLine(x, y, areaWidth + 1, color);
			return;
		} else {
			drawHorizontalLine(x + areaWidth, y, -areaWidth + 1, color);
			return;
		}
	if(areaWidth == 0)
		if(areaHeight >= 0) {
			drawVLine(x, y, areaHeight + 1, color);
			return;
		} else {
			drawHLine(x, y + areaHeight, -areaHeight + 1, color);
			return;
		}
	if(areaWidth + areaHeight < 0) {
		x += areaWidth;
		areaWidth = -areaWidth;
		y += areaHeight;
		areaHeight = -areaHeight;
	}
	if(areaWidth > areaHeight) {
		y <<= 16;
		y += 32768;
		areaHeight <<= 16;
		int j1 = (int)Math.floor((double)areaHeight / (double)areaWidth + 0.5D);
		areaWidth += x;
		if(x < topX) {
			y += j1 * (topX - x);
			x = topX;
		}
		if(areaWidth >= bottomX)
			areaWidth = bottomX - 1;
		for(; x <= areaWidth; x++) {
			int l1 = y >> 16;
		if(l1 >= topY && l1 < bottomY)
			drawAlpha(pixels, x + l1 * width, color, color, 255);
		y += j1;
		}
		return;
	}
	x <<= 16;
	x += 32768;
	areaWidth <<= 16;
	int k1 = (int)Math.floor((double)areaWidth / (double)areaHeight + 0.5D);
	areaHeight += y;
	if(y < topY) {
		x += k1 * (topY - y);
		y = topY;
	}
	if(areaHeight >= bottomY)
		areaHeight = bottomY - 1;
	for(; y <= areaHeight; y++) {
		int i2 = x >> 16;
	if(i2 >= topX && i2 < bottomX)
		drawAlpha(pixels, i2 + y * width, color, color, 255);
	x += k1;
	}
	}

	public static void drawGradient(int x, int y, int gradientWidth,
			int gradientHeight, int startColor, int endColor) {
		int k1 = 0;
		int l1 = 0x10000 / gradientHeight;
		if (x < topX) {
			gradientWidth -= topX - x;
			x = topX;
		}
		if (y < topY) {
			k1 += (topY - y) * l1;
			gradientHeight -= topY - y;
			y = topY;
		}
		if (x + gradientWidth > bottomX)
			gradientWidth = bottomX - x;
		if (y + gradientHeight > bottomY)
			gradientHeight = bottomY - y;
		int i2 = width - gradientWidth;
		int j2 = x + y * width;
		for (int k2 = -gradientHeight; k2 < 0; k2++) {
			int l2 = 0x10000 - k1 >> 8;
		int i3 = k1 >> 8;
	int j3 = ((startColor & 0xff00ff) * l2 + (endColor & 0xff00ff) * i3 & 0xff00ff00)
			+ ((startColor & 0xff00) * l2 + (endColor & 0xff00) * i3 & 0xff0000) >>> 8;
		for (int k3 = -gradientWidth; k3 < 0; k3++)
			drawAlpha(pixels, j2++, j3, j3, 255);
		j2 += i2;
		k1 += l1;
		}
	}

	public static void drawAlphaGradient2(int x, int y, int gradientWidth,
			int gradientHeight, int startColor, int endColor, int alpha) {
		int k1 = 0;
		int l1 = 0x10000 / gradientHeight;
		if (x < topX) {
			gradientWidth -= topX - x;
			x = topX;
		}
		if (y < topY) {
			k1 -= (topY - y) * l1;
			gradientHeight -= topY - y;
			y = topY;
		}
		if (x + gradientWidth > bottomX)
			gradientWidth = bottomX - x;
		if (y + gradientHeight > bottomY)
			gradientHeight = bottomY - y;
		int i2 = width - gradientWidth;
		int total_pixels = x + y * width;
		for (int k2 = -gradientHeight; k2 < 0; k2++) {
			int alpha2 = (gradientHeight+k2)*(gradientHeight/alpha);
			int result_alpha = 256 - alpha2;
			int gradient1 = 0x10000 - k1 >> 8;
		int gradient2 = k1 >> 8;
		int gradient_color = ((startColor & 0xff00ff) * gradient1
				+ (endColor & 0xff00ff) * gradient2 & 0xff00ff00)
				+ ((startColor & 0xff00) * gradient1 + (endColor & 0xff00)
						* gradient2 & 0xff0000) >>> 8;
						int color = ((gradient_color & 0xff00ff) * alpha >> 8 & 0xff00ff)
								+ ((gradient_color & 0xff00) * alpha >> 8 & 0xff00);
						for (int k3 = -gradientWidth; k3 < 0; k3++) {
							int colored_pixel = pixels[total_pixels];
							colored_pixel = ((colored_pixel & 0xff00ff) * result_alpha >> 8 & 0xff00ff)
									+ ((colored_pixel & 0xff00) * result_alpha >> 8 & 0xff00);
							drawAlpha(pixels, total_pixels++, color + colored_pixel, color + colored_pixel, alpha2);
						}
						total_pixels += i2;
						k1 -= l1;
		}
	}

	public void drawAlphaGradient(int x, int y, int gradientWidth,
			int gradientHeight, int startColor, int endColor, int alpha) {
		int k1 = 0;
		int l1 = 0x10000 / gradientHeight;
		if (x < topX) {
			gradientWidth -= topX - x;
			x = topX;
		}
		if (y < topY) {
			k1 += (topY - y) * l1;
			gradientHeight -= topY - y;
			y = topY;
		}
		if (x + gradientWidth > bottomX)
			gradientWidth = bottomX - x;
		if (y + gradientHeight > bottomY)
			gradientHeight = bottomY - y;
		int i2 = width - gradientWidth;
		int result_alpha = 256 - alpha;
		int total_pixels = x + y * width;
		for (int k2 = -gradientHeight; k2 < 0; k2++) {
			int gradient1 = 0x10000 - k1 >> 8;
		int gradient2 = k1 >> 8;
		int gradient_color = ((startColor & 0xff00ff) * gradient1
				+ (endColor & 0xff00ff) * gradient2 & 0xff00ff00)
				+ ((startColor & 0xff00) * gradient1 + (endColor & 0xff00)
						* gradient2 & 0xff0000) >>> 8;
						int color = ((gradient_color & 0xff00ff) * alpha >> 8 & 0xff00ff)
								+ ((gradient_color & 0xff00) * alpha >> 8 & 0xff00);
						for (int k3 = -gradientWidth; k3 < 0; k3++) {
							int colored_pixel = pixels[total_pixels];
							colored_pixel = ((colored_pixel & 0xff00ff) * result_alpha >> 8 & 0xff00ff)
									+ ((colored_pixel & 0xff00) * result_alpha >> 8 & 0xff00);
							drawAlpha(pixels, total_pixels++, color + colored_pixel, color + colored_pixel, alpha);
						}
						total_pixels += i2;
						k1 += l1;
		}
	}

	public static void drawAlphaHorizontalLine2(int x, int y, int lineWidth,
			int color, int alpha) {// drawAlphaHorizontalLine
		if (y < topY || y >= bottomY)
			return;
		if (x < topX) {
			lineWidth -= topX - x;
			x = topX;
		}
		if (x + lineWidth > bottomX)
			lineWidth = bottomX - x;
		int i3 = x + y * width;
		for (int j3 = 0; j3 < lineWidth; j3++) {
			int alpha2 = (lineWidth-j3)/(lineWidth/alpha);
			int j1 = 256 - alpha2;
			int k1 = (color >> 16 & 0xff) * alpha2;
			int l1 = (color >> 8 & 0xff) * alpha2;
			int i2 = (color & 0xff) * alpha2;
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8)
					+ (i2 + l2 >> 8);
			drawAlpha(pixels, i3++, k3, k3, 255);
		}
	}

	public static void resetImage()
	{
		int i = width * height;
		for(int j = 0; j < i; j++) {
			pixels[j] = HdPlugin.process() ? 0 : Client.instance.skyboxColor;
			depthBuffer[j] = Float.MAX_VALUE;
		}


	}
	public static void drawVLine(int x, int y, int height, int colour)
	{
		if(x < topX || x >= bottomY)
			return;
		if(y < topY)
		{
			height -= topY - y;
			y = topY;
		}
		if(y + height > bottomX)
			height = bottomX - y;
		int ptr = x + y * width;
		for(int y_off = 0; y_off < height; y_off++)
			drawAlpha(pixels, ptr + y_off * width, colour, colour, 255);

	}

	public static void blur(int x, int y, int width, int height, int speed)
	{
		try {
			blur(pixels, pixels, width, height, speed);
			blur(pixels, pixels, height, width, speed);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void drawHLine(int x, int y, int width, int colour)
	{
		if(y < topY || y >= bottomY)
			return;
		if(x < topX)
		{
			width -= topX - x;
			x = topX;
		}
		if(x + width > bottomX)
			width = bottomX - x;
		int i1 = x + y * DrawingArea.width;
		for(int x_off = 0; x_off < width; x_off++)
			drawAlpha(pixels, i1 + x_off, colour, colour, 255);

	}
	public static void fillRectangle(int color, int y, int widthz, int heightz, int opacity, int x)
	{
		if(x < topX)
		{
			widthz -= topX - x;
			x = topX;
		}
		if(y < topY)
		{
			heightz -= topY - y;
			y = topY;
		}
		if(x + widthz > bottomX)
			widthz = bottomX - x;
		if(y + heightz > bottomY)
			heightz = bottomY - y;
		int decodedOpacity = 256 - opacity;
		int red = (color >> 16 & 0xff) * opacity;
		int green = (color >> 8 & 0xff) * opacity;
		int blue = (color & 0xff) * opacity;
		int pixelWidthStep = width - widthz;
		int startPixel = x + y * width;
		for(int h = 0; h < heightz; h++)
		{
			for(int w = -widthz; w < 0; w++)
			{
				int pixelRed = (pixels[startPixel] >> 16 & 0xff) * decodedOpacity;
				int pixelBlue = (pixels[startPixel] >> 8 & 0xff) * decodedOpacity;
				int pixelGreen = (pixels[startPixel] & 0xff) * decodedOpacity;
				int pixelRGB = ((red + pixelRed >> 8) << 16) + ((green + pixelBlue >> 8) << 8) + (blue + pixelGreen >> 8);
				drawAlpha(pixels, startPixel++, pixelRGB, pixelRGB, opacity);
			}

			startPixel += pixelWidthStep;
		}
	}
	public static void drawPixels(int height_, int yPos, int xPos, int color, int width_, int alpha)
	{
		if(xPos < topX)
		{
			width_ -= topX - xPos;
			xPos = topX;
		}
		if(yPos < topY)
		{
			height_ -= topY - yPos;
			yPos = topY;
		}
		if(xPos + width_ > bottomX)
			width_ = bottomX - xPos;
		if(yPos + height_ > bottomY)
			height_ = bottomY - yPos;
		int decodedOpacity = 256 - alpha;
		int widthPixelStep = width - width_;
		int startPixel = xPos + yPos * width;
		int red = (color >> 16 & 0xff) * alpha;
		int green = (color >> 8 & 0xff) * alpha;
		int blue = (color & 0xff) * alpha;
		for(int i2 = -height_; i2 < 0; i2++)
		{
			for(int j2 = -width_; j2 < 0; j2++) {
				int pixelRed = (pixels[startPixel] >> 16 & 0xff) * decodedOpacity;
				int pixelBlue = (pixels[startPixel] >> 8 & 0xff) * decodedOpacity;
				int pixelGreen = (pixels[startPixel] & 0xff) * decodedOpacity;
				int pixelRGB = ((red + pixelRed >> 8) << 16) + ((green + pixelBlue >> 8) << 8) + (blue + pixelGreen >> 8);
				drawAlpha(pixels, startPixel++, pixelRGB, pixelRGB, alpha);
			}

			startPixel += widthPixelStep;
		}

	}
	
	public static void drawAlphaPixels(int x, int y, int w, int h, int color, int alpha) {
		if (x < topX) {
			w -= topX - x;
			x = topX;
		}
		if (y < topY) {
			h -= topY - y;
			y = topY;
		}
		if (x + w > bottomX)
			w = bottomX - x;
		if (y + h > bottomY)
			h = bottomY - y;
		int l1 = 256 - alpha;
		int i2 = (color >> 16 & 0xff) * alpha;
		int j2 = (color >> 8 & 0xff) * alpha;
		int k2 = (color & 0xff) * alpha;
		int k3 = width - w;
		int l3 = x + y * width;
		for (int i4 = 0; i4 < h; i4++) {
			for (int j4 = -w; j4 < 0; j4++) {
				int l2 = (pixels[l3] >> 16 & 0xff) * l1;
				int i3 = (pixels[l3] >> 8 & 0xff) * l1;
				int j3 = (pixels[l3] & 0xff) * l1;
				int k4 = ((i2 + l2 >> 8) << 16) + ((j2 + i3 >> 8) << 8) + (k2 + j3 >> 8);
				drawAlpha(pixels, l3++, k4, k4, alpha);
			}

			l3 += k3;
		}
	}
	
	public static void drawPixels(int height_, int yPos, int xPos, int color, int width_)
	{
		if(xPos < topX)
		{
			width_ -= topX - xPos;
			xPos = topX;
		}
		if(yPos < topY)
		{
			height_ -= topY - yPos;
			yPos = topY;
		}
		if(xPos + width_ > bottomX)
			width_ = bottomX - xPos;
		if(yPos + height_ > bottomY)
			height_ = bottomY - yPos;
		int k1 = width - width_;
		int l1 = xPos + yPos * width;
		for(int i2 = -height_; i2 < 0; i2++)
		{
			for(int j2 = -width_; j2 < 0; j2++) {
				int s = l1++;
				if(s < pixels.length)
					drawAlpha(pixels, s, color, color, 255);
			}

			l1 += k1;
		}

	}

	public static void drawPixels2(int i, int j, int k, int l, int i1) {
		if (k < topX) {
			i1 -= topX - k;
			k = topX;
		}
		if (j < topY) {
			i -= topY - j;
			j = topY;
		}
		if (k + i1 > bottomX) {
			i1 = bottomX - k;
		}
		if (j + i > bottomY) {
			i = bottomY - j;
		}
		int k1 = width - i1;
		int l1 = k + j * width;
		for (int i2 = -i; i2 < 0; i2++) {
			for (int j2 = -i1; j2 < 0; j2++) {
				drawAlpha(pixels, l1++, l, l, 255);
			}
			l1 += k1;
		}
	}

	public static void fillPixels(int x, int width, int y, int color, int height)
	{
		drawLine(height, color, width, x);
		drawLine((height + y) - 1, color, width, x);
		drawLineVertical(height, color, y, x);
		drawLineVertical(height, color, y, (x + width) - 1);
	}

	public static void drawRectangle(int y, int height, int opacity, int color, int width, int x)
	{
		drawHLine(color, width, y, opacity, x);
		drawHLine(color, width, (y + height) - 1, opacity, x);
		if(height >= 3)
		{
			drawVLine(color, x, opacity, y, height - 2);
			drawVLine(color, (x + width) - 1, opacity, y, height - 2);
		}
	}

	public static void drawLine(int yPos, int color, int widthToDraw, int xPos)
	{
		if(yPos < topY || yPos >= bottomY)
			return;
		if(xPos < topX)
		{
			widthToDraw -= topX - xPos;
			xPos = topX;
		}
		if(xPos + widthToDraw > bottomX)
			widthToDraw = bottomX - xPos;
		int base = xPos + yPos * width;
		for(int ptr = 0; ptr < widthToDraw; ptr++)
			drawAlpha(pixels, base + ptr, color, color, 255);
	}

	public static void drawHorizontalLine(int i, int j, int k, int l) {
		if (i < topY || i >= bottomY)
			return;
		if (l < topX) {
			k -= topX - l;
			l = topX;
		}
		if (l + k > bottomX)
			k = bottomX - l;
		int i1 = l + i * width;
		for (int j1 = 0; j1 < k; j1++)
			drawAlpha(pixels, i1 + j1, j, j, 255);

	}
	
    public static void drawVerticalLine(int xPosition, int yPosition, int height, int rgbColour) {
        if (xPosition < topX || xPosition >= bottomX)
            return;
        if (yPosition < topY) {
            height -= topY - yPosition;
            yPosition = topY;
        }
        if (yPosition + height > bottomY)
            height = bottomY - yPosition;
        int pixelIndex = xPosition + yPosition * width;
        for (int rowIndex = 0; rowIndex < height; rowIndex++)
			drawAlpha(pixels, pixelIndex + rowIndex * width, rgbColour, rgbColour, 255);
    }
	
	protected static void drawHLine(int i, int j, int k, int l, int i1)
	{
		if(k < topY || k >= bottomY)
			return;
		if(i1 < topX)
		{
			j -= topX - i1;
			i1 = topX;
		}
		if(i1 + j > bottomX)
			j = bottomX - i1;
		int j1 = 256 - l;
		int k1 = (i >> 16 & 0xff) * l;
		int l1 = (i >> 8 & 0xff) * l;
		int i2 = (i & 0xff) * l;
		int i3 = i1 + k * width;
		for(int j3 = 0; j3 < j; j3++)
		{
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			drawAlpha(pixels, i3++, k3, k3, l);
		}

	}

	public static void drawLineVertical(int heights, int color, int yPos, int xPos)
	{
		if(xPos < topX || xPos >= bottomX)
			return;
		if(heights < topY)
		{
			yPos -= topY - heights;
			heights = topY;
		}
		if(heights + yPos > bottomY)
			yPos = bottomY - heights;
		int j1 = xPos + heights * width;
		for(int k1 = 0; k1 < yPos; k1++) {
			if(j1 + k1 * width < pixels.length)
				drawAlpha(pixels, j1 + k1 * width, color, color, 255);
		}

	}

	protected static void drawVLine(int i, int j, int k, int l, int i1) {
		if(j < topX || j >= bottomX)
			return;
		if(l < topY) {
			i1 -= topY - l;
			l = topY;
		}
		if(l + i1 > bottomY)
			i1 = bottomY - l;
		int j1 = 256 - k;
		int k1 = (i >> 16 & 0xff) * k;
		int l1 = (i >> 8 & 0xff) * k;
		int i2 = (i & 0xff) * k;
		int i3 = j + l * width;
		for(int j3 = 0; j3 < i1; j3++) {
			int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			int l2 = (pixels[i3] & 0xff) * j1;
			int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
			drawAlpha(pixels, i3 += width, k3, k3, k);
		}
	}

	public static void fillCircle(int posX, int posY, int radius, int colour, int alpha)
	{
		int dest_intensity = 256 - alpha;
		int src_red = (colour >> 16 & 0xff) * alpha;
		int src_green = (colour >> 8 & 0xff) * alpha;
		int src_blue = (colour & 0xff) * alpha;
		int radiusPixels = posY - radius;
		if(radiusPixels < 0)
			radiusPixels = 0;
		int max = posY + radius;
		if(max >= height)
			max = height - 1;
		for(int y = radiusPixels; y <= max; y++)
		{
			int beginYPos = y - posY;
			int heighestXPos = (int)Math.sqrt(radius * radius - beginYPos * beginYPos);
			int x = posX - heighestXPos;
			if(x < 0)
				x = 0;
			int maxPixelX = posX + heighestXPos;
			if(maxPixelX >= width)
				maxPixelX = width - 1;
			int pixel_offset = x + y * width;
			for(int pixelX = x; pixelX <= maxPixelX; pixelX++)
			{
				int dest_red = (pixels[pixel_offset] >> 16 & 0xff) * dest_intensity;
				int dest_green = (pixels[pixel_offset] >> 8 & 0xff) * dest_intensity;
				int dest_blue = (pixels[pixel_offset] & 0xff) * dest_intensity;
				int result_rgb = ((src_red + dest_red >> 8) << 16) + ((src_green + dest_green >> 8) << 8) + (src_blue + dest_blue >> 8);
				drawAlpha(pixels, pixel_offset++, result_rgb, result_rgb, alpha);
			}

		}

	}
	
	public static void drawHorizontalLine(int x, int y, int length, int color, int alpha) {
		if (y < topY || y >= bottomY) {
			return;
		}
		if (x < topX) {
			length -= topX - x;
			x = topX;
		}
		if (x + length > bottomX) {
			length = bottomX - x;
		}
		final int j1 = 256 - alpha;
		final int k1 = (color >> 16 & 0xff) * alpha;
		final int l1 = (color >> 8 & 0xff) * alpha;
		final int i2 = (color & 0xff) * alpha;
		int i3 = x + y * width;
		for (int j3 = 0; j3 < length; j3++) {
			final int j2 = (pixels[i3] >> 16 & 0xff) * j1;
			final int k2 = (pixels[i3] >> 8 & 0xff) * j1;
			final int l2 = (pixels[i3] & 0xff) * j1;
			final int k3 = (k1 + j2 >> 8 << 16) + (l1 + k2 >> 8 << 8) + (i2 + l2 >> 8);
			drawAlpha(pixels, i3++, k3, k3, alpha);
		}
	}
	
	public static void drawVerticalLine(int x, int y, int length, int color, int alpha) {
		if (x < topX || x >= bottomX) {
			return;
		}
		if (y < topY) {
			length -= topY - y;
			y = topY;
		}
		if (y + length > bottomY) {
			length = bottomY - y;
		}
		final int j1 = 256 - alpha;
		final int k1 = (color >> 16 & 0xff) * alpha;
		final int l1 = (color >> 8 & 0xff) * alpha;
		final int i2 = (color & 0xff) * alpha;
		int i3 = x + y * width;

		int pixelIndex = x + y * width;
		for (int rowIndex = 0; rowIndex < length; rowIndex++) {
			int dest_red = (pixels[i3] >> 16 & 0xff) * j1;
			int dest_green = (pixels[i3] >> 8 & 0xff) * j1;
			int dest_blue = (pixels[i3] & 0xff) * j1;
			int result_rgb = ((k1 + dest_red >> 8) << 16) + ((l1 + dest_green >> 8) << 8) + (i2 + dest_blue >> 8);
			drawAlpha(pixels, pixelIndex + rowIndex * width, result_rgb, result_rgb, 255);
		}
	}
	
    public static final int STYLISH_BOX_OUTLINE_OUTLINE_COLOR = 0x383023;
    public static final int STYLISH_BOX_OUTLINE_COLOR = 0x5a5245;
    public static final int STYLISH_BOX_BACKGROUND_COLOR = 0x463D32;

    public static void drawStylishBox(int x, int y, int width, int height) {
    	DrawingArea.drawBoxOutline(x, y, width, height, STYLISH_BOX_OUTLINE_OUTLINE_COLOR);
    	DrawingArea.drawBoxOutline(x + 1, y + 1, width - 2, height - 2, STYLISH_BOX_OUTLINE_COLOR);
        DrawingArea.drawTransparentBox(x + 2, y + 2, width - 4, height - 4, STYLISH_BOX_BACKGROUND_COLOR, 156);
    }
	
	private static final ColorModel COLOR_MODEL = new DirectColorModel(32, 0xff0000, 0xff00, 0xff);

	public static Graphics2D createGraphics(boolean renderingHints) {
		Graphics2D g2d = createGraphics(pixels, width, height);
		if (renderingHints) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		return g2d;
	}

	public static Graphics2D createGraphics(int[] pixels, int width, int height) {
		return new BufferedImage(COLOR_MODEL, Raster.createWritableRaster(COLOR_MODEL.createCompatibleSampleModel(width, height), new DataBufferInt(pixels, width * height), null), false,
				new Hashtable<Object, Object>()).createGraphics();
	}
	
    public static Shape createSector(int x, int y, int r, int angle) {
        return new Arc2D.Double(x, y, r, r, 90, -angle, Arc2D.PIE);
    }

    public static Shape createCircle(int x, int y, int r) {
        return new Ellipse2D.Double(x, y, r, r);
    }

    public static Shape createRing(Shape sector, Shape innerCircle) {
        Area ring = new Area(sector);
        ring.subtract(new Area(innerCircle));
        return ring;
    }
	
	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g
	 *            The Graphics instance.
	 * @param text
	 *            The String to draw.
	 * @param rect
	 *            The Rectangle to center the text in.
	 */
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		// Get the FontMetrics
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as
		// in java 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(text, x, y);
	}
	
	public DrawingArea() {

	}

	public static float depthBuffer[];
	public static int pixels[];
	public static int width;
	public static int height;
	public static int topY;
	public static int bottomY;
	public static int topX;
	public static int bottomX;
	public static int viewportRX;
	public static int viewport_centerX;
	public static int viewport_centerY;

	public static void drawFilledCircle(int x, int y, int radius, int color, int alpha) {
		int y1 = y - radius;
		if (y1 < 0) {
			y1 = 0;
		}
		int y2 = y + radius;
		if (y2 >= height) {
			y2 = height - 1;
		}
		int a2 = 256 - alpha;
		int r1 = (color >> 16 & 0xff) * alpha;
		int g1 = (color >> 8 & 0xff) * alpha;
		int b1 = (color & 0xff) * alpha;
		for (int iy = y1; iy <= y2; iy++) {
			int dy = iy - y;
			int dist = (int) Math.sqrt(radius * radius - dy * dy);
			int x1 = x - dist;
			if (x1 < 0) {
				x1 = 0;
			}
			int x2 = x + dist;
			if (x2 >= width) {
				x2 = width - 1;
			}
			int pos = x1 + iy * width;
			for (int ix = x1; ix <= x2; ix++) {
				int r2 = (pixels[pos] >> 16 & 0xff) * a2;
				int g2 = (pixels[pos] >> 8 & 0xff) * a2;
				int b2 = (pixels[pos] & 0xff) * a2;
				int rgb = ((r1 + r2 >> 8) << 16) + ((g1 + g2 >> 8) << 8) + (b1 + b2 >> 8);
				drawAlpha(pixels, pos++, rgb, rgb, alpha);
			}
		}
	}
	
    /**
     * Draws a box filled with a certain colour.
     *
     * @param topX     The left edge X-Coordinate of the box.
     * @param topY      The top edge Y-Coordinate of the box.
     * @param width     The width of the box.
     * @param height    The height of the box.
     * @param rgbColour The RGBColour of the box.
     */
	public static void drawBox(int topX, int topY, int width, int height, int rgbColour) {
		if (topX < DrawingArea.topX) {
			width -= DrawingArea.topX - topX;
			topX = DrawingArea.topX;
		}
		if (topY < DrawingArea.topY) {
			height -= DrawingArea.topY - topY;
			topY = DrawingArea.topY;
		}
		if (topX + width > bottomX)
			width = bottomX - topX;
		if (topY + height > bottomY)
			height = bottomY - topY;
		int leftOver = DrawingArea.width - width;
		int pixelIndex = topX + topY * DrawingArea.width;
		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			for (int columnIndex = 0; columnIndex < width; columnIndex++)
				drawAlpha(pixels, pixelIndex++, rgbColour, rgbColour, 255);
			pixelIndex += leftOver;
		}
	}

	public static void drawBox(int x, int y, int width, int height, int border, int color, int alpha) {
		for (int i = 0; i < border; i++) {
			drawHorizontalLine(x, y + i, width, color, alpha);
			drawHorizontalLine(x, y + height - i - border, width, color, alpha);
			
			drawVerticalLine(x + i, y + border - 1, height - border * 2, color, alpha);
			drawVerticalLine(x + width - border + i, y + border - 1, height - border * 2, color, alpha);
		}
	}
	
	public static void drawBox(int x, int y, int width, int height, int border, int borderColor, int color, int alpha) {
		drawHorizontalLine(x + 1, y, width, color, alpha);
		drawHorizontalLine(x, y + height - 2, width, color, alpha);
		
		drawVerticalLine(x, y, height - 2, color, alpha);
		drawVerticalLine(x + width, y + 1, height - 2, color, alpha);
		
		for (int i = 1; i < border; i++) {
			drawHorizontalLine(x + 1, y + i, width - 1, borderColor, alpha);
			drawHorizontalLine(x + border, y + height - i - 2, width - border * 2 + 1, borderColor, alpha);
			
			drawVerticalLine(x + i, y + border, height - border - 2, borderColor, alpha);
			drawVerticalLine(x + width - border + i, y + border, height - border - 2, borderColor, alpha);
		}
	}
	
    /**
     * Draws a transparent box.
     *
     * @param topX     The left edge X-Coordinate of the box.
     * @param topY      The top edge Y-Coordinate of the box.
     * @param width     The box width.
     * @param height    The box height.
     * @param rgbColour The box colour.
     * @param opacity   The opacity value ranging from 0 to 256.
     */
    public static void drawTransparentBox(int topX, int topY, int width, int height, int rgbColour, int opacity) {
        if (topX < DrawingArea.topX) {
            width -= DrawingArea.topX - topX;
            topX = DrawingArea.topX;
        }
        if (topY < DrawingArea.topY) {
            height -= DrawingArea.topY - topY;
            topY = DrawingArea.topY;
        }
        if (topX + width > bottomX)
            width = bottomX - topX;
        if (topY + height > bottomY)
            height = bottomY - topY;
        int transparency = 256 - opacity;
        int red = (rgbColour >> 16 & 0xff) * opacity;
        int green = (rgbColour >> 8 & 0xff) * opacity;
        int blue = (rgbColour & 0xff) * opacity;
        int leftOver = DrawingArea.width - width;
        int pixelIndex = topX + topY * DrawingArea.width;
        for (int rowIndex = 0; rowIndex < height; rowIndex++) {
            for (int columnIndex = 0; columnIndex < width; columnIndex++) {
                int otherRed = (pixels[pixelIndex] >> 16 & 0xff) * transparency;
                int otherGreen = (pixels[pixelIndex] >> 8 & 0xff) * transparency;
                int otherBlue = (pixels[pixelIndex] & 0xff) * transparency;
                int transparentColour = ((red + otherRed >> 8) << 16) + ((green + otherGreen >> 8) << 8) + (blue + otherBlue >> 8);
				drawAlpha(pixels, pixelIndex++, transparentColour, transparentColour, opacity);
            }
            pixelIndex += leftOver;
        }
    }
    
    /**
     * Draws a 1 pixel thick box outline in a certain colour.
     *
     * @param leftX     The left edge X-Coordinate.
     * @param topY      The top edge Y-Coordinate.
     * @param width     The width.
     * @param height    The height.
     * @param rgbColour The RGB-Colour.
     */
    public static void drawBoxOutline(int leftX, int topY, int width, int height, int rgbColour) {
    	drawHLine(leftX, topY, width, rgbColour);
        drawHLine(leftX, (topY + height) - 1, width, rgbColour);
        drawVerticalLine(leftX, topY, height, rgbColour);
        drawVerticalLine((leftX + width) - 1, topY, height, rgbColour);
    }

	protected static void drawAlpha(int[] pixels, int index, int value, int color, int alpha) {
		if (!HdPlugin.process()) {
			pixels[index] = value;
			return;
		}

		if (alpha == 0) {
			return;
		}

		int prevColor = pixels[index];

		if ((prevColor & 0xFF000000) == 0 || alpha == 255) {
			// No transparency, so we can cheat to save CPU resources
			pixels[index] = (color & 0xFFFFFF) | (alpha << 24);
			return;
		}

		if ((prevColor & 0xFF000000) == 0xFF000000) {
			// When the background is opaque, the result will also be opaque,
			// so we can simply use the value calculated by Jagex.
			pixels[index] = value | 0xFF000000;
			return;
		}

		int prevAlpha = (prevColor >>> 24) * (255 - alpha) >>> 8;
		int finalAlpha = alpha + prevAlpha;

		// Scale alphas so (relativeAlpha >>> 8) is approximately equal to (alpha /
		// finalAlpha).
		// Avoiding extra divisions increase performance by quite a bit.
		// And with divisions we get a problems if dividing a number where
		// the last bit is 1 (as it will become negative).
		int relativeAlpha1 = (alpha << 8) / finalAlpha;
		int relativeAlpha2 = (prevAlpha << 8) / finalAlpha;

		// Red and blue are calculated at the same time to save CPU cycles
		int finalColor = (((color & 0xFF00FF) * relativeAlpha1 + (prevColor & 0xFF00FF) * relativeAlpha2 & 0xFF00FF00) | ((color & 0x00FF00) * relativeAlpha1 + (prevColor & 0x00FF00) * relativeAlpha2 & 0x00FF0000)) >>> 8;

		pixels[index] = finalColor | (finalAlpha << 24);
	}
}
