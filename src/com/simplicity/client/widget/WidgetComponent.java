package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

import java.awt.*;

public class WidgetComponent {
    public Point point;

    public RSInterface component;

    public int componentId;

    public WidgetComponent(Point point, RSInterface component) {
        this.point = point;
        this.component = component;
    }
}
