package com.simplicity.client.content.login;

import java.util.ArrayList;

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
	
	public static final int PROFILES_X = 241;
	public static final int PROFILES_Y = 395;
	
    public static boolean profileHover(int index, int mouseX, int mouseY) {
    	int baseX = PROFILES_X + 24 + (index * 95);
    	int baseY = PROFILES_Y;
    	
    	return mouseX >= baseX && mouseX <= baseX + 38 && mouseY >= baseY && mouseY <= baseY + 38;
    }
    
    public static boolean profileDeleteHover(int index, int mouseX, int mouseY) {
    	int baseX = PROFILES_X + 38 + (index * 95);
    	int baseY = PROFILES_Y + 29;
    	
    	return mouseX >= baseX && mouseX <= baseX + 10 && mouseY >= baseY && mouseY <= baseY + 10;
    }


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
			String encoded = StringUtils.base64decode(StringUtils.base64encode(password));
			
			return StringUtils.xorMessage(encoded, "yT4eHQk");
		}
		
	}
}
