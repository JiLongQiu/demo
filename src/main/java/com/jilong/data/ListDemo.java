package com.jilong.data;

/**
 * @author jilong.qiu
 * @date 2016/12/20.
 */
public class ListDemo {

    private static class Node<T> {
        public T t;
        public Node<T> next;
        public Node(T t) {
            this.t = t;
        }
    }

    public static void main(String[] args) {
        Node<Integer> next = null, root = null;
        for (int i = 0; i < 5; i++) {
            if (next == null) {
                next = new Node<>(i);
                root = next;
            } else {
                next.next = new Node<>(i);
                next = next.next;
            }
        }
        printNode(root);
        printNode(convert(root));
    }

    private static <T> Node<T> convert(Node<T> root) {
        Node<T> other = null;
        while (root != null) {
            if (other == null) {
                other = new Node<>(root.t);
            } else {
                Node<T> item = new Node<>(root.t);
                item.next = other;
                other = item;
            }
            root = root.next;
        }
        return other;
    }

    private static void printNode(Node<Integer> root) {
        while (root != null) {
            System.out.print(root.t + " ");
            root = root.next;
        }
        System.out.println();
    }

}
