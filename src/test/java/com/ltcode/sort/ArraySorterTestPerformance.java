package com.ltcode.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;
import static com.ltcode.sort.ArraySorterTest.*;

class ArraySorterTestPerformance {

    enum SortClassType {
        SAFE_BUILD_IN_MERGE_SORT, TESTED_MERGE_SORT, TESTED_MERGE_SORT_2, TESTED_MERGE_SORT_3
    }

    Animal[] expectedAnimals;
    Animal[] testedAnimals;
    Comparator<Animal> dogComparator;

    @BeforeEach
    void setUp() {
        dogComparator = (d1, d2) -> Integer.compare(d2.age, d1.age);
    }

    @Test
    void mergeSort() {
        int[] arrayLength = {100, 1000, 10_000, 100_000, 1_000_000, (int)1e7};

        for (int length : arrayLength) {
            Animal[] expectedAnimals;
            Animal[] testedAnimals;
            Animal[] testedAnimals2;
            Animal[] testedAnimals3;

            expectedAnimals = createRandomAnimalArray(length);
            testedAnimals = Arrays.copyOf(expectedAnimals, expectedAnimals.length);
            testedAnimals2 = Arrays.copyOf(expectedAnimals, expectedAnimals.length);
            testedAnimals3 = Arrays.copyOf(expectedAnimals, expectedAnimals.length);

            System.out.println("\n\tElements: " + length);
            long javaTime = measureSortTime(expectedAnimals, dogComparator, SortClassType.SAFE_BUILD_IN_MERGE_SORT);
            System.out.println("Java time    : " + javaTime);

            long testedTime = measureSortTime(testedAnimals, dogComparator, SortClassType.TESTED_MERGE_SORT);
            System.out.println("Tested time  : " + testedTime);
            System.out.println("tested time / java time   : " + 1.0 * testedTime / javaTime);

            long testedTime2 = measureSortTime(testedAnimals2, dogComparator, SortClassType.TESTED_MERGE_SORT_2);
            System.out.println("Tested time 2: " + testedTime2);
            System.out.println("tested time 2 / java time : " + 1.0 * testedTime2 / javaTime);

            long testedTime3 = measureSortTime(testedAnimals3, dogComparator, SortClassType.TESTED_MERGE_SORT_3);
            System.out.println("Tested time 3: " + testedTime3);
            System.out.println("tested time 3 / java time : " + 1.0 * testedTime3 / javaTime);

            assertArrayEquals(expectedAnimals, testedAnimals);
            assertArrayEquals(expectedAnimals, testedAnimals2);
            assertArrayEquals(expectedAnimals, testedAnimals3);
        }
    }

    // == HELPER METHODS

    long measureSortTime(Animal[] animals, Comparator<Animal> comparator, SortClassType sortClassType) {
        long startTime = System.nanoTime();
        switch (sortClassType) {
            case SAFE_BUILD_IN_MERGE_SORT:
                Arrays.sort(animals, comparator);
                break;
            case TESTED_MERGE_SORT:
                ArraySorter.mergeSort(animals, comparator);
                break;
            case TESTED_MERGE_SORT_2:
                ArraySorter.mergeSort2(animals, comparator);
                break;
            case TESTED_MERGE_SORT_3:
                ArraySorter.mergeSort3(animals, comparator);
                break;
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}