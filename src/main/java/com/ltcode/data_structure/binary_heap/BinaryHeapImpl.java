package com.ltcode.data_structure.binary_heap;

import java.util.Comparator;

public class BinaryHeapImpl<T> implements BinaryHeap<T> {

    private int size;
    /**
     * Comparator.compare(newValue, oldValue)
     * negative comparison result will result in the new value putting to the top
     */
    private Comparator<T> comparator;
    private T[] array;                  // starts with index 1 not 0

    public BinaryHeapImpl(Comparator<T> comparator) {
        this(8, comparator);
    }

    public BinaryHeapImpl(int capacity, Comparator<T> comparator) {
        this.comparator = comparator;
        this.array = (T[]) new Object[capacity + 1];
    }

    @Override
    public void insert(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Null value not allowed!");
        }
        if (isCapacityReached()) {
            int newSize = size * 2;
            resizeArray(newSize);
        }
        size++;
        array[size] = value;
        updatePositionOfNewValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T peek() {
        return isEmpty()
                ? null
                : array[1];
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        var toReturn = array[1];
        size--;
        if (size > 0) {
            array[1] = array[size + 1];
            array[size + 1] = null;
            topToBottom();
        }
        if (size > 0 && size <= 0.25 * array.length) {
            resizeArray(size * 2);
        }
        return toReturn;
    }

    @Override
    public int size() {
        return size;
    }

    // == PRIVATE METHODS ==

    private boolean isCapacityReached() {
        return size == this.array.length - 1;
    }

    private void resizeArray(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 1, newArray, 1, size);
        array = newArray;
    }

    /*
    bottom to top
     */
    private void updatePositionOfNewValue() {
        int currIdx = size;
        int parentIdx = currIdx / 2;
        T lastValue = array[currIdx];
        T parentValue = array[parentIdx];

        /*
        As long as the comparator will be < 0, the new value will be pushed one level up the heap
        */
        while (parentIdx >= 1 && comparator.compare(lastValue, parentValue) < 0) {
            swap(currIdx, parentIdx);

            currIdx = parentIdx;
            parentIdx /= 2;
            parentValue = array[parentIdx];
        }
    }

    /*
    top to bottom
     */
    private void topToBottom() {
        // put last element to top
        int index = 1;

        while (true) {
            int leftIdx = index * 2;
            int rightIdx = index * 2 + 1;

            if (rightIdx <= size
                    && array[rightIdx] != null
                    && comparator.compare(array[rightIdx], array[index]) < 0
                    && comparator.compare(array[rightIdx], array[leftIdx]) < 0) {
                swap(index, rightIdx);
                index = rightIdx;
            } else if (leftIdx <= size
                    && array[leftIdx] != null
                    && comparator.compare(array[leftIdx], array[index]) < 0) {
                swap(index, leftIdx);
                index = leftIdx;
            } else {
                break;
            }
        }
    }

    private void swap(int idx1, int idx2) {
        T temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }
}
