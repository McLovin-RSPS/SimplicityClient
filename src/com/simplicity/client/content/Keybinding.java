package com.simplicity.client.content;

import java.awt.event.KeyEvent;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.ClientSettings;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.settings.Setting;
import com.simplicity.client.widget.settings.Settings;

public class Keybinding {
	
	public static final int interfaceID = 90000;

    public static final int MIN_FRAME = 90011;
    public static final int RESTORE_DEFAULT = 90051;
    public static final int ESCAPE_CONFIG = 90056;
    public static final String[] OPTIONS = {"None", "ESC", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12"};

    public static final int KEYS[] = {-1, KeyEvent.VK_ESCAPE, KeyEvent.VK_F1, KeyEvent.VK_F2, KeyEvent.VK_F3, KeyEvent.VK_F4, KeyEvent.VK_F5, KeyEvent.VK_F6, KeyEvent.VK_F7, KeyEvent.VK_F8,
            KeyEvent.VK_F9, KeyEvent.VK_F10, KeyEvent.VK_F11, KeyEvent.VK_F12};

    public static int[] KEYBINDINGS = new int[14];

    static {
        restoreDefault();
    }

    public static void restoreDefault() {
        KEYBINDINGS = new int[]{
                KeyEvent.VK_F1,
                KeyEvent.VK_F2,
                KeyEvent.VK_F3,
                -1,
                KeyEvent.VK_ESCAPE,
                KeyEvent.VK_F4,
                KeyEvent.VK_F5,
                KeyEvent.VK_F6,
                KeyEvent.VK_F11,
                KeyEvent.VK_F7,
                KeyEvent.VK_F8,
                KeyEvent.VK_F9,
                KeyEvent.VK_F10,
                KeyEvent.VK_F12,
        };

        Settings.set(Setting.ESC_CLOSES_INTERFACE, true);

        if (RSInterface.interfaceCache != null && RSInterface.interfaceCache[ESCAPE_CONFIG] != null) {
        	RSInterface.interfaceCache[ESCAPE_CONFIG].active = true;
        }
    }

    public static void checkDuplicates(int key, int index) {
        for (int i = 0; i < KEYBINDINGS.length; i++) {
            if (i == 3 && Settings.CONTROLS.getBoolean(Setting.ESC_CLOSES_INTERFACE) && KEYBINDINGS[3] == KeyEvent.VK_ESCAPE || KEYS[key] == KEYBINDINGS[i] && i != index && KEYBINDINGS[i] != -1) {
                KEYBINDINGS[i] = -1;
                RSInterface.interfaceCache[MIN_FRAME + 3 * i].dropdown.setSelected("None");
            }
        }
    }
    
    public static void toggleEsc() {
        Settings.set(Setting.ESC_CLOSES_INTERFACE, !Settings.getBoolean(Setting.ESC_CLOSES_INTERFACE));
    }

    public static void bind(int index, int key) {
        checkDuplicates(key, index);
        KEYBINDINGS[index] = KEYS[key];
        ClientSettings.save();
    }

    public static boolean isBound(int key) {
        for (int i = 0; i < KEYBINDINGS.length; i++) {
			if (key == KEYBINDINGS[i]) {
				if (i == 0 || i == 1 || i == 2 || i == 13) {
					Client.setTab(i);
				} else if (i == 3) {
					Client.setTab(14);
				} else if (i >= 4 && i <= 12 && i != 11 && i != 8) {
					Client.setTab(i - 1);
				} else if (i == 8) {
					Client.setTab(12);
				} else if (i == 11) {
					Client.setTab(7);
				}
				return true;
			}
		}
        return false;
    }

    public static void updateInterface() {

        for (int i = 0; i < OPTIONS.length - 1; i++) {

            int key = KEYBINDINGS[i];
            String current = "None";

            if (key == KeyEvent.VK_ESCAPE) {
            	current = OPTIONS[1];
            } else if (key >= KeyEvent.VK_F1 && key <= KeyEvent.VK_F12) {
                current = OPTIONS[key - KeyEvent.VK_F1 + 2];
            }

        	RSInterface.interfaceCache[MIN_FRAME + 3 * i].dropdown.setSelected(current);
        }
        
        Client.instance.sendFrame36(1014, Settings.CONTROLS.getBoolean(Setting.ESC_CLOSES_INTERFACE) ? 0 : 1);
    }
}
