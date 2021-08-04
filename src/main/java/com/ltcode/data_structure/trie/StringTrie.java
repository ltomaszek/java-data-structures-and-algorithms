package com.ltcode.data_structure.trie;

public class StringTrie {

    private int size;
    private TrieNode<Character> root;

    public StringTrie() {
        this.root = new TrieNode<>();
    }

    /**
     * @param text
     * @return true - if new text is inserted; false - if text already exists
     */
    public boolean insert(String text) {
        if (search(text)) {
            return false;
        }

        var node = root;
        for (char ch : text.toCharArray()) {
            node = node.addElement(ch);
        }
        node.setExist(true);
        size++;
        return true;
    }

    public boolean remove(String text) {
        if (search(text) == false) {
            return false;
        }

        removeNode(root, text);
        size--;
        return true;
    }

    public boolean search(String text) {
        if (text == null) {
            return false;
        }
        if (text.length() == 0) {
            return root.getExist();
        }

        var node = getNode(text);

        return node == null
                ? false
                : node.getExist();
    }

    public int size() {
        return size;
    }

    // == PRIVATE METHODS ==

    private TrieNode<Character> getNode(String text) {
        var node = root;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if ((node = node.getNode(ch)) == null) {
                return null;
            }
        }
        return node;
    }

    /**
     * Use only when the text exists
     */
    private boolean removeNode(TrieNode<Character> node, String text) {
        if (text.length() == 0) {
            node.setExist(false);

            // Remove the node if there are no other elements deeper
            return node.map.size() == 0;
        }

        char firstChar = text.charAt(0);
        var nextNode = node.getNode(firstChar);

        boolean toRemove = removeNode(nextNode, text.substring(1));
        if (toRemove) {
            // should link to deeper node be removed?
            if (nextNode.getExist() == false && nextNode.map.size() <= 1) {
                node.map.remove(firstChar);
                return true;
            }
        }
        return false;
    }
}
