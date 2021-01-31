package com.simplicity.client.instruction.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class SetWidgetText implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface widget = RSInterface.interfaceCache[args.getNextInt()];
        widget.message = args.getNextString();
        return null;
    }
}
