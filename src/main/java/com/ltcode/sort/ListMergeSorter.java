package com.ltcode.sort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ListMergeSorter<T> extends RecursiveAction {

    private final int THRESHOLD;
    private final List<T> list;
    private final Comparator<T> comparator;
    private final int startIdx;
    private final int endIdx;       // including

    public ListMergeSorter(int THRESHOLD, List<T> list, Comparator<T> comparator) {
        this(THRESHOLD, list, comparator,0, list.size() - 1);
    }

    public ListMergeSorter(int THRESHOLD, List<T> list, Comparator<T> comparator, int startIdx, int endIdx) {
        this.THRESHOLD = THRESHOLD;
        this.list = list;
        this.comparator = comparator;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    @Override
    protected void compute() {
        if (endIdx - startIdx + 1 > THRESHOLD) {
            int middle = startIdx + (endIdx - startIdx) / 2;
            invokeAll(
                    new ListMergeSorter<>(THRESHOLD, list, comparator, startIdx, middle),
                    new ListMergeSorter<>(THRESHOLD, list, comparator, middle + 1, endIdx)
            );
        }
        mergeSort(list, comparator, startIdx, endIdx);
    }

    private void mergeSort(List<T> list, Comparator<T> comparator, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(list, comparator, start, mid);
        mergeSort(list, comparator, mid + 1, end);

        merge(list, comparator, start, mid, end);
    }

    private void merge(List<T> list, Comparator<T> comparator, int start, int mid, int end) {
        T[] temp = (T[]) new Object[end-start+1];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = list.get(start + i);
        }

        int leftIdx = 0;
        int rightIdx = mid - start + 1;
        int maxLeft = rightIdx - 1;
        int maxRight = end - start;
        for (int i = start; i <= end; i++) {
            if (rightIdx > maxRight) {
                list.set(i, temp[leftIdx]);
                leftIdx++;
            } else if (leftIdx > maxLeft) {
                list.set(i, temp[rightIdx]);
                rightIdx++;
            } else {
                int comp = comparator.compare(temp[leftIdx], temp[rightIdx]);
                if (comp <= 0) {
                    list.set(i, temp[leftIdx]);
                    leftIdx++;
                } else {
                    list.set(i, temp[rightIdx]);
                    rightIdx++;
                }
            }
        }
    }
}
