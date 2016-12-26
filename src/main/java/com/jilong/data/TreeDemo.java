package com.jilong.data;

import com.google.common.collect.Lists;
import com.jilong.data.tree.Node;

import java.util.List;

/**
 * @author jilong.qiu
 * @date 2016/12/20.
 */
public class TreeDemo {

    public static Node<Integer> balance(Integer[] ints) {
        return geneRoot(ints);
    }

    private static Node<Integer> geneRoot(Integer[] ints) {
        return geneRoot(ints, 0, ints.length - 1);
    }

    private static Node<Integer> geneRoot(Integer[] ints, int start, int end) {
        int mid = (start + end) / 2;
        Node<Integer> root = new Node<>(ints[mid]);
        if (mid > start) {
            root.left = geneRoot(ints, start, mid - 1);
        }
        if (mid < end) {
            root.right = geneRoot(ints, mid + 1, end);
        }
        return root;
    }

    private static class Item<T> {
        public int left;
        public int right;
        public Node<T> node;
        public boolean isLeft;

        public Item(Node<T> node, int left, int right, boolean isLeft) {
            this.isLeft = isLeft;
            this.left = left;
            this.node = node;
            this.right = right;
        }
    }

    public static <T> Node<T> balance(T[] values) {
        List<Item<T>> list = Lists.newArrayList();
        int mid = (values.length - 1)  / 2;
        Node<T> root = new Node<>(values[mid]);
        if (mid > 0) {
            list.add(new Item<>(root, 0, mid - 1, true));
        }
        if (mid < values.length - 1) {
            list.add(new Item<>(root, mid + 1, values.length - 1, false));
        }
        while (true) {
            List<Item<T>> nextLayer = Lists.newArrayList();
            for (Item<T> item : list) {
                mid = (item.right + item.left) / 2;
                Node<T> index = new Node<>(values[mid]);
                if (item.isLeft) {
                    item.node.left = index;
                } else {
                    item.node.right = index;
                }
                if (mid > item.left) {
                    nextLayer.add(new Item<>(index, item.left, mid - 1, true));
                }
                if (mid < item.right) {
                    nextLayer.add(new Item<>(index, mid + 1, item.right, false));
                }
            }
            if (nextLayer.isEmpty()) {
                break;
            }
            list = nextLayer;
        }
        return root;
    }

}
