package com.jilong.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author jilong.qiu
 * @date 2016/11/10.
 */
public class TrieTree {

    private Node<Character> root;

    public TrieTree() {
        this.root = new Node<Character>(null);
    }

    public void insert(String string) {
        int len = string.length();
        Node<Character> insertPN = root;
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
            Node<Character> existN = insertPN.findChildren(c);
            if (existN == null) {
                Node<Character> newN = new Node<Character>(c);
                insertPN.appendChildren(newN);
                insertPN = newN;
            } else {
                existN.addAndGetCount(1);
                insertPN = existN;
            }
        }
    }

    public boolean remove(String string) {
        int len = string.length();
        Node<Character> searchPN = root;
        List<Node<Character>> removeChain = new ArrayList<Node<Character>>();
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
            Node<Character> existN = searchPN.findChildren(c);
            if (existN == null) {
                return false;
            }
            removeChain.add(existN);
            searchPN = existN;
        }
        if (removeChain.size() > 0) {
            Node<Character> lastN = removeChain.get(removeChain.size() - 1);
            if (lastN.isEnd()) {
                Node<Character> nowPN = root;
                for (Node<Character> nowN : removeChain) {
                    int nowCount = nowN.addAndGetCount(-1);
                    if (nowCount <= 0) {
                        nowPN.removeChildren(nowN);
                        break;
                    } else {
                        nowPN = nowN;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void print(Node<Character> parentN, StringBuilder sb) {
        Character c = parentN.getData();
        sb.append(c == null ? "" : c);
        int maxDiff = parentN.maxDiffWithChildren();
        for (int i = 0; i < maxDiff; i++) {
            System.out.println(sb.toString());
        }
        for (Node<Character> node : parentN.getChildren()) {
            print(node, sb);
            int len = sb.length();
            sb.deleteCharAt(len - 1);
        }
    }

    public void print() {
        print(root, new StringBuilder());
    }

    public List<String> search(String search) {
        int len = search.length();
        Node<Character> searchPN = root;
        for (int i = 0; i < len; i++) {
            char c = search.charAt(i);
            List<Node<Character>> matchNs = searchPN.matchChildren(matcher, c);

        }
    }

    private final BiFunction<Node<Character>, Character, Boolean> matcher = new BiFunction<Node<Character>, Character, Boolean>() {
        @Override
        public Boolean apply(Node<Character> node, Character c) {
            return true;
        }
    };


}
