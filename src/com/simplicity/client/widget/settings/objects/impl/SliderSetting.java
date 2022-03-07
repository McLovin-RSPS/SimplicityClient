package com.simplicity.client.widget.settings.objects.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.util.Alignment;

import java.util.function.Consumer;

public class SliderSetting extends SettingObject<Integer> {

    private int sliderType;

    public SliderSetting(String key, String name, String description, int sliderType, Integer defaultValue, Consumer<Integer> handle) {
        super(key, name, description, Alignment.CENTER, defaultValue, handle);
        this.sliderType = sliderType;
    }

    @Override
    public void draw(int idx, int y, int width, int height, CustomWidget widget) {
        widget.add(widget.addRectangle(width, 27 + height, 0, idx % 2 == 0 ? 200 : 225, true), 97, 12 + y);

        RSInterface slider = RSInterface.slider(widget.id++, 35, 0, 100, 372, 1340, 1338, sliderType);
        widget.add(slider, width / 2 + 32, 18 + height + y);
    }
}
