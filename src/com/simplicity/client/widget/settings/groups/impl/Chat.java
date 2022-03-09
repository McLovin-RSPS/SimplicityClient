package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.client.Client;
import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.Toggle;

import static com.simplicity.client.widget.settings.Setting.*;

public class Chat extends SettingGroup {

    final String options = "Game Chat";

    @Override
    public void init() {
        add(options, new Toggle(CHAT_EFFECTS, "Show chat effects",
                "When enabled, chat effects will be displayed when players use\\nthem.",
                true, enabled -> Client.instance.sendFrame36(171, enabled ? 0 : 1)));

        add(options, new Toggle(SPLIT_PRIVATE, "Split friends private chat",
                "When enabled, shows your private messages above your chat\\nbox.",
                true, enabled -> Client.instance.sendFrame36(287, enabled ? 1 : 0)));

        add(options, new Toggle(SAVE_INPUT, "Save input",
                "When enabled, enter amount input will be saved.",
                true));
    }
}
