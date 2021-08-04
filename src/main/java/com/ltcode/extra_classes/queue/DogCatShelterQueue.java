package com.ltcode.extra_classes.queue;


import com.ltcode.data_structure.queue.LinkedQueue;
import com.ltcode.data_structure.queue.Queue;

public class DogCatShelterQueue implements Queue<Animal> {

    private enum AnimalType {
        DOG, CAT
    }

    /**
     * All enqueued Cats and Dogs in first in order
     */
    private Queue<Animal> mainQueue;
    /**
     * All dogs or cats that were moved, because a different type of animal was dequeued than the first in mainQueue
     */
    private Queue<Animal> secondQueue;

    private AnimalType secondQueueType;

    public DogCatShelterQueue() {
        this.mainQueue = new LinkedQueue<>();
        this.secondQueue = new LinkedQueue<>();
    }

    @Override
    public void clear() {
        mainQueue.clear();
        secondQueue.clear();
    }

    @Override
    public Animal dequeue() {
        return secondQueue.isEmpty()
                ? mainQueue.dequeue()
                : secondQueue.dequeue();
    }

    @Override
    public void enqueue(Animal newAnimal) {
        mainQueue.enqueue(newAnimal);
    }

    @Override
    public boolean isEmpty() {
        return mainQueue.isEmpty() && secondQueue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Animal peek() {
        return secondQueue.isEmpty()
                ? mainQueue.peek()
                : secondQueue.peek();
    }

    public Animal dequeueAny() {
        return dequeue();
    }

    public Dog dequeueDog() {
        if (secondQueue.peek() instanceof Dog) {
            return (Dog) secondQueue.dequeue();
        }
        moveCatsToSecondQueue();
        return (Dog) mainQueue.dequeue();
    }

    public Cat dequeueCat() {
        if (secondQueue.peek() instanceof Cat) {
            return (Cat) secondQueue.dequeue();
        }
        moveDogsToSecondQueue();
        return (Cat) mainQueue.dequeue();
    }

    // == PRIVATE METHODS ==

    private void moveCatsToSecondQueue() {
        while (mainQueue.peek() instanceof Cat) {
            secondQueue.enqueue(mainQueue.dequeue());
        }
    }

    private void moveDogsToSecondQueue() {
        while (mainQueue.peek() instanceof Dog) {
            secondQueue.enqueue(mainQueue.dequeue());
        }
    }
}



