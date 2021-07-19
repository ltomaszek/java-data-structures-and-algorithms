package com.ltcode.queue;

import com.ltcode.list.SinglyLinkedList;

public class LinkedQueue<T> implements Queue<T> {

    private SinglyLinkedList<T> list;

    public LinkedQueue() {
        this.list = new SinglyLinkedList<>();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        return list.remove(0);
    }

    @Override
    public void enqueue(T newValue) {
        list.add(newValue);
    }

    @Override
    public boolean isEmpty() {
        return list.getSize() == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T peek() {
        return isEmpty()
                ? null
                : list.get(0);
    }
}
