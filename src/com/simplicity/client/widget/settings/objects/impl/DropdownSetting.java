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

    protected DropdownMenu menu;
    protected int priority;


    public DropdownSetting(String key, String name, String description, String[] options, boolean split, int priority, int defaultValue, Consumer<Integer> handle) {
        super(key, name, description, Alignment.LEFT, defaultValue, handle);
        this.menu = new DropdownMenu(split, defaultValue, options, null, option -> {
            Settings.set(key, option);
            handle.accept(option);
        });
        this.priority = priority;
    }

    public DropdownSetting(String key, String name, String description, String[] options, int width, boolean split, int priority, int defaultValue, Consumer<Integer> handle) {
        super(key, name, description, Alignment.LEFT, defaultValue, handle);
        this.menu = new DropdownMenu(width, split, defaultValue, options, null, option -> {
            Settings.set(key, option);
            handle.accept(option);
        });
        this.priority = priority;
    }

    @Override
    public int draw(int idx, int y, int width, int height, CustomWidget widget) {
        widget.add(widget.addRectangle(width, 27 + height, 0, idx % 2 == 0 ? 200 : 225, true), 97, 12 + y);

        RSInterface rsi = RSInterface.addInterface(widget.id++);
        rsi.type = 36;
        rsi.atActionType = 7;
        rsi.dropdownColours = new int[] { 0x0d0d0b, 0x464644, 0x473d32, 0x51483c, 0x787169 };
        rsi.dropdown = menu;

        widget.add(rsi, width - menu.getWidth() / 2 + 8, 28 + y, priority);
        return 0;
    }

    @Override
    public void update() {
        int selected = Settings.getInt(getKey());
        String option = menu.getOptions()[selected];
        menu.setSelected(option);
    }

}
