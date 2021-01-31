package com.simplicity.client.instruction;

import com.simplicity.client.instruction.impl.ClearChildren;
import com.simplicity.client.instruction.impl.SetWidgetText;
import com.simplicity.client.instruction.impl.SetWidgetVisibility;
import com.simplicity.client.instruction.impl.teleportmenu.AddTeleportCategory;
import com.simplicity.client.instruction.impl.teleportmenu.AddTeleportEntry;
import com.simplicity.client.instruction.impl.teleportmenu.SetTeleportCategorySelected;
import com.simplicity.client.instruction.impl.teleportmenu.TeleportCategoryInit;

/**
 * A list of instruction identifiers mapped to their respective instruction.
 * @author Heaven
 */
@SuppressWarnings("unchecked")
public enum InstructionId {
    NOTHING(-1),
    TELEPORT_MENU_CATEGORY_INIT(0) {
        @Override
        public VoidInstruction getInstruction() {
            return new TeleportCategoryInit();
        }
    },
    SET_WIDGET_VISIBILITY(1) {
        @Override
        public Instruction<Boolean> getInstruction() {
            return new SetWidgetVisibility();
        }
    },
    SET_WIDGET_TEXT(2) {
        @Override
        public VoidInstruction getInstruction() {
            return new SetWidgetText();
        }
    },
    ADD_TELEPORT_MENU_CATEGORY_ENTRY(3) {
        @Override
        public VoidInstruction getInstruction() {
            return new AddTeleportCategory();
        }
    },
    SET_TELEPORT_MENU_CATEGORY_SELECTED(4) {
        @Override
        public VoidInstruction getInstruction() {
            return new SetTeleportCategorySelected();
        }
    },
    CLEAR_CHILDREN_COMPONENTS(5) {
        @Override
        public VoidInstruction getInstruction() {
            return new ClearChildren();
        }
    },
    ADD_TELEPORT_MENU_ENTRY(6) {
        @Override
        public VoidInstruction getInstruction() {
            return new AddTeleportEntry();
        }
    }

    ; // End of enum

    public static final InstructionId[] VALUES = values();

    /**
     * The identifier value in the form of a int literal.
     */
    public final int uid;

    /**
     * A fallback instruction if no other is specified.
     */
    public <T> Instruction<T> getInstruction() {
        return null;
    }

    /**
     * A shorthand method to invoking an instruction without
     * @param args
     */
    public void invoke(InstructionArgs args) {
        getInstruction().invoke(args);
    }

    InstructionId(int uid) {
        this.uid = uid;
    }

    public static InstructionId fromId(int id) {
        for (InstructionId insId : VALUES) {
            if (insId.uid == id)
                return insId;
        }
        return NOTHING;
    }
}
