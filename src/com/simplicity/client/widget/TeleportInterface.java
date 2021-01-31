package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

public class TeleportInterface extends RSInterface {

    public static final int MAX_CATEGORY_SIZE = 7;

    public static void init(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(61300);
        addSprite(61301, 1748);
        addText(61302, "Simplicity Teleportation Menu", tda, 2, 0xff981f, true, true);
        addText(61303, "Name", tda, 2, 15781715, true, true);
        addText(61304, "", tda, 0, 0xff981f, false, true);
        addText(61305, "Info", tda, 2, 15781715, true, true);
        addText(61306, "Favorite", tda, 2, 15781715, true, true);
        addButton(61307, 1750, "Add to favorites");
        addText(61308, "Favorites", tda, 2, 15781715, true, true);
        addCloseButton(39021, 39022, 39023);

        int x = 9, y = 25, child = 0;
        tab.totalChildren(17);
        tab.child(child++, 61301, x, y);
        tab.child(child++, 39021, 474+x, 9+y);
        tab.child(child++, 39022, 474+x, 9+y);
        tab.child(child++, 61302, 250+x, 8+y);
        tab.child(child++, 61303, 200+x, 36+y);
        tab.child(child++, 61304, 300+x, 36+y);
        tab.child(child++, 61305, 340+x, 36+y);
        tab.child(child++, 61306, 450+x, 36+y);
        tab.child(child++, 61307, 395+44+x, 274+y);
        tab.child(child++, 61308, 81+x, 227+y);
        for (int i = 0; i < 3; ++i) {
            if (i == 0 || i == 2) {
                addPixels(61318 + i, 0x50473D, 154, 15, 0, true);
                tab.child(child++, 61318 + i, x + 7, 242 + (i * 15) + y);
            }
            addClickableText(61314 + i, "Favorite " + i, "Quick-Teleport", tda, 0, 0xff981f, true, true, 50);
            tab.child(child++, 61314 + i, 57+x, 244 + (i * 15) + y);
        }
        tab.child(15, 61322, x + 6, 35+y);
        tab.child(16, 61360, x + 155, 58+y);
        RSInterface pages = addInterface(61322);
        interfaceCache[61302].parentID = 61300;
        pages.totalChildren(MAX_CATEGORY_SIZE * 4);
        child = 0;
        int yy = 5;
        for (int i = 0; i < MAX_CATEGORY_SIZE; i++) {
            addPixels(61323 + i, 0x50473D, 154, 35, i % 2 == 0 ? 0 : 200, true);
            pages.child(child++, 61323 + i, 0, yy - 5);
            yy += 35;
        }

        int yy2 = 5;
        configHoverButton(61343, "Select" + child, 1750, 1749, 1749, 1749, false, 61344, 61345, 61346, 61347, 61348, 61349);
        pages.child(child++, 61343, 0, yy2 - 5);
        yy2 += 35;
        configHoverButton(61344, "Select" + child, 1750, 1749, 1749, 1749, false, 61343, 61345, 61346, 61347, 61348, 61349);
        pages.child(child++, 61344, 0, yy2 - 5);
        yy2 += 35;
        configHoverButton(61345, "Select" + child, 1750, 1749, 1749, 1749, false, 61343, 61344, 61346, 61347, 61348, 61349);
        pages.child(child++, 61345, 0, yy2 - 5);
        yy2 += 35;
        configHoverButton(61346, "Select" + child, 1750, 1749, 1749, 1749, false, 61343, 61344, 61345, 61347, 61348, 61349);
        pages.child(child++, 61346, 0, yy2 - 5);
        yy2 += 35;
        configHoverButton(61347, "Select" + child, 1750, 1749, 1749, 1749, false, 61343, 61344, 61345, 61346, 61348, 61349);
        pages.child(child++, 61347, 0, yy2 - 5);
        yy2 += 35;
        configHoverButton(61348, "Select" + child, 1750, 1749, 1749, 1749, false, 61343, 61344, 61345, 61346, 61347, 61349);
        pages.child(child++, 61348, 0, yy2 - 5);
        yy2 += 35;
        configHoverButton(61349, "Select" + child, 1750, 1749, 1749, 1749, false, 61343, 61344, 61345, 61346, 61347, 61348);
        pages.child(child++, 61349, 0, yy2 - 5);

        yy = 5;
        for (int i = 0; i < MAX_CATEGORY_SIZE; i++) {
            addToItemGroup(60663 + i, 3, 5, 5, 5, false, null, null, null);
            pages.child(child++, 60663 + i, 2, yy - 4);
            addText(61333 + i, "Category "+ child +", "+ i, tda, 1, 0xff981f, false, true);
            pages.child(child++, 61333 + i, 45, yy + 4);
            yy += 35;
        }

        pages.width = 140;
        pages.height = 186;
        pages.scrollMax = Math.max(187, 35 * MAX_CATEGORY_SIZE);

        int tpIdx = 1;
        for (int index = 0; index < MAX_CATEGORY_SIZE; index++) {
            yy = 4;
            RSInterface r = addInterface(61360 + index);
            child = 0;
            r.totalChildren(50 * 5);
            int interfaceStart = 61600 + (index * 50);
            for (int i = 0; i < 50; ++i) {
                addPixels(interfaceStart + child, 0x50473D, 312, 35, i % 2 == 0 ? 0 : 200, true);
                r.child(child, interfaceStart + child, 13, yy - 3);
                child++;

                configHoverButton(interfaceStart + child, "Teleport", 1752, 1753, 1753, 1753, false, interfaceStart + child);
                r.child(child, interfaceStart + child, 13, yy - 4);
                child++;

                addText(interfaceStart + child, "Teleport " + (interfaceStart + child), tda, 1, 0xff981f, false, true);
                r.child(child, interfaceStart + child, 20, yy + 6);
                child++;

                addText(interfaceStart + child, "Description " + (interfaceStart + child), tda, 0, 0xff981f, true, true);
                r.child(child, interfaceStart + child, 185, yy + 3);
                child++;

                addConfigButtonWCacheLoader(interfaceStart + child, 61360, 1754, 1755, 30, 25, "Favorite", 1, 5, 1160 + tpIdx++);
                r.child(child, interfaceStart + child, 288, yy + 6);
                child++;

                yy += 36;
            }
            r.width = 325;
            r.height = 229;
            r.scrollMax = Math.max(230, 36 * 50);
        }
    }
}
