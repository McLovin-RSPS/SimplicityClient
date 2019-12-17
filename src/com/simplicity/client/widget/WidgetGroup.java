package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WidgetGroup extends RSInterface {
    public int lines;

    public int scrollWidth;

    public int scrollHeight;

    public int difference;

    public int offsetX;

    public int offsetY;

    public int scrollMax;

    public List<WidgetComponent> multipleComponents;

    public List<WidgetComponent> singleComponents;

    public WidgetGroup(int lines, int scrollWidth, int scrollHeight, int difference, int offsetX, int offsetY) {
        this.lines = lines;
        this.scrollHeight = scrollHeight;
        this.scrollWidth = scrollWidth;
        this.difference = difference;
        this.multipleComponents = new ArrayList<WidgetComponent>();
        this.singleComponents = new ArrayList<WidgetComponent>();
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public WidgetGroup(int lines, int scrollWidth, int scrollHeight, int difference) {
        this(lines, scrollWidth, scrollHeight, difference, 0, 0);
    }

    public WidgetGroup(int scrollWidth, int scrollHeight, int scrollMax) {
        this(1, scrollWidth, scrollHeight, 0, 0, 0);
        this.scrollMax = scrollMax;
    }

    public WidgetGroup(int scrollWidth, int scrollHeight) {
        this(1, scrollWidth, scrollHeight, 0, 0, 0);
    }

    public WidgetGroup() {
        this(0, 0);
    }

    public void add(RSInterface widget, int x, int y) {
        WidgetComponent component = new WidgetComponent(new Point(x, y), widget);
        component.componentId = widget.componentId;
        multipleComponents.add(component);
    }

    public void addSingle(RSInterface widget, int x, int y) {
        WidgetComponent component = new WidgetComponent(new Point(x, y), widget);
        component.componentId = widget.componentId;
        singleComponents.add(component);
    }
}
