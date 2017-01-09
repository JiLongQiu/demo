package com.jilong.data;

import com.google.common.collect.Lists;
import com.jilong.data.tree.Node;

import java.util.List;

/**
 * @author jilong.qiu
 * @date 2016/12/20.
 */
public class BalanceTree {

    public static <T> Node<T> balance(T[] values) {
        return geneRoot(values, 0, values.length - 1);
    }

    private static <T> Node<T> geneRoot(T[] values, int start, int end) {
        int mid = (start + end) / 2;
        Node<T> root = new Node<>(values[mid]);
        if (mid > start) {
            root.left = geneRoot(values, start, mid - 1);
        }
        if (mid < end) {
            root.right = geneRoot(values, mid + 1, end);
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

    public static <T> Node<T> balanceTwo(T[] values) {
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

    public static <T> Node<T> gene(T[] firstVal, T[] midVal) {
        return gene(firstVal, 0, firstVal.length - 1, midVal, 0, midVal.length - 1);
    }

    private static <T> Node<T> gene(T[] firstVal, int firstL, int firstR, T[] midVal, int midL, int midR) {
        T rootVal = firstVal[firstL];
        Node<T> root = new Node<>(rootVal);
        int midValRootIndex = -1;
        for (int i = 0; i < midVal.length; i++) {
            if (midVal[i].equals(rootVal)) {
                midValRootIndex = i;
                break;
            }
        }
        if (midValRootIndex == -1) {
            throw new RuntimeException("");
        }
        int leftTreeLen = midValRootIndex - midL;
        if (leftTreeLen > 0) {
            root.left = gene(firstVal, firstL + 1, firstL + leftTreeLen, midVal, midL, midL + leftTreeLen - 1);
        } else {
            root.left = null;
        }
        int rightTreeLen = midR - midValRootIndex;
        if (rightTreeLen > 0) {
            root.right = gene(firstVal, firstR - rightTreeLen + 1, firstR, midVal, midValRootIndex + 1, midR);
        } else {
            root.right = null;
        }
        return root;
    }

    private static class IndexPair<T> {
        public int firstL;
        public int firstR;
        public int midL;
        public int midR;
        public boolean isLeftTree;
        public Node<T> parentN;

        public IndexPair(int firstL, int firstR, int midL, int midR, boolean isLeftTree, Node<T> parentN) {
            this.firstL = firstL;
            this.firstR = firstR;
            this.midL = midL;
            this.midR = midR;
            this.isLeftTree = isLeftTree;
            this.parentN = parentN;
        }
    }

    public static <T> Node<T> gene2(T[] firstVal, T[] midVal) {
        List<IndexPair> nowLayer = Lists.newArrayList();
        nowLayer.add(new IndexPair<>(0, firstVal.length - 1, 0, midVal.length - 1, false, null));
        List<IndexPair> nextLayer;
        Node<T> root = null;
        do {
            nextLayer = Lists.newArrayList();
            for (IndexPair pair : nowLayer) {
                T treeVal = firstVal[pair.firstL];
                Node<T> treeRoot = new Node<>(treeVal);
                int midValRootIndex = -1;
                for (int i = 0; i < midVal.length; i++) {
                    if (midVal[i].equals(treeVal)) {
                        midValRootIndex = i;
                        break;
                    }
                }
                if (midValRootIndex == -1) {
                    throw new RuntimeException("");
                }
                if (root == null) {
                    root = treeRoot;
                } else {
                    if (pair.isLeftTree) {
                        pair.parentN.left = treeRoot;
                    } else {
                        pair.parentN.right = treeRoot;
                    }
                }
                int leftTreeLen = midValRootIndex - pair.midL;
                if (leftTreeLen > 0) {
                    nextLayer.add(new IndexPair<>(pair.firstL + 1, pair.firstL + leftTreeLen, pair.midL, pair.midL + leftTreeLen - 1, true, treeRoot));
                }
                int rightTreeLen = pair.midR - midValRootIndex;
                if (rightTreeLen > 0) {
                    nextLayer.add(new IndexPair<>(pair.firstR - rightTreeLen + 1, pair.firstR, midValRootIndex + 1, pair.midR, false, treeRoot));
                }
            }
            nowLayer = nextLayer;
        } while (!nextLayer.isEmpty());
        return root;
    }

}
