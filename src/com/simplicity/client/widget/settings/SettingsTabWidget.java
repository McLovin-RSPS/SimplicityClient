package com.simplicity.client.widget.settings;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.ClientSettings;
import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.widget.SettingOld;
import com.simplicity.client.widget.dropdown.Dropdown;
import com.simplicity.client.widget.dropdown.DropdownMenu;

/**
 * A class that handles all of the client's settings.
 * 
 * @author Blake
 *
 */
public class SettingsTabWidget extends RSInterface {
	
	public static final int DISPLAY_BUTTON = 46_000;
	public static final int AUDIO_BUTTON = 46_001;
	public static final int CHAT_BUTTON = 46_002;

	public static final int ALL_SETTINGS = 46_045;

	public static final int PLAYER_ATTACK_DROPDOWN = 46_004;
    public static final int NPC_ATTACK_DROPDOWN = 46_005;

	public static final int HEADER_ID = 46_046;
	
	private static final int CONTROL_SETTINGS = 20_000;
	private static final int AUDIO_SETTINGS = CONTROL_SETTINGS + 100;
	private static final int DISPLAY_SETTINGS = AUDIO_SETTINGS + 100;

	public static final int ADVANCED = CONTROL_SETTINGS + 4;
	public static final int TOGGLE_ZOOM = 20201;
	public static final int ZOOM_SLIDER = 20204;
	public static final int CLIENT_LAYOUT_DROPDOWN = 20205;
	
	private static final int ADVANCED_OPTIONS = 56_000;
	
	private static Map<Integer, SettingOld> TOGGLES = new LinkedHashMap<Integer, SettingOld>();
	
	public static void unpack(TextDrawingArea[] tda) {
		init();
		optionsMain(tda);
		controlSettings(tda);
		audioSettings(tda);
		displaySettings(tda);
		advancedOptions(tda);
	}
	
	public static void optionsMain(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(904);

		configHoverButton(DISPLAY_BUTTON, "Controls", 1995, 1995, 1997, 1996, true, new int[] { AUDIO_BUTTON, CHAT_BUTTON });
		configHoverButton(AUDIO_BUTTON, "Audio", 1997, 1996, 1995, 1995, false, new int[] { DISPLAY_BUTTON, CHAT_BUTTON });
		configHoverButton(CHAT_BUTTON, "Display", 1997, 1996, 1995, 1995, false, new int[] { DISPLAY_BUTTON, AUDIO_BUTTON });
		addHorizontalLine(46003, 190, 0x5d5848, 255);

		int child = 0;
		
		tab.totalChildren(12);
		tab.child(child++, 962, 3, 41 + 8);
		tab.child(child++, CONTROL_SETTINGS, 0, 0);
		tab.child(child++, 46003, 0, 29);

        int x = 0;

        for (int i = 0; i < 3; i++, x += 64) {
            tab.child(child++, DISPLAY_BUTTON + i, 2 + x, 4);
        }

		addSprite(46010, 1998);
		tab.child(child++, 46010, 23, 8);

		addSprite(46011, 1999);
		tab.child(child++, 46011, 88, 8);

		addSprite(46012, 2000);
		tab.child(child++, 46012, 151, 7);

		addDynamicButton(46045, "All Settings", 138, 28, StyleScheme.DARK);
		addText(46044, "All Settings", tda, 0, 0xfe971e, true, true).setSize(138, 28);
		tab.child(child++, 46045, 28, 229);
		tab.child(child++, 46044, 28, 236);

		addText(HEADER_ID, "Controls Settings", tda, 2, 0xfe971e, true, true).setSize(141, 28);
		tab.child(child++, HEADER_ID, 24, 32);
	}
	
	private static void controlSettings(TextDrawingArea[] tda) {
		int id = CONTROL_SETTINGS;

		RSInterface tab = addTabInterface(id++);
		
		tab.totalChildren(14);
		
		int child = 0;

		String[] options = {"Depends on combat levels", "Always right-click", "Left-click where available", "Hidden"};

		dropdownMenu(PLAYER_ATTACK_DROPDOWN, 162, 0, options, option -> Settings.set(Setting.PLAYER_ATT_OPT, option, true));
		addText(id, "Player 'Attack' options:", tda, 1, 0xfe971e, false, true);

		dropdownMenu(NPC_ATTACK_DROPDOWN, 162, 2, options, option -> Settings.set(Setting.NPC_ATT_OPT, option, true));
		addText(id + 1, "NPC 'Attack' options:", tda, 1, 0xfe971e, false, true);

		setBounds(id, 14, 118 - 25, child++, tab);
		setBounds(NPC_ATTACK_DROPDOWN, 14, 174 - 25, child++, tab);
		setBounds(id + 1, 14, 157 - 25, child++, tab);
		setBounds(PLAYER_ATTACK_DROPDOWN, 14, 136 - 25, child++, tab);
		id += 2;

		RSInterface energy = interfaceCache[149];
		energy.disabledColor = 0xff9933;
		addSpriteLoader(962, 1221);
		addSpriteLoader(907, 1223); // zoom
		addSpriteLoader(909, 88);

		addConfigButtonWSpriteLoader(12464, 904, 1220, 1219, 40, 40, "Toggle-Accept Aid", 0, 5, 427);
		addSpriteLoader(949, 1248);

		addConfigButtonWSpriteLoader(152, 904, 1219, 1220, 40, 40, "Toggle-run", 1, 5, 173);
		addSpriteLoader(947, 1247);

		addButton(id, 1219, "View House Options");
		interfaceCache[id].onClick = () -> {
			Client.getClient().pushMessage("Coming soon", 0, "");
			return false;
		};
		tab.child(child++, id++, 55 + 41, 223 - 4 - 45);

		addSprite(id, 2003);
		tab.child(child++, id++, 55 + 45, 223 - 4 - 40);

		addButton(id, 1219, "View Store");
		interfaceCache[id].onClick = () -> {
			Client.getClient().launchURL("https://store.simplicityps.org/");
			return false;
		};
		tab.child(child++, id++, 55 + 83, 223 - 4 - 45);

		addSprite(id, 2004);
		tab.child(child++, id++, 55 + 87, 223 - 4 - 40);

		tab.child(child++, 12464, 7 + 5, 223 - 4 - 45);
		tab.child(child++, 949, 12 + 5, 228 - 4 - 45);
		tab.child(child++, 152, 54, 223 - 4 - 45);
		tab.child(child++, 947, 65, 226 - 4 - 45);
		tab.child(child++, 149, 59, 243 - 4 - 45);

		addHoverText(id, "<spr=2005:0> PK Skull Prevention", "Toggle skull prevention", tda, 1, 0xff981f, false, true, 200, 16);
		interfaceCache[id].useNewFonts = true;
		interfaceCache[id].onClick = () -> {
			// TODO: toggle change text according to setting value
			Client.getClient().pushMessage("Coming soon", 0, "");
			return false;
		};
		tab.child(child++, id++, 14, 68);
		
		if (!Configuration.enableZooming) {
			configHoverButtonSwitch(interfaceCache[TOGGLE_ZOOM]);
		}
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
		int y = 10;

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
	
	private static void displaySettings(TextDrawingArea[] tda) {
		int id = DISPLAY_SETTINGS;

		RSInterface tab = addTabInterface(id++);

		tab.totalChildren(9);

		int child = 0;

		System.out.println("TOGGLE ZOOM ID: " + id);
		configHoverButton(TOGGLE_ZOOM, "Toggle Zooming", new String[] { "Restore Default Zoom" }, 1343, 1344, 1342, 1341, false, TOGGLE_ZOOM);
		tab.child(child++, TOGGLE_ZOOM, 13, 100 - 5);
		id+=3;

		System.out.println("ZOOM SLIDER: " + id);
		slider(ZOOM_SLIDER, 0, -1800, -50, 128, 1340, 1338, 1);
		tab.child(child++, id++, 48, 100 - 5 + 8);

		addSpriteLoader(905, 1222);
		tab.child(child++, 905, 13, 66 - 8);
		tab.child(child++, 906, 48, 66);
		tab.child(child++, 908, 80, 66);
		tab.child(child++, 910, 112, 66);
		tab.child(child++, 912, 144, 66);

		dropdownMenu(CLIENT_LAYOUT_DROPDOWN, 162, 0, new String[] { "Fixed - Classic layout", "Resizable - Classic layout" }, size -> {
			Settings.set(Setting.CLIENT_LAYOUT, size, true);
			Client.getClient().toggleSize(size);
		});
		addText(id + 1, "Game client layout:", tda, 1, 0xfe971e, false, true);
		setBounds(id, 14, 152, child++, tab);
		setBounds(id + 1, 14, 134, child++, tab);
	}
	
	public static void init() {
		int button = ADVANCED_OPTIONS + 12;
		
		for (SettingOld setting : SettingOld.values()) {
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
		
		boolean scrollbar = SettingOld.size() > 21;

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
		tab.totalChildren(SettingOld.size());
		tab.height = 228;
		tab.scrollMax = scrollbar ? 425 : tab.height;
		tab.width = 477;
		
		int x = 22;
		int y = 7;
		
		int width = 153;
		
		int height = 29;
		
		int col = 0;
		
		child = tab.children.length - 1;
		
		for (SettingOld s : SettingOld.values()) {
			if (s.isCategory()) {
				x = 22;
				
				addText(id, s.getName(), tda, 1, Color.YELLOW.getRGB(), true);
				
				if (s.ordinal() != 0) { // Skip first category shifting
					y += col != 0 ? 32 : 4;
				}
				
				tab.child(child--, id, x + 220, y);
				
				y += 32;
				id += 4;
				col = 0;
				continue;
			}
			
			addRectangle(id, 0, 0x80786d, false, width + 3, height + 4);
			addRectangle(id + 1, 255, 0x383530, true, width, height);
			addText(id + 2, s.getName(), tda, 0, 0xebe0bc);
			
			boolean dropdown = s.getDropdownMenu() != null;
			
			if (dropdown) {
				dropdownMenu(id + 3, s.getDropdownMenu());
			} else {
				configButton(id + 3, "Toggle " + s.getName(), 1040, 1039);
			}
			
			tab.child(child--, id + 3, dropdown ? x + 63 : x + 120, dropdown ? y : y + 3);
			tab.child(child--, id + 2, x, y + 5);
			tab.child(child--, id + 1, x - 10, y - 5);
			tab.child(child--, id, x - 12, y - 7);
			
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
	public static boolean handleButton(int button, int index) {
		if (TOGGLES.containsKey(button)) {
			TOGGLES.get(button).handle();
			ClientSettings.save();
			return true;
		}
		
		switch (button) {
		case TOGGLE_ZOOM:
			if (index == 0) {
				Configuration.enableZooming = !Configuration.enableZooming;
				configHoverButtonSwitch(interfaceCache[TOGGLE_ZOOM]);
				ClientSettings.save();
			} else if (index == 1) {
				Client.cameraZoom = Client.instance.getDefaultCameraZoom();
				
				interfaceCache[ZOOM_SLIDER].slider.setValue(Math.negateExact(1800 - Client.cameraZoom));
			}
			return true;
		/*case KEYBINDING:
			Keybinding.updateInterface();
			Client.openInterfaceID = Keybinding.interfaceID;
			return true;*/
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
		updatePlayerAttackDropdown();
		updateNpcAttackDropdown();
	}

	public static void updatePlayerAttackDropdown() {
		RSInterface rsi = interfaceCache[PLAYER_ATTACK_DROPDOWN];
		rsi.dropdown.setSelected(rsi.dropdown.getOptions()[Settings.getInt(Setting.PLAYER_ATT_OPT)]);
	}

	public static void updateNpcAttackDropdown() {
		RSInterface rsi = interfaceCache[NPC_ATTACK_DROPDOWN];
		rsi.dropdown.setSelected(rsi.dropdown.getOptions()[Settings.getInt(Setting.NPC_ATT_OPT)]);
	}

	public static void updateClientLayout() {
		RSInterface rsi = interfaceCache[CLIENT_LAYOUT_DROPDOWN];
		rsi.dropdown.setSelected(rsi.dropdown.getOptions()[Settings.getInt(Setting.CLIENT_LAYOUT)]);
	}

	/**
	 * Updates the advanced options interface button states.
	 */
	public static void updateAdvancedOptions() {
		for (Entry<Integer, SettingOld> toggle : TOGGLES.entrySet()) {
			int button = toggle.getKey();

			SettingOld setting = toggle.getValue();
			
			if (!setting.isCategory()) {
				DropdownMenu menu = setting.getDropdownMenu();
				
				try {
					if (menu != null) {
						menu.getDrop().selectOption(menu.getSelectedIndex(), null);
					} else {
						interfaceCache[button].active = setting.enabled();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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
		int[] tabs = new int[] {CONTROL_SETTINGS, AUDIO_SETTINGS, DISPLAY_SETTINGS};
		interfaceCache[904].children[1] = tabs[tab];
		interfaceCache[HEADER_ID].message = tab == 0 ? "Controls Settings" : tab == 1 ? "Audio Settings" : "Display Settings";
	}
    
}
