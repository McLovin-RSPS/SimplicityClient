package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
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
                0,
                Client.getClient()::toggleSize
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
