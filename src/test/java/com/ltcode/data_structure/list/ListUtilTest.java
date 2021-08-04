package com.ltcode.data_structure.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilTest {


    @BeforeEach
    void setUp() {

    }

    @Test
    void deleteDuplicate_v1() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        final int START_LIST_SIZE = 4;
        list.add(1);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(4);

        ListUtil.deleteDuplicate(list);

        String expectedList = "[1, 3, 2, 4]";
        assertEquals(expectedList, list.toString());
    }

    @Test
    void testPartition() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(7);
        list.add(5);
        list.add(1);
        list.add(3);

        String originalList = list.toString();

        int partitionValue = -1;
        ListUtil.partition(list, partitionValue);
        assertEquals(originalList, list.toString());

        partitionValue = 10;
        ListUtil.partition(list, partitionValue);
        assertEquals(originalList, list.toString());

        partitionValue = 3;
        String expectedList = "[1, 2, 1, 3, 3, 7, 5]";
        ListUtil.partition(list, partitionValue);
        assertEquals(expectedList, list.toString());
    }

    @Test
    void testSumLists() {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>(7, 1, 6);
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>(5, 9, 2);
        assertEquals("[2, 1, 9]", ListUtil.sumLists(list1, list2).toString());

        list1 = new SinglyLinkedList<>();
        list2 = new SinglyLinkedList<>(5, 9, 2);
        assertEquals("[5, 9, 2]", ListUtil.sumLists(list1, list2).toString());

        list1 = new SinglyLinkedList<>(5, 9, 2);
        list2 = new SinglyLinkedList<>();
        assertEquals("[5, 9, 2]", ListUtil.sumLists(list1, list2).toString());

        list1 = new SinglyLinkedList<>(3, 4, 5);
        list2 = new SinglyLinkedList<>(9, 9, 9);
        assertEquals("[2, 4, 5, 1]", ListUtil.sumLists(list1, list2).toString());
    }

    @Test
    void testAreIntersecting_true() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>();

        SinglyLinkedList.Node<String> node1 = new SinglyLinkedList.Node("A");
        SinglyLinkedList.Node<String> node2 = new SinglyLinkedList.Node("B");
        SinglyLinkedList.Node<String> node3 = new SinglyLinkedList.Node("C");
        SinglyLinkedList.Node<String> node4 = new SinglyLinkedList.Node("D");
        SinglyLinkedList.Node<String> node5 = new SinglyLinkedList.Node("E");
        SinglyLinkedList.Node<String> node6 = new SinglyLinkedList.Node("F");

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        list1.head = node1;
        list1.size = 5;

        // Node 4 is the common first node
        node6.next = node4;
        list2.head = node6;
        list2.size = 3;

        assertTrue(ListUtil.areIntersecting(list1, list2));
    }

    @Test
    void testAreIntersecting_false() {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>();

        SinglyLinkedList.Node<String> node1 = new SinglyLinkedList.Node("A");
        SinglyLinkedList.Node<String> node2 = new SinglyLinkedList.Node("B");
        SinglyLinkedList.Node<String> node3 = new SinglyLinkedList.Node("C");
        SinglyLinkedList.Node<String> node4 = new SinglyLinkedList.Node("D");
        SinglyLinkedList.Node<String> node5 = new SinglyLinkedList.Node("E");
        SinglyLinkedList.Node<String> node6 = new SinglyLinkedList.Node("F");

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        list1.head = node1;
        list1.size = 4;

        node5.next = node6;
        list2.head = node5;
        list2.size = 2;

        assertFalse(ListUtil.areIntersecting(list1, list2));
    }
}
