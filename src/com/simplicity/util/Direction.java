package com.simplicity.util;

/**
 * An enumerated type that represents a direction.
 *
 * @author Blake
 */
public enum Direction {

    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(-1, 1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(1, -1),
    NONE(0, 0);

    public final int deltaX;
    public final int deltaY;

    /**
     * Constructs a new {@link Direction}.
     *
     * @param deltaX The x delta.
     * @param deltaY The y delta.
     */
    private Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int[] getDirectionDelta() {
        switch (this) {
            case NORTH:
                return new int[] { 0, 1 };
            case NORTH_EAST:
                return new int[] { 1, 1 };
            case EAST:
                return new int[] { 1, 0 };
            case SOUTH_EAST:
                return new int[] { 1, -1 };
            case SOUTH:
                return new int[] { 0, -1 };
            case SOUTH_WEST:
                return new int[] { -1, -1 };
            case WEST:
                return new int[] { -1, 0 };
            case NORTH_WEST:
                return new int[] { -1, 1 };
            default:
                return new int[] { 0, 0 };
        }
    }

}
