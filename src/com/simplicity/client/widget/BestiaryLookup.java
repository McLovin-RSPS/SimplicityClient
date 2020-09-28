package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.cache.definitions.MobDefinition;

public class BestiaryLookup extends RSInterface {

    public static final int ID = 90450;
    private static final int BOX_HEIGHT = 48;
    private static int npcModelWidgetId;
    private static int npcNameWidgetId;
    private static final int MAX_LIST_SIZE = 100;

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
        npcNameWidgetId = widgetId;
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
        npcModelWidgetId = widgetId;
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
        addText(widgetId, "Attack bonus:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Melee defence:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Ranged defence:", fonts, 0, 0xff981f);
        parent.child(childId++, widgetId++, x, y);

        y += 16;
        addText(widgetId, "Magic defence:", fonts, 0, 0xff981f);
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

        x = 28 + font.getTextWidth("Attack bonus:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Melee defence:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Ranged defence:") + 3;
        y += 16;
        addText(widgetId, "???", fonts, 0, 0xFFFFFF);
        parent.child(childId++, widgetId++, x, y);

        x = 28 + font.getTextWidth("Magic defence:") + 3;
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
        RSInterface.interfaceCache[widgetId].disabledMouseOverColor = 0x808080;
        RSInterface.interfaceCache[widgetId].contentType = 1420;
        parent.child(childId++, widgetId, x, y);

        x = 155;
        y = 90;
        RSInterface listing = createList(91990);
        parent.child(childId, listing.id, x, y);
    }

    private static RSInterface createList(int widget) {
        RSInterface parent = addInterface(widget);
        parent.width = 325;
        parent.height = 206;
        final int boxWidth = parent.width - 4;
        final int yPadding = 6;
        parent.scrollMax = (BOX_HEIGHT + yPadding) * MAX_LIST_SIZE;
        parent.children(MAX_LIST_SIZE * 6);
        widget += 2;
        for (int i = 0; i < MAX_LIST_SIZE; i++) {
            final int color = i % 2 == 0 ? 0x564c42 : 0x483f33;
            final int rectWidgetId = widget + i;
            final int itemNameWidgetId = widget + (i + MAX_LIST_SIZE);
            final int boxWidgetId = widget + (i + (MAX_LIST_SIZE * 2));
            final int itemSpriteId = widget + (i + (MAX_LIST_SIZE * 3));
            final int quantityWidgetId = widget + (i + (MAX_LIST_SIZE * 4));
            final int rarityWidgetId = widget + (i + (MAX_LIST_SIZE * 5));
            final int topY = (BOX_HEIGHT + yPadding) * i;

            addRectangleClickable(rectWidgetId, 0, color, true, boxWidth, BOX_HEIGHT);
            addSprite(itemSpriteId, 0, null, 150, 150);
            addText(itemNameWidgetId, ItemDefinition.forID(4151).name, fonts, 1, 0xff981f);
            addText(quantityWidgetId, "1", fonts, 1, 0xff981f);
            addText(rarityWidgetId, "1/256", fonts, 1, 0xff981f);
            drawBox(boxWidgetId, boxWidth + 1, BOX_HEIGHT + 1, 2, 0, 0, 255);

            parent.child(i, rectWidgetId, 0, topY);
            parent.child(i + MAX_LIST_SIZE, itemNameWidgetId, 64, topY + (BOX_HEIGHT / 2) - 8);
            parent.child(i + (MAX_LIST_SIZE * 4), quantityWidgetId, 190, topY + (BOX_HEIGHT / 2) - 8);
            parent.child(i + (MAX_LIST_SIZE * 5), rarityWidgetId, 268, topY + (BOX_HEIGHT / 2) - 8);
            parent.child(i + (MAX_LIST_SIZE * 2), boxWidgetId, 0, topY);
            parent.child(i + (MAX_LIST_SIZE * 3), itemSpriteId, 16, topY + ((BOX_HEIGHT - 32) / 2));
        }
        return parent;
    }

    public static void rebuild(int npcId, Object[][] dropList) {
        RSInterface.interfaceCache[npcModelWidgetId].mediaID = MobDefinition.forID(npcId).models[0];
        RSInterface.interfaceCache[npcNameWidgetId].message = MobDefinition.forID(npcId).name;

        final int listingSize = dropList == null ? 0 : dropList.length;
        RSInterface list = RSInterface.interfaceCache[91990];
        list.scrollMax = (BOX_HEIGHT + 6) * listingSize;
        final int widget = list.id + 2;
        for (int i = 0; i < listingSize; i++) {
            Object[] entry = dropList[i];
            final int itemNameWidgetId = widget + (i + MAX_LIST_SIZE);
            final int itemSpriteId = widget + (i + (MAX_LIST_SIZE * 3));
            final int quantityWidgetId = widget + (i + (MAX_LIST_SIZE * 4));
            final int rarityWidgetId = widget + (i + (MAX_LIST_SIZE * 5));
            final int itemId = (Integer) entry[0];
            final int minQuantity = (Integer) entry[1];
            final int maxQuantity = (Integer) entry[2];
            final int rarity = (Integer) entry[3];

            RSInterface.interfaceCache[itemNameWidgetId].message = ItemDefinition.forID(itemId).name;
            RSInterface.interfaceCache[itemSpriteId].itemSpriteId1 = itemId;
            RSInterface.interfaceCache[itemSpriteId].disabledSprite = null;
            RSInterface.interfaceCache[itemSpriteId].itemSpriteZoom1 = 150;
            RSInterface.interfaceCache[itemSpriteId].itemSpriteZoom2 = 150;
            RSInterface.interfaceCache[itemSpriteId].itemSpriteId2 = itemId;

            RSInterface.interfaceCache[quantityWidgetId].message = minQuantity == maxQuantity ?
                    Integer.toString(minQuantity) : minQuantity +"-"+ maxQuantity;
            RSInterface.interfaceCache[rarityWidgetId].message = "1/"+ rarity;
        }
    }
}
