package com.simplicity.client.instruction.impl;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class SetWidgetGraphic implements VoidInstruction {
    @Override
    public Void invoke(InstructionArgs args) {
        final int graphicId = args.getNextInt();
        final int widgetId = args.getNextInt();
        RSInterface widget = RSInterface.interfaceCache[widgetId];
        widget.disabledSprite = Client.cacheSprite[graphicId];
        return null;
    }
}
