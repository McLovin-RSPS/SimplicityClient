package com.simplicity.client;


import com.simplicity.client.cache.node.Node;

import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;

public final class Tile extends Node {

	public Tile(int i, int j, int k)
	{
		interactableObjects = new InteractableObject[5];
		anIntArray1319 = new int[5];
		plane = tileZ = i;
		tileX = j;
		tileY = k;
	}

	int tileZ;
	final int tileX;
	final int tileY;
	final int plane;
	public SceneTilePaint plainTile;
	public SceneTileModel shapedTile;
	public WallObject wallObject;
	public WallDecoration wallDecoration;
	public GroundDecoration groundDecoration;
	public GroundItem groundItem;
	int entityCount;
	public final InteractableObject[] interactableObjects;
	final int[] anIntArray1319;
	int anInt1320;
	int logicHeight;
	boolean aBoolean1322;
	boolean aBoolean1323;
	boolean aBoolean1324;
	int anInt1325;
	int anInt1326;
	int anInt1327;
	int anInt1328;
	public Tile tileBelowThisTile;

	public Tile getBridge() {
		return tileBelowThisTile;
	}

	public SceneTilePaint getSceneTilePaint() {
		return plainTile;
	}

	public SceneTileModel getSceneTileModel() {
		return shapedTile;
	}
	
	public InteractableObject[] getGameObjects() {
		return interactableObjects;
	}
	
	public GroundDecoration getDecorativeObject() {
		return groundDecoration;
	}
	
	public WallObject getWallObject() {
		return wallObject;
	}
	
	public GroundItem getGroundObject() {
		return groundItem;
	}
	
	public int getZ() {
		return logicHeight;
	}
	
	public LocalPoint getLocalLocation() {
		return LocalPoint.fromScene(tileX, tileY);
	}

	public Point getSceneLocation()
	{
		return new Point(getX(), getY());
	}
	
	public WorldPoint getWorldLocation() {
		return WorldPoint.fromScene(RuneLite.getClient(), tileX, tileY, tileZ);
	}

	public boolean hasLineOfSightTo(Tile targetTile) {
		return false;
	}
	
	public GroundItem getItemLayer() {
		return groundItem;
	}

	public int getPlane() {
		return plane;
	}

	public int getX() {
		return tileX;
	}

	public int getY() {
		return tileY;
	}
	
}
