package com.ltcode.data_structure.stack;

import com.ltcode.data_structure.list.SinglyLinkedList;

public class LinkedStack<T> implements Stack<T> {

    private SinglyLinkedList<T> list;

    public LinkedStack() {
        this.list = new SinglyLinkedList<>();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.getSize() == 0;
    }

    @Override
    public boolean isFull() {
        // size grows so it's never full
        return false;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return getTopElement();
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return list.remove(0);
    }

    @Override
    public void push(T newValue) {
        list.add(0, newValue);
    }

    // == PRIVATE METHODS ==

    private T getTopElement() {
        return list.get(0);
    }
}
