package com.jilong.array;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 全排列算法 递归
 * @author Administrator
 * @date 2016/12/12.
 */
public class FullArray<T> {

    private T[] array;

    public FullArray(T[] array) {
        this.array = array;
    }

    public void print() {
        print0(array, 0).forEach(System.out::println);
    }

    private List<List<T>> print0(T[] array, int start) {
        if (start == array.length - 1) {
            List<List<T>> result = Lists.newArrayList();
            List<T> resultItem = Lists.newArrayList();
            resultItem.add(array[array.length - 1]);
            result.add(resultItem);
            return result;
        }
        List<List<T>> nextArray = print0(array, start + 1);
        T item = array[start];
        List<List<T>> result = Lists.newArrayList();
        for (List<T> resultItem : nextArray) {
            for (int i = 0; i < resultItem.size() + 1; i++) {
                List<T> copyItem = new ArrayList<>(resultItem.size());
                copyItem.addAll(resultItem);
                copyItem.add(i, item);
                result.add(copyItem);
            }
        }
        return result;
    }

    private int size() {
        return print0(array, 0).size();
    }

    public static void main(String[] args) {
        new FullArray<Integer>(new Integer[]{0, 1, 2}).print();
    }

}
