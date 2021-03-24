package com.simplicity.tools.util;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents a mapping for model recoloring.
 * 
 * @author Blake
 *
 */
public class ModelColorMapping {

	/**
	 * The original colors.
	 */
	private int[] originalFaceColors;

	/**
	 * The edited colors.
	 */
	private Map<Integer, Integer> editedFaceColors;

	/**
	 * Constructs a new {@link ModelColorMapping}.
	 * 
	 * @param face_color The face colors.
	 */
	public ModelColorMapping(int[] face_color) {
		originalFaceColors = new int[face_color.length];

		for (int i = 0; i < face_color.length; i++) {
			originalFaceColors[i] = face_color[i];
		}

		editedFaceColors = new HashMap<>();
	}

	/**
	 * Gets the original colors.
	 * 
	 * @return The original colors.
	 */
	public int[] getOriginalFaceColors() {
		return originalFaceColors;
	}

	/**
	 * Gets the new face colors with edits applied.
	 * 
	 * @return The new face colors.
	 */
	public int[] getNewFaceColors() {
		int[] newFaceColors = new int[originalFaceColors.length];

		for (int i = 0; i < originalFaceColors.length; i++) {
			int color = originalFaceColors[i];

			newFaceColors[i] = editedFaceColors.containsKey(color) ? editedFaceColors.get(color) : color;
		}

		return newFaceColors;
	}

	/**
	 * Recolors the specified color.
	 * 
	 * @param original The original color.
	 * @param edited   The edited color.
	 */
	public void recolor(int original, int edited) {
		editedFaceColors.put(original, edited);
	}

}
