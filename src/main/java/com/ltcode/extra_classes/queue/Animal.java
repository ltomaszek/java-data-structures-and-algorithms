package com.ltcode.extra_classes.queue;

import lombok.Getter;

@Getter
public abstract class Animal {

    private String name;

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }
}
