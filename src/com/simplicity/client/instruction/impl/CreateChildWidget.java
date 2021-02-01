package com.simplicity.client.instruction.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.Instruction;
import com.simplicity.client.instruction.InstructionArgs;

/**
 * Creates a child component of the specified widget type for the parent id.
 * @author Heaven
 */
public class CreateChildWidget implements Instruction<RSInterface> {

    @Override
    public RSInterface invoke(InstructionArgs args) {
        final RSInterface parent = RSInterface.interfaceCache[args.getNextInt()];
        final int type = args.getNextInt();
        return parent.createChildComponent(type);
    }
}
