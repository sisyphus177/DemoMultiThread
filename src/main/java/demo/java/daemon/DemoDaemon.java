package demo.java.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by raist on 2016/6/6.
 *
 */
public class DemoDaemon {

    private static Logger Log = LoggerFactory.getLogger(DemoDaemon.class);

    public static void main(String[] args) {
        Log.info("Start Main Thread "+Thread.currentThread().getName()+".");
        Thread thread = new Thread(new DaemonThread());
        thread.setName("DaemonThread");
        thread.setDaemon(true);
        thread.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.next());
        Log.info("Stop Main Thread "+Thread.currentThread().getName()+".");
    }

}

class DaemonThread implements Runnable {

    private static Logger Log = LoggerFactory.getLogger(DaemonThread.class);

    private static boolean flag = true;

    @SuppressWarnings("unused")
    public static void setFlag(boolean flag) {
        DaemonThread.flag = flag;
    }

    @SuppressWarnings("unused")
    public static boolean isFlag() {
        return flag;
    }

    public void run() {
        Log.info("Start DaemonThread "+Thread.currentThread().getName()+".");
        int count = 0;
        while (flag) {
            Log.info("DaemonThread running for "+(count++)+" seconds.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.info("Stop DaemonThread "+Thread.currentThread().getName()+".");
    }

}