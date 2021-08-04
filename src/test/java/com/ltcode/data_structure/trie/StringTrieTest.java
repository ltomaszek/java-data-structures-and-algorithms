package com.ltcode.data_structure.trie;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringTrieTest {

    static String ALBERT;
    static String TEXT1;
    static String TEXT2;
    static String ALBERTO;

    StringTrie trie;

    @BeforeAll
    static void beforeAll() {
        ALBERT = "ALBERT";
        TEXT1 = "Here you go again!";
        TEXT2 = "Don't know what to do with this 5 letters!";
        ALBERTO = "ALBERTO";
    }

    @BeforeEach
    void setUp() {
        this.trie = new StringTrie();
        trie.insert(ALBERT);
        trie.insert(TEXT1);
        trie.insert(TEXT2);
        trie.insert(ALBERTO);
    }

    @Test
    void insert() {
        assertTrue(trie.insert("NEW WORD"));
        assertFalse(trie.insert(TEXT1));
    }

    @Test
    void remove_v1() {
        assertFalse(trie.remove("NOT INSERTED TEXT"));
        assertFalse(trie.remove(ALBERT.substring(0, ALBERT.length() - 1)));

        assertTrue(trie.remove(ALBERTO));
        assertTrue(trie.remove(ALBERT));

        assertFalse(trie.remove(ALBERTO));
        assertFalse(trie.remove(ALBERT));

        assertTrue(trie.remove(TEXT1));
        assertTrue(trie.remove(TEXT2));
    }

    @Test
    void remove_v2() {
        assertTrue(trie.remove(ALBERT));
        assertTrue(trie.remove(ALBERTO));

        assertFalse(trie.remove(ALBERTO));
        assertFalse(trie.remove(ALBERT));
    }

    @Test
    void search() {
        assertTrue(trie.search(ALBERT));
        assertTrue(trie.search(TEXT1));
        assertTrue(trie.search(TEXT2));

        assertFalse(trie.search("NOT INSERTED TEXT"));
    }

    @Test
    void size() {
        assertEquals(4, trie.size());

        trie.insert("WORD");
        assertEquals(5, trie.size());

        trie.insert("WORD");
        assertEquals(5, trie.size());
    }
}