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
	
	
	RED_DRAGONSTONE_HELM(56005, 54034, "Dragonstone full helm", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15 }),
	RED_DRAGONSTONE_PLATE(56006, 54037, "Dragonstone platebody", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15 }),
	RED_DRAGONSTONE_LEGS(56007, 54040, "Dragonstone platelegs", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15 }),
	RED_DRAGONSTONE_BOOTS(56008, 54043, "Dragonstone boots", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15 }),
	RED_DRAGONSTONE_GLOVES(56009, 54046, "Dragonstone gloves", new int[] { 36252, 36257, 36133, 37165, 43059, 43067, 51111, 51133 }, new int[] { 933, 933, 933, 933, 933, 933, 0, 15 }),
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
