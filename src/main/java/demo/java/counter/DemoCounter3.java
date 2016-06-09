package demo.java.counter;

import java.util.concurrent.CountDownLatch;

/**
 * Created by raist on 2016/6/3.
 *
 */
public class DemoCounter3 {

    public static void main(String[] args) throws InterruptedException {

        final int threadCount = 1000;

        final CountDownLatch latch = new CountDownLatch(threadCount);

        final Counter3 counter = new Counter3();

        int i;

        for (i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    counter.inc();
                    latch.countDown();
                }
            },""+(i+1));
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("运行结果:Counter3.count=" + counter.getCount());
    }

}

class Counter3 {

    private volatile int count = 0;

    private final Object lock = new Object();

    int getCount() {
        return count;
    }

    int inc() {

        //这里延迟1毫秒，使得结果明显
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        synchronized (lock) {
            while(Integer.valueOf(Thread.currentThread().getName())!=1000-count){
                try {
                    //休眠
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count = count+1;
            System.out.println(count+" \t"+Thread.currentThread().getName());
            //唤醒
//            lock.notify();
            lock.notifyAll();
            return count;
        }
    }

}