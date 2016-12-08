package com.jilong.split;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jilong.qiu
 * @date 2016/12/8.
 */
public class SimpleDictionary implements Dictionary {

    private List<String> keyWords;

    public SimpleDictionary(String[] keyWords) {
        this.keyWords = Lists.newArrayList(keyWords);
        this.keyWords.sort((o1, o2) -> Integer.compare(o1.length(), o2.length()));
    }

    public int indexMaxLenSuffix(char[] chars, int start, int end) {
        List<String> searchList = keyWords;
        int i = start;
        for (; i <= end; i++) {
            String str = new String(chars, start, i - start + 1);
            List<String> list = Lists.newArrayList();
            list.addAll(searchList.stream().filter(item -> item.startsWith(str)).collect(Collectors.toList()));
            if (list.isEmpty()) {
                break;
            }
            searchList = list;
        }
        if (i > start) {
            return i - 1;
        }
        return -1;
    }

    @Override
    public boolean contains(char[] chars, int start, int end) {
        String str = new String(chars, start, end - start + 1);
        for (String item : keyWords) {
            if (item.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Dictionary dic = new SimpleDictionary(new String[] {"我爱中华", "中华", "共和国", "简单"});
        char[] chars = "我爱共和国么".toCharArray();
        System.out.println(dic.indexMaxLenSuffix(chars, 2, chars.length - 1));
    }

}
