package com.ltcode.data_structure.stack.extra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetOfStacksTest {

    SetOfStacks<Integer> stack;
    final int SUB_STACK_CAPACITY = 3;

    @BeforeEach
    void setUp() {
        stack = new SetOfStacks<>(SUB_STACK_CAPACITY);
    }

    @Test
    void clear() {
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void isFull() {
        assertFalse(stack.isFull());
    }

    @Test
    void peek() {
        assertNull(stack.peek());

        stack.push(11);
        assertEquals(11, stack.peek());

        stack.pop();
        assertNull(stack.peek());
    }

    @Test
    void pop() {
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
        }

        for (int i = 10; i >= 1; i--) {
            assertEquals(i, stack.pop());
        }

        assertThrows(RuntimeException.class,
                () -> stack.pop());
    }

    @Test
    void pop_fromSubStack() {
        for (int i = 1; i <= 9; i++) {
            stack.push(i);
        }

        assertThrows(RuntimeException.class,
                () -> stack.pop(0));
        assertThrows(RuntimeException.class,
                () -> stack.pop(4));

        assertEquals(6, stack.pop(2));
        assertEquals(9, stack.pop(3));
        assertEquals(3, stack.pop(1));

        assertThrows(RuntimeException.class,
                () -> stack.pop(3));

        // 1, 2, 4 -> 5, 7, 8

        assertEquals(8, stack.pop(2));
        assertEquals(4, stack.pop(1));
        assertEquals(7, stack.pop(2));

        // 1, 2, 5

        assertThrows(RuntimeException.class,
                () -> stack.pop(2));

        assertEquals(5, stack.pop(1));
        assertEquals(2, stack.pop(1));
        assertEquals(1, stack.pop(1));

        assertThrows(RuntimeException.class,
                () -> stack.pop(1));
    }

    @Test
    void push() {
        stack.push(1);
        assertEquals(1, stack.peek());
        stack.push(2);
        assertEquals(2, stack.peek());
        stack.push(3);
        assertEquals(3, stack.peek());
        stack.push(4);
        assertEquals(4, stack.peek());
        stack.push(5);
        assertEquals(5, stack.peek());
        stack.push(6);
        assertEquals(6, stack.peek());
        stack.push(11);
        assertEquals(11, stack.peek());
    }
}