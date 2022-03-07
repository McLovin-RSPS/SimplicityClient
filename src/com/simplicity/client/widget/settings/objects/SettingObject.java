package com.simplicity.client.widget.settings.objects;

import com.simplicity.client.widget.CustomWidget;
import com.simplicity.util.Alignment;
import lombok.*;

import java.util.function.Consumer;

@Getter
public abstract class SettingObject<T> {
    private final String key;
    private final String name;
    private final String description;
    private final Alignment alignment;
    private final T defaultValue;
    private final Consumer<T> handle;

    @Setter
    private T value;

    public SettingObject(String key, String name, String description, Alignment alignment, T defaultValue, Consumer<T> handle) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.alignment = alignment;
        this.defaultValue = value = defaultValue;
        this.handle = handle;
    }


    public void draw(int idx, int y, int width, int height, CustomWidget widget) {
        widget.add(widget.addRectangle(width, 27 + height, 0, idx % 2 == 0 ? 200 : 225, true), 97, 12 + y);
    }
}
