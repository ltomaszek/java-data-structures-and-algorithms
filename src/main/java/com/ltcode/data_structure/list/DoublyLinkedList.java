package com.ltcode.data_structure.list;


public class DoublyLinkedList<T> extends AbstractLinkedList<T> {

    protected static class Node<T> {
        T value;
        Node prev;
        Node next;

        public Node(T value) {
            this.value = value;
        }
    }

    protected Node<T> head;
    protected Node<T> tail;

    @Override
    public boolean add(T newValue) {
        Node<T> newNode = new Node(newValue);
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkGetIndex(index);
        if (index == size - 1) {
            return tail.value;
        }
        Node<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.value;
    }


    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        checkGetIndex(index);
        T value;
        if (size == 1) {
            value = head.value;
            head = null;
            tail = null;
        } else if (index == 0) {
            value = head.value;
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            value = tail.value;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> node = head.next;               // start from second element
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            value = node.value;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
        return value;
    }

    /*
    All prev references must be deleted to make the nodes eligible for GC
     */
    @Override
    public void clear() {
        Node<T> currNode = head;
        for (int i = 0; i < size; i++) {
            currNode.prev = null;
            currNode = currNode.next;
        }
        head = null;
        tail = null;
        size = 0;
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
}