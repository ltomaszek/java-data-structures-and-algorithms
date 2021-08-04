package com.ltcode.data_structure.tree;

public interface Tree<T> {

    boolean add(T value);

    void clear();

    boolean contains(T value);

    boolean isEmpty();

    boolean remove(T value);
}
