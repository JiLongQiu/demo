package jilong.multithread.tool;

import java.util.concurrent.*;

/**
 * @author jilong.qiu
 * @date 2017/5/10.
 */
public class Tools {

    private static void cdl() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(2);
        Thread t1 = new Thread(cdl::countDown);
        Thread t2 = new Thread(cdl::countDown);
        System.out.println(cdl.await(3, TimeUnit.SECONDS));
        t1.start();t2.start();
        cdl.await();
        System.out.println("over");
    }

    private static void semaphore() throws InterruptedException {
        Semaphore sem = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    sem.acquire();
                    Thread.sleep(3000);
                    System.out.println("i = " + Integer.toString(j) + " over ");
                    sem.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void cyclicBarrier() throws InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(5);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            Thread.sleep(1000);
            new Thread(() -> {
                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + Integer.toString(j) + " over ");
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        cdl();
//        semaphore();
//        cyclicBarrier();
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(() -> {
            throw new RuntimeException("hhaa");
        });
    }

}
