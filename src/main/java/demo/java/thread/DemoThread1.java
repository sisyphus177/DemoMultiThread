package demo.java.thread;

/**
 * Created by raist on 2016/6/3.
 *
 */
public class DemoThread1 extends Thread{
    public void run(){
        System.out.println(getName()+" thread started.");
        int count = 0;
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println(getName()+" thread count "+(++count)+".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count>=10){
                keepRunning = false;
            }
        }
        System.out.println(getName()+" thread completed.");
    }

    public static void main(String[] args) {
        Thread thread1 = new DemoThread1();
        thread1.setName("Alex");
//        Thread thread2 = new DemoThread1();
//        thread2.setName("Bob");
//        Thread thread3 = new DemoThread1();
//        thread3.setName("Charlie");
        thread1.start();
//        thread2.start();
//        thread3.start();

        Thread thread4 = new Thread(new DemoRunnableInDemoThread1(),"Dave");
//        Thread thread5 = new Thread(new DemoRunnableInDemoThread1(),"Eric");
//        Thread thread6 = new Thread(new DemoRunnableInDemoThread1(),"Francis");
        thread4.start();
//        thread5.start();
//        thread6.start();
    }
}

class DemoRunnableInDemoThread1 implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName()+" Runnable object started.");
        int count = 0;
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println(Thread.currentThread().getName()+" Runnable object count "+(++count)+".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count>=10){
                keepRunning = false;
            }
        }
        System.out.println(Thread.currentThread().getName()+" Runnable object completed.");
    }
}