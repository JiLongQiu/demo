package com.jilong.tree;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jilong.qiu
 * @date 2016/11/10.
 */
public class CharInfo {

    private static final CharPinyinCache CHAR_PINYIN_CACHE = new CharPinyinCache();

    private static class CharPinyinCache {

        private final Map<Character, String> charPinyinMap = new ConcurrentHashMap<Character, String>();

        public String getPinyin(Character c) {
            String result = charPinyinMap.get(c);
            if (result == null) {
                String[] pinyin = PinyinHelper.convertToPinyinArray(c, PinyinFormat.WITHOUT_TONE);
                if (pinyin != null && pinyin.length > 0) {
                    result = pinyin[0];
                }
                if (result != null) {
                    charPinyinMap.put(c, result);
                }
            }
            return result;
        }

    }

    private Character c;
    private String pinyin;

    public CharInfo(Character c) {
        this.c = c;
        this.pinyin = CHAR_PINYIN_CACHE.getPinyin(c);
    }

    public boolean match(Character character, CharMatchType matchType) {
        switch (matchType) {
            case SHORT_PINYIN:

                break;
            case SUFFIX_PINYIN:

                break;
        }
        return true;
    }

    public CharMatchType match(Character character) {
        return null;
    }

}
