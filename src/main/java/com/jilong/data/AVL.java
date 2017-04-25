package com.jilong.data;

import com.jilong.data.tree.Node;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2017/4/25.
 */
public class AVL {

    private Node<Integer> root;

    public void insert(Integer data) {
        if (root == null) {
            root = new Node<>(data);
            return;
        }
        Node<Integer> index = root, newNode;
        Stack<Node<Integer>> parentStack = new Stack<>();
        while (true) {
            parentStack.push(index);
            Node<Integer> next = data < index.data ? index.left : index.right;
            if (next == null) {
                newNode = new Node<>(data);
                if (data < index.data) {
                    index.left = newNode;
                } else {
                    index.right = newNode;
                }
                break;
            }
        }
        index = newNode;
        Node<Integer> pNode = parentStack.pop();
        if (pNode.depth > 1) {
            return;
        }
        pNode.depth += 1;
        pNode.computeBalance();
        while (!parentStack.empty()) {
            pNode = parentStack.pop();
        }
    }

}
