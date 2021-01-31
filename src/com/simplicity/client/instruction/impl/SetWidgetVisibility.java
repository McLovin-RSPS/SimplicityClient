package com.simplicity.client.instruction.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.Instruction;
import com.simplicity.client.instruction.InstructionArgs;

/**
 * An instruction to set the specified interface component visibility.
 * Args: [visibility state, widgetId]
 * states: 0 = visible, 1 = hidden
 * @author Heaven
 */
public class SetWidgetVisibility implements Instruction<Boolean> {

    @Override
    public Boolean invoke(InstructionArgs args) {
        RSInterface widget = RSInterface.interfaceCache[args.getNextInt()];
        return widget.hidden = args.getNextInt() == 1;
    }
}
