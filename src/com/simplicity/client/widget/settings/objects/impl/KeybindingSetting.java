package com.simplicity.client.widget.settings.objects.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.content.Keybinding;
import com.simplicity.client.widget.CustomWidget;
import com.simplicity.client.widget.settings.SettingsWidget;

import java.util.function.Consumer;

public class KeybindingSetting extends DropdownSetting {

    public static final int BOX_WIDTH = 122;
    public static final int BOX_HEIGHT = 44;

    private static final int[] ICONS = new int[] { 657, 658, 659, 660, 661, 662, 663, 664, 670, 666, 667, 668, 669, 671 };
    private static final int[] OFFX = new int[] { 6, 5, 6, 5, 6, 8, 5, 6, 6, 6, 7, 5, 5, 5 };
    private static final int[] OFFY = new int[] { 8, 8, 7, 7, 7, 7, 6, 8, 8, 8, 7, 8, 8, 6 };

    private int col;

    public KeybindingSetting(String key, int col, int priority, Integer defaultValue, Consumer<Integer> handle) {
        super(key, "", "", Keybinding.OPTIONS, 60, true, priority, defaultValue, handle);
        this.col = col;
    }

    @Override
    public int draw(int unused, int y, int width, int height, CustomWidget widget) {
        int row = col / 3;
        int colIndex = col % 3;
        int x = 97 + (colIndex) * (BOX_WIDTH + 10);
        int yPos = 12 + y;
        widget.add(widget.addRectangle(BOX_WIDTH, BOX_HEIGHT, 0, row % 2 == 0 ? 200 : 225, true), x, yPos);

        widget.add(widget.addSprite(1037), x + 5, yPos + 4);
        widget.add(widget.addSprite(ICONS[col]), x + 5 + OFFX[col], yPos + 4 + OFFY[col]);

        RSInterface rsi = RSInterface.addInterface(widget.id++);
        rsi.type = 37;
        rsi.atActionType = 7;
        rsi.dropdownColours = new int[] { 0x0d0d0b, 0x464644, 0x473d32, 0x51483c, 0x787169 };
        rsi.dropdown = menu;

        widget.add(rsi, x + 46, yPos + 12, priority);

        if (col == 13) {
            RSInterface button = widget.hoverButton(2001, 2002, "Select").setLayer(SettingsWidget.WIDGET_ID);
            button.onClick = () -> {
                Keybinding.restoreDefault();
                return true;
            };

            widget.add(button, x + 132, y + 16);
        }

        return colIndex == 2 ? BOX_HEIGHT : col == 13 ? 12 + 36 : 0;
    }

}
