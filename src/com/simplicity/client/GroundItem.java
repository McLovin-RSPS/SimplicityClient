package com.simplicity.client;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;

import net.runelite.api.ItemLayer;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;

public final class GroundItem implements ItemLayer {

	GroundItem()
	{
	}

	int zPos;
	int xPos;
	int yPos;
	Animable firstGroundItem;
	Animable secondGroundItem;
	Animable thirdGroundItem;
	int uid;
	int newuid;
	int topItem;
	
	public int getHeight() {
		return zPos;
	}

	@Override
	public long getHash() {
		return uid;
	}

	@Override
	public int getX() {
		return xPos;
	}

	@Override
	public int getY() {
		return yPos;
	}

	@Override
	public int getPlane() {
		return zPos;
	}

	@Override
	public int getId() {
		return uid;
	}

	@Override
	public WorldPoint getWorldLocation() {
		return WorldPoint.fromLocal(RuneLite.getClient(), getX(), getY(), getPlane());
	}

	@Override
	public LocalPoint getLocalLocation() {
		return new LocalPoint(getX(), getY());
	}

	@Override
	public Point getCanvasLocation() {
		return getCanvasLocation(0);
	}

	@Override
	public Point getCanvasLocation(int zOffset) {
		return Perspective.localToCanvas(RuneLite.getClient(), getLocalLocation(), getPlane(), zOffset);
	}

	@Override
	public Polygon getCanvasTilePoly() {
		return Perspective.getCanvasTilePoly(RuneLite.getClient(), getLocalLocation());
	}

	@Override
	public Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset) {
		return Perspective.getCanvasTextLocation(RuneLite.getClient(), graphics, getLocalLocation(), text, zOffset);
	}

	@Override
	public Point getMinimapLocation() {
		return Perspective.localToMinimap(RuneLite.getClient(), getLocalLocation());
	}
	
	@Override
	public Area getClickbox() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Animable getBottom() {
		return thirdGroundItem;
	}

	@Override
	public Animable getMiddle() {
		return secondGroundItem;
	}

	@Override
	public Animable getTop() {
		return firstGroundItem;
	}

}
