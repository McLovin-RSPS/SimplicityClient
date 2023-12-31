package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;

public class PortalNexusConfirm extends RSInterface {

    public static final int PARENT_ID = 85400;

    public static void init() {
        RSInterface parent = addInterface(PARENT_ID);
        parent.children(1);
        parent.child(0, infoBox().id, 15, 50);
    }

    private static RSInterface infoBox() {
        int widget = 85402;
        RSInterface container = addInterface(widget);
        container.children(10);
        final int boxWidth = 485;
        final int boxHeight = 260;
        widget += 2;
        int currentChildId = 0;

        drawBox(widget, boxWidth, boxHeight, 0, 0x0E0E0E, 0x0E0E0E, 200);
        container.child(currentChildId++, widget++, 0, 0);

        addColorBox(widget, boxWidth, boxHeight, 0x0E0E0E);
        container.child(currentChildId++, widget++, 0, 0);

        addText(widget, "These changes to your portal nexus will cost:", fonts, 1, 0xff981f);
        container.child(currentChildId++, widget++,  10, 15);

        addItemContainer(widget, new int[] {24, 8}, new int[] {10, 3}, new String[] {}, false);
        container.child(currentChildId++, widget++,  15, 45);

        addHoverButton_sprite_loader(widget, 1313, 90 + 18, 28, "Confirm", -1, widget + 1, 5);
        container.child(currentChildId++, widget,  20, 200);
        addHoveredImageWSpriteLoader(widget + 1, 1314, 90 + 18, 28, widget + 2);
        container.child(currentChildId++, widget + 1,  20, 200);
        widget += 3;

        addHoverButton_sprite_loader(widget, 1313, 90 + 18, 28, "Decline", -1, widget + 1, 5);
        container.child(currentChildId++, widget,  158, 200);
        addHoveredImageWSpriteLoader(widget + 1, 1314, 90 + 18, 28, widget + 2);
        container.child(currentChildId++, widget + 1,  158, 200);
        widget += 3;

        addText(widget, "Confirm", fonts, 3, 0xff981f, false, true);
        container.child(currentChildId++, widget++,  38, 204);

        addText(widget, "Decline", fonts, 3, 0xff981f, false, true);
        container.child(currentChildId, widget,  176, 204);
        return container;
    }
}
