package jilong.multithread;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author jilong.qiu
 * @date 2017/6/9.
 */
public class ConcurrentModify {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        Iterator<Integer> ite = list.iterator();
        if (ite.hasNext()) {
            System.out.println(ite.next());
            Thread t = new Thread(() -> {
                list.remove((Integer) 3);
            });
            t.start();
            t.join();
            if (ite.hasNext()) {
                System.out.println(ite.next());
            }
        }
    }

}
