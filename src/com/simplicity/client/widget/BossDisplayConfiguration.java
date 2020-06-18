package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;

public class BossDisplayConfiguration extends RSInterface {

    private static final int OPTION_LEN = 8;

    public static void init(TextDrawingArea[] tda) {
        int parentId = 80200;
        RSInterface parent = addInterface(parentId);
        parent.children(2);

        int widgetId = parentId + 1;
        addClosableWindow(widgetId, 500, 304, true, "Boss Display Selection");
        parent.child(0, widgetId, 8, 16);

        RSInterface selection = createSelection(tda);
        parent.child(1, selection.id, 32,80);
    }

    private static RSInterface createSelection(TextDrawingArea[] tda) {
        int widgetId = 80300;

        RSInterface selection = addInterface(widgetId);
        selection.children(2);

        selection.width = 430;
        selection.height = 215;
        selection.scrollMax = 64 * OPTION_LEN;

        widgetId++;

        drawBox(widgetId, selection.width + 5, selection.height + 5, 2, 0x333333, 0x333333, 150);
        selection.child(1, widgetId, 0, 0);

        RSInterface container = createContainer(tda);
        selection.child(0, container.id, 2, 2);

        return selection;
    }

    private static RSInterface createContainer(TextDrawingArea[] tda) {
        int widgetId = 80302;
        RSInterface container = addTabInterface(widgetId);
        widgetId++;
        container.children(OPTION_LEN * 3);
        int childId = 0;
        for (int i = 0; i < OPTION_LEN; i++) {
            final int color = i % 2 == 0 ? 0x564c42 : 0x483f33;
            final int rectWidgetId = widgetId + i;
            final int textWidgetId = widgetId + (i + 8);
            final int itemWidgetId = widgetId + (i + 16);

            addRectangleClickable(rectWidgetId, 0, color, true, 428, 64);
            addText(textWidgetId, "---", tda, 1, 0xff981f);
            addItemModel(itemWidgetId, 0, 32, 32, 125);

            int topY = 64 * i;
            container.child(childId, rectWidgetId, 0, topY);
            container.child(childId + 8, textWidgetId, 128, topY + 20);
            container.child(childId + 16, itemWidgetId, 32, topY + 16);
            childId++;
        }
        return container;
    }
}
