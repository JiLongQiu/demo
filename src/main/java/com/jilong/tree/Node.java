package com.jilong.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author jilong.qiu
 * @date 2016/11/10.
 */
public class Node<T> {

    private T data;
    private int count;
    private List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.count = 1;
    }

    public boolean isEnd() {
        return maxDiffWithChildren() > 0;
    }

    public int maxDiffWithChildren() {
        if (getChildren().isEmpty()) {
            return count;
        }
        int maxDiff = 0;
        for (Node<T> node : getChildren()) {
            int diff = count - node.getCount();
            maxDiff = maxDiff > diff ? maxDiff : diff;
        }
        return maxDiff;
    }

    public Node<T> findChildren(T data) {
        for (Node<T> node : getChildren()) {
            if (node.getData().equals(data)) {
                return node;
            }
        }
        return null;
    }

    public List<Node<T>> matchChildren(BiFunction<Node<T>, T, Boolean> matcher, T data) {
        return getChildren().stream().filter(item -> matcher.apply(item, data)).collect(Collectors.toList());
    }

    public Node<T> getChildren(int index) {
        return getChildren().get(index);
    }

    public void appendChildren(Node<T> node) {
        getChildren().add(node);
    }

    public void removeChildren(Node<T> node) {
        getChildren().remove(node);
    }

    public int addAndGetCount(int add) {
        count += add;
        return count;
    }

    public int getCount() {
        return count;
    }

    public T getData() {
        return data;
    }

    public List<Node<T>> getChildren() {
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }
        return children;
    }

}
