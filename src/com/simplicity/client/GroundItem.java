package com.simplicity.client;

import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;

public final class GroundItem {

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
	
}
