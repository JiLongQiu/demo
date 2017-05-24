package jilong.multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author jilong.qiu
 * @date 2017/5/10.
 */
public class MyBlockingQueue2<T> {

    private int capacity;
    private int size;
    private int head;
    private int tail;
    private Object[] items;
    private final Object lock;

    public MyBlockingQueue2(int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
        this.lock = new Object();
    }

    @SuppressWarnings("unchecked")
    public T take() throws InterruptedException {
        synchronized (lock){
            while (size == 0)
                lock.wait();
            T rs = (T) items[tail];
            tail = roundIndex(tail + 1);
            size--;
            lock.notifyAll();
            return rs;
        }
    }

    private int roundIndex(int index) {
        return index % capacity;
    }

    public boolean put(T t) throws InterruptedException {
        synchronized (lock){
            while (size == capacity)
                lock.wait();
            items[head] = t;
            head = roundIndex(head + 1);
            size++;
            lock.notifyAll();
            return true;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MyBlockingQueue2<Integer> queue = new MyBlockingQueue2<>(4);
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
