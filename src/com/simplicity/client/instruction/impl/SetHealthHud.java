package com.simplicity.client.instruction.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;
import com.simplicity.client.widget.raids.nightmare.HealthHud;

public class SetHealthHud implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        int type = args.getNextInt();
        HealthHud.setHudType(HealthHud.HudType.values()[type]);
        RSInterface header = RSInterface.interfaceCache[HealthHud.HEADER_ID];
        header.message = args.getNextString();
        return null;
    }
}
