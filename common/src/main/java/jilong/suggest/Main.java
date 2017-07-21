package jilong.suggest;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jilong.qiu
 * @date 2017/6/28.
 */
public class Main {

    public static void main(String[] args) {
        TreeMap<String, List<String>> map = new TreeMap<>(new StringComparator());
        for (String s : gene()) {
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                String key = new String(chars, i, chars.length - i);
                map.computeIfAbsent(key, keyItem -> Lists.newArrayList());
                List<String> strList = map.get(key);
                strList.add(s);
            }
        }
        String suggest = "xihoshi";
        List<String> strList = map
                .subMap(suggest, suggest + Character.MAX_VALUE)
                .values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(strList);
    }

    private static List<String> gene() {
        List<String> list = Lists.newArrayList();
        list.add("西红柿炒鸡蛋");
        list.add("西红师长");
        list.add("西瓜方便面");
        list.add("西瓜炒鸡蛋");
        list.add("豆角焖面");
        list.add("胡萝卜汤");
        list.add("土豆炖牛腩");
        list.add("牛腩西红柿");
        return list;
    }

    private static final Map<Character, String> pinyinCache = new HashMap<>();

    private static class GetPinyinFunc implements Function<Character, String> {

        @Override
        public String apply(Character c) {
            String[] cs = PinyinHelper.convertToPinyinArray(c, PinyinFormat.WITHOUT_TONE);
            if (cs.length == 0) {
                return null;
            }
            return cs[0];
        }
    }

    private static final GetPinyinFunc PinyinFunc = new GetPinyinFunc();

    private static class StringComparator implements Comparator<String> {

        private int pinyin(char c, String str, int strIndex) {
            int strLen = str.length();
            String pinyin = Main.pinyinCache.computeIfAbsent(c, PinyinFunc);
            int index = 0;
            if (pinyin != null) {
                while (strIndex < strLen && index < pinyin.length() && pinyin.charAt(index) == str.charAt(strIndex)) {
                    index++;
                    strIndex++;
                }
            }
            return index;
        }

        @Override
        public int compare(String o1, String o2) {
            int index1 = 0, index2 = 0;
            int len1 = o1.length(), len2 = o2.length();
            while (index1 < len1 && index2 < len2) {
                char c1 = o1.charAt(index1);
                char c2 = o2.charAt(index2);
                int pinyin;
                String c1Pinyin = pinyinCache.computeIfAbsent(c1, PinyinFunc);
                String c2Pinyin = pinyinCache.computeIfAbsent(c2, PinyinFunc);
                if (c1 == c2 || (c1Pinyin != null && c2Pinyin != null && c1Pinyin.equals(c2Pinyin))) {
                    index1++;
                    index2++;
                } else if ((pinyin = pinyin(c1, o2, index2)) > 0) {
                    index1++;
                    index2 += pinyin;
                } else if ((pinyin = pinyin(c2, o1, index1)) > 0) {
                    index1 += pinyin;
                    index2++;
                } else {
                    if (c1Pinyin == null && c2Pinyin == null) {
                        return Character.compare(c1, c2);
                    } else if (c1Pinyin == null) {
                        return new String(o1.toCharArray(), index1, len1 - index1).compareTo(c2Pinyin);
                    } else if (c2Pinyin == null) {
                        return c1Pinyin.compareTo(new String(o2.toCharArray(), index2, len2 - index2));
                    } else {
                        return c1Pinyin.compareTo(c2Pinyin);
                    }
                }
            }
            if (index1 >= len1 && index2 >= len2) {
                return 0;
            } else if (index1 >= len1) {
                return -1;
            } else if (index2 >= len2) {
                return 1;
            }
            throw new RuntimeException("");
        }

    }

}
