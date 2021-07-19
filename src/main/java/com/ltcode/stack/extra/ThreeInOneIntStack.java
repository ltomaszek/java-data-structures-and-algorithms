package com.ltcode.stack.extra;

public class ThreeInOneIntStack {


    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] array;        // values
    private int[] topOfStack;   // indexes for top elements in all 3 stacks

    public ThreeInOneIntStack(int stackSize) {
        this.array = new int[stackSize * 3];
        this.stackCapacity = stackSize;
        this.topOfStack = new int[3];
        // init top of stack
        for (int numStack = 1; numStack <= 3; numStack++) {
            topOfStack[numStack - 1] = startIndex(numStack) - 1;
        }
    }

    // isFull
    public boolean isFull(int stackNum) {
        return indexOfTop(stackNum) == endIndex(stackNum);
    }

    // isEmpty
    public boolean isEmpty(int stackNum) {
        return indexOfTop(stackNum) < startIndex(stackNum);
    }

    // push
    public void push(int stackNum, int value) {
        if (isFull(stackNum)) {
            throw new RuntimeException("Stack is full.");
        }
        increaseIndexOfTop(stackNum);
        array[indexOfTop(stackNum)] = value;
    }

    // pop
    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new RuntimeException("Stack is empty.");
        }
        int value = array[indexOfTop(stackNum)];
        decreaseIndexOfTop(stackNum);
        return value;
    }

    // peek

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            throw new RuntimeException("Stack is empty.");
        }
        int value = array[indexOfTop(stackNum)];
        return value;
    }

    // == PRIVATE METHODS ==

    private int indexOfTop(int stackNum) {
        return topOfStack[stackNum - 1];
    }

    private void increaseIndexOfTop(int stackNum) {
        topOfStack[stackNum - 1]++;
    }

    private void decreaseIndexOfTop(int stackNum) {
        topOfStack[stackNum - 1]--;
    }

    private int startIndex(int stackNum) {  // include
        return (stackNum - 1) * stackCapacity;
    }

    private int endIndex(int stackNum) {    // include
        return stackNum * stackCapacity - 1;
    }

}
