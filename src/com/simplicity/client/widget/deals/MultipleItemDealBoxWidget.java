package com.simplicity.client.widget.deals;

import com.simplicity.client.widget.CustomWidget;

public class MultipleItemDealBoxWidget extends CustomWidget {

    public MultipleItemDealBoxWidget() {
        super(66700);
    }

    @Override
    public String getName() {
        return "Item Deals";
    }

    @Override
    public void init() {
        addWidget(DealBoardWidget.ID, 0, 0);

        int y = 125;

        ItemDealBoxWidget box = new ItemDealBoxWidget(id);
        addWidget(box.mainId, 256 - 220, y);

        id += 25;
        box = new ItemDealBoxWidget(id);
        addWidget(box.mainId, 256 - (139 / 2), y);

        id += 25;
        box = new ItemDealBoxWidget(id);
        addWidget(box.mainId, 256 + 82, y);

        id+=25;

        add(addButton("Box Rewards", 1313, 1314, 0, OR1), 216, 253);

        //add(addCenteredText("After donating for the\\nitems type ::donated", 1, 0xDC9221), 90, 255);
        //add(addCenteredText("PM a staff member after\\ninfo more info", 1, 0xDC9221), 416, 257);

    }
}
