package com.simplicity.client.widget.deals;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;

public class DealBoardWidget extends CustomWidget {

    public static final int ID = 66000;

    public static int PBAR_ID;

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

        add(addButtonList(new String[] { "Goodie Bag", "Item Deals", "Box Deals", "Special Deals" }, 954, 955, 1, OR1,
                5, false), 18, 51);

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

        int width = 485;
        int height = 19;
        int x = 14;
        int y = 287;

        add(drawBox(481,height + 53, 2, 0x5a5245,0x383023, 255), x, y - 43);
        add(addHorizontalLine(480, 0x5a5245, 150), x + 1, y - 23);
        add(addSprite(1755), x + 3, y - 40);
        add(addText("Promotions:", 2), x + 22, y - 39);
        add(addText("Reach the goals by the end of the week to receive bonus rewards.", 1), x + 23 + 83, y - 39);

        PBAR_ID = id;
        RSInterface pbar = addProgressBar(width - 6,height - 2);
        pbar.progressBackColor = 0x3c3328;
        pbar.progressBackAlpha = 250;
        pbar.fillColor = 0xeec00a;
        pbar.drawProgressText = false;
        pbar.message = 50 + "/" + 100;
        pbar.enabledMessage = 50 + "/" + 100;
        add(pbar, x + 4, y - 1);
        add(addRectangle(width - 11, height,0x322b19, 5, false), x + 3, y - 2);

        add(addSprite(1412), x, y - 9);
        add(addSprite(1720), x + 11, y + 2);

        add(addSprite(1849), x + 2 + 102, y - 10);
        add(addSprite(1849), x + 2 + 222, y - 10);
        add(addSprite(1849), x + 2 + 342, y - 10);
        add(addSprite(1849), x + 2 + width - 43, y - 10);

        add(addText("", 0, 0xffff00, false), x + 9, y - 21);
        add(addText("", 0, 0xffff00, false), x + 6 + 103, y - 21);
        add(addText("", 0, 0xffff00, false), x + 6 + 222, y - 21);
        add(addText("", 0, 0xffff00, false), x + 6 + 342, y - 21);
        add(addText("", 0, 0xffff00, false), x + 6 + width - 43, y - 21);

        int[] cX = new int[] { x + 3 + 103, x + 4 + 222, x + 4 + 342, x + 4 + width - 43 };

        for (int i = 0; i < cX.length; i++) {
            RSInterface container = addItemContainer(1, 1, 44, 0, new String[0], "");
            add(container, cX[i], y - 7);
        }

        add(addHorizontalSeparator(483,true), 14, 80);
    }
}
