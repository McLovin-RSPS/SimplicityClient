package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.DataType;

public class PortalNexusTeleportMenu extends RSInterface {

    public static void init() {
        int widgetId = 46380;
        RSInterface parent = addInterface(widgetId);
        parent.width = 500;
        parent.height = 304;
        int childId = 0;
        parent.children(3);
        widgetId += 2;
        addClosableWindow(widgetId, parent.width, parent.height, true, "Portal Nexus");
        parent.child(childId++, widgetId, 8, 16);
        widgetId += 50;

        addModel(widgetId, 36050, 1360, 0, 0, DataType.OLDSCHOOL);
        parent.child(childId++, widgetId++, 410, 235);

        RSInterface menu = createListing(widgetId);
        parent.child(childId, menu.id, 20, 55);
    }

    private static RSInterface createListing(int widget) {
        RSInterface parent = addInterface(widget);
        parent.width = 300;
        parent.height = 240;
        parent.children(2);
        widget += 2;
        drawBox(widget, 265, 218, 2, 0, 0x333333, 250);
        parent.child(1, widget, 0, 20);
        widget++;
        RSInterface container = addInterface(widget);
        parent.child(0, container.id, 0, 20);
        final int parentWidth = 250;
        container.width = parentWidth;
        container.height = 215;
        final int boxHeight = 24;
        final int listingSize = 20;
        container.scrollMax = boxHeight * listingSize;
        container.children(listingSize * 2);
        widget += 2;
        for (int i = 0; i < listingSize; i++) {
            final int color = i % 2 == 0 ? 0x564c42 : 0x483f33;
            final int rectWidgetId = widget + i;
            final int textWidgetId = widget + (i + listingSize);
            final int topY = boxHeight * i;
            addRectangleClickable(rectWidgetId, 0, color, true, parentWidth, boxHeight);
            addText(textWidgetId, "---", fonts, 1, 0xff981f);
            container.child(i, rectWidgetId, 0, topY);
            container.child(i + listingSize, textWidgetId, (container.width / 2) - 12, topY + (boxHeight / 2) - 6);
        }
        return parent;
    }
}
