package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

public class BlastFurnaceCoffer extends CustomWidget {

    public BlastFurnaceCoffer() {
        super(94500);
    }

    @Override
    public void init() {
        int x = 458;
        int y = 6;
        add(addBox(48,58, 2, 0xe0e0c, 0x474745, 255, 0x544b40,255), x, y);
        RSInterface text = addText("Coffer", 1, 0xff981f);
        text.width = 48;
        text.height = 48;
        text.centerText = true;
        add(text, x, y + 5);
        RSInterface item = addItemContainer(1,1,0,0,null,null);
        item.inv[0] = 996;
        add(item, x + 6,y + 21);
    }

    @Override
    public String getName() {
        return "Blast Furnace Coffer";
    }

}
