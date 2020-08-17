package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

public class StorageContainer extends RSInterface {

    public static void init() {
        RSInterface parent = addInterface(42125);
        parent.children(3);
        int currentWidgetId = 42127;

        addClosableWindow(currentWidgetId, 500, 304, true, "Costume Storage");
        parent.child(0, currentWidgetId, 8, 16);

        currentWidgetId += 50;

        addText(currentWidgetId, "% / % Capacity", fonts, 1, 0xff981f);
        parent.child(2, currentWidgetId++, 24, 66);

        RSInterface box = addInterface(currentWidgetId);
        parent.child(1, box.id, 20, 90);
        box.children(4);

        currentWidgetId += 2;

        box.width = 476;
        box.height = 216;
        final int boxWidth = box.width - 5;
        final int boxHeight = box.height - 20;
        int currentChildId = 0;

        addColorBox(currentWidgetId, boxWidth, boxHeight, 0x564c42);
        box.child(currentChildId++, currentWidgetId++, 0, 0);

        drawBox(currentWidgetId, boxWidth, boxHeight, 0, 0, 0, 255);
        box.child(currentChildId++, currentWidgetId++, 0, 0);

        addText(currentWidgetId, "You have stored these items:", fonts, 1, 0xff981f);
        box.child(currentChildId++, currentWidgetId++, 12, 8);

        RSInterface container = addInterface(currentWidgetId);
        container.width = 446;
        container.height = 172;
        container.scrollMax = 32 * 6;
        box.child(currentChildId, container.id, 8, 21);
        container.children(1);

        currentWidgetId += 2;

        addItemContainer(currentWidgetId, new int[] {8, 8}, new int[] {6, 8}, new String[] {"Remove", "Remove-5", "Remove-10", "Remove-All", "Remove-X"}, true);
        container.child(0, currentWidgetId, 0, 0);
    }
}
