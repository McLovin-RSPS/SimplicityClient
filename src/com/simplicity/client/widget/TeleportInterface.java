package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.TextDrawingArea;
import com.simplicity.client.instruction.VoidInstruction;
import com.simplicity.client.instruction.impl.ClearChildren;

public class TeleportInterface extends RSInterface {

    public static void init(TextDrawingArea[] tda) {
        RSInterface tab = addInterface(61300);
        addSprite(61301, 1748);
        addText(61302, "Simplicity Teleportation Menu", tda, 2, 0xff981f, true, true);
        addText(61303, "Name", tda, 2, 15781715, true, true);
        addText(61304, "", tda, 0, 0xff981f, false, true);
        addText(61306, "Favorite", tda, 2, 15781715, true, true);
        addButton(61307, 1750, "Add to favorites");
        addText(61308, "Favorites", tda, 2, 15781715, true, true);
        addCloseButton(39021, 39022, 39023);

        int x = 9, y = 25, child = 0;
        tab.totalChildren(16);
        tab.child(child++, 61301, x, y);
        tab.child(child++, 39021, 474+x, 9+y);
        tab.child(child++, 39022, 474+x, 9+y);
        tab.child(child++, 61302, 250+x, 8+y);
        tab.child(child++, 61303, 200+x, 36+y);
        tab.child(child++, 61304, 300+x, 36+y);
        tab.child(child++, 61306, 450+x, 36+y);
        tab.child(child++, 61307, 395+44+x, 274+y);
        tab.child(child++, 61308, 81+x, 227+y);
        for (int i = 0; i < 3; ++i) {
            if (i == 0 || i == 2) {
                addPixels(61318 + i, 0x50473D, 154, 15, 0, true);
                tab.child(child++, 61318 + i, x + 7, 242 + (i * 15) + y);
            }

            addClickableText(61314 + i, "", "Quick-Teleport", tda, 0, 0xff981f, true, true, 50);
            tab.child(child++, 61314 + i, 57 + x, 244 + (i * 15) + y);
        }

        tab.child(child++, 61322, x + 6, 35+y);
        tab.child(child, 61360, x + 155, 58+y);
        RSInterface pages = addInterface(61322);
        pages.parentID = tab.id;
        pages.newFormat = true;
        pages.totalChildren(0);

        pages.width = 140;
        pages.height = 186;
        pages.closeInstructions = new VoidInstruction[] { new ClearChildren() };

        RSInterface teleportList = addInterface(61360);
        teleportList.totalChildren(0);
        teleportList.parentID = tab.id;
        teleportList.newFormat = true;
        teleportList.width = 325;
        teleportList.height = 229;
        teleportList.closeInstructions = new VoidInstruction[] { new ClearChildren() };
    }
}
