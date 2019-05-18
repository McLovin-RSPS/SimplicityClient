package com.simplicity.client.content;

import com.simplicity.Configuration;

/**
 * Player rights enum that MUST be synced with servers rights.
 *
 * @author Gabriel || Wolfsdarker
 */
public enum PlayerRights {

    PLAYER(-1),
    MODERATOR(680),
    ADMINISTRATOR(681),
    OWNER(682),
    DEVELOPER(683),
    BRONZE_MEMBER(684, 14, 1),
    SILVER_MEMBER(685, 14, 1),
    GOLD_MEMBER(686, 14, 0),
    PLATINUM_MEMBER(687, 14, 1),
    DIAMOND_MEMBER(688, 14, 1),
    SUPPORT(689),
    YOUTUBER(690, 18, 1),
    RUBY_MEMBER(691, 14, 1),
    MANAGER(692, 20, 1),
    SAPPHIRE(693),
    EMERALD(694),
    ONYX(695),
    CRYSTAL(1141, 12, 0),
    HEAD_MODERATOR(1032, 16, 0),
    SUPER_ADMIN(1033, 16, 0),
    ;

    /**
     * The crown sprite ID.
     */
    private int crownId;

    /**
     * The draw offset in the X coordinate.
     */
    private int drawOffsetX;

    /**
     * The draw offset in the Y coordinate.
     */
    private int drawOffsetY;

    PlayerRights(int crownId) {
        this.crownId = crownId;
        this.drawOffsetX = 17;
        this.drawOffsetY = 1;
    }

    PlayerRights(int crownId, int drawOffsetX, int drawOffsetY) {
        this.crownId = crownId;
        this.drawOffsetX = drawOffsetX;
        this.drawOffsetY = drawOffsetY;
    }

    public int getCrownId() {
        return crownId;
    }

    public int getDrawOffsetX() {
        return drawOffsetX;
    }

    public int getDrawOffsetY() {
        return drawOffsetY;
    }

    public boolean isHighStaff() {
        return this == ADMINISTRATOR || this == SUPER_ADMIN || this == MANAGER || this == OWNER || this == DEVELOPER;
    }

    public static PlayerRights get(int rights) {
        if (rights > 14) {
            rights -= 2;
        }
        if (rights >= values().length) {
            if (Configuration.LOCALHOST) {
                throw new IllegalArgumentException("Player rights with index " + rights + " does not exist.");
            } else {
                return PLAYER;
            }
        }
        return values()[rights];
    }
}
