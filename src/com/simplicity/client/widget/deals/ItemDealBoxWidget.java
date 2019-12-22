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
        add(addSprite(1311), 0, 0);
        add(addCenteredText("Very long title display #", 1), 70, 1);

        int x = 14;

        add(addButton("#", 1312, 1312, 0, OR1), 49, 26 + x);
        add(addCenteredText("Expired #", 1), 70, 64+ x);
        add(addCenteredText("Remaining #", 1, 0xFFFFFF), 70,76+ x);
        add(addButton("", 1309, 1309, 0, 0), 20, 40+ x);
        add(addButton("", 1310, 1310, 0, 0), 105, 40+ x);
        add(addItemContainer(1, 1, 0, 0, null, "deal box container #"), 55, 30+ x);
    }


}
