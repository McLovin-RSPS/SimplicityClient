package com.simplicity.client.widget;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.cache.definitions.Varp;

public class BossDisplayConfiguration extends RSInterface {

    public static void init(TextDrawingArea[] tda) {
        int parentId = 80200;
        RSInterface parent = addInterface(parentId);
        parent.children(2);

        int widgetId = parentId + 1;
        addClosableWindow(widgetId, 500, 304, true, "Boss Display Selection");
        parent.child(0, widgetId, 8, 16);

        RSInterface selection = createSelection(tda);
        parent.child(1, selection.id, 24,56);
    }

    private static RSInterface createSelection(TextDrawingArea[] tda) {
        int widgetId = 80300;
        RSInterface selection = addInterface(widgetId);
        selection.children(8 * 3);
        selection.width = 460;
        selection.height = 256;
        selection.scrollMax = 64 * 8;
        ++widgetId;

        for (int i = 0; i < 8; i++) {
            int color = i % 2 == 0 ? 0x564c42 : 0x483f33;
            int rectWidgetId = widgetId + i;
            int textWidgetId = widgetId + (i + 8);
            int itemWidgetId = widgetId + (i + 16);

            addRectangleClickable(rectWidgetId, 0, color, true, 460, 64);
            addText(textWidgetId, "---", tda, 2, 0xff981f);
            addItemModel(itemWidgetId, 0, 32, 32, 150);

            int topY = 64 * i;
            selection.child(i, rectWidgetId, 0, topY);
            selection.child(i + 8, textWidgetId, 200, topY + 20);
            selection.child(i + 16, itemWidgetId, 32, topY + 16);
        }
        return selection;
    }
}
