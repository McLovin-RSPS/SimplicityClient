package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.client.widget.Slider;
import com.simplicity.client.widget.settings.groups.SettingGroup;
import com.simplicity.client.widget.settings.objects.impl.SliderSetting;
import com.simplicity.client.widget.settings.objects.impl.Toggle;

import static com.simplicity.client.widget.settings.Setting.*;

public class Display extends SettingGroup {

    final String graphics = "Graphics";

    @Override
    public void init() {
        add(graphics, new SliderSetting(BRIGHTNESS, "Screen Brightness",
                "Change the level of brightness to make the world look lighter or\\ndarker.",
                Slider.ZOOM,
                4D, brightness -> { }));

        add(graphics, new SliderSetting(ZOOM, "Camera zoom distance",
                "Use the slider to change the camera zoom level.",
                Slider.ZOOM,
                1D, zoom -> { }));

        add(graphics, new Toggle(ROOFS, "Hide roofs",
                "When enabled, hides all roofs. If you are inside a building, the\\nroof will be hidden regardless of the setting being disabled.",
                false));

        add(graphics, new Toggle(PARTICLES, "Particles",
                "When enabled, particles will be rendered.",
                true));
    }
}
