package com.simplicity.util;

import com.simplicity.client.RSFontSystem;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MiscUtils {

	/**
	 * Methods from the internet used for counter progress bar
	 */
	public static int mixColors(Color color1, Color color2, double percent) {
		double inverse_percent = 1.0 - percent;
		int redPart = (int) (color1.getRed() * percent + color2.getRed() * inverse_percent);
		int greenPart = (int) (color1.getGreen() * percent + color2.getGreen() * inverse_percent);
		int bluePart = (int) (color1.getBlue() * percent + color2.getBlue() * inverse_percent);
		return getIntFromColor(redPart, greenPart, bluePart);
	}
	
    private static int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }
    
	public static String formatCoins(final long quantity) {
		if (quantity >= 10000 && quantity < 10000000) {
			return quantity / 1000 + "K";
		} else if (quantity >= 10000000 && quantity <= Integer.MAX_VALUE) {
			return quantity / 1000000 + "M";
		} else if (quantity > Integer.MAX_VALUE && quantity <= Long.MAX_VALUE) {
			return quantity / 10000000 + "B";
		} else {
			return Long.toString(quantity);
		}
	}

	public static int square(int num) {
		return num * num;
	}

	public static boolean isInCircleArea(int radius, int circleCenterX, int circleCenterY, int x, int y) {
		return square(radius) >= square((x - circleCenterX)) + square((y - circleCenterY));
	}

    public static int ensureRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
    
	public static double max(double... n) {
		int i = 0;
		double max = n[i];

		while (++i < n.length) {
			if (n[i] > max) {
				max = n[i];
			}
		}

		return max;
	}

	public static String[] getSplitString(RSFontSystem font, String name, int maxWidth, int maxLines) {
		int totalWidth = font.getTextWidth(name);

		if (totalWidth > maxWidth) {
			String[] split = name.split(" ", maxLines);

			for (int idx = 0; idx < split.length; idx++) {
				String str = split[idx];

				if (str == null) {
					continue;
				}

				if (idx >= maxLines - 1 && str.length() > 10) {
					split[idx] = str.substring(0, 10) + "...";
				}
			}

			return split;
		} else {
			return new String[] { name };
		}
	}

	public static String capitalizeFirstLetter(String s) {
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

	public static String capitalize(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (i == 0) {
				s = String.format("%s%s", Character.toUpperCase(s.charAt(0)), s.substring(1));
			}
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				if (i + 1 < s.length()) {
					s = String.format("%s%s%s", s.subSequence(0, i + 1), Character.toUpperCase(s.charAt(i + 1)), s.substring(i + 2));
				}
			}
		}
		return s;
	}

	private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList(
			'A', 'E', 'I', 'O', 'U'
	));

	public static String getAOrAn(String s) {
		return startsWithVowel(s) ? "an" : "a";
	}

	public static boolean startsWithVowel(String word) {
		return VOWELS.contains(Character.toUpperCase(word.charAt(0)));
	}
}
