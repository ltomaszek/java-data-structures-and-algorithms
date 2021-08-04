package com.ltcode.data_structure.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryNode<T> {

    public T value;
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    public BinaryNode(T value) {
        this.value = value;
    }

    public void setChildren(BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
        left = leftChild;
        right = rightChild;
    }

    public List<T> preOrder() {
        List<T> preOrderList = new ArrayList<>();
        preOrder(preOrderList);
        return preOrderList;
    }

    public List<T> inOrder() {
        List<T> inOrderList = new ArrayList<>();
        inOrder(inOrderList);
        return inOrderList;
    }

    public List<T> postOrder() {
        List<T> postOrderList = new ArrayList<>();
        postOrder(postOrderList);
        return  postOrderList;
    }

    // == PRIVATE METHODS ==

    private void preOrder(List<T> preOrderList) {
        preOrderList.add(value);
        if (left != null) {
            left.preOrder(preOrderList);
        }
        if (right != null) {
            right.preOrder(preOrderList);
        }
    }

    private void inOrder(List<T> inOrderList) {
        if (left != null) {
            left.inOrder(inOrderList);
        }
        inOrderList.add(value);
        if (right != null) {
            right.inOrder(inOrderList);
        }
    }

    private void postOrder(List<T> postOrderList) {
        if (left != null) {
            left.postOrder(postOrderList);
        }
        if (right != null) {
            right.postOrder(postOrderList);
        }
        postOrderList.add(value);
    }

}
