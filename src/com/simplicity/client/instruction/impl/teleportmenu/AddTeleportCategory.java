package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class AddTeleportCategory implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface[] widgets = RSInterface.interfaceCache;
        RSInterface parent = widgets[61322];

        int index = args.getNextInt();

        RSInterface ccRect = parent.createChildComponent(3);
        ccRect.setRect(0x50473D, 154, 35, index % 2 == 0 ? 0 : 200, true);
        parent.setChildPosition(ccRect, 0, index * 35);

        RSInterface ccButton = parent.createChildComponent(5);
        ccButton.setConfigHoverButton("Select", 1749, 1750);
        parent.setChildPosition(ccButton, 0, index * 35);

        RSInterface ccIcon = parent.createChildComponent(2);
        ccIcon.setItemContainer(3, 5, 5, 5);
        ccIcon.setItemModel(args.getNextInt() + 1, 1);
        parent.setChildPosition(ccIcon, 2, (index * 35) + 1);

        RSInterface ccText = parent.createChildComponent(4);
        ccText.setText(args.getNextString(), 1, 0xff981f, false, true);
        parent.setChildPosition(ccText, 45, (index * 35) + 6);

        parent.scrollMax = Math.max(187, 35 * index);
        return null;
    }
}
