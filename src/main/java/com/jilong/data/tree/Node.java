package com.jilong.data.tree;

/**
 * @author jilong.qiu
 * @date 2016/12/26.
 */
public class Node<T> {

    public Node<T> left;
    public Node<T> right;
    public T data;

    public Node(T data) {
        this.data = data;
    }

}
