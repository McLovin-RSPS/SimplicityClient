package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.client.widget.Slider;
import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.SliderSetting;

import static com.simplicity.client.widget.settings.Setting.*;

public class Audio extends SettingGroup {

    final String general = "General";

    @Override
    public void init() {
        add(general, new SliderSetting(SOUND_VOLUME,
                "Sound effect volume", "",
                Slider.ZOOM,1, vol -> {}));

        add(general, new SliderSetting(MUSIC_VOLUME,
                "Music volume", "",
                Slider.ZOOM, 1, vol -> {}));
    }
}
