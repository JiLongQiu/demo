package com.jilong.dp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jilong.RecordTime;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author jilong.qiu
 * @date 2017/4/7.
 */
public class Coin {

    private static Map<Integer, Integer> cache = Maps.newHashMap();

    private static Integer coin(List<Integer> coins, Integer price) {
        if (price == 0) {
            return 0;
        } else if (coins.contains(price)) {
            return 1;
        } else {
            Integer min = Collections.min(coins);
            if (price < min) {
                cache.put(price, null);
                throw new IllegalArgumentException("Error");
            }
        }
        Integer min = Integer.MAX_VALUE;
        int errorCount = 0;
        for (int coin : coins) {
            Integer nextPrice = price - coin;
            if (cache.containsKey(nextPrice)) {
                Integer cacheVal = cache.get(nextPrice);
                if (cacheVal == null) {
                    errorCount++;
                } else {
                    cacheVal += 1;
                    min = cacheVal < min ? cacheVal : min;
                }
            } else {
                try {
                    Integer rs = coin(coins, nextPrice);
                    rs += 1;
                    min = rs < min ? rs : min;
                } catch (IllegalArgumentException e) {
                    errorCount++;
                }
            }
        }
        if (errorCount == coins.size()) {
            cache.put(price, null);
            throw new IllegalArgumentException();
        }
        cache.put(price, min);
        return min;
    }

    private static int coinV2(List<Integer> coins, Integer price) {
        Stack<Integer> stack = new Stack<>();
        Integer minCoin = Collections.min(coins);
        stack.push(price);
        Integer calPrice;
        while (!stack.isEmpty()) {
            calPrice = stack.pop();
            if (calPrice == 0) {
                cache.put(calPrice, 0);
                continue;
            } else if (coins.contains(calPrice)) {
                cache.put(calPrice, 1);
                continue;
            } else if (price < minCoin) {
                cache.put(calPrice, null);
                continue;
            }
            List<Integer> hasCalNextPrice = Lists.newArrayList();
            List<Integer> hasNotCalNextPrice = Lists.newArrayList();
            for (Integer coin : coins) {
                Integer nextPrice = calPrice - coin;
                if (cache.containsKey(nextPrice)) {
                    hasCalNextPrice.add(nextPrice);
                } else {
                    hasNotCalNextPrice.add(nextPrice);
                }
            }
            if (hasCalNextPrice.size() == coins.size()) {
                Integer min = Integer.MAX_VALUE;
                Integer errorCount = 0;
                for (Integer nextPrice : hasCalNextPrice) {
                    Integer cacheVal = cache.get(nextPrice);
                    if (cacheVal == null) {
                        errorCount++;
                    } else {
                        cacheVal++;
                        min = cacheVal < min ? cacheVal : min;
                    }
                }
                if (errorCount == hasCalNextPrice.size()) {
                    cache.put(calPrice, null);
                } else {
                    cache.put(calPrice, min);
                }
            } else {
                stack.push(calPrice);
                hasNotCalNextPrice.forEach(stack::push);
            }
        }
        return cache.get(price);
    }

    public static void main(String[] args) {
        List<Integer> coins = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        Integer price = 500000;
        RecordTime.execute(() -> {
            coin(coins, price);
            return null;
        });
        cache.clear();
        RecordTime.execute(() -> {
            coinV2(coins, price);
            return null;
        });
    }

}
