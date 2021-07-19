package com.ltcode.list;

public class CircularDoublyLinkedList<T> extends DoublyLinkedList<T>  {

    @Override
    public boolean add(T newValue) {
        boolean toReturn = super.add(newValue);
        head.prev = tail;
        tail.next = head;
        return toReturn;
    }

    @Override
    public void clear() {
        // Delete circular reference
        head.prev = null;
        tail.next = null;

        super.clear();
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        this.checkGetIndex(index);
        if (size == 1) {
            head.prev = null;
            tail.next = null;
        }

        T toReturn = super.remove(index);

        if (size > 0 && (index == 0 || index == size)) { // size is decreased by 1 already
            head.prev = tail;
            tail.next = head;
        }

        return toReturn;
    }
}
