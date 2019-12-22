package com.simplicity.client.widget.deals;

import com.simplicity.client.widget.CustomWidget;

public class ItemDealBoardWidget extends CustomWidget {

    public ItemDealBoardWidget() {
        super(66600);
    }

    @Override
    public String getName() {
        return "Item Deals";
    }

    @Override
    public void init() {
        addWidget(DealBoardWidget.ID, 0, 0);

        ItemDealBoxWidget box = new ItemDealBoxWidget(id);
        addWidget(box.mainId, 256 - (139 / 2), 130);
    }
}
