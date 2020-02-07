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
	BLACK_DRAGONSTONE_GLOVES(56004, 54046, "Dragonstone gloves", new int[] { 36252, 36257, 36133, 37165, 43059, 43067 }, new int[] { 15, 15, 15, 15, 15, 15 }),;
	
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
