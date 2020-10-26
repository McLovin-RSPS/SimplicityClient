package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.SpriteLoader;

public class ItemsKeptOnDeath extends RSInterface {

    private static final int PARENT = 120000;
    private static int parentContainerWidgetId;

    public static void init() {
        int widgetId = PARENT;
        RSInterface parent = addInterface(widgetId);
        parent.totalChildren(4);

        widgetId += 2;
        addClosableWindow(widgetId, 500, 322, false, "Items Kept on Death");
        parent.child(0, widgetId, 6, 6);

        widgetId += 50;
        addRectangle(widgetId, 0, 0x000000, false, 480, 272);
        parent.child(1, widgetId, 15, 45);

        widgetId++;
        int parentId = widgetId;
        widgetId = initRightPanel(parentId);
        parent.child(2, parentId, 350, 45);

        widgetId++;
        parentId = widgetId;
        initLeftPanel(widgetId);
        parent.child(3, parentId, 15, 45);
    }

    private static int initLeftPanel(int widgetId) {
        RSInterface parent = addInterface(widgetId);
        parentContainerWidgetId = parent.id;
        parent.totalChildren(2);
        parent.height = 272;
        parent.width = 320;
        parent.scrollMax = 273;

        int x = 0, y = 0;
        widgetId += 2;
        RSInterface keptContainer = createItemContainer(widgetId, "Items that are @whi@KEPT:");
        interfaceCache[keptContainer.children[1]].onItemTrasmit = widget -> {
            int[] items = widget.inv;
            if (items != null) {
                int occupied = 0;
                for (int i : items) {
                    if (i > 0)
                        occupied++;
                }
                occupied--;
                int rows = occupied / 7;
                if (occupied - (rows * 7) > 0)
                    rows++;
                keptContainer.height = Math.max((rows * 44) + 1, 60) + 8;
                keptContainer.childY[2] = keptContainer.height - 2;
                rebuildLeftContainer();
            }
        };
        parent.child(0, keptContainer.id, x, y);
        widgetId += 4;
        y += keptContainer.height;
        RSInterface lostContainer = createItemContainer(widgetId, "Items that are @red@LOST:");
        interfaceCache[lostContainer.children[1]].onItemTrasmit = widget -> {
            int[] items = widget.inv;
            if (items != null) {
                int occupied = 0;
                for (int i : items) {
                    if (i > 0)
                        occupied++;
                }
                occupied--;
                int rows = occupied / 7;
                if (occupied - (rows * 7) > 0)
                    rows++;
                lostContainer.height = Math.max((rows * 44) + 10, 60) + 8;
                lostContainer.childY[2] = lostContainer.height - 2;
            }
        };
        parent.child(1, lostContainer.id, x, y);
        return widgetId;
    }

    private static int initRightPanel(int widgetId) {
        RSInterface parent = addInterface(widgetId);
        parent.totalChildren(12);
        int boxWidth = 150;
        int boxHeight = 272;

        widgetId += 2;
        addVerticalLine(widgetId, boxHeight, 0x000000, 255);
        parent.child(0, widgetId, 0, 0);

        String text = "Showing info for:";
        int fontId = 1;
        widgetId++;
        addText(widgetId, text, fonts, fontId, 0xFF981F);
        parent.child(1, widgetId, centerText(fontId, text, boxWidth), 2);

        text = "Guide risk value:";
        fontId = 0;
        widgetId++;
        addText(widgetId, text, fonts, fontId, 0xFF981F);
        parent.child(2, widgetId, centerText(fontId, text, boxWidth), 242);

        widgetId++;
        text = "0";
        addText(widgetId, text, fonts, fontId, 0xffffff);
        parent.child(3, widgetId, centerText(fontId, text, boxWidth), 258);

        widgetId++;
        addDeathButton(widgetId, 130, 36, 700);
        parent.child(4, widgetId, centerWidget(130, 150), 20);

        widgetId += 3;
        text = "Protect Item prayer\\nenabled";
        addText(widgetId, text, fonts, fontId, 0xFF981F);
        int x = (130 - fonts[0].getTextWidth("Protect Item prayer")) / 2;
        parent.child(5, widgetId, 22 + x, (20 + (36 - fonts[fontId].anInt1497) / 2) - 4);

        widgetId++;
        addDeathButton(widgetId, 130, 36, 701);
        parent.child(6, widgetId, centerWidget(130, 150), 60);

        widgetId += 3;
        text = "PK Skull active";
        x = (130 - fonts[0].getTextWidth(text)) / 2;
        addText(widgetId, text, fonts, fontId, 0xFF981F);
        parent.child(7, widgetId, 22 + x, 60 + (36 - fonts[fontId].anInt1497) / 2);

        widgetId++;
        addDeathButton(widgetId, 130, 36, 702);
        parent.child(8, widgetId, centerWidget(130, 150), 100);

        widgetId += 3;
        text = "Killed by a player";
        x = (130 - fonts[0].getTextWidth(text)) / 2;
        addText(widgetId, text, fonts, fontId, 0xFF981F);
        parent.child(9, widgetId, 22 + x, 100 + (36 - fonts[fontId].anInt1497) / 2);

        widgetId++;
        addDeathButton(widgetId, 130, 36, 703);
        parent.child(10, widgetId, centerWidget(130, 150), 140);

        widgetId += 3;
        text = "Wilderness beyond";
        x = (130 - fonts[0].getTextWidth(text)) / 2;
        addText(widgetId, "Wildernes beyond\\nlevel 20", fonts, fontId, 0xFF981F);
        parent.child(11, widgetId, 22 + x, (140 + (36 - fonts[fontId].anInt1497) / 2) - 4);
        return widgetId;
    }

    private static RSInterface createItemContainer(int widgetId, String title) {
        RSInterface parent = addInterface(widgetId);
        parent.height = 60;
        parent.totalChildren(3);

        int fontId = 0;
        widgetId++;
        addText(widgetId, title, fonts, fontId, 0xFF981F);
        parent.child(0, widgetId, 6, 6);

        widgetId++;
        addItemContainer(widgetId, new int[] {8, 8}, new int[] {7, 6}, new String[] {}, false);
        parent.child(1, widgetId, 6, 20);

        widgetId++;
        addHorizontalLine(widgetId, 320, 0x000000, 255);
        parent.child(2, widgetId, 0, 58);
        return parent;
    }

    private static RSInterface addDeathButton(int widgetId, int width, int height, int configId) {
        RSInterface button = addInterface(widgetId);
        button.width = width;
        button.height = height;
        button.totalChildren(2);

        widgetId++;
        addDynamicButton(widgetId, "Select", width, height, true);
        button.child(0, widgetId, 0, 0);

        widgetId++;
        RSInterface cfg = addInterface(widgetId);
        cfg.type = 5;
        cfg.width = 16;
        cfg.height = 16;
        cfg.valueCompareType = new int[1];
        cfg.requiredValues = new int[1];
        cfg.valueCompareType[0] = 1;
        cfg.requiredValues[0] = 1;
        cfg.valueIndexArray = new int[1][3];
        cfg.valueIndexArray[0][0] = 5;
        cfg.valueIndexArray[0][1] = configId;
        cfg.valueIndexArray[0][2] = 0;
        cfg.disabledSprite = SpriteLoader.sprites[1465];
        cfg.enabledSprite = SpriteLoader.sprites[1466];
        button.child(1, widgetId, 10, (height - cfg.enabledSprite.myHeight) / 2);
        return button;
    }

    private static void rebuildLeftContainer() {
        RSInterface rsi = interfaceCache[parentContainerWidgetId];
        rsi.childY[1] = interfaceCache[rsi.children[0]].height;
    }
}
