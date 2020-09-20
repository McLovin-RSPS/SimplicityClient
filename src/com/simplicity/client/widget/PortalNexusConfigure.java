package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

public class PortalNexusConfigure extends RSInterface {

    public static void init() {
        int widgetId = 88744;
        RSInterface parent = addInterface(widgetId);
        parent.width = 500;
        parent.height = 304;
        int childId = 0;
        parent.children(5);
        widgetId += 2;
        addClosableWindow(widgetId, parent.width, parent.height, true, "Portal Nexus");
        parent.child(childId++, widgetId, 8, 16);
        widgetId += 50;
        RSInterface first = createListing(widgetId, "Available -/-");
        parent.child(childId++, first.id, 30, 70);
        widgetId += 400;
        RSInterface second = createListing(widgetId, "Slots -/-");
        parent.child(childId++, second.id, first.width + 70, 70);
        widgetId += 400;
        addHoverButton(widgetId, 605, 605, 190, 24, "Confirm", -1, widgetId + 1, 1);
        parent.child(childId++, widgetId, 335, 265);
        widgetId++;
        addHoveredButton(widgetId, 606, 606, 190, 24, 17243);
        parent.child(childId, widgetId, 335, 265);
    }

    private static RSInterface createListing(int widget, String header) {
        RSInterface parent = addInterface(widget);
        parent.width = 150;
        parent.height = 240;
        parent.children(3);
        widget += 2;
        addText(widget, header, fonts, 1, 0xff981f);
        parent.child(2, widget, 25, 0);
        widget++;
        drawBox(widget, 142, 203, 2, 0, 0x333333, 250);
        parent.child(1, widget, 0, 20);
        widget++;
        RSInterface container = createContainer(widget);
        parent.child(0, container.id, 0, 20);
        return parent;
    }

    private static RSInterface createContainer(int widget) {
        RSInterface parent = addInterface(widget);
        parent.width = 125;
        parent.height = 200;
        final int boxHeight = 24;
        final int listingSize = 20;
        parent.scrollMax = boxHeight * listingSize;
        parent.children(listingSize * 2);
        widget += 2;
        for (int i = 0; i < listingSize; i++) {
            final int color = i % 2 == 0 ? 0x564c42 : 0x483f33;
            final int rectWidgetId = widget + i;
            final int textWidgetId = widget + (i + listingSize);
            final int topY = boxHeight * i;
            addRectangleClickable(rectWidgetId, 0, color, true, 123, boxHeight);
            addText(textWidgetId, "---", fonts, 1, 0xff981f);
            parent.child(i, rectWidgetId, 0, topY);
            parent.child(i + listingSize, textWidgetId, (parent.width / 2) - 12, topY + (boxHeight / 2) - 6);
        }
        return parent;
    }
}
