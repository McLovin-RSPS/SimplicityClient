package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.widget.Slider;
import com.simplicity.client.widget.settings.SettingsTabWidget;
import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.DropdownSetting;
import com.simplicity.client.widget.settings.objects.impl.LegacySliderSetting;
import com.simplicity.client.widget.settings.objects.impl.SliderSetting;
import com.simplicity.client.widget.settings.objects.impl.Toggle;

import static com.simplicity.client.widget.settings.Setting.*;

public class Display extends SettingGroup {

    final String graphics = "Graphics";

    @Override
    public void init() {
        add(graphics, new LegacySliderSetting(BRIGHTNESS, "Screen Brightness",
                "Change the level of brightness to make the world look lighter or\\ndarker.",
                32, 906, 908, 910, 912));

        add(graphics, new LegacySliderSetting(ZOOM, "Camera zoom distance",
                "Use the slider to change the camera zoom level.",
                32, SettingsTabWidget.ZOOM_SLIDER));

        add(graphics, new Toggle(ROOFS, "Hide roofs",
                "When enabled, hides all roofs. If you are inside a building, the\\nroof will be hidden regardless of the setting being disabled.",
                false));

        add(graphics, new Toggle(PARTICLES, "Particles",
                "When enabled, particles will be rendered.",
                true));
    }
}
