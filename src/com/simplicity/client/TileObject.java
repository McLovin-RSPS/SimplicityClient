package com.simplicity.client;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import javax.annotation.Nullable;

import net.runelite.api.NullObjectID;
import net.runelite.api.ObjectID;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

/**
 * A class that represents an object on a tile.
 * 
 * @author Blake
 *
 */
public interface TileObject
{
	long getHash();

	/**
	 * Gets the x-axis coordinate of the object in local context.
	 *
	 * @see LocalPoint
	 */
	int getX();

	/**
	 * Gets the y-axis coordinate of the object in local context.
	 *
	 * @see LocalPoint
	 */
	int getY();

	/**
	 * Gets the plane of the tile that the object is on.
	 */
	int getPlane();

	/**
	 * Gets the ID of the object.
	 *
	 * @see ObjectID
	 * @see NullObjectID
	 */
	int getId();

	WorldPoint getWorldLocation();

	LocalPoint getLocalLocation();

	/**
	 * Calculates the position of the center of this tile on the canvas
	 */
	Point getCanvasLocation();

	/**
	 * Calculates the position of the center of this tile on the canvas
	 *
	 * @param zOffset Vertical offset to apply before projection
	 */
	Point getCanvasLocation(int zOffset);

	/**
	 * Creates a polygon outlining the tile this object is on
	 */
	Polygon getCanvasTilePoly();

	/**
	 * Calculates the canvas point to center {@code text} above the tile this object is on.
	 *
	 * @param graphics the graphics to use for font size calculation
	 * @param zOffset Vertical offset to apply before projection
	 * @return the canvas point to draw the text at
	 */
	Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset);

	/**
	 * Gets a point on the canvas of where this objects mini-map indicator
	 * should appear.
	 *
	 * @return mini-map location on canvas
	 */
	Point getMinimapLocation();

	/**
	 * Calculate the on-screen clickable area of the object.
	 */
	@Nullable
	Shape getClickbox();
}
