package jilong.data.tree;

/**
 * @author jilong.qiu
 * @date 2016/12/26.
 */
public class Node<T> {

    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public T data;
    public int balance;
    public int depth = 1;

    public Node(T data) {
        this.data = data;
    }

    public int computeBalance() {
        int leftDepth = left == null ? 0 : left.depth;
        int rightDepth = right == null ? 0 : right.depth;
        balance = leftDepth - rightDepth;
        return balance;
    }

    public int computeDepth() {
        int leftDepth = left == null ? 0 : left.depth;
        int rightDepth = right == null ? 0 : right.depth;
        depth = Math.max(leftDepth, rightDepth) + 1;
        return depth;
    }

    public void computeAll() {
        computeBalance();
        computeDepth();
    }

}
