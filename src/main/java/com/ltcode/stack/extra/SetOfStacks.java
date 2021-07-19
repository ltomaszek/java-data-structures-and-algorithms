package com.ltcode.stack.extra;


import com.ltcode.stack.Stack;

import java.util.ArrayList;
import java.util.List;

public class SetOfStacks<T> implements Stack<T> {

    private static class SubStack<T> {

        Node<T> head;   // bottom of stack
        Node<T> tail;   // top of stack

        private static class Node<T> {
            T value;
            Node<T> next;
            Node<T> prev;

            public Node(T value) {
                this.value = value;
            }
        }

        void push(T value) {
            Node<T> newNode = new Node<>(value);
            if (head == null) {
                head = newNode;
            } else {
                newNode.prev = tail;
                tail.next = newNode;
            }
            tail = newNode;
        }

        void pushNode(Node<T> node) {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }

        T popFromTop() {
            var value = tail.value;
            tail = tail.prev;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
            return value;
        }

        Node<T> popNodeFromTop() {
            var node = tail;
            tail = tail.prev;
            if (tail == null) {
                head = null;
            }
            return node;
        }

        Node<T> popNodeFromBottom() {
            var node = head;
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }
            return node;
        }

        T peek() {
            return tail.value;
        }

        boolean isEmpty() {
            return head == null;
        }
    }

    private int subStackCapacity;
    private int numElements;            // number of elements in all sub stacks
    private List<SubStack<T>> subStackList;

    public SetOfStacks(int subStackCapacity) {
        this.subStackCapacity = subStackCapacity;
        this.numElements = 0;
        this.subStackList = new ArrayList<>();
    }

    @Override
    public void clear() {
        subStackList.clear();
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T peek() {
        return isEmpty()
                ? null
                : getCurrentSubStack().peek();
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        SubStack<T> currentSubStack = getCurrentSubStack();
        T value = currentSubStack.popFromTop();
        if (currentSubStack.isEmpty()) {
            removeCurrentSubStack();
        }
        numElements--;
        return value;
    }

    /**
     *
     * subStackNumber starts from 1
     *
     * @param subStackNumber - index of sub stack
     * @return
     */
    public T pop(int subStackNumber) {
        if (subStackNumber > subStackList.size() || subStackNumber < 1) {
            throw new RuntimeException("Invalid sub stack index.");
        }

        subStackNumber--;

        var subStack = subStackList.get(subStackNumber);
        if (subStack == getCurrentSubStack()) { // last subStack
            return pop();
        }

        // adjust nodes
        var toReturn = subStack.popFromTop();
        for (int i = subStackNumber + 1; i < subStackList.size(); i++) {
            var prevSubStack = subStackList.get(i - 1);
            var currSubStack = subStackList.get(i);

            var nodeToMove = currSubStack.popNodeFromBottom();
            prevSubStack.pushNode(nodeToMove);
        }
        // remove last subStack if it's empty
        if (getCurrentSubStack().isEmpty()) {
            removeCurrentSubStack();
        }
        numElements--;

        return toReturn;
    }

    @Override
    public void push(T newValue) {
        SubStack<T> currentSubStack = isCurrentSubStackFull()
                ? createNewSubStack()
                : getCurrentSubStack();
        currentSubStack.push(newValue);
        numElements++;
    }

    // == PRIVATE METHODS ==

    /**
     * SubStack with the last element
     */
    public SubStack<T> getCurrentSubStack() {
        return subStackList.get((numElements - 1) / subStackCapacity);
    }

    public boolean isCurrentSubStackFull() {
        return numElements % subStackCapacity == 0;
    }

    public SubStack<T> createNewSubStack() {
        SubStack<T> newSubStack = new SubStack<>();
        subStackList.add(newSubStack);
        return newSubStack;
    }

    public void removeCurrentSubStack() {
        subStackList.remove(subStackList.remove(subStackList.size() - 1));
    }
}
