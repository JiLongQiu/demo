package com.jilong.data;

import com.jilong.data.tree.Node;
import com.jilong.data.tree.TreePrinter;

/**
 * @author jilong.qiu
 * @date 2016/12/26.
 */
public class Main {

    public static void main(String[] args) {
        Node<Integer> root = BalanceTree.balance(new Integer[] {1, 2, 3, 4, 5, 6, 10, 11, 12});
        Node<Integer> root2 = BalanceTree.balanceTwo(new Integer[] {1, 2, 3, 4, 5});
//        Node<Integer> root = new Node<>(1);
//        root.left = new Node<>(2);
//        root.right = new Node<>(6);
//        root.left.left = new Node<>(3);
//        root.left.right = new Node<>(4);
//        root.left.right.left = new Node<>(5);
        TreePrinter.one(root);
        TreePrinter.one(root2);
        TreePrinter.two(root);
        TreePrinter.two(root2);
        TreePrinter.three(root);
        TreePrinter.three(root2);
        TreePrinter.threeV2(root);
        TreePrinter.threeV2(root2);
        TreePrinter.draw(root);

        Node<Character> root3 = BalanceTree.gene2(new Character[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N'}, new Character[]{'C', 'B', 'E', 'D', 'G', 'F', 'A', 'J', 'H', 'K', 'M', 'L', 'N'});
        TreePrinter.draw(root3);
        TreePrinter.three(root3);
        Node<Character> root4 = BalanceTree.gene(new Character[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N'},
            new Character[] {'C', 'B', 'E', 'D', 'G', 'F', 'A', 'J', 'H', 'K', 'M', 'L', 'N'});
        TreePrinter.draw(root4);
        TreePrinter.three(root4);
    }

}
