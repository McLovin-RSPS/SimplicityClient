package com.simplicity.client.widget.settings.objects.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.settings.objects.SettingObject;
import com.simplicity.util.Alignment;

public class LegacySliderSetting extends SettingObject<Integer> {

    private int[] ids;
    private int spriteWidth;

    public LegacySliderSetting(String key, String name, String description, int spriteWidth, int...ids) {
        super(key, name, description, Alignment.CENTER, 4, value -> { });
        this.ids = ids;
        this.spriteWidth = spriteWidth;
    }

    @Override
    public int draw(int idx, int y, int width, int height, CustomWidget widget) {
        widget.add(widget.addRectangle(width, 27 + height, 0, idx % 2 == 0 ? 200 : 225, true), 97, 12 + y);

        int xPos = width / 2 + (spriteWidth == 32 ? 0 : spriteWidth / 2);

        for (int id : ids) {
            widget.add(RSInterface.interfaceCache[id], xPos += spriteWidth, 18 + height + y);
        }

        return 0;
    }

    @Override
    public void update() {
    }

}
