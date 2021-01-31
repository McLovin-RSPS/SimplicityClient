package com.simplicity.client.instruction.impl.teleportmenu;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.InstructionArgs;
import com.simplicity.client.instruction.VoidInstruction;

public class AddTeleportEntry implements VoidInstruction {

    @Override
    public Void invoke(InstructionArgs args) {
        RSInterface teleportList = RSInterface.interfaceCache[61360];

        int length = args.getNextInt();
        for (int i = 0; i < length; i++) {
            RSInterface ccRect = teleportList.createChildComponent(3);
            ccRect.setRect(0x50473D, 312, 35, i % 2 == 0 ? 0 : 200, true);
            teleportList.setChildPosition(ccRect, 13, (i * 36) + 1);

            RSInterface ccButton = teleportList.createChildComponent(20);
            ccButton.setConfigHoverButton("Teleport", 1752, 1753, 1753, 1753, false);
            teleportList.setChildPosition(ccButton, 13, (i * 36) + 1);

            RSInterface ccName = teleportList.createChildComponent(4);
            ccName.setText(args.getNextString(), 1, 0xff981f, false, true);
            teleportList.setChildPosition(ccName, 20, (i * 36) + 3);

            RSInterface ccDescr = teleportList.createChildComponent(4);
            ccDescr.setText(args.getNextString(), 0, 0xff981f, false, true);
            teleportList.setChildPosition(ccDescr, 20, (i * 36) + RSInterface.defaultFont[0].anInt1497 + 12);

            RSInterface ccFav = teleportList.createChildComponent(5);
            ccFav.setConfigButton(1754, 1755, 30, 25, "Favorite", 1, 5, 1160 + i);
            teleportList.setChildPosition(ccFav, 288, (i * 36) + 6);
        }

        teleportList.scrollMax = Math.max(187, 35 * length);
        return null;
    }
}
