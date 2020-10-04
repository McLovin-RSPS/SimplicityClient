package com.simplicity.client;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;

import net.runelite.api.ItemLayer;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlane() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WorldPoint getWorldLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalPoint getLocalLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getCanvasLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getCanvasLocation(int zOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polygon getCanvasTilePoly() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getMinimapLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Area getClickbox() {
		// TODO Auto-generated method stub
		return null;
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
