package jilong.data.tree;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * @author jilong.qiu
 * @date 2017/4/25.
 */
public class TreePrinter2 {

    /**
     * 先序
     */
    public static <T> void one(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        List<T> printList = Lists.newArrayList();
        while (!stack.isEmpty()) {
            Node<T> index = stack.pop();
            printList.add(index.data);
            if (index.right != null) {
                stack.push(index.right);
            }
            if (index.left != null) {
                stack.push(index.left);
            }
        }
        System.out.println(printList);
    }

    /**
     * 中序
     */
    public static <T> void two(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        List<T> printList = Lists.newArrayList();
        boolean leftInsert = true;
        while (!stack.isEmpty()) {
            Node<T> index = stack.pop();
            if (index.left != null && leftInsert) {
                stack.push(index);
                stack.push(index.left);
            } else {
                printList.add(index.data);
                if (index.right != null) {
                    stack.push(index.right);
                    leftInsert = true;
                } else {
                    leftInsert = false;
                }
            }
        }
        System.out.println(printList);
    }

    public static <T> void three(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        List<T> printList = Lists.newArrayList();
        Node<T> lastIndex = null;
        while (!stack.isEmpty()) {
            Node<T> index = stack.pop();
            if ((index.left != null && index.left == lastIndex) || (index.right != null && index.right == lastIndex)) {
                printList.add(index.data);
                lastIndex = index;
                continue;
            }
            if (index.left != null) {
                stack.push(index);
                if (index.right != null) {
                    stack.push(index.right);
                }
                stack.push(index.left);
            } else {
                if (index.right != null) {
                    stack.push(index);
                    stack.push(index.right);
                } else {
                    printList.add(index.data);
                }
            }
            lastIndex = index;
        }
        System.out.println(printList);
    }

}
