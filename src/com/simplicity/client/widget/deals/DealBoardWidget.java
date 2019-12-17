package com.simplicity.client.widget.deals;

import com.simplicity.client.widget.CustomWidget;

public class DealBoardWidget extends CustomWidget {

    public static final int ID = 66000;

    public DealBoardWidget() {
        super(ID);
    }

    @Override
    public String getName() {
        return "Donation Board";
    }

    @Override
    public void init() {
        add(addBackground(1315), 0, 0);

        add(addButtonList(new String[] { "Goodie Bag", "Item Deals", "Box Deals", "Special Deals" }, 1313, 1314, 1, OR1,
                20, false), 44, 50);

//        add(addPercentageBar(360, 100, 214, 183, false), 700, 700); //34, 284

//		add(addButton("Claim Now!", 111, 112, 1, OR1), 400, 277);
//
//		add(addButton("", 219, 220, 0, OR1), 20, 270);
//		add(addButton("", 221, 222, 0, OR1), 120, 273);
//		add(addButton("", 221, 222, 0, OR1), 250, 273);
//		add(addButton("", 221, 222, 0, OR1), 357, 273);

        add(addText("", 0), 20, 250);
        add(addText("", 0), 350, 250);

        add(addCenteredText("", 2), 256, 90);

//		add(addConfigButton("", 139, 140, 0, 0, 800), 148, 298);
//		add(addConfigButton("", 139, 140, 0, 0, 801), 278, 298);
//		add(addConfigButton("", 139, 140, 0, 0, 802), 378, 298);
    }
}
