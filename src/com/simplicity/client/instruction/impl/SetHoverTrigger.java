package com.simplicity.client.instruction.impl;

import com.simplicity.client.RSInterface;
import com.simplicity.client.Sprite;
import com.simplicity.client.SpriteCache;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class SetHoverTrigger implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        final RSInterface layer = RSInterface.interfaceCache[args.getIntArg(0)];
        final RSInterface hoverLink = RSInterface.interfaceCache[args.getIntArg(1)];

        final int x = args.getIntArg(2);
        final int y = args.getIntArg(3);
        final int width = args.getIntArg(4);
        final int height = args.getIntArg(5);
        RSInterface hover = layer.createChildComponent(0);
        hoverLink.hoverType = hover.id;
        hover.hoverType = -1;
        hover.interfaceShown = true;
        layer.setChildPosition(hover, x, y);
        layer.setChildSize(hover, width, height);

        RSInterface child = hover.createChildComponent(5);
        hover.setChildSize(child, width, height);
        hover.setChildPosition(child, 0, 0);
        child.enabledSpriteId = args.getIntArg(6);
        return null;
    }
}
