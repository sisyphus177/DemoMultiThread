package demo.java.counter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by raist on 2016/6/5.
 *
 */
public class DemoCounter4 {

    private final static int threadCount = 10;

    public static void main(String[] args) {

        int threadCount = DemoCounter4.threadCount;
        int i;

        DemoThread4[] threads = new DemoThread4[threadCount];
        for(i=0;i<threadCount;i++){
            threads[i] = new DemoThread4();
            threads[i].setName("Thread"+i);
            threads[i].start();

//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

//        long time = System.currentTimeMillis();
//        while(System.currentTimeMillis()-time<3000){
//        }

        List<DemoThread4> list = new ArrayList<DemoThread4>(Arrays.asList(threads));
        boolean completed;
        do{
            completed = true;
            Iterator<DemoThread4> iterator = list.iterator();
            while(iterator.hasNext()){
                DemoThread4 t = iterator.next();
                if(t.isAlive()){
                    completed = false;
                }else{
                    System.out.println(t.getName()+" is completed.");
                    iterator.remove();
                }
            }
        }while(!completed);

//        boolean completed;
//        do{
//            completed = true;
//            for(i=0;i<threadCount;i++){
//                completed = completed && !threads[i].isAlive();
//            }
//        }while (!completed);

        for(i=0;i<threadCount;i++){
            if(threads[i].getCountForThisThread()!=0) {
                System.out.println(threads[i].getName() + " count " + threads[i].getCountForThisThread() + " in total.");
            }
        }

    }

}

class DemoThread4 extends Thread {

    private volatile static int count = 20;

    private volatile static String lastThread = "";

    private static final Object lock = new Object();

    private int countForThisThread = 0;

    int getCountForThisThread() {
        return countForThisThread;
    }

    @Override
    public void run() {

        while (true){
            synchronized (lock) {

                while(lastThread.equals(Thread.currentThread().getName())){
                    if(count==0){
                        break;
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(count==0){
                    break;
                }

                count--;
                countForThisThread++;
                lastThread = Thread.currentThread().getName();

                long time = System.currentTimeMillis();
                while(System.currentTimeMillis()-time<1000){
                    System.out.print("");
                }
                System.out.println(Thread.currentThread().getName() + " count down 1, " + count + " left.");

//                if (countForThisThread >= 5) {
//                    break;
//                }

                lock.notifyAll();

            }
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
