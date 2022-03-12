package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.dropdown.Dropdown;
import com.simplicity.client.widget.settings.SettingsTabWidget;
import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.DropdownSetting;
import com.simplicity.client.widget.settings.objects.impl.Toggle;

import static com.simplicity.client.widget.settings.Setting.*;

public class Interfaces extends SettingGroup {

    final String general = "General";

    @Override
    public void init() {
        add(general, new DropdownSetting(CLIENT_LAYOUT,
                "Game client layout",
                "Select which client mode you\\nwould like to play the game in.\\nFixed mode is the classic client\\nlayout.\\nResizable will let you make the\\nclient as large as your screen.",
                new String[] { "Fixed", "Resizable" },
                100, false, 0, size -> {
                    Client.getClient().toggleSize(size);
                    SettingsTabWidget.updateClientLayout();
                }
        ));

        add(general, new DropdownSetting(GAMEFRAME,
                "Gameframe style",
                "Select what the style for the\\ngame frame is.",
                new String[]{ "#500+ (2009)", "#600+ (2010)", "OSRS" },
                100, false, 1, selected -> {
            if (selected != 2) {
                Configuration.enableOldschoolFrame = false;
            }

            if (selected == 0) {
                Configuration.enableOldFrame = true;

                if (Client.tabID == 13) {
                    Client.tabID = 15;
                }
            } else if (selected == 1) {
                Configuration.enableOldFrame = false;

                if (Client.tabID == 15) {
                    Client.tabID = 13;
                }
            } else if (selected == 2) {
                Configuration.enableOldFrame = true;
                Configuration.enableOldschoolFrame = true;

                if (Client.tabID == 15) {
                    Client.tabID = 13;
                }
            }
        }));

        add(general, new DropdownSetting(HITMARKERS,
                "Hitmarkers style",
                "Select what the style for the hitmarkers is.",
                new String[] { "562", "OSRS" },
                100, false, 0, value -> Configuration.enableOldHitmarkers = value == 0 ? false : true
        ));

        add(general, new DropdownSetting(HP_BARS,
                "HP Bar style",
                "Select what the style for the hp bar is.",
                new String[] { "562", "OSRS" },
                100, false, 0, value -> Configuration.enableNewHpBars = value == 0 ? true : false
        ));

        add(general, new DropdownSetting(ITEM_STATS,
                "Item stats",
                "Select when item stats should be enabled.",
                new String[] { "On Hover", "Hold Ctrl", "Disabled" },
                100, false, 1, value -> {}
        ));

        add(general, new Toggle(SHOW_ORBS,
                "Show data orbs",
                "When enabled, data orbs are shown around the world map.",
                true));

        add(general, new Toggle(SPECIAL_ORB,
                "Show special orb",
                "When enabled, special orb is shown around the world map.",
                true, enabled -> {
                    RSInterface orb = RSInterface.interfaceCache[41005];

                    if (enabled) {
                        if (!Client.instance.parallelWidgetList.contains(orb)) {
                            Client.instance.parallelWidgetList.add(orb);
                        }
                    } else {
                        if (Client.instance.parallelWidgetList.contains(orb)) {
                            Client.instance.parallelWidgetList.remove(orb);
                        }
                    }
                }));
    }

}
