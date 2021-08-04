package com.ltcode.data_structure.table;

import java.util.ArrayList;
import java.util.function.Function;

public class DirectChainingHashTable<T> implements Table<T> {

    private ArrayList<T>[] table;
    private Function<T, Integer> hashFunction;

    public DirectChainingHashTable(int capacity, Function<T, Integer> hashFunction) {
        this.table = (ArrayList<T>[]) new ArrayList<?>[capacity];
        this.hashFunction = hashFunction;
    }

    @Override
    public boolean contains(T value) {
        int index = hashFunction.apply(value);
        if (table[index] == null) {
            return false;
        }
        for (T element : table[index]) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(T value) {
        int index = hashFunction.apply(value);
        if (table[index] == null) {
            return false;
        }
        var isRemoved = table[index].remove(value);
        if (isRemoved && table[index].size() == 0) {
            table[index] = null;
        }
        return isRemoved;
    }

    @Override
    public void displayTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t: " + table[i]);
        }
    }

    @Override
    public boolean insert(T value) {
        if (contains(value)) {
            return false;
        }
        int index = hashFunction.apply(value);
        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }
        table[index].add(value);
        return true;
    }
}
