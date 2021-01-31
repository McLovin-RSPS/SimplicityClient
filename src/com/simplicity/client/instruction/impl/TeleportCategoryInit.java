package com.simplicity.client.instruction.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.VoidInstruction;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.widget.TeleportInterface;

/**
 * An instruction that sets up the category list within the teleport menu.
 */
public class TeleportCategoryInit implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        int categorySize = args.getNextInt();
        RSInterface parent = RSInterface.interfaceCache[args.getNextInt()];
        if (categorySize > TeleportInterface.MAX_CATEGORY_SIZE) {
            categorySize = TeleportInterface.MAX_CATEGORY_SIZE;
        }
        int hide = TeleportInterface.MAX_CATEGORY_SIZE - categorySize;
        if (hide > 0) {
            for (int i = 0; i < hide; i++) {
                final int index = TeleportInterface.MAX_CATEGORY_SIZE - (i + 1);
                hideCategoryEntry(index, parent);
            }
            parent.scrollMax = Math.max(187, 35 * categorySize);
        }
        return null;
    }

    private void hideCategoryEntry(int index, RSInterface parent) {
        RSInterface[] widgets = RSInterface.interfaceCache;
        // Hide rect
        widgets[parent.children[index]].hidden = true;
        System.out.println("Hiding rect widget "+ widgets[parent.children[index]].id);

        // hide button
        widgets[parent.children[7 + index]].hidden = true;
        System.out.println("Hiding button widget "+ widgets[parent.children[7 + index]].id);

        // Hide item
        widgets[parent.children[14 + (2 * index)]].hidden = true;
        System.out.println("Hiding item widget "+ widgets[parent.children[14 + (2 * index) - 1]].id);

        // Hide text
        widgets[parent.children[14 + (2 * index) + 1]].hidden = true;
        System.out.println("Hiding text widget "+ widgets[parent.children[14 + (2 * index)]].id +", "+ widgets[parent.children[14 + (2 * index)]].message);
    }
}
