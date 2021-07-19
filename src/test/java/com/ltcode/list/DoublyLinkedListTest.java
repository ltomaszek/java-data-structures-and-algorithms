package com.ltcode.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    DoublyLinkedList<Integer> list;
    int START_LIST_SIZE;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(11);
        START_LIST_SIZE = 6;
    }

    @Test
    void testAdd_correctness() {
        final boolean EXPECTED_RETURN_VALUE = true;

        int addValue = 11;
        boolean returnValue = list.add(addValue);
        assertEquals(EXPECTED_RETURN_VALUE, returnValue);
        assertEquals(addValue, list.get(START_LIST_SIZE));

        addValue = 13;
        returnValue = list.add(addValue);
        assertEquals(EXPECTED_RETURN_VALUE, returnValue);
        assertEquals(addValue, list.get(START_LIST_SIZE + 1));

        addValue = -1;
        returnValue = list.add(addValue);
        assertEquals(EXPECTED_RETURN_VALUE, returnValue);
        assertEquals(addValue, list.get(START_LIST_SIZE + 2));
    }

    @Test
    void testAdd_size() {
        list.add(0);
        assertEquals(START_LIST_SIZE + 1, list.getSize());

        list.add(0);
        assertEquals(START_LIST_SIZE + 2, list.getSize());

        list.add(0);
        assertEquals(START_LIST_SIZE + 3, list.getSize());
    }

    @Test
    void testClear() {
        var head = list.head;
        var tail = list.tail;

        list.clear();

        assertEquals(0, list.getSize());
        assertNull(list.head);
        assertNull(list.tail);

        assertNull(tail.next);

        // Assert that no node points to the previous one
        // Only next pointers are allowed for GC to work properly
        var currNode = head;
        while (currNode != null) {
            assertNull(currNode.prev);
            currNode = currNode.next;
        }
    }

    @Test
    void testGet() {
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(7, list.get(3));
        assertEquals(9, list.get(4));

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(-100));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(START_LIST_SIZE));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(START_LIST_SIZE + 100));
    }

    @Test
    void testGetSize() {
        assertEquals(START_LIST_SIZE, list.getSize());

        for (int i = 0; i < 10; i++) {
            list.add(0);
            int expectedSize = START_LIST_SIZE + i + 1;
            assertEquals(expectedSize, list.getSize());
        }
    }

    @Test
    void testRemove_correctness() {
        // remove last element
        int removedValue = list.remove(list.getSize() - 1);
        assertEquals(11, removedValue);

        // remove first element
        removedValue = list.remove(0);
        assertEquals(1, removedValue);

        // remove second from last
        removedValue = list.remove(2);
        assertEquals(7, removedValue);

        // remove in the middle
        removedValue = list.remove(1);
        assertEquals(5, removedValue);

        removedValue = list.remove(0);
        assertEquals(3, removedValue);

        removedValue = list.remove(0);
        assertEquals(9, removedValue);
    }

    @Test
    void testRemove_index() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(-100));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(START_LIST_SIZE));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(START_LIST_SIZE + 100));
    }

    @Test
    void testRemove_size() {
        int expectedSize = START_LIST_SIZE;
        list.remove(5);
        assertEquals(expectedSize - 1, list.getSize());

        list.remove(0);
        assertEquals(expectedSize - 2, list.getSize());

        list.remove(3);
        assertEquals(expectedSize - 3, list.getSize());

        list.remove(1);
        assertEquals(expectedSize - 4, list.getSize());

        list.remove(0);
        assertEquals(expectedSize - 5, list.getSize());

        list.remove(0);
        assertEquals(0, list.getSize());
    }

    @Test
    void test_headAndTailReference() {
        int[] indexesToRemove = {5, 0, 2, 1, 0};
        for (int indexToRemove : indexesToRemove) {
            list.remove(indexToRemove);

            assertNotNull(list.head);
            assertNotNull(list.tail);

            assertNull(list.head.prev);
            assertNull(list.tail.next);
        }

        // remove last node
        assertEquals(1, list.getSize());
        assertEquals(list.head, list.tail);

        var headAndTail = list.head;
        list.remove(0);

        // assert that the last node is not pointing to itself
        assertNull(headAndTail.next);
        assertNull(headAndTail.prev);

        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    void testToString() {
        assertEquals("[1, 3, 5, 7, 9, 11]", list.toString());
    }
}