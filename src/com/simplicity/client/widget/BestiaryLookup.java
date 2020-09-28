package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;

public class BestiaryLookup extends RSInterface {

    public static final int ID = 90450;

    public static void init() {
        int widgetId = ID;
        RSInterface parent = addInterface(ID);
        parent.width = 500;
        parent.height = 304;
        parent.children(24);

        int childId = 0;
        int x = 8;
        int y = 16;
        widgetId += 2;
        addClosableWindow(widgetId, parent.width, parent.height, true, "Simplicity Bestiary");
        parent.child(childId++, widgetId, x, y);

        x = 55;
        y += 45;
        widgetId += 50;
        addText(widgetId, "Monster", fonts, 2, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        x += 165;
        addText(widgetId, "Item Name", fonts, 2, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        x += 100;
        addText(widgetId, "Quantity", fonts, 2, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        x += 95;
        addText(widgetId, "Chance", fonts, 2, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        x = 55;
        x += 25;
        y += 100;
        addModel(widgetId, 5065, 1000, 0, 0, DataType.REGULAR);
        parent.child(childId++, widgetId++, x, y);

        x = 28;
        y += 20;
        addText(widgetId, "Combat level:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Health:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Respawn time:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Attack level:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Defence level:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Ranged level:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Magic level:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Aggressive:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        TextDrawingArea font = fonts[0];
        x = 28 + font.getTextWidth("Combat level:") + 3;
        y = 181;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Health:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Respawn time:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Attack level:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Defence level:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Ranged level:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Magic level:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Aggressive:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 26;
        y = 28;
        addClickableText(widgetId, "Search Monster", "Search", fonts, 0, 0xff981f,
                fonts[0].getTextWidth("Search Monster"), 13);
        RSInterface.interfaceCache[widgetId].shadowed = true;
        RSInterface.interfaceCache[widgetId].hovers = true;
        RSInterface.interfaceCache[widgetId].disabledMouseOverColor = 0x808080;
        parent.child(childId++, widgetId, x, y);

        x = 155;
        y = 90;
        RSInterface listing = createList(91990);
        parent.child(childId, listing.id, x, y);
    }

    private static RSInterface createList(int widget) {
        RSInterface parent = addInterface(widget);
        widget += 2;
        parent.width = 325;
        parent.height = 206;
        final int boxHeight = 48;
        final int boxWidth = parent.width - 4;
        final int listingSize = 20;
        parent.scrollMax = boxHeight * listingSize;
        parent.children(listingSize * 6);
        widget += 2;
        int yPadding = 4;
        for (int i = 0; i < listingSize; i++) {
            final int color = i % 2 == 0 ? 0x564c42 : 0x483f33;
            final int rectWidgetId = widget + i;
            final int itemNameWidgetId = widget + (i + listingSize);
            final int boxWidgetId = widget + (i + (listingSize * 2));
            final int itemSpriteId = widget + (i + (listingSize * 3));
            final int quantityWidgetId = widget + (i + (listingSize * 4));
            final int rarityWidgetId = widget + (i + (listingSize * 5));
            final int topY = (boxHeight + yPadding) * i;

            addRectangleClickable(rectWidgetId, 0, color, true, boxWidth, boxHeight);
            addSprite(itemSpriteId, 4151, null, 64, 64);
            addText(itemNameWidgetId, ItemDefinition.forID(4151).name, fonts, 1, 0xff981f);
            addText(quantityWidgetId, "1", fonts, 1, 0xff981f);
            addText(rarityWidgetId, "1/256", fonts, 1, 0xff981f);
            drawBox(boxWidgetId, boxWidth + 1, boxHeight + 1, 2, 0, 0, 255);

            parent.child(i, rectWidgetId, 0, topY);
            parent.child(i + listingSize, itemNameWidgetId, 64, topY + (boxHeight / 2) - 8);
            parent.child(i + (listingSize * 4), quantityWidgetId, 190, topY + (boxHeight / 2) - 8);
            parent.child(i + (listingSize * 5), rarityWidgetId, 268, topY + (boxHeight / 2) - 8);
            parent.child(i + (listingSize * 2), boxWidgetId, 0, topY);
            parent.child(i + (listingSize * 3), itemSpriteId, 16, topY + ((boxHeight - 32) / 2));
        }
        return parent;
    }
}
