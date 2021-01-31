package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class SetTeleportCategorySelected implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface parent = RSInterface.interfaceCache[61322];
        final int categories = parent.children.length / 4;
        for (int index = 0; index < categories; index++) {
            RSInterface button = RSInterface.interfaceCache[index * 4];
            button.toggled = true;
        }

        int childId = args.getNextInt();
        RSInterface.interfaceCache[parent.children[childId * 4]].toggled = false;
        return null;
    }
}
