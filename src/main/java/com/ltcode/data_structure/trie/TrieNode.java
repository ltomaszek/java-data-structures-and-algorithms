package com.ltcode.data_structure.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> {

    private boolean exist;
    Map<T, TrieNode<T>> map;

    TrieNode() {
        this(false);
    }

    TrieNode(boolean exist) {
        this.exist = exist;
        map = new HashMap<>();
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    TrieNode<T> addElement(T newLink) {
        return addElement(newLink, false);
    }

    TrieNode<T> addElement(T value, boolean exist) {
        if (!map.containsKey(value)) {
            TrieNode<T> newNode = new TrieNode<>(exist);
            map.put(value, newNode);
            return newNode;
        }
        return getNode(value);
    }

    TrieNode<T> getNode(T value) {
        return map.get(value);
    }

    boolean getExist() {
        return exist;
    }
}
