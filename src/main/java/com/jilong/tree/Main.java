package com.jilong.tree;

import com.github.stuxuhai.jpinyin.PinyinException;

/**
 * @author jilong.qiu
 * @date 2016/11/10.
 */
public class Main {

    public static void main(String[] args) throws PinyinException {
        TrieTree tree = new TrieTree();
        tree.insert("he");
        tree.insert("he 2");
        tree.insert("he 2");
        tree.print();
        System.out.println(tree.remove("he"));
        tree.print();
        System.out.println(tree.remove("he 2"));
        tree.print();
        System.out.println(tree.remove("he "));
        tree.print();

    }

}
