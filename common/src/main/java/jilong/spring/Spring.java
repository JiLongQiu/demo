package jilong.spring;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.*;

/**
 * @author jilong.qiu
 * @date 2016/11/30.
 */
public class Spring {

    private static final LoadingCache<Integer, Integer> cache = CacheBuilder.newBuilder().build(new CacheLoader<Integer, Integer>() {
        @Override
        public Integer load(Integer key) throws Exception {
            System.out.println("loading cache");
            return null;
        }
    });

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 0);

        float a = 2.3f;
        float b = 2.299999237060546963f;
        System.out.println(a);
    }

}
