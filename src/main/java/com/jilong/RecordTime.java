package com.jilong;

import java.util.function.Supplier;

/**
 * @author jilong.qiu
 * @date 2017/4/7.
 */
public class RecordTime {

    public static <T> void execute(Supplier<T> func) {
        long start = System.currentTimeMillis();
        func.get();
        System.out.println(System.currentTimeMillis() - start);
    }

}
