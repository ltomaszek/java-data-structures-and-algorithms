package com.ltcode.sort;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class ArraySorterTest {

    Animal[] expectedAnimals;
    Animal[] testedAnimals;
    Comparator<Animal> dogComparator;

    @BeforeEach
    void setUp() {
        final int NUM_OF_ANIMALS = 10_000_000;
        expectedAnimals = createRandomAnimalArray(NUM_OF_ANIMALS);
        testedAnimals = Arrays.copyOf(expectedAnimals, expectedAnimals.length);
        dogComparator = (d1, d2) -> Integer.compare(d2.age, d1.age);
    }

    @Test
    void insertSort() {
        Arrays.sort(expectedAnimals, dogComparator);
        ArraySorter.insertSort(testedAnimals, dogComparator);

        // System.out.println(Arrays.toString(expectedAnimals));
        // System.out.println(Arrays.toString(testedAnimals));
        assertArrayEquals(expectedAnimals, testedAnimals);
    }

    @Test
    void mergeSort() {
        Arrays.sort(expectedAnimals, dogComparator);
        ArraySorter.mergeSort(testedAnimals, dogComparator);

        // System.out.println(Arrays.toString(expectedAnimals));
        // System.out.println(Arrays.toString(testedAnimals));
        assertArrayEquals(expectedAnimals, testedAnimals);
    }

    @Test
    void mergeSort2() {
        Arrays.sort(expectedAnimals, dogComparator);
        ArraySorter.mergeSort2(testedAnimals, dogComparator);

        //System.out.println(Arrays.toString(expectedAnimals));
        //System.out.println(Arrays.toString(testedAnimals));
        assertArrayEquals(expectedAnimals, testedAnimals);
    }

    @Test
    void mergeSort3() {
        Arrays.sort(expectedAnimals, dogComparator);
        ArraySorter.mergeSort3(testedAnimals, dogComparator);

        //System.out.println(Arrays.toString(expectedAnimals));
        //System.out.println(Arrays.toString(testedAnimals));
        assertArrayEquals(expectedAnimals, testedAnimals);
    }

    // == HELPER METHODS ==

    static Animal[] createRandomAnimalArray(int size) {
        Random random = new Random();
        Animal[] animals = new Animal[size];
        for (int i = 0; i < size; i++) {
            animals[i] = new Animal(random.nextInt());
        }
        return animals;
    }
}

// == HELPER CLASSES ==

class Animal implements Comparable<Animal> {

    int age;

    public Animal(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(@NotNull Animal o) {
        return Integer.compare(age, o.age);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "age=" + age +
                '}';
    }
}

class Dog extends Animal  {

    public Dog(int age) {
        super(age);
    }


}