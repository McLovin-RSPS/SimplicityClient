package com.simplicity.client.cache.definitions.custom;

import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;

/**
 * An enumerated type that represents a custom recolored item.
 * 
 * @author Blake
 *
 */
public enum CustomRecolor {

	BLACK_DRAGONSTONE_HELM(56000, 54034, "Dragonstone full helm", new int[] { 36252, 36257, 36133, 37165, 43059, 43067 }, new int[] { 15, 15, 15, 15, 15, 15 }),
	BLACK_DRAGONSTONE_PLATE(56001, 54037, "Dragonstone platebody", new int[] { 36252, 36257, 36133, 37165, 43059, 43067 }, new int[] { 15, 15, 15, 15, 15, 15 }),
	BLACK_DRAGONSTONE_LEGS(56002, 54040, "Dragonstone platelegs", new int[] { 36252, 36257, 36133, 37165, 43059, 43067 }, new int[] { 15, 15, 15, 15, 15, 15 }),
	BLACK_DRAGONSTONE_BOOTS(56003, 54043, "Dragonstone boots", new int[] { 36252, 36257, 36133, 37165, 43059, 43067 }, new int[] { 15, 15, 15, 15, 15, 15 }),
	BLACK_DRAGONSTONE_GLOVES(56004, 54046, "Dragonstone gloves", new int[] { 36252, 36257, 36133, 37165, 43059, 43067 }, new int[] { 15, 15, 15, 15, 15, 15 }),
	
	GROUP_IRONMAN_HELM(56005, 42810, "Group ironman helm", new int[] { 24, 37, 53, 57, 70, 78, 86, 90, 99, 107, 111, 115  }, new int[] { 16022, 16, 30, 34, 16022, 16021, 16023, 16022, 16026, 16026, 16022, 16022 }),
	GROUP_IRONMAN_PLATE(56006, 42811, "Group ironman plate", new int[] { 49, 111, 57, 78, 86, 41, 107, 70  }, new int[] { 16026, 16026, 16026, 16026, 16026, 24, 16026, 16025 }),
	GROUP_IRONMAN_LEGS(56007, 42812, "Group ironman legs", new int[] { 24, 37, 49, 53, 57, 70, 78, 86, 90, 94, 99, 103, 107, 111, 115  }, new int[] { 16026, 16026, 16026, 16026, 16026, 16026, 16026, 16023, 16026, 16026, 16026, 16026, 16022, 16026, 16026 }),
	
	
	RED_DRAGONSTONE_HELM(56008, 54034, "Dragonstone full helm", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133, 52122 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15, 0 }),
	RED_DRAGONSTONE_PLATE(56009, 54037, "Dragonstone platebody", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133, 52122 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15, 0 }),
	RED_DRAGONSTONE_LEGS(56010, 54040, "Dragonstone platelegs", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133, 52122 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15, 0 }),
	RED_DRAGONSTONE_BOOTS(56011, 54043, "Dragonstone boots", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133, 52122 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15, 0 }),
	RED_DRAGONSTONE_GLOVES(56012, 54046, "Dragonstone gloves", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133, 52122 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15, 0 }),
	
	DON_JR_SANG_X(13991, 52323, "Jesus's staff", 
			new int[] { 836, 156, 3127, 142, 3140, 24, 20, 28, 836, 37, 49, 41, 57, 33, 16, 284 }, 
			new int[] { 37631, 57300, 129770, 129770, 57300, 57300, 57300, 57300, 57300, 57300, 129770, 129770, 129770, 57300, 57300, 57300 }),
	
	EMAN_ANGELIC_CAPE(11206, 11614, "USA Angelic Cape", 
			new int[]{ 40023, 40036, 1822, 36, 25, 34243, 9230, 40040, 10348, 48, 34251, 0, 43335, 6218, 23, 11013 },
            new int[]{ 1024, 1024, 1024, 933, 933, 1024, 933, 1024, 1024, 933, 1024, 1024, 1024, 1024, 933, 1024 }),
	
	EMAN_SCYTHE_X(11207, 15000, "USA Dragonstone Scythe",
			new int[] { 784, 790, 796, 536, 61, 78, 49 },
            new int[] {  933, 933, 933, 933, 0, 933, 0  }),
	
	DARK_PRIMAL_HELM(3068, 16711, "Dark Primal Full Helm",
			new int[] { 2838, 2848, 2866, 2048, 2855, 7731, 7757, 7694, 4110, 2049,
						7753, 7718, 7372, 7712, 7403, 7713, 7726, 7743, 7721, 7684,
						7738, 7716, 7680, 7698, 7711, 7733, 7693, 7706, 7728 },
			new int[] { 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
						2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
						2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049 }),
	
	DARK_PRIMAL_BODY(3067, 17259, "Dark Primal Platebody",
			new int[] { 2828, 2840, 2848, 2833, 2834, 2845, 2824, 2826, 2855, 2830,
						2827, 2888, 2846, 2847, 2817, 2866, 2880, 7703, 7710, 7731,
						2822, 7694, 2816, 2862, 2823, 2844, 2853, 2831, 2839 },
			new int[] { 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
						2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
						2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049 }),
	
	DARK_PRIMAL_LEGS(3066, 16689, "Dark Primal Platelegs",
			new int[] { 2830, 7694, 7731, 7710, 7703, 2816, 2880, 2844, 7700, 2866,
						2848, 2853, 2839, 2925, 2857, 2840, 2836, 2822, 2832, 2828,
						2876, 2823, 2827, 2833, 2847, 2842, 7724, 7721, 7715, 7735,
						7714, 7740, 7719 },
			new int[] { 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
						2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
						2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
						2049, 2049, 2049 }),
	
	DARK_PRIMAL_KITE(3065, 17361, "Dark Primal Kiteshield",
			new int[] { 1567, 1559, 1549, 1557, 6694, 6704, 1539, 6729, 6719, 1562,
						1572, 5403, 5398, 5388, 5393, 1550, 5380, 5385, 5424, 5408,
						1584, 1554, 1544, 1574, 5378, 5376, 1540, 5383, 1589, 5411,
						5406, 5429, 5413, 1579, 5414, 5396, 5401, 5419},
            new int[] { 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
            			2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
            			2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049,
            			2049, 2049, 2049, 2049, 2049, 2049, 2049, 2049 }),
	
	DARK_PRIMAL_BOOTS(3064, 16359, "Dark Primal Boots",
			new int[] { 2830, 2853, 2840, 2836 },
            new int[] {  2049, 2049, 2049, 2049 }),
	
	DARK_PRIMAL_GLOVES(3063, 16293, "Dark Primal Gauntlents",
			new int[] { 2839, 2848, 2826 },
            new int[] {  2049, 2049, 2049 }),
	
	BLACK_BUNNY_EARS(56013, 1037, "Black Bunny ears", new int[] { 220 }, new int[] { 10 }),
	BLACK_BUNNY_TOP(56014, 43663, "Black Bunny top", new int[] { 2370, 2378, 2382 }, new int[] { 10, 10, 10 }),
	BLACK_BUNNY_LEGS(56015, 43664, "Black Bunny legs", new int[] { 2370, 2378, 2382 }, new int[] { 10, 10, 10 }),
	BLACK_BUNNY_PAWS(56016, 43665, "Black Bunny paws", new int[] { 2370, 2378, 2382 }, new int[] { 10, 10, 10 }),
	BLACK_BUNNY_BOOTS(56017, 43182, "Black Bunny boots", new int[] { 2370, 2378, 2382 }, new int[] { 10, 10, 10 }),
	EVIL_BUNNY_BOOTS(20208, 43182, "Evil Bunny boots", new int[] { 2370, 2378, 2382 }, new int[] { 16026, 16026, 16026 }),
	
	SCYTHE_OF_VITUR_INFERNAL(56018, 22011, "Scythe of Vitur Infernal", new int[] { 69 }, new int[] { 59 })
	
	
	;
	/**
	 * Constructs a new {@link CustomRecolor}.
	 * 
	 * @param itemId           The item id.
	 * @param copyFromId       The id to copy from.
	 * @param name             The name.
	 * @param editedModelColor The edited model colors.
	 * @param newModelColor    The new model colors.
	 */
	private CustomRecolor(int itemId, int copyFromId, String name, int[] editedModelColor, int[] newModelColor) {
		ItemDefinition def = new ItemDefinition();

		def.id = itemId;
		def.setDefaults();
		def.copy(ItemDefinition.forID(copyFromId));
		def.name = name;
		def.editedModelColor = editedModelColor;
		def.newModelColor = newModelColor;

		ItemDefinition.getCustomRecolors().put(itemId, def);
	}

}
