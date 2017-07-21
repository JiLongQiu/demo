package jilong.data.queue;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * @author jilong.qiu
 * @date 2017/4/17.
 */
public class MyQueue<T extends Comparable<? super T>> extends AbstractQueue<T> {

    transient Object[] queue;
    private int size;

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    public MyQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MyQueue(int initSize) {
        this.queue = new Object[initSize];
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(T t) {
        size++;
        if (size > queue.length) {
            grow();
        }
        if (size - 1 == 0) {
            queue[0] = t;
        } else {
            siftUp(size - 1, t);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void siftUp(int index, T t) {
        while (index > 0) {
            int pIndex = (index - 1) / 2;
            if (t.compareTo((T) queue[pIndex]) < 0) {
                queue[index] = queue[pIndex];
                index = pIndex;
            } else {
                break;
            }
        }
        queue[index] = t;
    }

    private void grow() {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1));
        queue = Arrays.copyOf(queue, newCapacity);
    }

    @SuppressWarnings("unchecked")
    private void siftDown(int index, T t) {
        while (true) {
            int lc = 2 * index + 1, rc = 2 * index + 2;
            if (lc > size - 1)
                break;
            int minLc = rc > size - 1 ? lc : ((T) queue[lc]).compareTo((T) queue[rc]) < 0 ? lc : rc;
            T minLcT = (T) queue[minLc];
            if (minLcT.compareTo(t) < 0) {
                queue[index] = minLcT;
                index = minLc;
            } else
                break;
        }
        queue[index] = t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T poll() {
        if (size == 0) return null;
        int lastIndex = --size;
        T result = (T) queue[0];
        T insertT = (T) queue[lastIndex];
        queue[lastIndex] = null;
        siftDown(0, insertT);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        return size == 0 ? null : (T) queue[0];
    }

    public void printInfo() {
        System.out.println(queue.length + "_" + size);
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            queue.add(Math.abs(r.nextInt()) % 1000);
        }
        System.out.println();
        queue.printInfo();
        while (queue.peek() != null) {
            Integer t = queue.poll();
            System.out.println(t);
        }
        queue.printInfo();
    }

}
