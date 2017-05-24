package jilong.sort;

import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author jilong.qiu
 * @date 2017/5/8.
 */
public class Sort2 {

    private static class Task implements Runnable {

        private ExecutorService innerEs;
        private int[] ints;
        private int start, end;
        private Queue<Future<?>> runningFuture;

        public Task(ExecutorService innerEs, int[] ints, int start, int end) {
            this.innerEs = innerEs;
            this.ints = ints;
            this.start = start;
            this.end = end;
            this.runningFuture = new ConcurrentLinkedQueue<>();
        }

        private Task(ExecutorService innerEs, int[] ints, int start, int end, Queue<Future<?>> runningFuture) {
            this.innerEs = innerEs;
            this.ints = ints;
            this.start = start;
            this.end = end;
            this.runningFuture = runningFuture;
        }

        @Override
        public void run() {
            int l = start, r = end;
            int item = ints[l];
            while (l < r) {
                while (l < r && ints[r] >= item) {
                    r--;
                }
                if (l < r) {
                    swap(ints, l, r);
                }
                while (l < r && ints[l] <= item) {
                    l++;
                }
                if (l < r) {
                    swap(ints, l, r);
                }
            }
            assert l == r;
            int mid = l;
            if (mid > start) {
                runningFuture.add(innerEs.submit(new Task(innerEs, ints, start, mid - 1, runningFuture)));
            }
            if (mid < end) {
                runningFuture.add(innerEs.submit(new Task(innerEs, ints, mid + 1, end, runningFuture)));
            }
        }

        public void start() {
            runningFuture.add(innerEs.submit(this));
        }

    }

    private static void swap(int[] ints, int l, int r) {
        ints[l] = ints[l] + ints[r];
        ints[r] = ints[l] - ints[r];
        ints[l] = ints[l] - ints[r];
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Set<Integer> sets = Sets.newHashSet();
        for (int i = 0; i < 10000; i++) {
            sets.add(i);
        }
        List<Integer> lists = sets.stream().collect(Collectors.toList());
        int[] ints = new int[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            ints[i] = lists.get(i);
        }
        Task task = new Task(Executors.newFixedThreadPool(8), ints, 0, ints.length - 1);
        long start = System.currentTimeMillis();
        task.start();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(Arrays.toString(ints));
    }

}
