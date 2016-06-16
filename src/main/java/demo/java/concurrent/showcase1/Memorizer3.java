package demo.java.concurrent.showcase1;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by raist on 2016/6/16.
 *
 */
class Memorizer3<A,V> implements Computable<A,V> {

    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> computable;

    Memorizer3(Computable<A, V> computable){
        this.computable = computable;
    }

    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if(f==null){
            Callable<V> eval = new Callable<V>(){
                public V call() throws InterruptedException {
                    return computable.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg,ft);
            ft.run();
        }
        try{
            return f.get();
        }catch(ExecutionException e){
            throw launderThrowable(e.getCause());
        }
    }

    private static RuntimeException launderThrowable(Throwable t){
        if(t instanceof RuntimeException){
            return (RuntimeException)t;
        }else if(t instanceof Error){
            throw (Error)t;
        }else{
            throw new IllegalStateException("Unchecked",t);
        }
    }

}
