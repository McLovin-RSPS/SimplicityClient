package com.simplicity;

public class Configuration {

	/**
	 * Connection information
	 */
	public final static boolean LOCALHOST = false;
	public final static boolean BETA = true;
	public static String HOST = LOCALHOST ? "127.0.0.1" : BETA ? "beta.simplicity-data.com" : "149.56.28.66";
	public static int PORT = 43594;

	/**
	 * Client information
	 */
	public final static String CLIENT_NAME = "Simplicity RSPS - The Biggest Pre-EOC Server 2021";
	public static final boolean SEND_HASH = true;
	public static boolean upscaling = true;
	public static final boolean DISCO_ITEMS = false;

	/**
	 * Misc.
	 */
	public final static String JAGGRAB_HOST = "127.0.0.1";
	public static final boolean JAGCACHED_ENABLED = false;

	public static final boolean debuggingModels = true;

	/**
	 * The NPC bits. 12 = 317/377 14 = 474+ 16 = 600+
	 */
	public final static int NPC_BITS = 18;

	public static final int[] packetSizes = { 0, 0, -2, 1, 6, 0, 0, 0, 4, 0, // 0
			0, 2, -1, 1, 1, -1, 1, 0, 0, 0, // 10
			0, 0, 0, 0, 1, 0, 0, -1, 1, 1, // 20
			0, 0, 0, 0, -2, 4, 3, 0, 4, 0, // 30
			0, 0, 0, 0, 7, 8, 0, 6, 0, 0, // 40
			10, 0, 0, -2, 3, 0, 0, 0, 0, 0, // 50
			-2, 1, 0, 0, 2, -2, 0, 0, 0, 0, // 60
			6, 5, 2, 4, 2, 4, -2, 0, 0, 4, // 70
			0, -2, 0, 0, 7, 2, 1, 6, 6, 0, // 80
			2, 2, 0, 0, 0, 0, 0, 4, 0, 1, // 90
			2, 2, 8, 1, -1, 4, 1, 0, 1, 0, // 100
			1, 1, 1, 1, 2, 1, 0, 15, 0, 0, // 110
			0, 4, 4, -1, 9, 0, -2, 1, 0, 0, // 120 // 9
			-1, 0, 0, 0, 9, 0, 0, 0, 0, 0, // 130
			3, 10, 2, 0, 0, 0, 0, 14, 0, 0, // 140
			0, 6, 7, 3, 0, 0, 3, 0, 0, 0, // 150
			4, 5, 0, 0, 2, 0, 6, 0, 0, 0, // 160
			// 0, 3, /*0*/ -1, 0, 5, 7, 10, 6, 5, 1, //170
			0, 3, -2, -2, 5, 5, 10, 6, 5, -2, // 170
			0, 28, 0, 0, 0, 2, 0, -1, 0, 0, // 180
			0, 0, 0, 0, 0, 2, -1, 0, -1, 0, // 190
			4, 0, 0, 0, 0, -1, 3, 10, 6, 4, // 200
			0, 0, 0, 0, -1, 7, 0, -2, 2, 0, // 210
			0, 1, -2, -2, 0, 0, 0, 0, 0, 0, // 220
			8, 8, 5, 0, 0, 0, 0, 0, 3, 0, // 230
			2, -2, 0, 0, -1, 0, 6, 0, 6, 3, // 240
			-1, 0, 0, -1, 6, 0, 0// 250

	};

	static {
		packetSizes[246] = 8;
		packetSizes[245] = -2;
	}
	
    /**
     * Exp counter
     */
    public static boolean xpCounterOpen = true;
    public static int xpDropsPosition = 0;
    public static int xpCounterSize = 2;
    public static int xpDropsSpeed = 0;
    public static int xpCounterType = 0;
    public static int xpCounterProgress = 1;
    public static int xpDropsColour = 0xffffff;
    public static boolean xpDropsGroup = true;
    
    /**
     * Indicates whether tooltips on hover are enabled or not.
     */
	public static boolean enableTooltipHover;
	
    /**
     * Indicates whether osrs hitmarkers are enabled or not.
     */
	public static boolean enableOldHitmarkers;
	
    /**
     * Indicates whether custom cursors are enabled or not.
     */
	public static boolean enableCursors = true;
	
    /**
     * Indicates whether old gameframe is enabled or not.
     */
	public static boolean enableOldFrame;
	
    /**
     * Indicates whether chat censor is enabled or not.
     */
	public static boolean enableCensor;

    /**
     * Indicates whether skill status bars are enabled or not.
     */
	public static boolean enableSkillStatusBars = false;
	
	/**
	 * Indicates whether bounty target is enabled or not.
	 */
	public static boolean enableBountyTarget = true;
	
	/**
	 * Indicates whether moderation menu is enabled or not.
	 */
	public static boolean enableModerationMenu = true;
	
	/**
	 * Indicates whether ammunition overlay is enabled or not.
	 */
	public static boolean enableAmmunitionOverlay = false;
	
	/**
	 * Indicates whether kdr overlay is enabled or not.
	 */
	public static boolean enableKDROverlay = false;
	
	/**
	 * Indicates whether new hp bars are enabled or not.
	 */
	public static boolean enableNewHpBars = true;
	
	/**
	 * Indicates whether oldschool frame is enabled or not.
	 */
	public static boolean enableOldschoolFrame = false;

	public static long lastModerationToggleTime; //todo draw text of change or something
}
