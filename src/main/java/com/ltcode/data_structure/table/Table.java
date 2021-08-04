package com.ltcode.data_structure.table;

public interface Table<T> {

    boolean contains(T value);

    boolean delete(T value);

    void displayTable();

    boolean insert(T value);
}