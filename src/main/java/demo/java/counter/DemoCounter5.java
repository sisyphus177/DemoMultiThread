package demo.java.counter;

//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by raist on 2016/6/5.
 *
 */
public class DemoCounter5 {
    public static void main(String[] args) {
        DemoRunnable5 runnable = new DemoRunnable5();
        for(int i=0;i<5;i++){
            Thread t = new Thread(runnable,"Thread"+i);
            t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class DemoRunnable5 implements Runnable{

    private volatile static int count = 20;

    private volatile static String lastThread = "";

    private final Object lock = new Object();

    public void run() {
        while (true){
//            Lock lk = new ReentrantLock();
//            lk.lock();
//            try{
//                count = count - 1;
//            }finally {
//                lk.unlock();
//            }
            synchronized (lock) {
                while(lastThread.equals(Thread.currentThread().getName())){
                    if(count==0)break;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(count==0){
                    break;
                }
                count = count - 1;

                long time = System.currentTimeMillis();
                while(System.currentTimeMillis()-time<1000){
                    System.out.print("");
                }
                System.out.println(Thread.currentThread().getName()+" count down 1, "+count+" left.");
                lastThread = Thread.currentThread().getName();

                lock.notifyAll();
            }
        }
    }

}
