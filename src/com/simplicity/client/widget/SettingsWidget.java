package com.simplicity.client.widget;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.ClientSettings;
import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.content.CustomisableHotKeys;
import com.simplicity.client.content.dropdownmenu.DropDownAction;
import com.simplicity.client.content.dropdownmenu.DropDownMenu;

/**
 * A class that handles all of the client's settings.
 * 
 * @author Blake
 *
 */
public class SettingsWidget extends RSInterface {
	
	public static final int DISPLAY_BUTTON = 46_000;
	public static final int AUDIO_BUTTON = 46_001;
	public static final int CHAT_BUTTON = 46_002;
	public static final int CONTROL_BUTTON = 46_003;
	
	public static final int PLAYER_ATTACK_DROPDOWN = 46_004;
    public static final int NPC_ATTACK_DROPDOWN = 46_005;
	
	private static final int DISPLAY_SETTINGS = 20_000;
	private static final int AUDIO_SETTINGS = DISPLAY_SETTINGS + 100;
	private static final int CHAT_SETTINGS = AUDIO_SETTINGS + 100;
	private static final int CONTROL_SETTINGS = CHAT_SETTINGS + 100;
	
	public static final int FIXED = DISPLAY_SETTINGS + 1;
	public static final int RESIZABLE = DISPLAY_SETTINGS + 2;
	public static final int ADVANCED = DISPLAY_SETTINGS + 4;
	
	public static final int MOUSE_CAMERA = CONTROL_SETTINGS + 1;
	public static final int FOLLOWER_OPTIONS = CONTROL_SETTINGS + 3;
	public static final int SHIFT_CLICK_DROP = CONTROL_SETTINGS + 5;
	public static final int KEYBINDING = CONTROL_SETTINGS + 7;
	
	public static final int MOUSE_BUTTONS = 913;
	public static final int CHAT_EFFECTS = 915;
	public static final int SPLIT_PRIVATE_CHAT = 957;
	
	private static final int ADVANCED_OPTIONS = 56_000;
	
	private static Map<Integer, Setting> TOGGLES = new LinkedHashMap<Integer, Setting>();
	
	public static void unpack(TextDrawingArea[] tda) {
		init();
		optionsMain(tda);
		displaySettings(tda);
		audioSettings(tda);
		chatSettings(tda);
		controlSettings(tda);
		advancedOptions(tda);
	}
	
	public static void optionsMain(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(904);
		RSInterface energy = interfaceCache[149];
		energy.disabledColor = 0xff9933;
		addSpriteLoader(962, 1221);
		addSpriteLoader(905, 1222);
		addSpriteLoader(907, 1223); // zoom
		addSpriteLoader(909, 88);
		addConfigButtonWSpriteLoader(12464, 904, 1219, 1220, 40, 40, "Toggle-Accept Aid", 0, 5, 427);
		addSpriteLoader(949, 1248);
		
		addConfigButtonWSpriteLoader(152, 904, 1219, 1220, 40, 40, "Toggle-run", 1, 5, 173);
		addSpriteLoader(947, 1247);
		
		configHoverButton(DISPLAY_BUTTON, "Display", 1231, 1232, 1233, 1234, true, new int[] { AUDIO_BUTTON, CHAT_BUTTON, CONTROL_BUTTON });
		configHoverButton(AUDIO_BUTTON, "Audio", 1235, 1236, 1237, 1238, false, new int[] { DISPLAY_BUTTON, CHAT_BUTTON, CONTROL_BUTTON });
		configHoverButton(CHAT_BUTTON, "Chat", 1239, 1240, 1241, 1242, false, new int[] { DISPLAY_BUTTON, AUDIO_BUTTON, CONTROL_BUTTON });
		configHoverButton(CONTROL_BUTTON, "Controls", 1243, 1244, 1245, 1246, false, new int[] { DISPLAY_BUTTON, AUDIO_BUTTON, CHAT_BUTTON });
        
		int child = 0;
		
		tab.totalChildren(11);
		tab.child(child++, 962, 3, 41);
		tab.child(child++, 12464, 7, 223 - 4);
		tab.child(child++, 949, 12, 228 - 4);
		tab.child(child++, 152, 54, 223 - 4);
		tab.child(child++, 947, 65, 226 - 4);
		tab.child(child++, 149, 59, 243 - 4);
		tab.child(child++, DISPLAY_SETTINGS, 0, 0);
		
        int x = 0;
        
        for (int i = 0; i < 4; i++, x += 46) {
            tab.child(child++, DISPLAY_BUTTON + i, 6 + x, 0);
        }
	}
	
	private static void displaySettings(TextDrawingArea[] tda) {
		int id = DISPLAY_SETTINGS;

		RSInterface tab = addTabInterface(id++);
		
		tab.totalChildren(9);
		
		int child = 0;
		
		configHoverButton(id, "Fixed mode", 1225, 1225, 1224, 1228, true, id + 1);
        configHoverButton(id + 1, "Resizable mode", 1226, 1229, 1227, 1227, false, id);
		tab.child(child++, id, 28, 124 - 5);
		tab.child(child++, id + 1, 106, 124 - 5);
		id+=2;
		
		
		addSpriteLoader(id, 1230);
		addHoverText(id + 1, "Advanced Options", "Advanced Options", tda, 1, 0xff981f, true, true, 140, 30);
		tab.child(child++, id, 28, 180 - 5);
		tab.child(child++, id + 1, 28, 187 - 5);
		id += 2;
		
		tab.child(child++, 905, 13, 88 - 5);
		tab.child(child++, 906, 48, 100 - 5);
		tab.child(child++, 908, 80, 100 - 5);
		tab.child(child++, 910, 112, 100 - 5);
		tab.child(child++, 912, 144, 100 - 5);
	}
	
	private static void audioSettings(TextDrawingArea[] tda) {
		int id = AUDIO_SETTINGS;

		RSInterface tab = addTabInterface(id++);
		
		tab.totalChildren(12);
		
		int child = 0;
		
		addHDSprite(907, 378, 378);
		addHDSprite(909, 379, 379);
		
		addConfigButton(930, 904, 395, 396, 26, 16, "Music Off", 4, 5, 168);
		addConfigButton(931, 904, 397, 398, 26, 16, "Music Level-1", 3, 5, 168);
		addConfigButton(932, 904, 399, 400, 26, 16, "Music Level-2", 2, 5, 168);
		addConfigButton(933, 904, 401, 402, 26, 16, "Music Level-3", 1, 5, 168);
		addConfigButton(934, 904, 403, 404, 24, 16, "Music Level-4", 0, 5, 168);
		addConfigButton(941, 904, 395, 396, 26, 16, "Sound Effects Off", 4, 5, 169);
		addConfigButton(942, 904, 397, 398, 26, 16, "Sound Effects Level-1", 3, 5, 169);
		addConfigButton(943, 904, 399, 400, 26, 16, "Sound Effects Level-2", 2, 5, 169);
		addConfigButton(944, 904, 401, 402, 26, 16, "Sound Effects Level-3", 1, 5, 169);
		addConfigButton(945, 904, 403, 404, 24, 16, "Sound Effects Level-4", 0, 5, 169);

		int x = 0;
		int y = 2;

		tab.child(child++, 907, 14 + x, 55 + y);
		tab.child(child++, 930, 49 + x, 61 + y);
		tab.child(child++, 931, 75 + x, 61 + y);
		tab.child(child++, 932, 101 + x, 61 + y);
		tab.child(child++, 933, 127 + x, 61 + y);
		tab.child(child++, 934, 151 + x, 61 + y);
		tab.child(child++, 909, 13 + x, 99 + y);
		tab.child(child++, 941, 49 + x, 104 + y);
		tab.child(child++, 942, 75 + x, 104 + y);
		tab.child(child++, 943, 101 + x, 104 + y);
		tab.child(child++, 944, 127 + x, 104 + y);
		tab.child(child++, 945, 151 + x, 104 + y);
		
		//addSpriteLoader(id, 1230);
		
		//tab.child(child++, id, 0, -3);
	}
	
	private static void chatSettings(TextDrawingArea[] tda) {
		int id = CHAT_SETTINGS;

		RSInterface tab = addTabInterface(id++);
		
		tab.totalChildren(4);
		
		int child = 0;
		
		addSpriteLoader(953, 1250);
		configButton(CHAT_EFFECTS, "Toggle-Chat Effects", 1219, 1220);

		addSpriteLoader(951, 1249);
		configButton(SPLIT_PRIVATE_CHAT, "Toggle-Split Private Chat", 1219, 1220);
		
		tab.child(child++, 915, 20, 60);
		tab.child(child++, 953, 24, 67);
		tab.child(child++, 957, 20 + 50, 60);
		tab.child(child++, 951, 24 + 50, 67);
	}
	
	private static void controlSettings(TextDrawingArea[] tda) {
		int id = CONTROL_SETTINGS;

		RSInterface tab = addTabInterface(id++);
		
		tab.totalChildren(14);
		
		int child = 0;
		
		int yPos = 48;
		
		configButton(MOUSE_BUTTONS, "Toggle number of Mouse Buttons", 1219, 1220);
		addSpriteLoader(955, 1251);
		tab.child(child++, 913, 20, yPos);
		tab.child(child++, 955, 24, yPos + 7);
		
		configButton(id, "Toggle Mouse Camera", 1219, 1220);
		addSpriteLoader(id + 1, 1252);
		tab.child(child++, id, 24 + 50, yPos);
		tab.child(child++, id + 1, 27 + 50, yPos + 4);
		id += 2;
		
		configButton(id, "Toggle Follower options priority", 1219, 1220);
		addSpriteLoader(id + 1, 1253);
		tab.child(child++, id, 24 + 104, yPos);
		tab.child(child++, id + 1, 27 + 104, yPos + 4);
		id += 2;
		
		configButton(id, "Toggle Shift Click Drop", 1219, 1220);
		addSpriteLoader(id + 1, 1254);
		tab.child(child++, id, 24 + 14, yPos + 43);
		tab.child(child++, id + 1, 28 + 14, yPos + 4 + 43);
		id += 2;
		
		addButtonWSpriteLoader(id, 1219, "Keybinding");
		addSpriteLoader(id + 1, 1043);
		tab.child(child++, id, 24 + 15 + 70, yPos + 43);
		tab.child(child++, id + 1, 28 + 18 + 70, yPos + 8 + 43);
		id += 2;
		
		new DropDownMenu(PLAYER_ATTACK_DROPDOWN, 166, 0x3B3629, 0x695B36, 0, 1, 166,
                new DropDownAction[]{new DropDownAction(0, "Depends on combat levels"), new DropDownAction(1, "Always right-click"),
                        new DropDownAction(2, "Left-click where available"), new DropDownAction(3, "Hidden")});
		
        addText(id, "Player 'Attack' options:", tda, 1, 0xfe971e, false, true);

		new DropDownMenu(NPC_ATTACK_DROPDOWN, 166, 0x3B3629, 0x695B36, 0, 1, 166,
                new DropDownAction[]{new DropDownAction(0, "Depends on combat levels"), new DropDownAction(1, "Always right-click"),
                        new DropDownAction(2, "Left-click where available"), new DropDownAction(3, "Hidden")});
        
        addText(id + 1, "NPC 'Attack' options:", tda, 1, 0xfe971e, false, true);
        
        setBounds(id, 12, 118 + 15, child++, tab);
        setBounds(NPC_ATTACK_DROPDOWN, 12, 174 + 15, child++, tab);
        setBounds(id + 1, 12, 157 + 15, child++, tab);
        setBounds(PLAYER_ATTACK_DROPDOWN, 12, 136 + 15, child++, tab);
	}
	
	public static void init() {
		int button = ADVANCED_OPTIONS + 12;
		
		for (Setting setting : Setting.values()) {
			TOGGLES.put(button, setting);
			button += 4;
		}
	}
	
	private static void advancedOptions(TextDrawingArea[] tda) {
		int id = ADVANCED_OPTIONS;
		
		RSInterface tab = addInterface(id++);
		
		tab.totalChildren(7);
		
		int child = 0;
		
		addSpriteLoader(id, 948);
		tab.child(child++, id, 6, 9);
		id++;
		
		addText(id, "Advanced Options", tda, 2, 0xff9933);
		tab.child(child++, id, 194, 13);
		id++;
		
		addText(id, "If the game runs slowly on your computer, please try adjusting these settings.", tda, 0, 0xebe0bc);
		tab.child(child++, id, 58, 42);
		id++;
		
		boolean scrollbar = TOGGLES.size() > 21;

		addHoverText(id, "Restore defaults", "Restore defaults", tda, 1, 0xebe0bc, true, true, 100, 16);
		tab.child(child++, id, 205, scrollbar ? 64 : 62);
		id++;
		
		addCloseButton(id, id + 1, id + 2, true);
        tab.child(child++, id, 481, 12);
        tab.child(child++, id + 1, 481, 12);
		id+=3;
		
		tab.child(child++, id, scrollbar ? 6 : 13, scrollbar ? 90 : 86);
		
		/** Scroll start **/
		child = 0;
		tab = addInterface(id++);
		
		tab.totalChildren(TOGGLES.size() * 4);
		tab.height = 228;
		tab.scrollMax = scrollbar ? 310 : tab.height;
		tab.width = 477;
		
		int x = 22;
		int y = 7;
		
		int width = 153;
		
		int height = 29;
		
		int col = 0;
		for (Setting s : TOGGLES.values()) {
			addRectangle(id, 0, 0x80786d, false, width + 3, height + 4);
			addRectangle(id + 1, 255, 0x383530, true, width, height);
			addText(id + 2, s.getName(), tda, 0, 0xebe0bc);
			configButton(id + 3, "Toggle " + s.getName(), 1040, 1039);
			tab.child(child++, id, x - 12, y - 7);
			tab.child(child++, id + 1, x - 10, y - 5);
			tab.child(child++, id + 2, x, y + 5);
			tab.child(child++, id + 3, x + 120, y + 3);
			id += 4;
			if (col++ % 3 == 2) {
				y += 32;
				x = 22;
				col = 0;
			} else {
				x += width + 2;
			}
		}
	}
	
	/**
	 * Handles the buttons.
	 * 
	 * @param button
	 *            The button id.
	 * @return <code>true</code> if handled.
	 */
	public static boolean handleButton(int button) {
		if (TOGGLES.containsKey(button)) {
			TOGGLES.get(button).handle();
			ClientSettings.save();
			return true;
		}
		
		switch (button) {
		case FIXED:
			Client.instance.toggleSize(0);
			return true;
		case RESIZABLE:
			Client.instance.toggleSize(1);
			return true;
		case CHAT_EFFECTS:
			Client.instance.sendFrame36(171, Client.instance.variousSettings[171] == 1 ? 0 : 1);
			ClientSettings.save();
			return true;
		case SPLIT_PRIVATE_CHAT:
			Client.instance.sendFrame36(287, Client.instance.variousSettings[287] == 1 ? 1 : 0);
			ClientSettings.save();
			return true;
		case MOUSE_BUTTONS:
			Client.instance.sendFrame36(170, Client.instance.variousSettings[170] == 1 ? 0 : 1);
			ClientSettings.save();
			return true;
		case MOUSE_CAMERA:
			Configuration.enableMouseCamera = !Configuration.enableMouseCamera;
			ClientSettings.save();
			return true;
		case FOLLOWER_OPTIONS:
			Configuration.enableFollowerOptionPriority = !Configuration.enableFollowerOptionPriority;
			ClientSettings.save();
			return true;
		case SHIFT_CLICK_DROP:
			Configuration.enableShiftClickDrop = !Configuration.enableShiftClickDrop;
			ClientSettings.save();
			return true;
		case KEYBINDING:
			Client.openInterfaceID = CustomisableHotKeys.interfaceID;
			return true;
		case ADVANCED_OPTIONS + 4:
			ClientSettings.setDefaults();
			ClientSettings.save();
			updateAdvancedOptions();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Updates the settings tab button states.
	 */
	public static void updateSettingsTab() {
		interfaceCache[CHAT_EFFECTS].active = Client.instance.variousSettings[171] == 1;
		interfaceCache[SPLIT_PRIVATE_CHAT].active = Client.instance.variousSettings[287] == 0;
		interfaceCache[MOUSE_BUTTONS].active = Client.instance.variousSettings[170] == 1;
		interfaceCache[MOUSE_CAMERA].active = !Configuration.enableMouseCamera;
		interfaceCache[SHIFT_CLICK_DROP].active = !Configuration.enableShiftClickDrop;
		interfaceCache[PLAYER_ATTACK_DROPDOWN].setDropdownValue(Configuration.playerAttackOptionPriority);
		interfaceCache[NPC_ATTACK_DROPDOWN].setDropdownValue(Configuration.npcAttackOptionPriority);
	}

	/**
	 * Updates the advanced options interface button states.
	 */
	public static void updateAdvancedOptions() {
		for (Entry<Integer, Setting> toggle : TOGGLES.entrySet()) {
			int button = toggle.getKey();
			
			Setting setting = toggle.getValue();
			
			interfaceCache[button].active = setting.enabled();
		}
	}
	
	/**
	 * Switches the panels of the settings tab.
	 * 
	 * @param button
	 *            The button.
	 */
	public static void switchSettings(int button) {
		int tab = button - DISPLAY_BUTTON;
		int[] tabs = new int[] { DISPLAY_SETTINGS, AUDIO_SETTINGS, CHAT_SETTINGS, CONTROL_SETTINGS };
		interfaceCache[904].children[6] = tabs[tab];
	}
    
}
