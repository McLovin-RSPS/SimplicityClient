package com.simplicity.client;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;

import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;

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
		return 0;
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
		return wallObjUID;
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
		return null;
	}

	@Override
	public Point getMinimapLocation() {
		return null;
	}

	public Area getClickbox() {
		return Perspective.getClickbox(RuneLite.getClient(), getModel(), orientation, getLocalLocation());
	}
	
	private Model getModel()
	{
		Model model = null;

		if (node1 instanceof Model) {
			model = (Model) node1;
		} else if (node1 instanceof Animable) {
			model = ((Animable) node1).getRotatedModel();
		}

		return model;
	}
	
}
