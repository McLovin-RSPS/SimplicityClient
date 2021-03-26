package com.simplicity.client.content;

import com.simplicity.Configuration;
import com.simplicity.client.AnimatedSprite;
import com.simplicity.client.Sprite;
import com.simplicity.client.SpriteLoader;

/**
 * Player rights enum that MUST be synced with servers rights.
 *
 * @author Gabriel || Wolfsdarker
 */
public enum PlayerRights {

    PLAYER(-1, PlayerRightsType.DEFAULT),
    MODERATOR(680, PlayerRightsType.STAFF),
    ADMINISTRATOR(681, PlayerRightsType.STAFF),
    OWNER(682, PlayerRightsType.STAFF),
    DEVELOPER(1159, 18, 0, PlayerRightsType.STAFF),
    BRONZE_MEMBER(684, 14, 1, PlayerRightsType.DONATOR),
    SILVER_MEMBER(685, 14, 1, PlayerRightsType.DONATOR),
    GOLD_MEMBER(686, 14, 0, PlayerRightsType.DONATOR),
    PLATINUM_MEMBER(687, 14, 1, PlayerRightsType.DONATOR),
    DIAMOND_MEMBER(688, 14, 1, PlayerRightsType.DONATOR),
    SUPPORT(689, PlayerRightsType.STAFF),
    YOUTUBER(1723, 18, 1, PlayerRightsType.STAFF),
    RUBY_MEMBER(691, 14, 1, PlayerRightsType.DONATOR),
    MANAGER(1034, 20, 1, PlayerRightsType.STAFF),
    SAPPHIRE(693, PlayerRightsType.DONATOR),
    EMERALD(694, PlayerRightsType.DONATOR),
    ONYX(695, PlayerRightsType.DONATOR),
    CRYSTAL(1141, 12, 0, PlayerRightsType.DONATOR),
    HEAD_MODERATOR(1032, 16, 0, PlayerRightsType.STAFF),
    SUPER_ADMIN(1033, 16, 0, PlayerRightsType.STAFF),
    MIDDLEMAN(1317, 16, 0, PlayerRightsType.DEFAULT),
    INFERNAL(AnimatedSprite.sprites[0], 20, -1, PlayerRightsType.DONATOR),
    WIKI_EDITOR(1781, 20, -1, PlayerRightsType.STAFF),

    IRON_MAN(712, PlayerRightsType.CUSTOM_GAME_MODE),
    ULTIMATE_IRON_MAN(711, PlayerRightsType.CUSTOM_GAME_MODE),
    HARDCORE_IRON_MAN(1142, PlayerRightsType.CUSTOM_GAME_MODE),
    GROUP_IRON_MAN(1316, PlayerRightsType.CUSTOM_GAME_MODE),
    REALISM(1727, 18, -2, PlayerRightsType.CUSTOM_GAME_MODE)

    ;
	
	/**
	 * The sprite.
	 */
	private Sprite sprite;

    /**
     * The draw offset in the X coordinate.
     */
    private int drawOffsetX;

    /**
     * The draw offset in the Y coordinate.
     */
    private int drawOffsetY;

    /**
     * Is this rank a donator?
     */
    private PlayerRightsType playerRightsType;

    PlayerRights(int crownId, PlayerRightsType playerRightsType) {
        this.sprite = crownId == -1 ? null : SpriteLoader.sprites[crownId];
        this.drawOffsetX = 17;
        this.drawOffsetY = 1;
        this.playerRightsType = playerRightsType;
    }

    PlayerRights(int crownId, int drawOffsetX, int drawOffsetY, PlayerRightsType playerRightsType) {
    	this.sprite = crownId == -1 ? null : SpriteLoader.sprites[crownId];
        this.drawOffsetX = drawOffsetX;
        this.drawOffsetY = drawOffsetY;
        this.playerRightsType = playerRightsType;
    }
    
    PlayerRights(Sprite sprite, int drawOffsetX, int drawOffsetY, PlayerRightsType playerRightsType) {
        this.sprite = sprite;
        this.drawOffsetX = drawOffsetX;
        this.drawOffsetY = drawOffsetY;
        this.playerRightsType = playerRightsType;
    }
    
    public Sprite getSprite() {
    	return sprite;
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
        if(rights == 60) {
            return IRON_MAN;
        }
        if(rights == 61) {
            return ULTIMATE_IRON_MAN;
        }
        if(rights==62) {
        	return HARDCORE_IRON_MAN;
        }
        if(rights==63) {
        	return GROUP_IRON_MAN;
        }
        if (rights == 64) {
            return REALISM;
        }
        if (rights >= values().length) {
            if (Configuration.LOCALHOST) {
                //throw new IllegalArgumentException("Player rights with index " + rights + " does not exist.");
                return PLAYER;
            } else {
                return PLAYER;
            }
        }
        return values()[rights];
    }

    public void setPlayerRightsType(PlayerRightsType playerRightsType) {
        this.playerRightsType = playerRightsType;
    }

    public boolean isStaff() {
        return this.playerRightsType == PlayerRightsType.STAFF;
    }

    public boolean isDonator() {
        return this.playerRightsType == PlayerRightsType.DONATOR;
    }

    public boolean isCustomGameMode() {
        return this.playerRightsType == PlayerRightsType.CUSTOM_GAME_MODE;
    }
}
