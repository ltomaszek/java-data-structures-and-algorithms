package com.ltcode.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ArrayMergeSorter<T> extends RecursiveAction {

    private final int THRESHOLD;
    private final T[] array;
    private final Comparator<T> comparator;
    private final int startIdx;
    private final int endIdx;       // including

    public ArrayMergeSorter(int THRESHOLD, T[] array, Comparator<T> comparator) {
        this(THRESHOLD, array, comparator,0, array.length - 1);
    }

    public ArrayMergeSorter(int THRESHOLD, T[] array, Comparator<T> comparator, int startIdx, int endIdx) {
        this.THRESHOLD = THRESHOLD;
        this.array = array;
        this.comparator = comparator;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    @Override
    protected void compute() {
        if (endIdx - startIdx + 1 > THRESHOLD) {
            int middle = startIdx + (endIdx - startIdx) / 2;
            invokeAll(
                    new ArrayMergeSorter<>(THRESHOLD, array, comparator, startIdx, middle),
                    new ArrayMergeSorter<>(THRESHOLD, array, comparator, middle + 1, endIdx)
            );
        }
        mergeSort(array, comparator, startIdx, endIdx);
    }

    private void mergeSort(T[] array, Comparator<T> comparator, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(array, comparator, start, mid);
        mergeSort(array, comparator, mid + 1, end);

        merge(array, comparator, start, mid, end);
    }

    private void merge(T[] array, Comparator<T> comparator, int start, int mid, int end) {
        T[] temp = Arrays.copyOfRange(array, start, end + 1);

        int leftIdx = 0;
        int rightIdx = mid - start + 1;
        int maxLeft = rightIdx - 1;
        int maxRight = end - start;
        for (int i = start; i <= end; i++) {
            if (rightIdx > maxRight) {
                array[i] = temp[leftIdx];
                leftIdx++;
            } else if (leftIdx > maxLeft) {
                array[i] = temp[rightIdx];
                rightIdx++;
            } else {
                int comp = comparator.compare(temp[leftIdx], temp[rightIdx]);
                if (comp <= 0) {
                    array[i] = temp[leftIdx];
                    leftIdx++;
                } else {
                    array[i] = temp[rightIdx];
                    rightIdx++;
                }
            }
        }
    }
}
