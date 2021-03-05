package com.simplicity.client.instruction;

import com.simplicity.client.RSInterface;
import com.simplicity.client.instruction.impl.*;
import com.simplicity.client.instruction.impl.teleportmenu.AddTeleportCategory;
import com.simplicity.client.instruction.impl.teleportmenu.SetTeleportMenuTeleList;
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
            return new SetTeleportMenuTeleList();
        }
    },
    CREATE_CHILD_WIDGET(7) {
        @Override
        public Instruction<RSInterface> getInstruction() {
            return new CreateChildWidget();
        }
    },
    SET_VARP(8) {
        @Override
        public VoidInstruction getInstruction() {
            return new SetVarp();
        }
    },
    MOUSE_ENTER(9) {
        @Override
        public VoidInstruction getInstruction() {
            return new MouseEnterWidget();
        }
    },
    MOUSE_EXIT(10) {
        @Override
        public VoidInstruction getInstruction() {
            return new MouseExitWidget();
        }
    },
    SET_WIDGET_GRAPHIC(11) {
        @Override
        public VoidInstruction getInstruction() {
            return new SetWidgetGraphic();
        }
    },
    SET_HOVER_TRIGGER(12) {
        @Override
        public VoidInstruction getInstruction() {
            return new SetHoverTrigger();
        }
    },

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
