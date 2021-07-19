package com.ltcode.queue;

public interface Queue<T> {

    void clear();

    T dequeue();

    void enqueue(T newValue);

    boolean isEmpty();

    boolean isFull();

    T peek();
}
