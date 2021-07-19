package com.ltcode.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedStack<>();
        stack.push(1);
        stack.push(3);
    }

    @Test
    void clear() {
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    void isEmpty() {
        assertFalse(stack.isEmpty());
        stack.pop();
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void isFull() {
        assertFalse(stack.isFull());
        stack.push(3);
        assertFalse(stack.isFull());
    }

    @Test
    void peek() {
        assertEquals(3, stack.peek());
        stack.push(13);
        assertEquals(13, stack.peek());
    }

    @Test
    void pop() {
        Integer value = stack.pop();
        assertEquals(3, value);

        value = stack.pop();
        assertEquals(1, value);

        assertThrows(RuntimeException.class,
                () -> stack.pop());
    }

    @Test
    void push() {
        stack.push(12);
        assertEquals(12, stack.peek());
        stack.push(13);
        assertEquals(13, stack.peek());
    }
}