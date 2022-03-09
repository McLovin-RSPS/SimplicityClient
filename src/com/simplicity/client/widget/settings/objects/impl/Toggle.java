package com.simplicity.client.widget.settings.objects.impl;

import com.simplicity.client.Client;
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
    private static final int DISABLED_SPRITE = 1993;
    private static final int ENABLED_SPRITE = 1994;

    public Toggle(String key, String name, String description, boolean defaultValue) {
        this(key, name, description, defaultValue, b -> {});
    }

    public Toggle(String key, String name, String description, boolean defaultValue, Consumer<Boolean> handle) {
        super(key, name, description, Alignment.LEFT, defaultValue, handle);
    }

    @Override
    public int draw(int idx, int y, int width, int height, CustomWidget widget) {
        toggle = widget.addSprite(DISABLED_SPRITE);
        RSInterface rectangle = widget.addRectangleClickable(idx % 2 == 0 ? 200 : 225, 0, true, width, 27 + height, new String[] { "Toggle" });
        rectangle.hovers = true;
        rectangle.setLayer(SettingsWidget.WIDGET_ID);
        rectangle.enabledOpacity = 240;
        rectangle.enabledColor = 0;
        rectangle.enabledMouseOverColor = 0;
        rectangle.onClick = () -> {
            toggleSprite(!toggle.active);
            Settings.set(getKey(), toggle.active);
            getHandle().accept(toggle.active);
            return true;
        };
        widget.add(rectangle, 97, 12 + y);
        widget.add(toggle, 461, 6 + height + y);
        return 0;
    }

    private void toggleSprite(boolean active) {
        toggle.active = active;
        toggle.disabledSprite = Client.getClient().cacheSprite[active ? ENABLED_SPRITE : DISABLED_SPRITE];
    }

    @Override
    public void update() {
        toggleSprite(Settings.getBoolean(getKey()));
    }

}
