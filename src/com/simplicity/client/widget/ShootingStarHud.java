package com.simplicity.client.widget;

/**
 * A class that represents the Shooting star hud widget.
 *
 * @author Blake
 */
public class ShootingStarHud extends CustomWidget {

    public static final int WIDGET_ID = 100_000;

    public ShootingStarHud() {
        super(WIDGET_ID);
    }

    @Override
    public void init() {
        int w = 170;
        int h = 80;

        int x = 330;
        int y = 10;
        add(addRectangle(w, h, 0x373735, 150, true), x, y);
        add(addRectangle(w, h, 0, 150, false), x, y);
        add(addText("<spr=1884:-1> Shooting Star <spr=1884:-1>", 0, 0xff9800, true), x + w / 2, y += 10);
        add(addText("Total Miners: <col=ffffff>0</col>", 0, 0x9d9996, true), x + w / 2, y += 16);
        add(addText("Total Stardust Mined: <col=ffffff>0</col>", 0, 0x9d9996, true), x + w / 2, y += 16);
        add(addText("Stardust Left: <col=ffffff>0</col>", 0, 0x9d9996, true), x + w / 2, y += 16);
    }

    @Override
    public String getName() {
        return "Shooting Star";
    }

}
