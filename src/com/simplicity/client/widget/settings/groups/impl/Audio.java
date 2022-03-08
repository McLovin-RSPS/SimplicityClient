package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.LegacySliderSetting;

import static com.simplicity.client.widget.settings.Setting.*;

public class Audio extends SettingGroup {

    final String general = "General";

    @Override
    public void init() {
        add(general, new LegacySliderSetting(SOUND_VOLUME, "Sound effect volume", "", 26, 941, 942, 943, 944, 945));
        add(general, new LegacySliderSetting(MUSIC_VOLUME, "Music volume", "", 26, 930, 931, 932, 933, 934));
    }
}
