package com.jilong.multithread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author jilong.qiu
 * @date 2017/2/7.
 */
public class SumTaskThreadPool {

    private static class SumTask extends RecursiveTask<Long> {

        private int start;
        private int end;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int ranLen = 1000;
            int diff = end - start;
            if (diff < ranLen) {
                return direct(start, end);
            } else {
                int taskCount = diff / ranLen;
                List<SumTask> tasks = Lists.newArrayList();
                for (int i = 1; i <= taskCount; i++) {
                    SumTask newTask = new SumTask(start + (i - 1) * ranLen, start + i * ranLen - 1);
                    newTask.fork();
                    tasks.add(newTask);
                }
                SumTask newTask = new SumTask(start + taskCount * ranLen, end);
                newTask.fork();
                tasks.add(newTask);
                long sum = 0;
                for (SumTask task : tasks) {
                    sum += task.join();
                }
                return sum;
            }

        }
    }

    private static Long direct(int start, int end) {
        long sum = 0;
        for (; start <= end; start++) {
            sum += start;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool pool = new ForkJoinPool();
        RecursiveTask task = new SumTask(1, 10000);
        long start = System.currentTimeMillis();
        pool.submit(task);
        try {
            System.out.println(task.get());
            System.out.println(System.currentTimeMillis() - start);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        start = System.currentTimeMillis();
        System.out.println(direct(1, 10000));
        System.out.println((System.currentTimeMillis() - start));
    }

}
