package jilong.multithread;

/**
 * @author jilong.qiu
 * @date 2017/6/30.
 */
public class Bucket {

    private class TokenBucket {

        private int capacity;

        private int rate;

        private int rateMillisSecond;

        private int size;

        private long lastTime;

        public TokenBucket(int capacity, int rate) {
            this.capacity = capacity;
            this.rate = rate;
            this.rateMillisSecond = 1000 * 1000 / rate;
            this.size = capacity;
            this.lastTime = System.currentTimeMillis();
        }

        private void computeSize() {
            long now = System.currentTimeMillis();
            int add = (int)(now - lastTime) / rateMillisSecond;
            size += add;
            size = size > capacity ? capacity : size;
            lastTime += add * rateMillisSecond;
        }

        public synchronized void get() throws InterruptedException {
            computeSize();
            if (size == 0) {
                Thread.sleep((lastTime + rateMillisSecond) - System.currentTimeMillis());
            } else {
                size--;
            }
        }

    }

}
