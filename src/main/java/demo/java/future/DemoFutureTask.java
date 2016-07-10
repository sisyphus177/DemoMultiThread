package demo.java.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by raist on 2016/7/10.
 *
 */
public class DemoFutureTask {
    private final FutureTask<String> future = new FutureTask<String>(new Callable<String>(){
        public String call() throws Exception {
            Thread.sleep(1000);
            return "FutureTask result.";
        }
    });

    private final Thread thread = new Thread(future);

    private void start(){
        thread.start();
    }

    private String get() throws InterruptedException{
        try{
            return future.get();
        }catch(ExecutionException e){
            Throwable cause = e.getCause();
            throw launderThrowable(cause);
        }
    }

    private static RuntimeException launderThrowable(Throwable t){
        if(t instanceof RuntimeException){
            return (RuntimeException)t;
        }else if(t instanceof Error){
            throw (Error)t;
        } else{
            throw new IllegalStateException("Unchecked Exception",t);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DemoFutureTask demoFutureTask = new DemoFutureTask();
        demoFutureTask.start();
        System.out.println(demoFutureTask.get());
    }
}
