package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.Toggle;

import static com.simplicity.client.widget.settings.Setting.*;

public class Gameplay extends SettingGroup {

    final String options = "Options";

    @Override
    public void init() {
        add(options, new Toggle(ACCEPT_AID, "Accept aid",
                "When enabled, helpful spells that target other players can be\\ncast on you.",
                true));
    }
}
