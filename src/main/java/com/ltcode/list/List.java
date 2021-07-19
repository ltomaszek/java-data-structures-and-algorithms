package com.ltcode.list;

public interface List<T> {

    boolean add(T newValue);

    T get(int index) throws IndexOutOfBoundsException;

    T remove(int index) throws IndexOutOfBoundsException;

    void clear();
}
