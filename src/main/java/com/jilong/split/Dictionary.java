package com.jilong.split;

/**
 * @author jilong.qiu
 * @date 2016/12/2.
 */
public interface Dictionary {

    int indexMaxLenSuffix(char[] str, int start, int end);

    boolean contains(char[] str, int start, int end);

}
