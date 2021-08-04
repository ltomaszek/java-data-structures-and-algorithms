package com.ltcode.data_structure.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BinaryNodeTest {

    /**
     *       0
     *   1       2
     *  3 4     5 6
     * 7
     */
    BinaryNode<Integer> root;
    BinaryNode<Integer> n1;
    BinaryNode<Integer> n2;
    BinaryNode<Integer> n3;
    BinaryNode<Integer> n4;
    BinaryNode<Integer> n5;
    BinaryNode<Integer> n6;
    BinaryNode<Integer> n7;

    @BeforeEach
    void setUp() {
        root = new BinaryNode<>(0);
        n1 = new BinaryNode<>(1);
        n2 = new BinaryNode<>(2);
        n3 = new BinaryNode<>(3);
        n4 = new BinaryNode<>(4);
        n5 = new BinaryNode<>(5);
        n6 = new BinaryNode<>(6);
        n7 = new BinaryNode<>(7);

        root.setChildren(n1, n2);
        n1.setChildren(n3, n4);
        n2.setChildren(n5, n6);
        n3.setChildren(n7, null);
    }

    @Test
    void preOrder() {
        var expectedList = List.of(root.value, n1.value, n3.value, n7.value, n4.value, n2.value, n5.value, n6.value);
        assertEquals(expectedList, root.preOrder());
    }

    @Test
    void inOrder() {
        var expectedList = List.of(n7.value, n3.value, n1.value, n4.value, root.value, n5.value, n2.value, n6.value);
        assertEquals(expectedList, root.inOrder());
    }

    @Test
    void postOrder() {
        var expectedList = List.of(n7.value, n3.value, n4.value, n1.value, n5.value, n6.value, n2.value, root.value);
        assertEquals(expectedList, root.postOrder());
    }
}