package com.ltcode.list;

import java.util.HashSet;
import java.util.Set;

public class ListUtil {

    /**
     * Order will be as original
     * All values smaller than 'value' will be on the left side and values greater than 'value' on the right side
     */
    public static <T extends Comparable<T>> void partition(SinglyLinkedList list, T partitionValue) {
        if (list.getSize() < 2) {
            return;
        }

        SinglyLinkedList.Node<T> dumbSmallerHead = new SinglyLinkedList.Node<>();
        SinglyLinkedList.Node<T> smallerTail = dumbSmallerHead;
        SinglyLinkedList.Node<T> dumbGreaterHead = new SinglyLinkedList.Node<>();
        SinglyLinkedList.Node<T> greaterTail = dumbGreaterHead;

        SinglyLinkedList.Node<T> currNode = list.head;
        SinglyLinkedList.Node<T> nextNode = null;

        while (currNode != null) {
            // save next Node reference, cause currentNode.next might be changed
            nextNode = currNode.next;

            T currValue = currNode.value;
            int compare = currValue.compareTo(partitionValue);

            if (compare == 0) {
                // set node at the beginning of 'greater list'
                currNode.next = dumbGreaterHead.next;
                dumbGreaterHead.next = currNode;

                // first element added
                if (greaterTail == dumbGreaterHead) {
                    greaterTail = currNode;
                }
            } else if (compare < 0) {
                smallerTail.next = currNode;
                smallerTail = currNode;
            } else {
                greaterTail.next = currNode;
                greaterTail = currNode;
            }

            currNode = nextNode;
        }

        // connect lists
        var isSmallerAdded = dumbSmallerHead != smallerTail;
        var isGreaterAdded = dumbGreaterHead != greaterTail;

        if (isSmallerAdded) {
            list.head = dumbSmallerHead.next;
            smallerTail.next = dumbGreaterHead.next;
        } else {
            list.head = dumbGreaterHead.next;
        }
        list.tail = isGreaterAdded
                ? greaterTail
                : smallerTail;

    }

    public static <T> void deleteDuplicate(DoublyLinkedList<T> list) {
        var isCircularList = list.getClass() == DoublyLinkedList.class;

        Set<T> visited = new HashSet<>();
        var node = list.head;
        var originalSize = list.getSize();

        list.head.prev = null;
        list.tail.next = null;

        for (int i = 0; i < originalSize; i++) {
            if (visited.contains(node.value)) {
                node.prev.next = node.next;

                if (i < list.size - 1) {
                    node.next.prev = node.prev;
                }

                list.size--;
            } else {
                visited.add(node.value);
                list.tail = node;
            }
            node = node.next;
        }

        if (isCircularList) {
            list.head.prev = list.tail;
            list.tail.next = list.head;
        }
    }

    public static SinglyLinkedList<Integer> sumLists(SinglyLinkedList<Integer> list1, SinglyLinkedList<Integer> list2) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        SinglyLinkedList.Node<Integer> node1 = list1.head;
        SinglyLinkedList.Node<Integer> node2 = list2.head;

        var rest = 0;

        while (node1 != null || node2 != null || rest > 0) {
            int roundSum = rest;
            if (node1 != null) {
                roundSum += node1.value;
                node1 = node1.next;
            }
            if (node2 != null) {
                roundSum += node2.value;
                node2 = node2.next;
            }
            rest = roundSum / 10;

            list.add(roundSum % 10);
        }
        return list;
    }

    /*
    @Deprecated
    public static <T> boolean areIntersecting(SinglyLinkedList<T> list1, SinglyLinkedList<T> list2) {
        HashSet<SinglyLinkedList.Node<T>> set = new HashSet<>();
        SinglyLinkedList.Node<T> currNode = list1.head;
        for (int i = 0; i < list1.size; i++) {
            set.add(currNode);
            currNode = currNode.next;
        }
        currNode = list2.head;
        for (int i = 0; i < list2.size; i++) {
            if (set.contains(currNode)) {
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }
    */

    public static <T> boolean areIntersecting(SinglyLinkedList<T> list1, SinglyLinkedList<T> list2) {
        SinglyLinkedList.Node<T> longerList = list1.size >= list2.size ? list1.head : list2.head;
        SinglyLinkedList.Node<T> shorterList = list1.size < list2.size ? list1.head : list2.head;

        // skip the size difference
        int sizeDifference = Math.abs(list1.size - list2.size);
        for (int i = 0; i < sizeDifference; i++) {
            longerList = longerList.next;
        }

        // compare nodes
        int shorterSize = (list1.size + list2.size - sizeDifference) / 2;
        for (int i = 0; i < shorterSize; i++) {
            if (longerList == shorterList) {
                return true;
            }
            longerList = longerList.next;
            shorterList = shorterList.next;
        }
        return false;
    }

}
