package com.ltcode.data_structure.queue.extra;

import com.ltcode.data_structure.queue.Queue;
import com.ltcode.data_structure.stack.LinkedStack;
import com.ltcode.data_structure.stack.Stack;

/**
 * Queue using two stacks
 */
public class QueueViaStack<T> implements Queue<T> {

    /**
     * New elements will be pushed to this stack
     */
    private Stack<T> enqueueStack;
    /**
     * Removed (dequeued) elements will be remove from this stack
     */
    private Stack<T> dequeueStack;

    public QueueViaStack() {
        this.enqueueStack = new LinkedStack<>();
        this.dequeueStack = new LinkedStack<>();
    }

    @Override
    public void clear() {
        enqueueStack.clear();
        dequeueStack.clear();
    }

    @Override
    public T dequeue() {
        if (dequeueStack.isEmpty()) {
            moveElementsBetweenStacks(enqueueStack, dequeueStack);
        }
        return dequeueStack.pop();
    }

    @Override
    public void enqueue(T newValue) {
        enqueueStack.push(newValue);
    }

    @Override
    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T peek() {
        if (dequeueStack.isEmpty()) {
            moveElementsBetweenStacks(enqueueStack, dequeueStack);
        }
        return dequeueStack.peek();
    }

    // == PRIVATE METHODS ==

    private void moveElementsBetweenStacks(Stack<T> fromStack, Stack<T> toStack) {
        while (!fromStack.isEmpty()) {
            toStack.push(fromStack.pop());
        }
    }
}
