package com.ltcode.data_structure.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeLinkedTest {

    /**
     *       2
     *   1       4
     *  0      3  6
     *           5 7
     */
    BinarySearchTreeLinked<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTreeLinked<>();
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(7);
        tree.add(5);
        tree.add(3);
        tree.add(1);
        tree.add(0);
    }

    @Test
    void add() {
        assertTrue(tree.add(10));
        assertTrue(tree.add(-1));
        assertTrue(tree.add(100));

        assertAll(
                () -> assertFalse(tree.add(0)),
                () -> assertFalse(tree.add(1)),
                () -> assertFalse(tree.add(2)),
                () -> assertFalse(tree.add(3)),
                () -> assertFalse(tree.add(4)),
                () -> assertFalse(tree.add(5)),
                () -> assertFalse(tree.add(6)),
                () -> assertFalse(tree.add(7))
        );
    }

    @Test
    void clear() {
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());

        assertAll(
                () -> assertFalse(tree.contains(0)),
                () -> assertFalse(tree.contains(1)),
                () -> assertFalse(tree.contains(2)),
                () -> assertFalse(tree.contains(3)),
                () -> assertFalse(tree.contains(4)),
                () -> assertFalse(tree.contains(5)),
                () -> assertFalse(tree.contains(6)),
                () -> assertFalse(tree.contains(7)),
                () -> assertFalse(tree.contains(-1)),
                () -> assertFalse(tree.contains(8))
        );
    }

    @Test
    void contains() {
        assertAll(
                () -> assertTrue(tree.contains(0)),
                () -> assertTrue(tree.contains(1)),
                () -> assertTrue(tree.contains(2)),
                () -> assertTrue(tree.contains(3)),
                () -> assertTrue(tree.contains(4)),
                () -> assertTrue(tree.contains(5)),
                () -> assertTrue(tree.contains(6)),
                () -> assertTrue(tree.contains(7)),

                () -> assertFalse(tree.contains(-1)),
                () -> assertFalse(tree.contains(8))
        );

        tree.add(22);
        assertTrue(tree.contains(22));
    }

    @Test
    void remove() {
        tree.remove(0);
        assertContains(1, 2, 3, 4, 5, 6, 7);
        tree.add(0);

        tree.remove(1);
        assertContains(0, 2, 3, 4, 5, 6, 7);
        tree.add(1);

        tree.remove(2);
        assertContains(0, 1, 3, 4, 5, 6, 7);
        tree.add(2);

        tree.remove(3);
        assertContains(0, 1, 2, 4, 5, 6, 7);
        tree.add(3);

        tree.remove(4);
        assertContains(0, 1, 2, 3, 5, 6, 7);
        tree.add(4);

        tree.remove(5);
        assertContains(0, 1, 2, 3, 4, 6, 7);
        tree.add(5);

        tree.remove(6);
        assertContains(0, 1, 2, 3, 4, 5, 7);
        tree.add(6);


        tree.remove(7);
        assertContains(0, 1, 2, 3, 4, 5, 6);

        tree.remove(5);
        assertContains(0, 1, 2, 3, 4, 6);

        tree.remove(0);
        assertContains(1, 2, 3, 4, 6);

        tree.remove(2);
        assertContains(1, 3, 4, 6);
        assertFalse(tree.remove(2));

        assertTrue(tree.remove(1));
        assertContains(3, 4, 6);
        assertFalse(tree.remove(1));

        tree.remove(4);
        assertContains(3, 6);

        tree.remove(3);
        assertContains(6);

        assertTrue(tree.remove(6));
        assertContains();
    }


    // == HELPER METHODS ==

    void assertContains(Integer... values) {
        Arrays.stream(values).parallel()
                .forEach(value -> assertTrue(tree.contains(value), "Value=" + value));
    }
}