package com.simplicity.client.content;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.content.dropdownmenu.DropDownMenu;

import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created using Eclipse.
 *
 * @author Ali | (https://www.rune-server.ee/members/_ali/)
 */
@SuppressWarnings("serial")
public class CustomisableHotKeys {

    public enum ChildTabKeyRelation {

        COMBAT_TAB(28670, 0, -1),
        SKILLS_TAB(28671, 1, KeyEvent.VK_F1),
        QUEST_TAB(28672, 2, KeyEvent.VK_F2),
        INVENTORY_TAB(28673, 3, KeyEvent.VK_F3),
        EQUIPMENT_TAB(28674, 4, KeyEvent.VK_F4),
        PRAYER_TAB(28675, 5, KeyEvent.VK_F5),
        SPELLBOOK_TAB(28676, 6, KeyEvent.VK_F6),
        FRIENDS_TAB(28677, 8, KeyEvent.VK_F8),
        IGNORE_TAB(28678, 9, KeyEvent.VK_F9),
        LOGOUT_TAB(28679, 10, KeyEvent.VK_F10),
        SETTINGS_TAB(28680, 11, KeyEvent.VK_F11),
        EMOTES_TAB(28681, 12, KeyEvent.VK_F12),
        CLANCHAT_TAB(28682, 7, KeyEvent.VK_F7),
        MUSIC_TAB(28683, 13, KeyEvent.VK_ESCAPE);

        /**
         * ChildId of the tab.
         */
        private final int childId;

        /**
         * Drop menu option identifier number.
         */
        private final int tabId;

        /**
         * Key code in relation to the identifer.
         */
        private final int keyCode;

        private ChildTabKeyRelation(final int childId, final int tabId, final int keyCode) {
            this.childId = childId;
            this.tabId = tabId;
            this.keyCode = keyCode;
        }

        public static final int[] getFromChildId(final int childId, final int identifier) {
            int[] data = {-1, -1};
            for (ChildTabKeyRelation childTabKeyInfo : VALUES) {
                if (childTabKeyInfo == null) {
                    continue;
                }
                if (childTabKeyInfo.getChildId() == childId) {
                    data[0] = childTabKeyInfo.getTabId();
                }
                if (childTabKeyInfo.getTabId() == identifier) {
                    data[1] = childTabKeyInfo.getKeyCode();
                }
            }
            return data;
        }

        public static final int getFromKeyCode(final int keyCode) {
            for (ChildTabKeyRelation childTabKeyInfo : VALUES) {
                if (childTabKeyInfo == null) {
                    continue;
                }
                if (childTabKeyInfo.getKeyCode() == keyCode) {
                    return childTabKeyInfo.getTabId();
                }
            }
            return -1;
        }

        private static final Set<ChildTabKeyRelation> VALUES = Collections.unmodifiableSet(EnumSet.allOf(ChildTabKeyRelation.class));

        public int getChildId() {
            return childId;
        }

        public int getTabId() {
            return tabId;
        }

        public int getKeyCode() {
            return keyCode;
        }

    }

    /**
     * The radio button config value setting identifier.
     */
    public static final int ESC_VALUE_SETTING_IDENTIFIER = 400;

    /**
     * Does esc key close interfaces?/
     */
    private static boolean escClosesInterface = true;

    /**
     * A map holding players customised hot key preferences.
     * <p>
     * Key; Child Id we are dealing with.
     * <p>
     * Value; As list(key stroke unique Id, tab id, drop menu identifier specific to the key stroke).
     */
    private static LinkedHashMap<Integer, List<Integer>> customisableHotKeys = new LinkedHashMap<Integer, List<Integer>>(14) {

        /**
         * Set the default F Keys for this client.
         *
         */ {
            put(28670, Arrays.asList(KeyEvent.VK_F5, 0, 5)); // Combat Tab.
            put(28671, Arrays.asList(KeyEvent.VK_F6, 1, 6)); // Skills Tab.
            put(28672, Arrays.asList(-1, 2, 0)); // Quest Tab.
            put(28673, Arrays.asList(KeyEvent.VK_F1, 3, 1)); // Inventory Tab.
            put(28674, Arrays.asList(KeyEvent.VK_F2, 3, 2)); // Equipment Tab.
            put(28675, Arrays.asList(KeyEvent.VK_F3, 4, 3)); // Prayer Tab.
            put(28676, Arrays.asList(KeyEvent.VK_F4, 5, 4)); // Spellbook Tab.
            put(28677, Arrays.asList(KeyEvent.VK_F8, 8, 8)); // Friends Tab.
            put(28678, Arrays.asList(KeyEvent.VK_F9, 13, 9)); // Ignore Tab.
            put(28679, Arrays.asList(-1, 10, 0)); // Logout Tab.
            put(28680, Arrays.asList(KeyEvent.VK_F10, 11, 10)); // Settings Tab.
            put(28681, Arrays.asList(KeyEvent.VK_F11, 12, 11)); // Emotes Tab.
            put(28682, Arrays.asList(KeyEvent.VK_F7, 11, 7)); // Clan Chat Tab.
            put(28683, Arrays.asList(KeyEvent.VK_F12, 9, 12)); // Music Tab.

        }

    };

    /**
     * Checks whether the key pressed is a match for the available hot keys. If
     * so opens the tab the {@value keyCode} is binded to. If there are no saved
     * customised hot keys then the default ones from class initialisation is
     * used.
     *
     * @param keyCode - The key this user has pressed.
     */
    public static void checkPressedKey(final int keyCode) {

        switch (keyCode) {
            case KeyEvent.VK_F1:
            case KeyEvent.VK_F2:
            case KeyEvent.VK_F3:
            case KeyEvent.VK_F4:
            case KeyEvent.VK_F5:
            case KeyEvent.VK_F6:
            case KeyEvent.VK_F7:
            case KeyEvent.VK_F8:
            case KeyEvent.VK_F9:
            case KeyEvent.VK_F10:
            case KeyEvent.VK_F11:
            case KeyEvent.VK_F12:
            case KeyEvent.VK_ESCAPE:

                int tabID = -1;

                for (List<Integer> hotKey : customisableHotKeys.values()) {
                    if (hotKey.get(0) == keyCode) {
                        tabID = hotKey.get(1);
                    }
                }

                if (tabID >= 0) {
                    Client.setTab(tabID);
                    if (keyCode == KeyEvent.VK_ESCAPE && isEscClosesInterface()) {
                        Client.instance.clearTopInterfaces();
                        break;
                    }
                } else {
                    if (keyCode == KeyEvent.VK_ESCAPE && isEscClosesInterface()) {
                        Client.instance.clearTopInterfaces();
                        break;
                    }
                }

                System.out.println("Tab : " + tabID);

                break;
        }

    }

    /**
     * Restore the default Pre-Eoc Keys for this user.
     */
    public static void restoreDefaults() {

        customisableHotKeys = new LinkedHashMap<Integer, List<Integer>>(14) {
            {
                put(28670, Arrays.asList(KeyEvent.VK_F5, 0, 5)); // Combat Tab.
                put(28671, Arrays.asList(KeyEvent.VK_F6, 1, 6)); // Skills Tab.
                put(28672, Arrays.asList(-1, 2, 0)); // Quest Tab.
                put(28673, Arrays.asList(KeyEvent.VK_F1, 14, 1)); // Achievements Tab.
                put(28674, Arrays.asList(KeyEvent.VK_F2, 3, 2)); // Inventory Tab.
                put(28675, Arrays.asList(KeyEvent.VK_F3, 4, 3)); // Equipment Tab.
                put(28676, Arrays.asList(KeyEvent.VK_F4, 5, 4)); // Prayer Tab.
                put(28677, Arrays.asList(KeyEvent.VK_F8, 6, 8)); // Spellbook Tab.
                put(28678, Arrays.asList(KeyEvent.VK_F9, 13, 9)); // Summoning Tab.
                put(28679, Arrays.asList(-1, 10, 0)); // Logout Tab.
                put(28680, Arrays.asList(KeyEvent.VK_F10, 9, 10)); // Settings Tab.
                put(28681, Arrays.asList(KeyEvent.VK_F11, 7, 11)); // Emotes Tab.
                put(28682, Arrays.asList(KeyEvent.VK_F7, 11, 7)); // Clan Chat Tab.
                put(28683, Arrays.asList(KeyEvent.VK_F12, 12, 12)); // Music Tab.
            }
        };

        if (isEscClosesInterface()) {
            setEscConfigButtonSettings(0);
            setEscClosesInterface(false);
        }

    }

    /**
     * Updates the drop down menu display to the correct message dependent on
     * players customised keys.
     */
    public static void updateDropDownMenuDisplaysOnLogin() {

        for (Map.Entry<Integer, List<Integer>> hotKey : customisableHotKeys.entrySet()) {
            final DropDownMenu inter = (DropDownMenu) RSInterface.interfaceCache[hotKey.getKey()];

            if (inter != null)
                inter.message = inter.getActions()[hotKey.getValue().get(2)].getMessage();
        }

        if (isEscClosesInterface()) {
            setEscConfigButtonSettings(1);
        }

    }

    /**
     * Set the config button status, to show the end user whether they have the
     * esc closes interface option selected or not.
     *
     * @param status 0 to turn the config button tick off, 1 to represent Esc closes interface is on.
     */
    private static void setEscConfigButtonSettings(final int status) {
        Client.instance.varbitConfigs[ESC_VALUE_SETTING_IDENTIFIER] = status;
        Client.instance.variousSettings[ESC_VALUE_SETTING_IDENTIFIER] = status;
        Client.getClient().handleActions(ESC_VALUE_SETTING_IDENTIFIER);
    }

    /**
     * Handles the swap between 2 tab F Keys. Self explanatory.
     *
     * @param childId    The childId we are handling as known as the tab.
     * @param identifier The drop down menu selection unique Id as known as
     *                   identifier/action id.
     */
    public static void processHotKeySelection(final int childId, final int identifier) {

        // The current tab we are dealing with already has this F Key assigned to it, stop the process as nothing needs done.
        if (identifier == customisableHotKeys.get(childId).get(2)) {
            return;
        }

        int newKeyCode = 0, tabId = 0;

        // Check the map to find out which tab currently has ownership of the F Key we are dealing with.
        // Returns optional empty if no tab has ownership of this F Key.
        int oldChildId = 0;
        for (Map.Entry<Integer, List<Integer>> hotKey : customisableHotKeys.entrySet()) {
            if (hotKey.getValue().get(2) == identifier) {
                oldChildId = hotKey.getKey();
            }
        }

        final int[] data = ChildTabKeyRelation.getFromChildId(childId, identifier);

        if (data[0] > -1) {
            tabId = data[0];
            newKeyCode = data[1];
        }

        // Get the currently handled drop down menu and update its display message to sync with the selected F Key.
        DropDownMenu inter = (DropDownMenu) RSInterface.interfaceCache[childId];
        inter.message = inter.getActions()[identifier].getMessage();

        // Update the old tabs values within the map that previously held the F Key that we are now assigning to the new tab.
        if (oldChildId > 0) {
            inter = (DropDownMenu) RSInterface.interfaceCache[oldChildId];
            inter.message = inter.getActions()[0].getMessage();
            customisableHotKeys.put(oldChildId, Arrays.asList(-1, -1, 0));
        }

        // Update the tab we are dealing with its new values.
        customisableHotKeys.put(childId, Arrays.asList(newKeyCode, tabId, identifier));

    }

    public static boolean isEscClosesInterface() {
        return escClosesInterface;
    }

    public static void setEscClosesInterface(boolean escClosesInterface) {
        CustomisableHotKeys.escClosesInterface = escClosesInterface;
    }

    public static LinkedHashMap<Integer, List<Integer>> getCustomisableHotKeys() {
        return customisableHotKeys;
    }

    public static void setCustomisableHotKeys(LinkedHashMap<Integer, List<Integer>> customisableHotKeys) {
        CustomisableHotKeys.customisableHotKeys = customisableHotKeys;
    }

    private CustomisableHotKeys() {

    }

}
