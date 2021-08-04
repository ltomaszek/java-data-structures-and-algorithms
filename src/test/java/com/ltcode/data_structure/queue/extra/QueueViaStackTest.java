package com.ltcode.data_structure.queue.extra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueViaStackTest {

    QueueViaStack<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new QueueViaStack<>();
        queue.enqueue(1);
        queue.enqueue(3);
    }

    @Test
    void clear() {
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    void dequeue() {
        Integer value = queue.dequeue();
        assertFalse(queue.isEmpty());
        assertEquals(1, value);

        value = queue.dequeue();
        assertTrue(queue.isEmpty());
        assertEquals(3, value);

        assertThrows(RuntimeException.class,
                () -> queue.dequeue());
    }

    @Test
    void enqueue() {
        queue.enqueue(5);
        queue.enqueue(7);

        queue.dequeue();
        queue.dequeue();

        assertEquals(5, queue.dequeue());
        assertEquals(7, queue.dequeue());
    }

    @Test
    void isEmpty() {
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void isFull() {
        assertFalse(queue.isFull());
    }

    @Test
    void peek() {
        assertEquals(1, queue.peek());
        queue.dequeue();

        queue.enqueue(10);
        assertEquals(3, queue.peek());

        assertEquals(3, queue.dequeue());

        queue.enqueue(20);
        assertEquals(10, queue.peek());
        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());

        assertNull(queue.peek());
    }

}