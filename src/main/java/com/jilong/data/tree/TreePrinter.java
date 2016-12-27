package com.jilong.data.tree;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author jilong.qiu
 * @date 2016/12/26.
 */
public class TreePrinter {

    /**
     * 遍历二叉树 中序
     */
    public static <T> void one(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> index = root;
        boolean leftInserting = true;
        List<Node<T>> list = Lists.newArrayList();
        while (true) {
            if (leftInserting && index.left != null) {
                stack.push(index);
                index = index.left;
                continue;
            }
            list.add(index);
            if (index.right == null) {
                if (stack.isEmpty()) {
                    break;
                }
                index = stack.pop();
                leftInserting = false;
            } else {
                index = index.right;
                leftInserting = true;
            }
        }
        print(list);
    }



    /**
     * 遍历二叉树 先序
     */
    public static <T> void two(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> index = root;
        List<Node<T>> list = Lists.newArrayList();
        while (true) {
            list.add(index);
            if (index.right != null) {
                stack.push(index.right);
            }
            if (index.left != null) {
                index = index.left;
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                index = stack.pop();
            }
        }
        print(list);
    }

    /**
     * 遍历二叉树 后序
     */
    public static <T> void three(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> index = root;
        List<Node<T>> list = Lists.newArrayList();
        boolean inserting = true;
        while (true) {
            stack.push(index);
            if (inserting) {
                if (index.right != null) {
                    stack.push(index.right);
                }
                if (index.left == null) {
                    if (index.right == null) {
                        list.add(index);
                        stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                        index = stack.pop();
                    } else {
                        index = index.right;
                        stack.pop();
                    }
                } else {
                    index = index.left;
                    inserting = true;
                }
            } else {

            }
        }
        print(list);
    }


    private static <T> void print(List<Node<T>> list) {
        System.out.println(Joiner.on(' ').join(list.stream().map(node -> node.data).collect(Collectors.toList())));
    }

}
