package demo.java.concurrent.showcase1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by raist on 2016/6/16.
 *
 */
class Memorizer2<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A,V> computable;

    Memorizer2(Computable<A, V> computable){
        this.computable = computable;
    }

    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
