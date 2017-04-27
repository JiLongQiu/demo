package com.jilong.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jilong.qiu
 * @date 2016/12/20.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        lockInterrupt();
    }

    private static void lockInterrupt() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread t1 = new MyThread(lock);
        Thread t2 = new MyThread(lock);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
    }

    private static class MyThread extends Thread {

        public MyThread(Lock lock) {
            super(() -> {
                try {
                    lock.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + " running");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " end");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupt");
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
        }

    }

}
