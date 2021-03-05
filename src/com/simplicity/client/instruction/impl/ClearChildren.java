package com.simplicity.client.instruction.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class ClearChildren implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface parent = RSInterface.interfaceCache[args.getNextInt()];
        for (int id : parent.children) {
            RSInterface.interfaceCache[id] = null;
        }

        parent.children = null;
        parent.children = new int[0];
        parent.childX = null;
        parent.childX = new int[0];
        parent.childY = null;
        parent.childY = new int[0];
        return null;
    }
}
