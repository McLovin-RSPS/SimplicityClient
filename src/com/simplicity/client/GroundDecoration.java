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

public final class GroundDecoration implements TileObject
{

	public GroundDecoration()
	{
	}

	int zPos;
	int xPos;
	int yPos;
	int plane;
	public Animable node;
	public int uid;
	int groundDecorUID;
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
		return plane;
	}
	
	@Override
	public int getId() {
		return groundDecorUID;
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
		return Perspective.getClickbox(RuneLite.getClient(), getModel(), 0, getLocalLocation());
	}
	
	public Polygon getConvexHull() {
		Model model = null;

		if (node instanceof Model) {
			model = (Model) node;
		} else if (node instanceof Animable) {
			model = ((Animable) node).getRotatedModel();
		}

		if (model == null) {
			return null;
		}

		int tileHeight = Perspective.getTileHeight(RuneLite.getClient(), new LocalPoint(getX(), getY()),
				Client.instance.plane);
		return model.getConvexHull(getX(), getY(), 0, tileHeight);
	}

	public Polygon getConvexHull2() {
		Model model = null;

		if (node instanceof Model) {
			model = (Model) node;
		} else if (node instanceof Animable) {
			model = ((Animable) node).getRotatedModel();
		}

		if (model == null) {
			return null;
		}

		int tileHeight = Perspective.getTileHeight(RuneLite.getClient(), new LocalPoint(getX(), getY()),
				Client.instance.plane);
		return model.getConvexHull(getX(), getY(), 0, tileHeight);
	}
	
	private Model getModel()
	{
		Model model = null;

		if (node instanceof Model) {
			model = (Model) node;
		} else if (node instanceof Animable) {
			model = ((Animable) node).getRotatedModel();
		}

		return model;
	}

	public Animable getRenderable() {
		return node;
	}
	
}
