package com.simplicity.client.content.overlay;

import com.simplicity.client.Client;

/**
 * An abstract class that represents a single screen overlay.
 * 
 * @author Blake
 *
 */
public abstract class ScreenOverlay {

	/**
	 * The preferred x-coordinate.
	 */
	private int x;

	/**
	 * The preferred y-coordinate.
	 */
	private int y;

	/**
	 * The width.
	 */
	private int width;

	/**
	 * The height.
	 */
	private int height;

	/**
	 * Constructs a new {@link ScreenOverlay}.
	 * 
	 * @param x      The x-coordinate.
	 * @param y      The y-coordinate.
	 * @param width  The width.
	 * @param height The height.
	 */
	public ScreenOverlay(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the width.
	 * 
	 * @return The width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height.
	 * 
	 * @return The height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the x-coordinate.
	 * 
	 * @return The x-coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y-coordinate.
	 * 
	 * @return The y-coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Attempts to draw the overlay on the screen.
	 * 
	 * @param client The client instance.
	 * @param x      The x-coordinate.
	 * @param y      The y-coordinate.
	 * @return <code>true</code> if the overlay was drawn.
	 * @throws Exception
	 */
	public abstract boolean draw(Client client, int x, int y) throws Exception;

	/**
	 * Gets the overlay's group.
	 * 
	 * @return The overlay's group.
	 */
	public abstract ScreenOverlayGroup getOverlayGroup();

	/**
	 * Checks if the overlay is enabled.
	 * 
	 * @return <code>true</code> if enabled.
	 */
	public abstract boolean enabled();
}
