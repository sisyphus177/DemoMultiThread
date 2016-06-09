package demo.java.counter;

/**
 * Created by raist on 2016/6/4.
 *
 */
public class DemoCounter2 {

    public static void main(String[] args) {
        final Counter2 counter = new Counter2();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    long time = System.currentTimeMillis();
                    counter.inc();
                    long t = 0;
                    while(System.currentTimeMillis()-time<1000){
                        t = System.currentTimeMillis();
                    }
                    System.out.println(t+" "+counter.count);
                    if(counter.count>=10){
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        thread.start();

        boolean ended = false;
        while(!ended){
            if(thread.isInterrupted()){
                ended = true;
                System.out.println("运行结果:Counter2.count=" + counter.count);
            }
        }

    }
}

class Counter2 {

    volatile int count = 0;

    synchronized void inc() {
        count = count+1;
//        System.out.println(count);
    }
}
