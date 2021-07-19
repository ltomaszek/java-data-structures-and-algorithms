package com.ltcode.stack.extra;

import com.ltcode.stack.extra.ThreeInOneIntStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ThreeInOneIntStackTest {

    ThreeInOneIntStack stack;

    @BeforeEach
    void setUp() {
        stack = new ThreeInOneIntStack(3);
    }

    @Test
    void isFull() {
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 10);
        }
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 11);
            assertFalse(stack.isFull(i));
        }
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 12);
            assertTrue(stack.isFull(i));
        }

        for (int i = 1; i <= 3; i++) {
            stack.pop(i);
            assertFalse(stack.isFull(i));
        }
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 12);
            assertTrue(stack.isFull(i));
        }
    }

    @Test
    void isEmpty() {
        for (int i = 1; i <= 3; i++) {
            assertTrue(stack.isEmpty(i));
        }
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 10);
            assertFalse(stack.isEmpty(i));
        }
        for (int i = 1; i <= 3; i++) {
            stack.pop(i);
            assertTrue(stack.isEmpty(i));
        }
    }

    @Test
    void push() {
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 10);
        }
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 11);
        }
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 12);
        }
        assertThrows(RuntimeException.class,
                () -> stack.push(1, 13));
        assertThrows(RuntimeException.class,
                () -> stack.push(2, 13));
        assertThrows(RuntimeException.class,
                () -> stack.push(3, 13));
    }

    @Test
    void pop() {
        for (int i = 1; i <= 3; i++) {
            stack.push(i, 10);
            stack.push(i, 11);
            stack.push(i, 12);
        }

        for (int i = 1; i <= 3; i++) {
            assertEquals(12, stack.pop(i));
            assertEquals(11, stack.pop(i));
            assertEquals(10, stack.pop(i));
        }

        assertThrows(RuntimeException.class,
                () -> stack.pop(1));
        assertThrows(RuntimeException.class,
                () -> stack.pop(2));
        assertThrows(RuntimeException.class,
                () -> stack.pop(3));
    }

    @Test
    void peek() {
        assertThrows(RuntimeException.class,
                () -> stack.pop(1));
        assertThrows(RuntimeException.class,
                () -> stack.pop(2));
        assertThrows(RuntimeException.class,
                () -> stack.pop(3));

        for (int i = 1; i <= 3; i++) {
            stack.push(i, 10);
            assertEquals(10, stack.peek(i));
            stack.push(i, 11);
            assertEquals(11, stack.peek(i));

            stack.pop(i);
            assertEquals(10, stack.peek(i));
        }
    }
}