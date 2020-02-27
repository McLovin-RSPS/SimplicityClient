package com.simplicity.client.entity;

/**
 * A class that represents a single world tile position.
 * 
 * @author Blake.
 *
 */
public class Position {

	/**
	 * Constructs a new {@link Position}.
	 * 
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param z The z-coordinate.
	 */
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructs a new {@link Position}.
	 * 
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 */
	public Position(int x, int y) {
		this(x, y, 0);
	}

	/**
	 * The x coordinate of the position.
	 */
	private int x;

	/**
	 * Gets the x coordinate of this position.
	 * 
	 * @return The associated x coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * The y coordinate of the position.
	 */
	private int y;

	/**
	 * Gets the y coordinate of this position.
	 * 
	 * @return The associated y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * The height level of the position.
	 */
	private int z;

	/**
	 * Gets the height level of this position.
	 * 
	 * @return The associated height level.
	 */
	public int getZ() {
		return z;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Position)) {
			return false;
		}
		
		Position position = (Position) other;
		
		return position.x == x && position.y == y && position.z == z;
	}
	
	/**
	 * Gets the local x coordinate relative to a specific region.
	 * 
	 * @return The local x coordinate.
	 */
	public int getLocalX() {
		return x - 8 * getRegionX();
	}

	/**
	 * Gets the local y coordinate relative to a specific region.
	 * 
	 * @return The local y coordinate.
	 */
	public int getLocalY() {
		return y - 8 * getRegionY();
	}
	
	/**
	 * Gets the region x coordinate.
	 * @return The region x coordinate.
	 */
	public int getRegionX() {
		return (x >> 3) - 6;
	}

	/**
	 * Gets the region y coordinate.
	 * @return The region y coordinate.
	 */
	public int getRegionY() {
		return (y >> 3) - 6;
	}
	
	/**
	 * Gets the region id.
	 * 
	 * @return The region id.
	 */
	public int getRegionID() {
		int regionX = getX() >> 3;
		
		int regionY = getY() >> 3;
		
		return ((regionX / 8) << 8) + (regionY / 8);
	}
	
	/**
	 * Checks if this location is within range of another.
	 * 
	 * @param other The other location.
	 * @return <code>true</code> if the location is in range, <code>false</code> if
	 *         not.
	 */
	public boolean isWithinDistance(Position other) {
		if (z != other.z) {
			return false;
		}

		int deltaX = other.x - x, deltaY = other.y - y;

		return deltaX <= 14 && deltaX >= -15 && deltaY <= 14 && deltaY >= -15;
	}

}
