package com.jilong.split;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author jilong.qiu
 * @date 2016/12/2.
 */
public class Splitter {

    private Dictionary dic;

    public Splitter(Dictionary dictionary) {
        this.dic = dictionary;
    }

    public String[] split(String str) {
        char[] chars = str.toCharArray();
        List<String> words = Lists.newArrayList();
        int len = str.length();
        int lastSuccIndex = -1;
        for (int i = 0; i < len; i++) {
            int maxLenSuffix = dic.indexMaxLenSuffix(chars, i, len - 1);
            if (maxLenSuffix >= 0) {
                if (dic.contains(chars, i, maxLenSuffix)) {
                    if (i - lastSuccIndex > 1) {
                        words.add(new String(chars, lastSuccIndex + 1, i - lastSuccIndex - 1));
                    }
                    words.add(new String(chars, i, maxLenSuffix - i + 1));
                    i = maxLenSuffix;
                    lastSuccIndex = i;
                }
            }
        }
        if (len - 1 - lastSuccIndex > 1) {
            words.add(new String(chars, lastSuccIndex + 1, len - lastSuccIndex - 1));
        }
        return words.toArray(new String[words.size()]);
    }

    public static void main(String[] args) {
        Dictionary dic = new SimpleDictionary(new String[] {"我爱中华", "中华", "共和国", "简单", "次奥"});
        System.out.println(Arrays.toString(new Splitter(dic).split("我放松放松我爱中华发顺丰顺丰共和国简单我次奥我放松放松我爱中华发顺丰顺丰共和国简单我次奥我放松放松我爱中华发顺丰顺丰共和国简单我次奥")));
    }

}
