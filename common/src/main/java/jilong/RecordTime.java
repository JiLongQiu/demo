package jilong;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    public static void main(String[] args) throws IOException {
        List<List<Integer>> arrays = Lists.newArrayList();
        arrays.add(Lists.newArrayList(1, 5, 9));
        arrays.add(Lists.newArrayList(3, 4, 6));
        arrays.add(Lists.newArrayList(2, 7, 8));
        List<Integer> indict = arrays.stream().map(item -> 0).collect(Collectors.toList());
        List<Integer> ret = Lists.newArrayList();
        Integer minArrayIndex;
        while (true) {
            Integer min = Integer.MAX_VALUE;
            minArrayIndex = -1;
            for (int i = 0; i < arrays.size(); i++) {
                Integer nowIndex = indict.get(i);
                List<Integer> nowArray = arrays.get(i);
                if (nowIndex >= nowArray.size()) {
                    continue;
                }
                Integer val = nowArray.get(nowIndex);
                if (val < min) {
                    min = val;
                    minArrayIndex = i;
                }
            }
            if (minArrayIndex >= 0) {
                ret.add(min);
                indict.set(minArrayIndex, indict.get(minArrayIndex) + 1);
            } else {
                break;
            }
        };
        System.out.println(ret);

    }

}
