package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

public class PetHouse extends RSInterface {

    public static void init() {
        RSInterface parent = addInterface(56005);
        parent.children(3);
        int currentWidgetId = 56007;

        addClosableWindow(currentWidgetId, 300, 280, true, "Pet house");
        parent.child(0, currentWidgetId, 112, 16);

        currentWidgetId += 50;

        RSInterface toggleButton = addInterface(currentWidgetId);
        parent.child(2, toggleButton.id, 124, 261);
        toggleButton.children(2);

        currentWidgetId += 2;

        addText(currentWidgetId, "Allow pets to roam", fonts, 1, 0xff981f);
        toggleButton.child(1, currentWidgetId++, 20, 0);

        addToggleButton(currentWidgetId, 132, 1640, 15, 15, "Toggle");
        toggleButton.child(0, currentWidgetId++, 0, 0);

        RSInterface box = addInterface(currentWidgetId);
        parent.child(1, box.id, 124, 58);
        box.children(4);

        currentWidgetId += 2;

        box.width = 276;
        box.height = 216;
        final int boxWidth = box.width - 5;
        final int boxHeight = box.height - 20;
        int currentChildId = 0;

        addColorBox(currentWidgetId, boxWidth, boxHeight, 0x564c42);
        box.child(currentChildId++, currentWidgetId++, 0, 0);

        drawBox(currentWidgetId, boxWidth, boxHeight, 0, 0, 0xCC7A19, 255);
        box.child(currentChildId++, currentWidgetId++, 0, 0);

        addText(currentWidgetId, "You have stored these pets:", fonts, 1, 0xff981f);
        box.child(currentChildId++, currentWidgetId++, 12, 8);

        RSInterface container = addInterface(currentWidgetId);
        container.width = 246;
        container.height = 172;
        container.scrollMax = 32 * 6;
        box.child(currentChildId, container.id, 8, 21);
        container.children(1);

        currentWidgetId += 2;

        addItemContainer(currentWidgetId, new int[] {8, 8}, new int[] {6, 8}, new String[] {"Remove"}, true);
        container.child(0, currentWidgetId, 0, 0);
    }
}
