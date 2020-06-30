package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

public class JewelleryBoxTeleport extends RSInterface {

    public static void init(TextDrawingArea[] fonts) {
        RSInterface parent = addInterface(65404);
        parent.children(1 + (6 * (4 + 5)));
        parent.width = 500;
        parent.height = 304;

        int currentChildId = 0;
        int currentWidgetId = 65406;

        addClosableWindow(currentWidgetId, parent.width, parent.height, true, "Jewellery Teleports");
        parent.child(currentChildId++, currentWidgetId, 8, 16);
        currentWidgetId += 50;

        final int boxWidth = 232;
        final int boxHeight = 78;
        int row = -1;
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0)
                row++;

            int x = 20 + ((i + 1) % 2 == 0 ? (boxWidth + 5) : 0);
            int y = 60 + ((boxHeight + 5) * row);

            addColorBox(currentWidgetId, boxWidth, boxHeight, 0x564c42);
            parent.child(currentChildId++, currentWidgetId++, x, y);

            drawBox(currentWidgetId, boxWidth + 1, boxHeight + 1, 1, 0, 0, 255);
            parent.child(currentChildId++, currentWidgetId++, x, y);

            addSprite(currentWidgetId, 0, null, 160, 160);
            parent.child(currentChildId++, currentWidgetId++, x + 25, y + 15);

            addText(currentWidgetId, "---", fonts, 0, 0xffffff);
            parent.child(currentChildId++, currentWidgetId++, x + 15, y + 47 + 6);

            for (int option = 0; option < 5; option++) {
                addClickableText(currentWidgetId, "---", "Teleport", fonts, 0,
                        0xff981f, false, true, 82);

                RSInterface.interfaceCache[currentWidgetId].disabledMouseOverColor = 0xffffff;

                parent.child(currentChildId++, currentWidgetId++, x + 130, y + 8 + (option * 13));
            }
        }
    }
}
