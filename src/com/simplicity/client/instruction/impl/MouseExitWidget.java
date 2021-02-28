package com.simplicity.client.instruction.impl;

import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.InstructionId;
import com.simplicity.client.instruction.VoidInstruction;

public class MouseExitWidget implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        InstructionId instructionId = InstructionId.fromId(args.getNextInt());
        instructionId.invoke(args);
        return null;
    }
}
