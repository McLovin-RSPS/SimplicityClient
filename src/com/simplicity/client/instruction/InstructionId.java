package com.simplicity.client.instruction;

import com.simplicity.client.instruction.impl.TeleportCategoryInit;

/**
 * A list of instruction identifiers mapped to their respective instruction.
 * @author Heaven
 */
public enum InstructionId {
    NOTHING(-1),
    TELEPORT_CATEGORY_INIT(0) {
        @Override
        public VoidInstruction getInstruction() {
            return new TeleportCategoryInit();
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
    public VoidInstruction getInstruction() {
        return null;
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
