package jilong.data.tree;

/**
 * @author jilong.qiu
 * @date 2017/4/27.
 */
public class Test {

    private Node<Integer> root;

    public void insert(Integer data) {
        Node<Integer> newN = new Node<>(data);
        if (root == null) {
            root = newN;
            return;
        }
        Node<Integer> index = root;
        while (true) {
            boolean isLeft = data < index.data;
            Node<Integer> next = isLeft ? index.left : index.right;
            if (next == null) {
                if (isLeft) {
                    index.left = newN;
                } else {
                    index.right = newN;
                }
                newN.parent = index;
                break;
            }
            index = next;
        }
        Node<Integer> disBalanceN = null;
        index = newN;
        while (index != null) {
            int balance = index.computeBalance();
            index.computeDepth();
            if (disBalanceN == null && Math.abs(balance) > 1) {
                disBalanceN = index;
            }
            index = index.parent;
        }
        if (disBalanceN == null) {
            return;
        }
        if (disBalanceN.balance == 2) {
            if (disBalanceN.left.balance == 1) {
                System.out.println("LL");
                rightRotate(disBalanceN);
            } else if (disBalanceN.left.balance == -1) {
                System.out.println("LR");
                leftRotate(disBalanceN.left);
                rightRotate(disBalanceN);
            } else {
                System.out.println("Bug");
            }
        } else if (disBalanceN.balance == -2) {
            if (disBalanceN.right.balance == 1) {
                System.out.println("RL");
                rightRotate(disBalanceN.right);
                leftRotate(disBalanceN);
            } else if (disBalanceN.right.balance == -1) {
                System.out.println("RR");
                leftRotate(disBalanceN);
            } else {
                System.out.println("Bug");
            }
        } else {
            System.out.println("Root Bug");
        }
    }

    private void rightRotate(Node<Integer> index) {
        Node<Integer> IP = index.parent;
        if (index == root) {
            root = index.left;
            index.left.parent = null;
        } else {
            if (IP.left == index) {
                IP.left = index.left;
            } else {
                IP.right = index.left;
            }
            index.left.parent = IP;
        }
        Node<Integer> I = index;
        index = index.left;
        Node<Integer> LR = index.right;
        index.right = I;    I.parent = index;
        I.left = LR;    LR.parent = I;
        index.right.computeAll();
        computeUntilRoot(index);
    }

    private void leftRotate(Node<Integer> index) {
        Node<Integer> IP = index.parent;
        if (index == root) {
            root = index.right;
            index.right.parent = null;
        } else {
            if (IP.left == index) {
                IP.left = index.right;
            } else {
                IP.right = index.right;
            }
            index.right.parent = IP;
        }
        Node<Integer> I = index;
        index = index.right;
        Node<Integer> RL = index.left;
        index.left = I;    I.parent = index;
        I.right = RL;   RL.parent = I;
        index.left.computeAll();
        computeUntilRoot(index);
    }

    private void computeUntilRoot(Node<Integer> index) {
        while (index != null) {
            index.computeAll();
            index = index.parent;
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 0; i < 9; i++) {
            test.insert(i);
            System.out.println(test.root.balance);
            TreePrinter.draw(test.root);
        }
        test.insert(9);
        TreePrinter.draw(test.root);
        TreePrinter3.two(test.root);
    }

}
