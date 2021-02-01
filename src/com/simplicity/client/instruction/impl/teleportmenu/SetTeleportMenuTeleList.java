package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.InstructionId;
import com.simplicity.client.instruction.VoidInstruction;

public class SetTeleportMenuTeleList implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        InstructionId.SET_VARP.invoke(InstructionArgs.empty().addNextInt(1160).addNextInt(0));
        InstructionId.SET_VARP.invoke(InstructionArgs.empty().addNextInt(1161).addNextInt(0));

        RSInterface teleportList = RSInterface.interfaceCache[61360];

        int length = args.getNextInt();
        for (int i = 0; i < length; i++) {
            RSInterface ccRect = teleportList.createChildComponent(3);
            ccRect.setRect(0x50473D, 312, 35, i % 2 == 0 ? 0 : 200, true);
            teleportList.setChildPosition(ccRect, 13, (i * 36) + 1);

            RSInterface ccButton = teleportList.createChildComponent(5);
            ccButton.setConfigHoverButton("Teleport", 1753, 1752);
            teleportList.setChildPosition(ccButton, 13, (i * 36) + 1);

            RSInterface ccName = teleportList.createChildComponent(4);
            ccName.setText(args.getNextString(), 1, 0xff981f, false, true);

            RSInterface ccDescr = teleportList.createChildComponent(4);
            ccDescr.setText(args.getNextString(), 0, 0xff981f, false, true);
            if (ccDescr.message.isEmpty()) {
                teleportList.setChildPosition(ccName, 20, (i * 36) + (35 - RSInterface.defaultFont[1].anInt1497) / 2);
            } else {
                teleportList.setChildPosition(ccName, 20, (i * 36) + 3);
                teleportList.setChildPosition(ccDescr, 20, (i * 36) + RSInterface.defaultFont[0].anInt1497 + 12);
            }

            RSInterface ccFav = teleportList.createChildComponent(5);
            ccFav.setConfigButton(1754, 1755, 30, 25, "Favorite", 1, 5, 1160 + i);
            teleportList.setChildPosition(ccFav, 288, (i * 36) + 6);
            InstructionId.SET_VARP.invoke(InstructionArgs.empty().addNextInt(args.getNextInt()).addNextInt(1160 + i));
        }

        teleportList.scrollMax = Math.max(187, 36 * length);
        return null;
    }
}
