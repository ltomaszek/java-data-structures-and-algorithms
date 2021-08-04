package com.ltcode.data_structure.binary_heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapImplTest {

    BinaryHeap<Integer> heap;
    /**
     * Smallest values will be place at the top
     */
    Comparator<Integer> MIN_HEAP_COMPARATOR;

    @BeforeEach
    void setUp() {
        int START_CAPACITY = 4;
        MIN_HEAP_COMPARATOR = (newValue, oldValue) -> Integer.compare(newValue, oldValue);
        heap = new BinaryHeapImpl<>(START_CAPACITY, MIN_HEAP_COMPARATOR);
    }

    @Test
    void insert() {
    }

    @Test
    void isEmpty() {
        assertTrue(heap.isEmpty());

        heap.insert(10);
        assertFalse(heap.isEmpty());

        heap.pop();
        assertTrue(heap.isEmpty());
    }

    @Test
    void peek() {
        assertNull(heap.peek());

        heap.insert(10);
        assertEquals(10, heap.peek());

        heap.insert(20);
        assertEquals(10, heap.peek());

        heap.insert(0);
        assertEquals(0, heap.peek());
    }

    @Test
    void pop() {
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);
        heap.insert(0);
        heap.insert(25);
        heap.insert(20);

        assertEquals(0, heap.pop());
        assertEquals(5, heap.pop());
        assertEquals(10, heap.pop());
        assertEquals(15, heap.pop());
        assertEquals(20, heap.pop());
        assertEquals(25, heap.pop());

        assertNull(heap.pop());
    }

    @Test
    void size() {
        assertEquals(0, heap.size());

        heap.insert(1);
        assertEquals(1, heap.size());

        heap.insert(2);
        assertEquals(2, heap.size());

        heap.insert(3);
        assertEquals(3, heap.size());

        heap.pop();
        assertEquals(2, heap.size());

        heap.pop();
        assertEquals(1, heap.size());

        heap.pop();
        assertEquals(0, heap.size());

        heap.pop();
        assertEquals(0, heap.size());
    }

    @Test
    void multiOperationTest() {
        int SIZE = 10_000;
        Integer[] numberArray = createRandomNumberArray(SIZE);
        heap = new BinaryHeapImpl<>(SIZE, MIN_HEAP_COMPARATOR);

        // insert random numbers into heap
        for (int num : numberArray) {
            heap.insert(num);
        }

        // sort the array with the same comparator as the heap
        Arrays.sort(numberArray, MIN_HEAP_COMPARATOR);

        // top heap values should be in the same order as the sorted array
        for (int num : numberArray) {
            assertEquals(num, heap.pop());
        }
    }

    // == HELPER METHODS ==
    Integer[] createRandomNumberArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}