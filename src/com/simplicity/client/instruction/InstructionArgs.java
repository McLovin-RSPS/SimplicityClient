package com.simplicity.client.instruction;

/**
 * Represents a set of arguments that are to be passed off to an {@link VoidInstruction} for processing.
 * @author Heaven
 */
public class InstructionArgs {

    /**
     * The int stack to push and pop values off as needed.
     */
    private int[] intStack;

    /**
     * The string stack to push and pop values off as needed.
     */
    private String[] stringStack;

    /**
     * The position we are at within the int stack.
     */
    private int intStackPos;

    /**
     * The position we are at within the string stack.
     */
    private int stringStackPos;

    /**
     * Creates an empty set of instruction arguments.
     * @return
     */
    public static InstructionArgs empty() {
        return new InstructionArgs(new int[0], new String[0]);
    }

    /**
     * Creates a new set of arguments consisting of the elements within the array passed through the constructor.
     * @param intArgs
     * @param stringArgs
     * @return
     */
    public static InstructionArgs of(int[] intArgs, String[] stringArgs) {
        return new InstructionArgs(intArgs, stringArgs);
    }

    /**
     * Private construction to avoid any undesirable instantiation.
     * @param intArgs
     * @param stringArgs
     */
    private InstructionArgs(int[] intArgs, String[] stringArgs) {
        this.intStack = intArgs;
        this.stringStack = stringArgs;
        this.intStackPos = intStack.length;
        this.stringStackPos = stringStack.length;
    }

    /**
     * Gets the next int-type argument in the array.
     * @return
     */
    public int getNextInt() {
        return intStack[--intStackPos];
    }

    /**
     * Adds a boolean value onto the stack by evaluating the input and setting a 0-1 logical value respectively.
     * @param bool
     */
    public void addNextBool(boolean bool) {
        addNextInt(bool ? 1 : 0);
    }

    /**
     * Adds an int value onto the stack to be returned on the next calling of {@link #getNextInt()}.
     */
    public void addNextInt(int value) {
        if (intStackPos >= intStack.length) {
            int oldSize = intStack.length;
            int[] newStack = new int[oldSize + 1];
            System.arraycopy(intStack, 0, newStack, 0, oldSize);
            intStack = null;
            intStack = newStack;
        }
        intStack[intStackPos++] = value;
    }

    /**
     * Gets the next string-type argument in the array.
     * @return
     */
    public String getNextString() {
        return stringStack[--stringStackPos];
    }

    /**
     * Adds an string value onto the stack to be returned on the next calling of {@link #getNextString()}.
     */
    public void addString(String value) {
        if (stringStackPos >= stringStack.length) {
            int oldSize = stringStack.length;
            String[] newStack = new String[oldSize + 1];
            System.arraycopy(stringStack, 0, newStack, 0, oldSize);
            stringStack = null;
            stringStack = newStack;
        }
        stringStack[stringStackPos++] = value;
    }
}
