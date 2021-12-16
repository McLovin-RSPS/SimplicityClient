package com.simplicity.client.widget;

import com.simplicity.client.cache.DataType;

public class SlayerPartner extends CustomWidget {

    public static final int WIDGET_ID = 98_500;

    public SlayerPartner() {
        super(WIDGET_ID);
    }

    @Override
    public void init() {
        int x = 105;
        int y = 68;
        add(addClosableWindow(300, 200, false, getName()), x, y);
        y += 24;
        add(addText("Current partner: <col=ff0000>(none)</col>", 1, 0xff981f), x + 10, y + 16);
        add(addText("Use the button to set yourself a Slayer Partner.", 0, 0xff981f), x + 10, y + 40);
        add(addText("If your partner's Slayer level is <col=ffffff>as high as yours</col>,\\nwhenever a task is <col=ffffff>assigned to them</col>, you'll receive the\\nsame task, as long as you are eligible for it.", 0, 0xff981f), x + 10, y + 60);
        add(addText("If your Slayer level is <col=ffffff>as high as your partner's</col>,\\nwhenever a task is <col=ffffff>assigned to you</col>, they'll receive the\\nsame task, as long as they are eligible for it.", 0, 0xff981f), x + 10, y + 100);
        add(addDynamicButton("New partner", 2, 0xff981f, 0, -3, 140,30), x + 78, y + 137);
        add(addModelSprite(33, 58, 2388, 350, 169, 112, DataType.REGULAR), x + 254, y + 6);
    }

    @Override
    public String getName() {
        return "Slayer Partner";
    }

}
