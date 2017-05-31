package jilong.data;

import jilong.data.tree.Node;

/**
 * @author Administrator
 * @date 2017/4/25.
 */
public class AVL {

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
            index.computeAll();
            if (disBalanceN == null && Math.abs(index.balance) > 1) {
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
                System.out.println("BUG");
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
                System.out.println("BUG");
            }
        } else {
            System.out.println("ROOT BUG");
        }
    }

    private void leftRotate(Node<Integer> index) {
        if (index == root) {
            root = index.right;
            root.parent = null;
        } else {
            if (index.parent.left == index) {
                index.parent.left = index.right;
            } else if (index.parent.right == index) {
                index.parent.right = index.right;
            }
            index.right.parent = index.parent;
        }
        Node<Integer> I = index;
        index = index.right;
        Node<Integer> RL = index.left;
        I.right = RL;   if (RL != null) RL.parent = I;
        index.left = I;     I.parent = index;
        I.computeAll();
        computeUpUntilRoot(index);
    }

    private void rightRotate(Node<Integer> index) {
        if (index == root) {
            root = index.left;
            root.parent = null;
        } else {
            if (index.parent.left == index) {
                index.parent.left = index.left;
            } else if (index.parent.right == index) {
                index.parent.right = index.left;
            }
            index.left.parent = index.parent;
        }
        Node<Integer> I = index;
        index = index.left;
        Node<Integer> LR = index.right;
        I.left = LR;   if (LR != null) LR.parent = I;
        index.right = I;     I.parent = index;
        I.computeAll();
        computeUpUntilRoot(index);
    }

    private void computeUpUntilRoot(Node<Integer> index) {
        while (index != null) {
            index.computeAll();
            index = index.parent;
        }
    }

    public static void main(String[] args) {
        AVL avl = new AVL();
//        List<Integer> ints = Lists.newArrayList(4, 5, 7, 2, 1, 3, 6);
//        ints.forEach(i -> {
//            avl.insert(i);
//            TreePrinter.draw(avl.root);
//        });
        System.out.println(avl.voice(Long.MAX_VALUE));
    }

    public String voice(long number) {
        String rs = "";
        int length = 0;
        String point = null;
        while (number > 0) {
            length++;
            long less = number % 10;
            if ((length - 1) % 4 == 0) {
                point = null;
                int l = (length - 1) / 4;
                int r = l / 2;
                for (int i = 0; i < r; i++) {
                    point = "亿" + (point == null ? "" : point);
                }
                if (l % 2 == 1) {
                    point = "万" + (point == null ? "" : point);
                }
            }
            if (less != 0) {
                if (point != null) {
                    rs = point + rs;
                    point = null;
                }
                rs = map(less) + point(length % 4) + rs;
            }
            number = number / 10;
        }
        return rs;
    }

    private String map(long less) {
        return Integer.toString(((int) less));
    }

    private String point(int length) {
        switch (length) {
            case 1:
                return "";
            case 2:
                return "十";
            case 3:
                return "百";
            case 0:
                return "千";
        }
        return "";
    }

}