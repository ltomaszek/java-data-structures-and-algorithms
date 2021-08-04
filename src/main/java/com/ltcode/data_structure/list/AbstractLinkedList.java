package com.ltcode.data_structure.list;

public abstract class AbstractLinkedList<T> implements List<T> {

    protected int size;

    public int getSize() {
        return size;
    }

    // == PRIVATE METHODS ==

    protected void checkGetIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    protected void checkAddIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
}
