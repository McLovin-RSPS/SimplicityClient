package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.DataType;

public class MotherlodeMineHud extends RSInterface {

    public static void init() {
        RSInterface parent = addInterface(69304);
        parent.children(1);
        RSInterface box = addInterface(69306);
        box.children(3);
        box.width = 100;
        box.height = 80;
        parent.child(0, box.id, 10, 10);
        int widgetId = 69308;
        int childId = 0;
        final int boxWidth = box.width - 5;
        final int boxHeight = box.height;

        drawBox(widgetId, boxWidth, boxHeight, 0x564c42, 0x383023, 0x564c42, 128);
        box.child(childId++, widgetId++, 0, 0);

        addModel(widgetId, 7701, 1230, 0, 0, DataType.OLDSCHOOL);
        box.child(childId++, widgetId++, (box.width / 2), 60);

        addText(widgetId, "0", fonts, 4, 0xc8c8c8);
        box.child(childId, widgetId, (box.width / 2) - 8, (box.height / 2) - 32);
    }
}
