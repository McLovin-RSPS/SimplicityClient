package com.simplicity.client.widget.settings.objects.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.settings.Settings;
import com.simplicity.client.widget.settings.SettingsWidget;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.util.Alignment;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class Toggle extends SettingObject<Boolean> {

    private RSInterface toggle;

    public Toggle(String key, String name, String description, boolean defaultValue) {
        this(key, name, description, defaultValue, b -> {});
    }

    public Toggle(String key, String name, String description, boolean defaultValue, Consumer<Boolean> handle) {
        super(key, name, description, Alignment.LEFT, defaultValue, handle);
    }

    @Override
    public int draw(int idx, int y, int width, int height, CustomWidget widget) {
        toggle = widget.configButton("Toggle", 1994, 1993);
        toggle.interactable = () -> false;
        RSInterface rectangle = widget.addRectangleClickable(idx % 2 == 0 ? 200 : 225, 0, true, width, 27 + height, new String[] { "Toggle" });
        rectangle.hovers = true;
        rectangle.setLayer(SettingsWidget.WIDGET_ID);
        rectangle.enabledOpacity = 240;
        rectangle.enabledColor = 0;
        rectangle.enabledMouseOverColor = 0;
        rectangle.onClick = () -> {
            toggle.active = !toggle.active;
            Settings.set(getKey(), toggle.active);
            return true;
        };
        widget.add(rectangle, 97, 12 + y);
        widget.add(toggle, 461, 6 + height + y);
        return 0;
    }

    @Override
    public void update() {
        toggle.active = Settings.getBoolean(getKey());
    }

}
