package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class SetTeleportCategorySelected implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface parent = RSInterface.interfaceCache[61322];
        final int categories = parent.children.length;
        for (int index = 0; index < categories; index += 4) {
            RSInterface button = RSInterface.interfaceCache[parent.children[index + 1]];
            button.toggled = false;
        }

        int childId = args.getNextInt();
        RSInterface.interfaceCache[parent.children[(childId + 1) * 4]].toggled = true;
        return null;
    }
}
