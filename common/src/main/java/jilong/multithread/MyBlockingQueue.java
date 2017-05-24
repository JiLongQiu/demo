package jilong.multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jilong.qiu
 * @date 2017/5/10.
 */
public class MyBlockingQueue<T> {

    private int capacity;
    private int size;
    private int head;
    private int tail;
    private Object[] items;
    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
    }

    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0)
                notEmpty.await();
            T rs = (T) items[tail];
            tail = roundIndex(tail + 1);
            size--;
            notFull.signalAll();
            return rs;
        } finally {
            lock.unlock();
        }
    }

    private int roundIndex(int index) {
        return index % capacity;
    }

    public boolean put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (size == capacity)
                notFull.await();
            items[head] = t;
            head = roundIndex(head + 1);
            size++;
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(4);
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("take : " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null && !line.equals("stop")) {
            queue.put(line.length());
        }
    }

}
