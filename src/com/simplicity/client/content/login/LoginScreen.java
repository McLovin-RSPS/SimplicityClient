package com.simplicity.client.content.login;

import java.util.ArrayList;

import com.simplicity.client.Client;
import com.simplicity.client.ClientSettings;
import com.simplicity.util.StringUtils;

public class LoginScreen {
	private static final int MAX_CHARACTERS = 3;
	
	public static boolean[] socialMediaState = new boolean[5];
	
	public static boolean[] deleteCharacterState = new boolean[MAX_CHARACTERS];

	public static final String[] SOCIAL_MEDIA = { "Facebook", "Twitter", "YouTube", "Twitch", "Discord" };

	public static ArrayList<CharacterFile> characters = new ArrayList<CharacterFile>();

	public static final String[] SOCIAL_MEDIA_LINKS = { "https://www.facebook.com/simplicityps", "", "http://www.youtube.com/channel/UCsQMC7RxvylgTL0jveRyjSw",
			"", "http://discord.gg/VJy7QAH", "" };


	public static boolean add(String username, String password, boolean save) {
		if (characters.size() == MAX_CHARACTERS) {
			return false;
		}
		for (CharacterFile c : characters) {
			if (c.username.equalsIgnoreCase(username)) {
				return false;
			}
		}
		
		CharacterFile file = new CharacterFile(username, password);
		characters.add(file);
		
		if (save) {
			ClientSettings.save();
		}
		
		return true;
	}
	
	public static void delete(String username) {
		ArrayList<CharacterFile> chars = (ArrayList<CharacterFile>) characters.clone();
		for (int i = 0; i< chars.size(); i++) {
			CharacterFile c = chars.get(i);
			if (c.username.equalsIgnoreCase(username)) {
				characters.remove(i);
				characters.trimToSize();
				ClientSettings.save();
				break;
			}
		}
	}
	
	public static final class CharacterFile {
		private String username;
		private String password;

		public CharacterFile(String username, String password) {
			this.username = username;
			this.password = StringUtils.xorMessage(password, "yT4eHQk");
		}
		
		public String getUsername() {
			return username;
		}
		
		public String getPassword() {
			if (password == null) {
				System.out.println("password is null, but username: " + username);
			}
			String encoded = StringUtils.base64decode(StringUtils.base64encode(password));
			
			return StringUtils.xorMessage(encoded, "yT4eHQk");
		}
		
	}
}
