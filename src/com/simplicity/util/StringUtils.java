package com.simplicity.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class StringUtils {

	public static String capitalizeFirst(String name) {
		if (name.length() < 1)
			return "";
		StringBuilder builder = new StringBuilder(name.length());
		char first = Character.toUpperCase(name.charAt(0));
		builder.append(first).append(name.toLowerCase().substring(1));
		return builder.toString();
	}
	
	/**
	 * Capitalized all words split by a space char.
	 * @param name	The string to format.
	 */
	public static String capitalizeWords(String name) {
		StringBuilder builder = new StringBuilder(name.length());
		String[] words = name.split("\\s");
		for(int i = 0, l = words.length; i < l; ++i) {
			if(i > 0)
				builder.append(" ");    
		
			builder.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));

		}
		
		return builder.toString();
	}

	public static String insertCommasToNumber(String number) {
		return number.length() < 4 ? number : insertCommasToNumber(number.substring(0, number.length() - 3)) + "," + number.substring(number.length() - 3, number.length());
	}
	
	private static final String DEFAULT_ENCODING = "UTF-8";

	public static String base64encode(String text) {
		try {
			return Base64.getEncoder().encodeToString(text.getBytes(DEFAULT_ENCODING));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String base64decode(String text) {
		try {
			return new String(Base64.getDecoder().decode(text), DEFAULT_ENCODING);
		} catch (IOException e) {
			return null;
		}
	}
	
	public static String xorMessage(String message, String key) {
		try {
			if (message == null || key == null)
				return null;

			char[] keys = key.toCharArray();
			char[] mesg = message.toCharArray();

			int ml = mesg.length;
			int kl = keys.length;
			char[] newmsg = new char[ml];

			for (int i = 0; i < ml; i++) {
				newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
			}

			return new String(newmsg);
		} catch (Exception e) {
			return null;
		}
	}

}
