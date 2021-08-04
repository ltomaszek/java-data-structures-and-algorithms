package com.ltcode.data_structure.stack;

public class FixedArrayStack<T> implements Stack<T> {

    private int index;  // last element index
    private T[] array;

    public FixedArrayStack(int capacity) {
        this.index = -1;
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    @Override
    public boolean isFull() {
        return index == getCapacity() - 1;
    }

    @Override
    public T peek() {
        return isEmpty()
                ? null
                : array[index];
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        T toReturn = array[index];
        array[index] = null;
        index--;
        return toReturn;
    }

    @Override
    public void push(T newValue) {
        if (isFull()) {
            throw new RuntimeException("Stack is full!");
        }
        index++;
        array[index] = newValue;
    }

    private int getCapacity() {
        return array.length;
    }
}
