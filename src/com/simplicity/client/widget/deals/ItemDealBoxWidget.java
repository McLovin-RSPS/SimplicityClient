package com.simplicity.client.widget.deals;

import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;

public class ItemDealBoxWidget extends CustomWidget {

    public ItemDealBoxWidget(int id) {
        super(id);
        Widget.init(this);
    }

    @Override
    public String getName() {
        return "Deal Item Box";
    }

    @Override
    public void init() {
        add(drawBox(138,108, 2, 0x5a5245,0x383023, 255), 0, 0);
        add(addCenteredText("Very long title display #", 1), 70, 9);

        int x = 14;

        add(addButton("#", 1941, 1941, 0, OR1), 49, 26 + x);
        add(addCenteredText("Expired #", 1), 70, 62+ x);
        add(addCenteredText("Remaining #", 1, 0xFFFFFF), 70,76+ x);
        add(addButton("", 1309, 1309, 0, 0), 20, 40+ x);
        add(addButton("", 1310, 1310, 0, 0), 105, 40+ x);
        add(addItemContainer(1, 1, 0, 0, null, "deal box container #"), 51, 29+ x);
        add(drawBox(138,32, 2, 0x5a5245,0x383023, 255), 0, 108 + 2);
        add(addText("#", 1, OR1, true), 67, 108 + 10);
    }


}
