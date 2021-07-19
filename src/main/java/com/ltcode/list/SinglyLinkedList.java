package com.ltcode.list;

public class SinglyLinkedList<T> extends AbstractLinkedList<T> {

    protected static class Node<T> {
        T value;
        Node<T> next;

        public Node() {
        }

        Node(T value) {
            this.value = value;
        }
    }

    protected Node<T> head;
    protected Node<T> tail;

    public SinglyLinkedList(T... elements) {
        for (T element : elements) {
            add(element);
        }
    }

    @Override
    public boolean add(T newValue) {
        Node<T> newNode = new Node<>(newValue);
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    public boolean add(int index, T newValue) {
        checkAddIndex(index);
        if (index == 0) {
            addToBeginningOfList(newValue);
        } else if (index == size) {
            add(newValue);
        } else {
            throw new RuntimeException("Still not implemented");
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkGetIndex(index);
        if (index == size - 1) {
            return tail.value;
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        checkGetIndex(index);
        T toReturn = null;
        if (size == 1) {
            toReturn = head.value;
            clear();
        } else if (index == 0) {
            toReturn = head.value;
            head = head.next;
            size--;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            toReturn = prev.next.value;
            prev.next = prev.next.next;

            if (index == size - 1) {
                tail = prev;
            }
            size--;
        }
        return toReturn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node<T> node = head;
        for (int i = 0; i < size - 1; i++) {
            sb.append(node.value);
            sb.append(", ");
            node = node.next;
        }
        sb.append(node.value);
        sb.append(']');
        return sb.toString();
    }

    // == PRIVATE METHODS ==

    private void addToBeginningOfList(T newValue) {
        Node<T> node = new Node<>(newValue);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            Node<T> oldHead = head;
            head = node;
            head.next = oldHead;
        }
        size++;
    }
}
