package com.ltcode.data_structure.table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class DirectChainingHashTableTest {

    DirectChainingHashTable<String> table;
    String[] INSERTED_VALUES = {"abc", "ddd", "Alex", "Kakarnaka", "Every guy is a guy"};

    @BeforeEach
    void setUp() {
        int CAPACITY = 10;
        Function<String, Integer> HASH_FUNCTION = s -> s.length() % 10;

        table = new DirectChainingHashTable<>(CAPACITY, HASH_FUNCTION);

        Arrays.stream(INSERTED_VALUES)
                .forEach(table::insert);
    }

    @Test
    void contains() {
        Arrays.stream(INSERTED_VALUES)
                .parallel()
                .forEach(value -> assertTrue(table.contains(value)));

        assertFalse(table.contains("not existing value"));
    }

    @Test
    void delete() {
        for (String value : INSERTED_VALUES) {
            assertTrue(table.delete(value));
        }

        for (String value : INSERTED_VALUES) {
            assertFalse(table.delete(value));
        }
    }

    @Test
    void insert() {
        assertTrue(table.insert("xxx"));
        assertFalse(table.insert("xxx"));
    }
}