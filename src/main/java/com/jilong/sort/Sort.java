package com.jilong.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Administrator
 * @date 2017/1/8.
 */
public class Sort {

    private class Pair<T> {
        public T left;
        public T right;

        public Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }
    }

    private void swap(Integer[] ints, int l, int r) {
        Integer mid = ints[l];
        ints[l] = ints[r];
        ints[r] = mid;
    }

    private void swap(int[] ints, int l, int r) {
        int mid = ints[l];
        ints[l] = ints[r];
        ints[r] = mid;
    }

    public void quickSort(Integer[] ints) {
        Stack<Pair<Integer>> stack = new Stack<>();
        stack.push(new Pair<>(0, ints.length - 1));
        while (!stack.empty()) {
            Pair<Integer> pair = stack.pop();
            Integer start = pair.left, end = pair.right, left = start, right = end;
            while (left < right) {
                while (ints[right] >= ints[left] && left < right) right--;
                if (left.equals(right)) break;
                swap(ints, left, right);
                while (ints[left] <= ints[right] && left < right) left++;
                if (left.equals(right)) break;
            }
            Integer mid = left;
            if (mid - 1 > start) {
                stack.push(new Pair<>(start, mid - 1));
            }
            if (mid + 1 < end) {
                stack.push(new Pair<>(mid + 1, end));
            }
        }
    }

    public void bound(Integer[] ints) {
        int end = ints.length - 1;
        while (true) {
            int last = 0;
            for (int i = 0; i < end; i++) {
                if (ints[i] > ints[i + 1]) {
                    swap(ints, i, i + 1);
                    last = i;
                }
            }
            if (last == 0) break;
            else end = last;
        }
    }

    private void heapSort0(Integer[] ints, int start, int end) {
        int index = start;
        while (true) {
            int lc = index * 2 + 1, rc = index * 2 + 2;
            if (lc > end) break;
            if (rc > end && ints[lc] > ints[index]) {
                swap(ints, lc, index);
                break;
            }
            if (rc <= end && (ints[lc] > ints[index] || ints[rc] > ints[index])) {
                int maxIndex = ints[lc] > ints[rc] ? lc : rc;
                swap(ints, maxIndex, index);
                index = maxIndex;
                continue;
            }
            break;
        }
    }

    public void heapSort(Integer[] ints) {
        for (int i = ints.length / 2 - 1; i >= 0; i--) {
            heapSort0(ints, i, ints.length - 1);
        }
        for (int i = ints.length - 1; i >= 1; i--) {
            swap(ints, 0, i);
            heapSort0(ints, 0, i - 1);
        }
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] ints = gene();
        sort.heap(ints);
        System.out.println(Arrays.toString(ints));
    }

    private static int[] gene() {
        return new int[] {24, 58, 17, 79, 27, 30, 8, 80, 12};
    }

    private void heap0(int[] ints, int swapIndex) {
        int lc = swapIndex * 2 + 1;
        int rc = swapIndex * 2 + 2;
        if (ints[swapIndex] < ints[lc]) {

        }
    }

    private void heap(int[] ints) {
        int length = ints.length;
        int l = 0, r = length - 1;

        int mid = (ints.length - 2) / 2;
        for (int i = mid; i >= 0; i--) {
            int lc = i * 2 + 1;
            int rc = i * 2 + 2;
            int swapIndex = -1;
            if (rc > ints.length - 1) {
                if (ints[lc] > ints[i]) {
                    swapIndex = lc;
                }
            } else {
                if (ints[lc] > ints[rc]) {
                    if (ints[i] < ints[lc]) {
                        swapIndex = lc;
                    }
                } else {
                    if (ints[i] < ints[rc]) {
                        swapIndex = rc;
                    }
                }
            }
            if (swapIndex > 0) {
                swap(ints, swapIndex, i);
                heap0(ints, swapIndex);
            }
        }
    }

}
