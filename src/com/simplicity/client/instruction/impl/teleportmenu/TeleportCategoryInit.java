package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionId;
import com.simplicity.client.instruction.VoidInstruction;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.widget.TeleportInterface;

/**
 * An instruction that sets up the category list within the teleport menu.
 */
public class TeleportCategoryInit implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        // Reset widget states on next initialize usage.
        resetStates();

        int categorySize = args.getNextInt();
        RSInterface parent = RSInterface.interfaceCache[61322];
        if (categorySize > TeleportInterface.MAX_CATEGORY_SIZE) {
            categorySize = TeleportInterface.MAX_CATEGORY_SIZE;
        }
        int hide = TeleportInterface.MAX_CATEGORY_SIZE - categorySize;
        if (hide > 0) {
            for (int i = 0; i < hide; i++) {
                final int index = TeleportInterface.MAX_CATEGORY_SIZE - (i + 1);
                setCategoryEntryVisibility(index, parent, true);
            }
            parent.scrollMax = Math.max(187, 35 * categorySize);
        }
        return null;
    }

    private void resetStates() {
        for (int i = 0; i < TeleportInterface.MAX_CATEGORY_SIZE; i++) {
            setCategoryEntryVisibility(i, RSInterface.interfaceCache[61322], false);
        }
    }

    private void setCategoryEntryVisibility(int index, RSInterface parent, boolean hidden) {
        RSInterface[] widgets = RSInterface.interfaceCache;

        int widgetId = widgets[parent.children[index]].id;
        InstructionArgs args = InstructionArgs.empty();
        args.addNextBool(hidden);
        args.addNextInt(widgetId);
        InstructionId.SET_WIDGET_VISIBILITY.invoke(args);

        widgetId = widgets[parent.children[7 + index]].id;
        args = InstructionArgs.empty();
        args.addNextBool(hidden);
        args.addNextInt(widgetId);
        InstructionId.SET_WIDGET_VISIBILITY.invoke(args);

        widgetId = widgets[parent.children[14 + (2 * index)]].id;
        args = InstructionArgs.empty();
        args.addNextBool(hidden);
        args.addNextInt(widgetId);
        InstructionId.SET_WIDGET_VISIBILITY.invoke(args);

        widgetId = widgets[parent.children[14 + (2 * index) + 1]].id;
        args = InstructionArgs.empty();
        args.addNextBool(hidden);
        args.addNextInt(widgetId);
        InstructionId.SET_WIDGET_VISIBILITY.invoke(args);
    }
}
