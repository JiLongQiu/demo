package com.jilong.dp;

/**
 * @author jilong.qiu
 * @date 2017/4/7.
 */
public class LIS {

    private static int v1(int[] values) {
        int[] results = new int[values.length];
        results[0] = 1;
        for (int i = 1; i < values.length; i++) {
            int max = Integer.MIN_VALUE;
            boolean hasSmaller = false;
            for (int j = 0; j < i; j++) {
                if (values[j] < values[i]) {
                    max = results[j] > max ? results[j] : max;
                    hasSmaller = true;
                }
            }
            if (hasSmaller) {
                results[i] = max + 1;
            } else {
                results[i] = results[i - 1];
            }
        }
        return results[values.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(v1(new int[] {1, 2, 3 ,4, 5, 6, 7}));
    }

}
