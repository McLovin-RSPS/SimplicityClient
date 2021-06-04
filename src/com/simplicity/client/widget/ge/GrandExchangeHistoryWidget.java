package com.simplicity.client.widget.ge;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

public class GrandExchangeHistoryWidget extends CustomWidget {

    public static final int WIDGET_ID = 98_000;

    public GrandExchangeHistoryWidget() {
        super(WIDGET_ID);
    }

    @Override
    public void init() {
        add(addClosableWindow(484, 304, false, getName()), 16, 14);
        add(addStoneButton(58, 20, 0xff981f, 0xffffff, "Exchange"), 21, 22);
        add(scroll(10), 38 - 18, 51);
    }

    public RSInterface scroll(int amount) {
        RSInterface scroll = RSInterface.addInterface(id, 456, 263);
        scroll.componentId = id++;
        scroll.scrollMax = amount * 38;
        scroll.totalChildren(amount * 6);

        int scrollChild = 0, y = 0;

        for (int i = 0; i < amount; i++) {
            RSInterface.fillRectangle(id,456,38, (i & 1) == 0 ? 0x504842 : 0x4b4439, 255);
            RSInterface.addText(id + 1,"", RSInterface.fonts, 0, 0, false, false);
            RSInterface.interfaceCache[id + 1].useNewFonts = true;
            RSInterface.addText(id + 2,"", RSInterface.fonts, 0, 0xff933f, false, true);
            RSInterface.addText(id + 3,"", RSInterface.fonts, 1, 0xffb451, true, true);
            RSInterface.interfaceCache[id + 3].useNewFonts = true;
            RSInterface.addContainer(id + 4, 1, 1, 1, 0, 0, false, new String[0]);
            RSInterface.interfaceCache[id + 4].itemExamine = false;
            RSInterface.interfaceCache[id + 4].inv[0] = 4152;
            RSInterface.interfaceCache[id + 4].invStackSizes[0] = 5;
            RSInterface.interfaceCache[id + 4].parentID = scroll.id;
            RSInterface.addText(id + 5,"", RSInterface.fonts, 0, 0xffb83f, true, true);
            RSInterface.interfaceCache[id + 5].useNewFonts = true;
            scroll.child(scrollChild++, id, 0, y);
            scroll.child(scrollChild++, id + 1, 1, y + 2);
            scroll.child(scrollChild++, id + 2, 40, y + 15);
            scroll.child(scrollChild++, id + 3, 132 + 40, y + 12);
            scroll.child(scrollChild++, id + 4, 247 + 30, y + 3);
            scroll.child(scrollChild++, id + 5, 400, y + 14);
            id += 7;
            y += 38;
        }

        return scroll;
    }

    @Override
    public String getName() {
        return "Grand Exchange: Trade History";
    }

}
