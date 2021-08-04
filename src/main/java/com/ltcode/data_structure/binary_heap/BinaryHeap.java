package com.ltcode.data_structure.binary_heap;

public interface BinaryHeap<T> {

    void insert(T value);

    boolean isEmpty();

    T peek();

    T pop();

    int size();
}
