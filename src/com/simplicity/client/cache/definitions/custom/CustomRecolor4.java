package com.simplicity.client.cache.definitions.custom;

import com.simplicity.client.cache.definitions.ItemDefinition;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * An enumerated type that represents a custom recolored item.
 * 
 * @author Blake
 *
 **/

public enum CustomRecolor4 {

	PLACEHOLDER(0, 0, "PLACEHOLDER",
			new int[] {},
			new int[] {}),

	;

	private static int[] randomScytheColor() {
		List<Integer> colors = Arrays.asList(
				1017, 58356, 42879, 22527, 9087, 8063, 51199, 25
		);
		Random rand = new Random();
		int COLOR = colors.get(rand.nextInt(colors.size()));
		return new int[] { 1024, 1024, 1024, COLOR, 1024, COLOR, COLOR, 1024, 1024, 1024, COLOR, COLOR, 1024, COLOR };
	}

	private static int[] appendArr(int[] a, int e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}

	private static int[] getModifiedColors(int[] id, int change) {
		int[] colors = {};
		for(int i = 0; i < id.length; i++) {
			colors = appendArr(colors, modifyColor(id[i], 0, 0, 500));
		}
		System.out.println("Original: " + Arrays.toString(id));
		System.out.println("Modified: " + Arrays.toString(colors));
		return colors;
	}

	public static int modifyColor(int c, int hue, int sat, int bright) {
		Color colorRGB = ItemDefinition.RS2HSB_to_RGB_MODIFIED(c, hue, sat, bright);
		return ItemDefinition.RGB_to_RS2HSB(colorRGB.getRed(), colorRGB.getGreen(), colorRGB.getBlue());
	}

	private static int[] getCustomCapeColor(int main, int accent) {
		return new int[] { main, accent, main, main, accent, accent, main, accent, accent, main, main, main, accent, main, accent, accent };
		// return new int[] { main, main, main, accent, accent, main, accent, main, main, accent, main, main, main, main, accent, main };
	}

	private CustomRecolor4(int itemId, int copyFromId, String name, int[] editedModelColor, int[] newModelColor) {
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
