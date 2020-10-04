package com.simplicity.client;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import com.simplicity.client.cache.definitions.ObjectDefinition;

import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;

public final class InteractableObject implements TileObject
{

	public InteractableObject()
	{
	}

	int zPos;
	int worldZ;
	int worldX;
	int worldY;
	public Animable node;
	public int rotation;
	int tileLeft;
	int tileRight;
	int tileTop;
	int tileBottom;
	int anInt527;
	int height;
	public int uid;
	int interactiveObjUID;
	byte objConf;
	
	@Override
	public long getHash() {
		return uid;
	}
	
	@Override
	public int getX() {
		return worldX;
	}
	
	@Override
	public int getY() {
		return worldY;
	}
	
	@Override
	public int getPlane() {
		return zPos;
	}
	
	@Override
	public int getId() {
		return interactiveObjUID;
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
	public Shape getClickbox() {
		return null;
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
		return model.getConvexHull(getX(), getY(), rotation, tileHeight);
	}
	
}
