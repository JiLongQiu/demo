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
            MatchResult mr = dic.indexMaxLenSuffix(chars, i, len - 1);
            int maxLenSuffix = mr.maxIndex;
            if (maxLenSuffix >= 0) {
                if (mr.contains) {
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            sb.append("我放松放松我爱中华发顺丰顺丰共和国简单哈");
        }
        String str = sb.toString();
        System.out.println(str.length());
        String[] splitter = new String[] {"我爱中华", "中华", "共和国", "简单", "次奥", "放松", "丰顺"};

        long start;

        start = System.currentTimeMillis();
        Dictionary dic = new SimpleDictionary(splitter);
        new Splitter(dic).split(str);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        dic = new TrieDictionary(splitter);
        new Splitter(dic).split(str);
        System.out.println(System.currentTimeMillis() - start);
    }

}
