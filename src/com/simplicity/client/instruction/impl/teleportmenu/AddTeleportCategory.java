package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.InstructionId;
import com.simplicity.client.instruction.VoidInstruction;

public class AddTeleportCategory implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface[] widgets = RSInterface.interfaceCache;
        RSInterface parent = widgets[61322];
        InstructionId.CLEAR_CHILDREN_COMPONENTS.invoke(InstructionArgs.createStack().addNextInt(61322));

        int count = args.getNextInt();
        for (int i = 0; i < count; i++) {
            RSInterface ccRect = parent.createChildComponent(3);
            ccRect.setRect(0x50473D, 154, 35, i % 2 == 0 ? 0 : 200, true);
            parent.setChildPosition(ccRect, 0, i * 35);

            RSInterface ccButton = parent.createChildComponent(5);
            ccButton.setConfigHoverButton("Select", 1749, 1750);
            ccButton.layerId = 61300;
            parent.setChildPosition(ccButton, 0, i * 35);
            /*InstructionId.SET_HOVER_TRIGGER.invoke(InstructionArgs.createStack()
                    .addNextInt(ccButton.parentID)
                    .addNextInt(ccButton.id)
                    .addNextInt(0)
                    .addNextInt(i * 35)
                    .addNextInt(ccButton.width)
                    .addNextInt(ccButton.height)
                    .addNextInt(1749)
            );*/

            RSInterface ccIcon = parent.createChildComponent(2);
            ccIcon.setItemContainer(3, 5, 5, 5);
            ccIcon.setItemModel(args.getNextInt() + 1, 1);
            parent.setChildPosition(ccIcon, 2, (i * 35) + 1);

            RSInterface ccText = parent.createChildComponent(4);
            ccText.setText(args.getNextString(), 1, 0xff981f, false, true);
            parent.setChildPosition(ccText, 45, (i * 35) + 6);

            parent.scrollMax = Math.max(187, 35 * i);
        }
        return null;
    }
}
