package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

public class BossDisplayConfiguration extends RSInterface {

    private static final int OPTION_LEN = 8;

    public static void init(TextDrawingArea[] tda) {
        int widgetId = 80200;
        RSInterface parent = addInterface(widgetId);
        parent.children(3);
        parent.width = 500;
        parent.height = 304;

        widgetId += 2;
        addClosableWindow(widgetId, parent.width, parent.height, true, "Boss Lair Jars");
        parent.child(0, widgetId, 8, 16);
        widgetId += 50;
        RSInterface selection = createSelection(tda, widgetId);
        parent.child(2, selection.id, 32,80);
        widgetId += 50;
        drawBox(widgetId, selection.width + 23, selection.height + 10, 5, 0x333333,
                0x333333, 250);
        parent.child(1, widgetId, 28, 76);
    }

    private static RSInterface createSelection(TextDrawingArea[] tda, int widgetId) {
        RSInterface selection = addInterface(widgetId);
        selection.children(OPTION_LEN * 3);
        selection.width = 430;
        selection.height = 215;
        selection.scrollMax = 64 * OPTION_LEN;
        widgetId++;
        int childId = 0;
        for (int i = 0; i < OPTION_LEN; i++) {
            final int color = i % 2 == 0 ? 0x564c42 : 0x483f33;
            final int rectWidgetId = widgetId + i;
            final int textWidgetId = widgetId + (i + 8);
            final int itemWidgetId = widgetId + (i + 16);

            addRectangleClickable(rectWidgetId, 0, color, true, 428, 64);
            addText(textWidgetId, "---", tda, 1, 0xff981f);
            addSprite(itemWidgetId, 0, null, 64, 64);

            int topY = 64 * i;
            selection.child(childId, rectWidgetId, 0, topY);
            selection.child(childId + 8, textWidgetId, 128, topY + 22);
            selection.child(childId + 16, itemWidgetId, 32, topY + 16);
            childId++;
        }

        return selection;
    }
}
