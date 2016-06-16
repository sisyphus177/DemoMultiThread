package demo.java.concurrent.showcase1;

import java.util.concurrent.*;


/**
 * Created by raist on 2016/6/16.
 *
 */
class Memorizer4<A,V> implements Computable<A,V> {

    private final ConcurrentMap<A,Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A,V> computable;

    Memorizer4(Computable<A, V> computable){
        this.computable = computable;
    }

    public V compute(final A arg) throws InterruptedException {
        while(true){
            Future<V> f = cache.get(arg);
            if(f==null){
                Callable<V> eval = new Callable<V>(){
                    public V call() throws InterruptedException {
                        return computable.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg,ft);
                if(f==null){
                    f=ft;
                    ft.run();
                }
            }
            try{
                return f.get();
            }catch(CancellationException e){
                cache.remove(arg,f);
            }catch(ExecutionException e){
                throw launderThrowable(e.getCause());
            }
        }
    }

    private static RuntimeException launderThrowable(Throwable throwable){
        if(throwable instanceof RuntimeException)
            return (RuntimeException)throwable;
        else if(throwable instanceof Error)
            throw (Error)throwable;
        else
            throw new IllegalStateException("Unchecked",throwable);
    }

}
