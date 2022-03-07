package com.simplicity.client.widget.settings.objects.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.dropdown.DropdownMenu;
import com.simplicity.client.widget.settings.Setting;
import com.simplicity.client.widget.settings.Settings;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.util.Alignment;

import java.util.function.Consumer;

public class DropdownSetting extends SettingObject<Integer> {

    private DropdownMenu menu;
    private int priority;

    public DropdownSetting(String key, String name, String description, String[] options, Consumer<Integer> onSelect, int priority, int defaultValue, Consumer<Integer> handle) {
        super(key, name, description, Alignment.LEFT, defaultValue, handle);
        this.menu = new DropdownMenu(false, defaultValue, options, null, option -> Settings.set(key, option));
        this.priority = priority;
    }

    @Override
    public void draw(int idx, int y, int width, int height, CustomWidget widget) {
        widget.add(widget.addRectangle(width, 27 + height, 0, idx % 2 == 0 ? 200 : 225, true), 97, 12 + y);

        RSInterface rsi = RSInterface.addInterface(widget.id++);
        rsi.type = 36;
        rsi.atActionType = 7;
        rsi.dropdownColours = new int[] { 0x0d0d0b, 0x464644, 0x473d32, 0x51483c, 0x787169 };
        rsi.dropdown = menu;

        widget.add(rsi, width - menu.getWidth() / 2 + 8, 28 + y, priority);
    }

    @Override
    public void update() {
    }

}
