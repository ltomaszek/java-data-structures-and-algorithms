package com.ltcode.data_structure.tree;

import java.util.List;
import java.util.Optional;

public class BinarySearchTreeLinked<T extends Comparable<T>> implements Tree<T> {

    private BinaryNode<T> root;

    /**
     *
     * @param value
     * @return false if value already exists, otherwise true
     */
    @Override
    public boolean add(T value) {
        if (root == null) {
            root = new BinaryNode<>(value);
            return true;
        }
        BinaryNode<T> parent = null;
        BinaryNode<T> comparedNode = root;

        while (comparedNode != null) {
            parent = comparedNode;
            int compareValue = value.compareTo(parent.value);

            if (compareValue == 0) {
                return false;
            } else if (compareValue < 0) {
                comparedNode = parent.left;
            } else {
                comparedNode = parent.right;
            }
        }

        var newNode = new BinaryNode<>(value);

        int compareValues = value.compareTo(parent.value);
        if (compareValues < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return true;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(T value) {
        return findNode(value).isPresent();
    }

    public List<T> inOrder() {
        return root.inOrder();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean remove(T value) {
        if (!contains(value)) {
            return false;
        }

        // find node with value to remove
        var optionalParent = findParentNode(value);
        BinaryNode<T> parent;
        BinaryNode<T> node;

        int compareValueParent = 0;

        if (optionalParent.isEmpty()) { // its root
            node = root;
            // create dumb parent
            parent = new BinaryNode<>(value);
            parent.left = node;
        } else {
            compareValueParent = value.compareTo(optionalParent.get().value);
            node = compareValueParent <= 0
                    ? optionalParent.get().left
                    : optionalParent.get().right;
            parent = optionalParent.get();
        }

        var smallerBiggestNode = findBiggestSmallerNode(node);

        if (smallerBiggestNode == null) {
            if (parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else {
            // remove old connection to smallerBiggest node
            var parentOfSmaller = findParentNode(smallerBiggestNode.value).get();
            if (parentOfSmaller.left == smallerBiggestNode) {
                parentOfSmaller.left = smallerBiggestNode.left;
            } else {
                parentOfSmaller.right = smallerBiggestNode.left;
            }

            if (parent.left == node) {
                parent.left = smallerBiggestNode;
            } else {
                parent.right = smallerBiggestNode;
            }
        }

        if (smallerBiggestNode != null) {
            if (smallerBiggestNode != node.left) {
                smallerBiggestNode.left = node.left;
            }
            smallerBiggestNode.right = node.right;
        }

        // is it root
        if (root.value == value) {
            this.root = smallerBiggestNode == null
            ? root.right
            : smallerBiggestNode;
        }
        return true;
    }

    // == PRIVATE METHODS ==

    private BinaryNode<T> findBiggestSmallerNode(BinaryNode<T> thanNode) {
        if (thanNode.left == null) {
            return null;
        }
        BinaryNode<T> node = thanNode.left;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Optional<BinaryNode<T>> findNode(T value) {
        BinaryNode<T> currNode = root;
        while (currNode != null) {
            int compareValues = value.compareTo(currNode.value);
            if (compareValues < 0) {
                currNode = currNode.left;
            } else if(compareValues > 0) {
                currNode = currNode.right;
            } else {
                return Optional.of(currNode);
            }
        }

        return Optional.empty();
    }

    private Optional<BinaryNode<T>> findParentNode(T ofValue) {
        if (root.value == ofValue) {
            return Optional.empty();
        }
        BinaryNode<T> parent = root;
        while (parent != null) {
            int compareValues = ofValue.compareTo(parent.value);
            if (compareValues < 0) {
                if (parent.left != null && parent.left.value == ofValue) {
                    return Optional.of(parent);
                }
                parent = parent.left;
            } else if(compareValues > 0) {
                if (parent.right != null && parent.right.value == ofValue) {
                    return Optional.of(parent);
                }
                parent = parent.right;
            }
        }

        return Optional.empty();
    }
}
