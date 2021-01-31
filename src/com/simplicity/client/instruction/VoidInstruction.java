package com.simplicity.client.instruction;

/**
 * A client instruction that performs an operation with no value expected in return.
 * @author Heaven
 */
public interface VoidInstruction extends Instruction<Void> {

    Void invoke(InstructionArgs args);
}
