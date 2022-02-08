package com.simplicity.client.widget;

/**
 * A class that represents the Equipment bonuses widget.
 *
 * @author Blake
 */
public class EquipmentBonuses extends CustomWidget {

    private static final int WIDGET_ID = 21172;

    private static final String[] REGULAR_BONUS = {"Stab", "Slash", "Crush", "Magic", "Range"};

    private static final String[] PRE_EOC_BONUS = {"Summoning", "Absorb Melee", "Absorb Magic", "Absorb Ranged"};

    private static final String[] OTHER_BONUS = {"Melee strength", "Ranged strength", "Magic damage", "Prayer"};

    public EquipmentBonuses() {
        super(WIDGET_ID);
    }

    @Override
    public void init() {
        int x = 7;
        int y = 6;

        add(addClosableWindow(500, 324, false, getName()), x, y);
        addWidget(1644, x - 4, y + 58);
        getWidget(1688).usableItemInterface = true;
        add(addDynamicButton("Set Bonus", 0, 0xff981f, 70, 26).setHidden(true), x + 56, y + 288);
        add(addChar(), x + 168, y + 200);

        y -= 4;

        add(addText("Attack bonus", 2, 0xff981f), x + 346, y + 40);

        for (int i = 0; i < REGULAR_BONUS.length; i++) {
            add(addText(REGULAR_BONUS[i] + ": #", 1, 0xff981f), x + 354, y + 54 + (i * 13));
        }

        y -= 2;

        add(addText("Defence bonus", 2, 0xff981f), x + 346, y + 122);

        for (int i = 0; i < REGULAR_BONUS.length; i++) {
            add(addText(REGULAR_BONUS[i] + ": 0.0", 1, 0xff981f), x + 354, y + 136 + (i * 13));
        }

        for (int i = 0; i < PRE_EOC_BONUS.length; i++) {
            add(addText(PRE_EOC_BONUS[i] + ": 0.0", 1, 0xff981f), x + 354, y + 136 + ((i + REGULAR_BONUS.length) * 13));
        }

        y -= 2;

        add(addText("Other bonuses", 2, 0xff981f), x + 346, y + 256);

        for (int i = 0; i < OTHER_BONUS.length; i++) {
            add(addText(OTHER_BONUS[i] + ": 0.0", 1, 0xff981f), x + 354, y + 256 + ((i + 1) * 13));
        }

    }

    @Override
    public String getName() {
        return "Equip Your Character...";
    }

}
