package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.client.Client;
import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.DropdownSetting;
import com.simplicity.client.widget.settings.objects.impl.Toggle;

import static com.simplicity.client.widget.settings.Setting.*;

public class Controls extends SettingGroup {

    final String control = "Control";
    final String[] attackOptions = { "Depends on combat levels", "Always right-click", "Left-click where available", "Hidden" };

    @Override
    public void init() {
        add(control, new DropdownSetting(PLAYER_ATT_OPT,
                "Player attack options",
                "Select what the default left click\\noption on another player will be.",
                attackOptions,
                slot -> System.out.println("Slot: " + slot),
                2, 2, value -> {}));

        add(control, new DropdownSetting(NPC_ATT_OPT,
                "Npc attack options",
                "Select what the default left click\\noption on on an NPC will be.",
                attackOptions,
                slot -> System.out.println("Slot: " + slot),
                1, 2, value -> {}));

        add(control, new Toggle(PK_SKULL_PREVENTION, "PK skull prevention",
                "When enabled, you will be prevented from attacking players in\\nany way that would cause you to become skulled.",
                false));

        add(control, new Toggle(SINGLE_MOUSE_BUTTON, "Single mouse button mode",
                "When enabled, if you left click where there is more than one\\noption, the click will act as if you right clicked.",
                false, enabled -> Client.instance.sendFrame36(170, enabled ? 0 : 1)));

        add(control, new Toggle(MIDDLE_MOUSE_CAMERA, "Middle mouse camera",
                "When enabled, if you hold down the middle mouse button, you\\ncan move the camera.",
                true));

        add(control, new Toggle(WASD_CAMERA, "WASD camera",
                "When enabled, you can use WASD keys to move the camera.",
                false, enabled -> Client.getClient().chatboxInFocus = !enabled));

        add(control, new Toggle(SHIFT_CLICK_DROP, "Shift click to drop items",
                "When enabled, if you hold shift, your left click option on items\\nin your inventory will default to drop.",
                false));

        /*add(control, new Toggle(FOLLOWER_PRIORITY, "Move follower options lower down",
                "Moves the pet options lower down in the options menu, this\\nstops your left click on the pet being Talk-to.",
                false));*/

        add(control, new Toggle(ESC_CLOSES_INTERFACE, "Esc closes the current interface",
                "When enabled, the escape key will close your active interface.",
                false));
    }

}
