package com.simplicity.client.widget.settings.objects.impl;

import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.util.Alignment;

import java.util.function.Consumer;

public class DropdownSetting extends SettingObject<Integer> {

    public DropdownSetting(String key, String name, String description, int defaultValue, Consumer<Integer> handle) {
        super(key, name, description, Alignment.LEFT, defaultValue, handle);
    }

    @Override
    public void draw(int idx, int y, int width, int height, CustomWidget widget) {
        widget.add(widget.addRectangle(width, 27 + height, 0, idx % 2 == 0 ? 200 : 225, true), 97, 12 + y);
    }
}
