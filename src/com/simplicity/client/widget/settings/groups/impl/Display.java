package com.simplicity.client.widget.settings.groups.impl;

import com.simplicity.Configuration;
import com.simplicity.client.Client;
import com.simplicity.client.Rasterizer;
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

        add(graphics, new Toggle(SCROLL_ZOOM, "Scroll wheel can change zoom distance",
                "When enabled, the scroll wheel can change zoom distance.",
                true, enabled -> SettingsTabWidget.updateZoomToggle()));

        add(graphics, new Toggle(PARTICLES, "Particles",
                "When enabled, particles will be rendered.",
                true));

        add(graphics, new Toggle(FOG, "FOG",
                "When enabled, fog will be rendered.",
                false));

        add(graphics, new Toggle(HD_TEXTURES, "HD Textures",
                "When enabled, textures will be rendered in higher quality.",
                false));

        add(graphics, new Toggle(MIP_MAPPING, "Mip-mapping",
                "When enabled, textures will appear smoother.",
                false, enabled -> Rasterizer.enableMipmapping = enabled));

        /* Disabled in client? */
        /*add(graphics, new Toggle(ANTI_ALIASING, "Anti-aliasing",
                "When enabled, models will be rendered with smooth edges instead of sharp.",
                false));*/

        add(graphics, new Toggle(HD_SHADING, "Tile shading",
                "When enabled, shading will be applied to tiles.",
                true, enabled -> Rasterizer.enableSmoothShading = enabled));
    }
}
