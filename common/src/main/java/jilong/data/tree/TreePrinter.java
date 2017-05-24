package jilong.data.tree;

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
        boolean revert = false;
        while (true) {
            boolean isEmpty = false;
            while (revert) {
                list.add(index);
                if (stack.isEmpty()) {
                    isEmpty = true;
                    break;
                }
                Node<T> old = index;
                index = stack.pop();
                revert = old == index.left || old == index.right;
            }
            if (isEmpty) {
                break;
            }
            if (index.left == null) {
                if (index.right == null) {
                    list.add(index);
                    if (stack.isEmpty()) {
                        break;
                    }
                    Node<T> old = index;
                    index = stack.pop();
                    if (old == index.left || old == index.right) {
                        revert = true;
                    }
                } else {
                    stack.push(index);
                    index = index.right;
                }
            } else {
                stack.push(index);
                if (index.right != null) {
                    stack.push(index.right);
                }
                index = index.left;
            }
        }
        print(list);
    }

    public static <T> void threeV2(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> index = root;
        List<Node<T>> list = Lists.newArrayList();
        while (true) {
            if (index.left == null) {
                if (index.right == null) {
                    list.add(index);
                    if (stack.empty()) {
                        break;
                    }
                    Node<T> lastN = index;
                    index = stack.pop();
                    boolean isEmpty = false;
                    while (lastN == index.left || lastN == index.right) {
                        list.add(index);
                        if (stack.empty()) {
                            isEmpty = true;
                            break;
                        }
                        lastN = index;
                        index = stack.pop();
                    }
                    if (isEmpty) {
                        break;
                    }
                } else {
                    stack.push(index);
                    index = index.right;
                }
            } else {
                stack.push(index);
                if (index.right != null) {
                    stack.push(index.right);
                }
                index = index.left;
            }
        }
        print(list);
    }

    public static <T> void threeV3(Node<T> root) {

    }

    private static <T> void print(List<Node<T>> list) {
        System.out.println(Joiner.on(' ').join(list.stream().map(node -> node.data).collect(Collectors.toList())));
    }

    public static <T> void draw(Node<T> root) {
        List<List<Node<T>>> layerNs = Lists.newArrayList();
        List<Node<T>> nowLayerNs = Lists.newArrayList();
        nowLayerNs.add(root);
        List<Node<T>> nextLayerNs;
        do {
            layerNs.add(nowLayerNs);
            nextLayerNs = Lists.newArrayList();
            for (Node<T> node : nowLayerNs) {
                if (node == null) {
                    nextLayerNs.add(null);
                    nextLayerNs.add(null);
                } else {
                    nextLayerNs.add(node.left);
                    nextLayerNs.add(node.right);
                }
            }
            nowLayerNs = nextLayerNs;
        } while (nowLayerNs.stream().filter(item -> item != null).count() > 0);
        printLayerNs(layerNs);
    }

    private static class LayerIndex {
        public int left;
        public int right;
        public int index;

        public LayerIndex(int index, int left, int right) {
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }

    private static <T> void printLayerNs(List<List<Node<T>>> layerNs) {
        int layerCount = layerNs.size();
        int maxCount = 1;
        for (int i = 1; i < layerCount; i++) {
            maxCount = 1 + 2 * maxCount;
        }
        List<List<LayerIndex>> layerIndexes = Lists.newArrayList();
        List<LayerIndex> nowLayIndex = Lists.newArrayList();
        nowLayIndex.add(new LayerIndex((maxCount + 1) / 2, 1, maxCount));
        layerIndexes.add(nowLayIndex);
        for (int i = 1; i < layerCount; i++) {
            List<LayerIndex> nextLayerIndex = Lists.newArrayList();
            for (LayerIndex nowItem : nowLayIndex) {
                nextLayerIndex.add(new LayerIndex((nowItem.left + nowItem.index - 1) / 2, nowItem.left, nowItem.index - 1));
                nextLayerIndex.add(new LayerIndex((nowItem.index + nowItem.right + 1) / 2, nowItem.index + 1, nowItem.right));
            }
            layerIndexes.add(nextLayerIndex);
            nowLayIndex = nextLayerIndex;
        }
        char empty = ' ';
        for (int i = 0; i < layerCount; i++) {
            List<Integer> li = layerIndexes.get(i).stream().map(item -> item.index).collect(Collectors.toList());
            List<Node<T>> ns = layerNs.get(i);
            for (int j = 1; j <= maxCount; j++) {
                int indexOf;
                if ((indexOf = li.indexOf(j)) >= 0) {
                    Node<T> n = ns.get(indexOf);
                    System.out.print(n == null ? '*' : n.data);
                } else {
                    System.out.print(empty);
                }
            }
            System.out.println();
        }
    }

}
