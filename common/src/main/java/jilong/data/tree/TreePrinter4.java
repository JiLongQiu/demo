package jilong.data.tree;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * @author jilong.qiu
 * @date 2017/5/19.
 */
public class TreePrinter4 {

    /**
     * 中序
     */
    public static <T> void one(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        List<T> printList = Lists.newArrayList();
        boolean revert = false;
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (node.left != null && !revert) {
                stack.push(node);
                stack.push(node.left);
            } else {
                printList.add(node.data);
                if (node.right != null) {
                    stack.push(node.right);
                    revert = false;
                } else {
                    revert = true;
                }
            }
        }
        System.out.println(printList);
    }
    /**
     * 先序
     */

    public static <T> void two(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        List<T> printList = Lists.newArrayList();
        while (!stack.empty()) {
            Node<T> node = stack.pop();
            printList.add(node.data);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null)  {
                stack.push(node.left);
            }
        }
        System.out.println(printList);
    }

    public static <T> void three(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        List<T> printList = Lists.newArrayList();
        Node<T> lastNode = null;
        while (!stack.empty()) {
            Node<T> node = stack.pop();
            if (lastNode != null && (lastNode == node.left || lastNode == node.right)) {
                printList.add(node.data);
            } else {
                if (node.right != null) {
                    stack.push(node);
                    stack.push(node.right);
                    if (node.left != null) {
                        stack.push(node.left);
                    }
                } else {
                    if (node.left == null) {
                        printList.add(node.data);
                    }
                }
            }
            lastNode = node;
        }
        System.out.println(printList);
    }




}
