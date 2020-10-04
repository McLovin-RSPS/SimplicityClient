package com.simplicity.client;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

public final class WallObject implements TileObject
{

	public WallObject()
	{
	}

	int zPos;
	int xPos;
	int yPos;
	int orientation;
	int orientation1;
	public Animable node1;
	public Animable node2;
	public int uid;
	int wallObjUID;
	byte objConfig;
	
	@Override
	public long getHash() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return xPos;
	}
	
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return yPos;
	}
	
	@Override
	public int getPlane() {
		return zPos;
	}
	
	@Override
	public int getId() {
		return wallObjUID;
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
	public Shape getClickbox() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
