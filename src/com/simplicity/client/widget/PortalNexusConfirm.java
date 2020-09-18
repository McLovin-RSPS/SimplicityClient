package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

public class PortalNexusConfirm extends RSInterface {

    public static final int PARENT_ID = 79975;

    public static void init() {
        RSInterface parent = addInterface(PARENT_ID);
        parent.height = 50;
        parent.children(1);
        parent.child(0, infoBox().id, 15, 50);
    }

    private static RSInterface infoBox() {
        int widget = 79977;
        RSInterface container = addInterface(widget);
        container.children(10);
        final int boxWidth = 485;
        final int boxHeight = 260;
        widget += 2;
        int currentChildId = 0;
        drawBox(widget, boxWidth, boxHeight, 0, 0x0E0E0E, 0x0E0E0E, 200);
        container.child(currentChildId++, widget++, 0, 0);
        addColorBox(widget, boxWidth, boxHeight, 0x0E0E0E);
        RSInterface.interfaceCache[widget].transparency = 200;
        container.child(currentChildId++, widget++, 0, 0);
        addText(widget, "These changes to your portal nexus will cost:", fonts, 1, 0xff981f);
        container.child(currentChildId++, widget,  10, 15);
        widget++;
        addItemContainer(widget, new int[] {12, 12}, new int[] {6, 8}, new String[] {}, true);
        container.child(currentChildId++, widget++,  15, 30);

        addHoverButton_sprite_loader(widget, 1313, 90 + 18, 28, "Confirm", -1, widget + 1, 5);
        container.child(currentChildId++, widget,  20, 200);
        widget++;
        addHoveredImageWSpriteLoader(widget, 1314, 90 + 18, 28, widget + 1);
        container.child(currentChildId++, widget,  20, 200);
        widget++;
        addHoverButton_sprite_loader(widget, 1313, 90 + 18, 28, "Decline", -1, widget + 1, 5);
        container.child(currentChildId++, widget,  158, 200);
        widget++;
        addHoveredImageWSpriteLoader(widget, 1314, 90 + 18, 28, widget + 1);
        container.child(currentChildId++, widget++,  158, 200);

        addText(widget, "Confirm", fonts, 3, 0xff981f, false, true);
        container.child(currentChildId++, widget++,  38, 204);

        addText(widget, "Decline", fonts, 3, 0xff981f, false, true);
        container.child(currentChildId, widget,  176, 204);
        return container;
    }
}
