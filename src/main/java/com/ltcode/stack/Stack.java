package com.ltcode.stack;

public interface Stack<T> {

    void clear();

    boolean isEmpty();

    boolean isFull();

    T peek();

    T pop();

    void push(T newValue);
}
