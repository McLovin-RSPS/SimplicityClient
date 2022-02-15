package com.simplicity.client.widget.presets;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.Widget;
import com.simplicity.client.widget.dropdown.Dropdown;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.client.widget.listener.WidgetStateListener;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PresetsWidget extends CustomWidget implements WidgetButtonListener {

    public static final int WIDGET_ID = 86_000;

    public PresetsWidget() {
        super(WIDGET_ID);
    }

    @Override
    public void init() {
        addButtonListener(this);

        int x = 5;
        int y = 2;

        add(addClosableWindow(502, 320, true, false, false, "Choose your loadout..."), 5, 7);

        RSInterface equip = copyWidget(1644, x + 116, y + 58);
        RSInterface.copy(id, 1688);
        equip.children[27] = id++;

        add(drawBox(124, 24, 1, 0x5a5245,0x5a5245, 250), x + 7, y + 36);
        add(drawBox(124, 24, 1, 0x5a5245,0x383023, 250), x + 6, y + 35);

        add(drawBox(124, 151, 1, 0x5a5245,0x5a5245, 250), x + 7, y + 36 + 24);
        add(drawBox(124, 151, 1, 0x5a5245,0x383023, 250), x + 6, y + 35 + 24);

        add(drawBox(124, 50, 1, 0x5a5245,0x5a5245, 250), x + 7, y + 36 + 24 + 151);
        add(drawBox(124, 50, 1, 0x5a5245,0x383023, 250), x + 6, y + 35 + 24 + 151);


        add(drawBox(156, 24, 1, 0x5a5245,0x5a5245, 250), x + 134, y + 36);
        add(drawBox(156, 24, 1, 0x5a5245,0x383023, 250), x + 133, y + 35);

        add(drawBox(156, 202, 1, 0x5a5245,0x5a5245, 250), x + 134, y + 36 + 24);
        add(drawBox(156, 202, 1, 0x5a5245,0x383023, 250), x + 133, y + 35 + 24);

        add(drawBox(156, 56, 1, 0x5a5245,0x5a5245, 250), x + 134, y + 36 + 24 + 202);
        add(drawBox(156, 56, 1, 0x5a5245,0x383023, 250), x + 133, y + 35 + 24 + 202);

        add(drawBox(202, 24, 1, 0x5a5245,0x5a5245, 250), x + 292, y + 36);
        add(drawBox(202, 24, 1, 0x5a5245,0x383023, 250), x + 291, y + 35);


        add(drawBox(202, 258, 1, 0x5a5245,0x5a5245, 250), x + 292, y + 36 + 24);
        add(drawBox(202, 258, 1, 0x5a5245,0x383023, 250), x + 291, y + 35 + 24);

        add(addText("<spr=1984:0> Presets", 1, 0xb29c85), x + 33, y + 40);
        add(addText("<spr=1866:0> Equipment", 1, 0xb29c85), x + 173, y + 40);
        add(addText("<spr=1987:0> Inventory", 1, 0xb29c85), x + 351, y + 40);

        add(addDynamicButton("<spr=1963:0> Save preset", 1, 0xbf751d, 122, 26), x + 9, y + 211 + 50);
        add(addDynamicButton("<spr=1964:0> Load preset", 1, 0xbf751d, 122, 26), x + 9, y + 240 + 50);

        add(addStoneButton(118, 21, 0xd2e6c6, 0xffffff, "Regular"), x + 10, y + 214);
        add(addSprite(1156), x + 12, y + 216);
        add(addSprite(1156), x + 12 + 97, y + 216);

        add(addStoneButton(118, 21, 0x5d88ff, 0xffffff, "Modern"), x + 10, y + 214 + 22);
        add(addSprite(1986), x + 14, y + 216 + 23);
        add(addSprite(1986), x + 14 + 97, y + 216 + 23);

        add(drawBox(38, 40, 1, 0x5a5245,0x5a5245, 250), x + 138, y + 250 + 19);
        add(addRectangle(39, 39, 0x000000, 175, true), x + 138, y + 250 + 19);

        RSInterface item = addItemContainer(40, 40, 0, 0, new String[0], "");
        item.usableItemInterface = true;
        item.inv[0] = 12094;
        item.invStackSizes[0] = 1;
        add(item, x + 141, y + 255 + 18);

        RSInterface inv = addItemContainer(4, 7, 18, 5, new String[] { "Edit", "Remove" }, "");
        for (int i = 0; i < 28; i++) {
            inv.inv[i] = 392;
            inv.invStackSizes[i] = 1;
        }
        add(inv, 305, 62);
        add(addText("Left-click and select\\na @whi@pouch@or1@ in your \\ninventory to update\\nyour @whi@familiar loadout@or1@.", 0, 0xFFA500, false), x + 181, y + 267);

        add(addContainer(), x - 5, y + 61);
    }

    private static int[] RECTANGLE_IDS;

    private RSInterface addContainer() {
        RSInterface rsi = RSInterface.addInterface(id);
        rsi.componentId = id++;
        rsi.parentID = WIDGET_ID;
        rsi.totalChildren(18);
        rsi.width = 118;
        rsi.height = 171 - 25;
        rsi.scrollMax = 286 - 15;

        int child = 0;

        RECTANGLE_IDS = IntStream.iterate(id, i -> i + 2).limit(9).toArray();

        for (int i = 0; i < 9; i++) {
            RSInterface.addRectangleClickable(id, i % 2 == 0 ? 200 : 175, 0, true, 106, 31, "View", "Rename", "Delete").setLayer(mainId);
            RSInterface.interfaceCache[id].enabledOpacity = 230;
            RSInterface.interfaceCache[id].enabledColor = 0x00ff00;
            RSInterface.interfaceCache[id].selectableInterfaces = RECTANGLE_IDS;
            RSInterface.interfaceCache[id].hovers = true;
            RSInterface.interfaceCache[id].hoverType = id;
            RSInterface.interfaceCache[id].enabledMouseOverColor = 0x5a5245;
            Widget.componentForMain.put(id, mainId);

            RSInterface.addText(id + 1, "Preset " + (i + 1), RSInterface.defaultFont, 0, 0xFFA500);
            rsi.child(child++, id, 13, (i * 30));
            rsi.child(child++, id + 1, 45, (i * 30) + 9);
            id += 2;
        }

        return rsi;
    }

    @Override
    public boolean onClick(int id) {
        if (Arrays.binarySearch(RECTANGLE_IDS, id) >= 0) {
            RSInterface.setSelectedInterface(id, true);
        }

        return false;
    }

    @Override
    public String getName() {
        return "Presets";
    }

}
