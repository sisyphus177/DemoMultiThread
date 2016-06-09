package demo.java.counter;

//import java.util.concurrent.atomic.AtomicInteger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by raist on 2016/6/3.
 *
 */
public class DemoCounter1 {

    public static void main(String[] args) throws InterruptedException {

        final int threadCount = 10000;

        final CountDownLatch latch = new CountDownLatch(threadCount);

        //同时启动1000个线程，去进行i++计算，看看实际结果

        final Counter1 counter = new Counter1();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    counter.inc();
//                    DemoCounter1.inc();
                    latch.countDown();
                }
            });
            thread.start();
//            thread.join();
        }

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter1.count=" + counter.count);
    }

}

class Counter1 {

    volatile int count = 0;

    synchronized void inc() {

        //这里延迟1毫秒，使得结果明显
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        count = count+1;
        System.out.println(count);

    }

}