package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

public class ItemStatComparePanel extends RSInterface {

    public static void init() {
        int widget = 48480;
        RSInterface parent = addInterface(widget);
        parent.children(2);
        widget += 2;
        drawBox(widget, parent.width, parent.height, 200, 0x0E0E0E, 0x0E0E0E, 200);
        parent.child(0, widget++, 0, 0);
        RSInterface panel = statPanel(widget);
        parent.child(1, panel.id, 102, 18);
    }

    private static RSInterface statPanel(int widget) {
        RSInterface parent = addInterface(widget);
        parent.children(2);

        widget += 2;
        addClosableWindow(widget, 315, 300, false, "Item Comparison");
        parent.child(0, widget, 0, 0);
        widget += 50;

        final String[] attackBonusNames = new String[] {
                "Stab", "Slash", "Crush", "Magic", "Ranged"
        };

        final String[] defenceBonusNames = new String[] {
                "Stab", "Slash", "Crush", "Magic", "Ranged", "Summoning", "Absorb Melee", "Absorb Magic", "Absorb Ranged"
        };

        final String[] otherBonusNames = new String[] {
                "Strength", "Ranged Strength", "Prayer", "Magic Damage"
        };

        RSInterface container = addInterface(widget);
        parent.child(1, container.id, 10, 35);
        container.children(1 + 1 + 1 + 1 + 1 +
                (attackBonusNames.length * 3) + (defenceBonusNames.length * 3) + (otherBonusNames.length * 3));
        container.width = 283;
        container.height = 258;
        container.scrollMax = 485;
        widget += 2;
        int compareWidgetId = widget + 50;
        int column = 2;
        int columnWidth = 86;
        int containerChild = 0;
        int yPos = 12;

        addText(widget, "Attack bonus", fonts, 1, 0xff981f);
        container.child(containerChild++, widget++, 10, yPos);

        addText(widget, ""+ widget, fonts, 1, 0xff981f);
        container.child(containerChild++, widget++, 123, yPos);

        addText(widget, ""+ widget, fonts, 1, 0xff981f);
        container.child(containerChild++, widget++, 206, yPos);

        yPos += 32;
        for (String name : attackBonusNames) {
            addText(widget, name, fonts, 1, 0xff981f);
            container.child(containerChild++, widget++, 15, yPos);
            for (int i = 1; i <= column; i++) {
                addText(compareWidgetId, ""+ compareWidgetId, fonts, 1, 0xff981f);
                container.child(containerChild++, compareWidgetId++, 40 + (columnWidth * i), yPos);
            }
            yPos += 18;
        }

        yPos += 25;
        addText(widget, "Defence bonus", fonts, 1, 0xff981f);
        container.child(containerChild++, widget++, 10, yPos);
        yPos += 32;
        for (String name : defenceBonusNames) {
            addText(widget, name, fonts, 1, 0xff981f);
            container.child(containerChild++, widget++, 15, yPos);
            for (int i = 1; i <= column; i++) {
                addText(compareWidgetId, ""+ compareWidgetId, fonts, 1, 0xff981f);
                container.child(containerChild++, compareWidgetId++, 40 + (columnWidth * i), yPos);
            }
            yPos += 18;
        }

        yPos += 25;
        addText(widget, "Other bonus", fonts, 1, 0xff981f);
        container.child(containerChild++, widget++, 10, yPos);
        yPos += 32;
        for (String name : otherBonusNames) {
            addText(widget, name, fonts, 1, 0xff981f);
            container.child(containerChild++, widget++, 15, yPos);
            for (int i = 1; i <= column; i++) {
                addText(compareWidgetId, ""+ compareWidgetId, fonts, 1, 0xff981f);
                container.child(containerChild++, compareWidgetId++, 40 + (columnWidth * i), yPos);
            }
            yPos += 18;
        }

        return parent;
    }
}
