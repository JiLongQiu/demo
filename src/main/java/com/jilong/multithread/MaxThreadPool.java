package com.jilong.multithread;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author jilong.qiu
 * @date 2017/2/7.
 */
public class MaxThreadPool {

    private static class MaxTask extends RecursiveTask<Integer> implements Callable<Integer> {

        private List<Integer> ints;
        private ExecutorService executor;

        public MaxTask(List<Integer> ints, ExecutorService executor) {
            this.ints = ints;
            this.executor = executor;
        }

        private Integer direct() {
            Integer maxVal = ints.get(0);
            for (Integer item : ints){
                if (item > maxVal) {
                    maxVal = item;
                }
            }
            return maxVal;
        }

        @Override
        protected Integer compute() {
            if (ints.size() <= 1000) {
                return direct();
            }
            List<List<Integer>> lists = Lists.partition(ints, 2);
            List<MaxTask> tasks = Lists.newArrayList();
            for (List<Integer> list : lists) {
                MaxTask task = new MaxTask(list, executor);
                task.fork();
                tasks.add(task);
            }
            Integer maxVal = null;
            for (MaxTask task : tasks) {
                try {
                    Integer val = task.get();
                    maxVal = maxVal == null ? val : val > maxVal ? val : maxVal;
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return maxVal;
        }

        @Override
        public Integer call() throws Exception {
            if (ints.size() <= 1000) {
                return direct();
            }
            List<List<Integer>> lists = Lists.partition(ints, 2);
            List<Future<Integer>> futures = lists.stream().map(item -> executor.submit(new MaxTask(item, executor))).collect(Collectors.toList());
            Integer maxVal = null;
            for (Future<Integer> future : futures) {
                try {
                    Integer val = future.get();
                    maxVal = maxVal == null ? val : val > maxVal ? val : maxVal;
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return maxVal;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int size = 10000000;
        List<Integer> ints = new ArrayList<>(size);
        for (int i = 0; i < size - 10000; i++) {
            ints.add(i);
        }
        System.out.println("add success");
        RecursiveTask task = new MaxTask(ints, pool);
        long start = System.currentTimeMillis();
        pool.submit((Callable)task);
        try {
            System.out.println(task.get());
            System.out.println(System.currentTimeMillis() - start);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
