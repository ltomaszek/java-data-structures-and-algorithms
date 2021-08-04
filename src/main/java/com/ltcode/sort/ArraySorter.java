package com.ltcode.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;

public class ArraySorter {

    public static <T extends Comparable<T>> void insertSort(T[] array) {
        insertSort(array, (t1, t2) -> t1.compareTo(t2));
    }

    public static <T> void insertSort(T[] array, Comparator<T> comparator) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(array[j - 1], array[j]) > 0) {
                swap(array, j - 1, j);
                j--;
            }
        }
    }

    // 4 cores
    public static <T> void mergeSort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return;
        }

        int N = array.length;
        int cores = 4;//Runtime.getRuntime().availableProcessors();
        int coreWork = (N + cores - 1) / cores;
        Thread[] threads = new Thread[cores];
        T[][] threadSortedArrays = (T[][]) new Object[cores][];

        for (int coreIdx = 0; coreIdx < cores; coreIdx++) {
            int i = coreIdx;
            int fromIdx = coreIdx * coreWork;
            int toIdx = Math.min(fromIdx + coreWork - 1, N - 1);
            Thread t = new Thread(() -> {
                threadSortedArrays[i] = mergeSort(array, comparator, fromIdx, toIdx);
            });
            t.start();
            threads[coreIdx] = t;
        }

        // merge all thread arrays
        for (int coreIdx = 0; coreIdx < cores; coreIdx++) {
            try {
                threads[coreIdx].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var sortedArray = mergeArraysRec(threadSortedArrays, comparator);//mergeSort(array, comparator,0, N - 1);
        for (int i = 0; i < N; i++) {
            array[i] = sortedArray[i];
        }
    }

    // 1 core
    public static <T> void mergeSort2(T[] array, Comparator<T> comparator) {
        mergeSort2(array, comparator, 0, array.length - 1);

        /* USING THREADS
        int minOneCoreWork = 500;
        if (array.length / minOneCoreWork <= 1) {
            mergeSort2(array, comparator, 0, array.length - 1);
            return;
        }

        int numCoresToUse = Math.min(array.length / minOneCoreWork, Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(numCoresToUse);
        final int workPerCore = (array.length + numCoresToUse - 1) / numCoresToUse;

        for (int coreIdx = 0; coreIdx < numCoresToUse; coreIdx++) {
            int startIdx = coreIdx * workPerCore;
            int endIdx = Math.min(startIdx + workPerCore - 1, array.length - 1);
            executorService.execute(() -> mergeSort2(array, comparator, startIdx, endIdx));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // merge subarrays from all threads           !!!! THIS MUST BE OPTIMIZED
        */
    }

    // up to all available cores - Recursive Action
    public static <T> void mergeSort3(T[] array, Comparator<T> comparator) {
        final int MIN_THRESHOLD = 100_000;
        if (array.length <= MIN_THRESHOLD) {
            mergeSort2(array, comparator, 0, array.length - 1);
            return;
        }

        int numCoresToUse = Math.min(array.length / MIN_THRESHOLD, Runtime.getRuntime().availableProcessors());
        final int WORK_PER_CORE = (array.length + numCoresToUse - 1) / numCoresToUse;

        ForkJoinTask task = new ArrayMergeSorter(WORK_PER_CORE, array, comparator);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(task);
    }

    // == PRIVATE METHODS ==

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T> T[] mergeSort(T[] array, Comparator<T> comparator, int fromIdx, int toIdx) {
        if (fromIdx == toIdx) {
            return (T[]) new Object[]{array[fromIdx]};
        }
        int midIdx = fromIdx + (toIdx - fromIdx) / 2;

        T[] sortedArrayLeft = mergeSort(array, comparator, fromIdx, midIdx);
        T[] sortedArrayRight = mergeSort(array, comparator, midIdx + 1, toIdx);
        return merge(sortedArrayLeft, sortedArrayRight, comparator);
    }

    private static <T> T[] merge(T[] array1, T[] array2, Comparator<T> comparator) {
        int size = array1.length + array2.length;
        T[] mergedArray = (T[]) new Object[size];
        int idx1 = 0;
        int idx2 = 0;
        for (int i = 0; i < size; i++) {
            if (idx1 == array1.length) {
                mergedArray[i] = array2[idx2];
                idx2++;
            } else if (idx2 == array2.length) {
                mergedArray[i] = array1[idx1];
                idx1++;
            } else {
                if (comparator.compare(array1[idx1], array2[idx2]) <= 0) {
                    mergedArray[i] = array1[idx1];
                    idx1++;
                } else {
                    mergedArray[i] = array2[idx2];
                    idx2++;
                }
            }
        }
        return mergedArray;
    }

    private static <T> T[] mergeArraysRec(T[][] arrays, Comparator<T> comparator) {
        if (arrays.length == 1) {
            return arrays[0];
        }
        int mid = arrays.length / 2;
        T[][] leftArrays = Arrays.copyOfRange(arrays, 0, mid);
        T[][] rightArrays = Arrays.copyOfRange(arrays, mid, arrays.length);

        return merge(
                mergeArraysRec(leftArrays, comparator),
                mergeArraysRec(rightArrays, comparator),
                comparator);
    }

    private static <T> void mergeSort2(T[] array, Comparator<T> comparator, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort2(array, comparator, start, mid);
        mergeSort2(array, comparator, mid + 1, end);

        merge2(array, comparator, start, mid, end);
    }

    private static <T> void merge2(T[] array, Comparator<T> comparator, int start, int mid, int end) {
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