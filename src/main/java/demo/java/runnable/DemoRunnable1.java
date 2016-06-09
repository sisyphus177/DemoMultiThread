package demo.java.runnable;

/**
 * Created by raist on 2016/6/3.
 *
 */
public class DemoRunnable1 implements Runnable{

    public void run() {
        System.out.println(Thread.currentThread().getName()+" Runnable object started.");
        int count = 0;
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println(Thread.currentThread().getName()+" Runnable object count "+(++count)+".");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count>=10){
                keepRunning = false;
            }
        }
        System.out.println(Thread.currentThread().getName()+" Runnable object completed.");
    }

    public static void main(String[] args) {
        Thread thread4 = new Thread(new DemoRunnable1(),"Dave");
        Thread thread5 = new Thread(new DemoRunnable1(),"Eric");
        Thread thread6 = new Thread(new DemoRunnable1(),"Francis");
        thread4.start();
        thread5.start();
        thread6.start();
    }

}
