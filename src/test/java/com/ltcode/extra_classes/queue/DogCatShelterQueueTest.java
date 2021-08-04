package com.ltcode.extra_classes.queue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogCatShelterQueueTest {

    DogCatShelterQueue animals;
    Cat cat1;
    Cat cat2;
    Dog dog1;
    Cat cat3;
    Cat cat4;
    Dog dog2;
    Dog dog3;
    Animal animal1;
    Animal animal2;
    Animal animal3;
    Animal animal4;
    Animal animal5;
    Animal animal6;
    Animal animal7;

    @BeforeEach
    void setUp() {
        animals = new DogCatShelterQueue();

        cat1 = new Cat();
        cat2 = new Cat();
        dog1 = new Dog();
        cat3 = new Cat();
        cat4 = new Cat();
        dog2 = new Dog();
        dog3 = new Dog();

        animal1 = cat1;
        animal2 = cat2;
        animal3 = dog1;
        animal4 = cat3;
        animal5 = cat4;
        animal6 = dog2;
        animal7 = dog3;

        animals.enqueue(animal1);
        animals.enqueue(animal2);
        animals.enqueue(animal3);
        animals.enqueue(animal4);
        animals.enqueue(animal5);
        animals.enqueue(animal6);
        animals.enqueue(animal7);
    }

    @Test
    void dequeueAny() {
        assertEquals(animal1, animals.dequeueAny());
        assertEquals(animal2, animals.dequeueAny());
        assertEquals(animal3, animals.dequeueAny());
        assertEquals(animal4, animals.dequeueAny());
        assertEquals(animal5, animals.dequeueAny());
        assertEquals(animal6, animals.dequeueAny());
        assertEquals(animal7, animals.dequeueAny());

        assertThrows(RuntimeException.class,
                () -> animals.dequeueAny());
    }

    @Test
    void dequeueDog() {
        assertEquals(animal3, animals.dequeueDog());
        assertEquals(dog2, animals.dequeueDog());

        Dog dog4 = new Dog();
        animals.enqueue(dog4);

        assertEquals(dog3, animals.dequeueDog());
        assertEquals(dog4, animals.dequeueDog());

        assertThrows(RuntimeException.class,
                () -> animals.dequeueDog());
    }

    @Test
    void dequeueCat() {
        assertEquals(animal1, animals.dequeueCat());
        assertEquals(cat2, animals.dequeueCat());

        Cat cat5 = new Cat();
        animals.enqueue(cat5);

        assertEquals(cat3, animals.dequeueCat());
        assertEquals(cat4, animals.dequeueCat());
        assertEquals(cat5, animals.dequeueCat());

        assertThrows(RuntimeException.class,
                () -> animals.dequeueCat());
    }

    @Test
    void dequeueInRotation() {
        assertEquals(cat1, animals.dequeueCat());
        assertEquals(dog1, animals.dequeueDog());

        assertEquals(cat2, animals.dequeueCat());
        assertEquals(cat3, animals.dequeueCat());

        assertEquals(dog2, animals.dequeueDog());
        assertEquals(cat4, animals.dequeueCat());

        assertEquals(dog3, animals.dequeueDog());
    }
}