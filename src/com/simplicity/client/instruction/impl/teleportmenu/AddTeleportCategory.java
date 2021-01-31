package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class AddTeleportCategory implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface[] widgets = RSInterface.interfaceCache;
        RSInterface parent = widgets[61322];
        int index = args.getNextInt() - 1;

        widgets[parent.children[14 + (2 * index)]].inv[0] = args.getNextInt() + 1;
        widgets[parent.children[14 + (2 * index)]].invStackSizes[0] = 1;

        widgets[parent.children[14 + (2 * index) + 1]].message = args.getNextString();
        return null;
    }
}
