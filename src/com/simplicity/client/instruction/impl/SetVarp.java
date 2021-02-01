package com.simplicity.client.instruction.impl;

import com.simplicity.client.Client;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class SetVarp implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        Client.instance.variousSettings[args.getNextInt()] = args.getNextInt();
        return null;
    }
}
