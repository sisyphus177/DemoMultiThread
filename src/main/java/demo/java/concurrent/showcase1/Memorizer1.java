package demo.java.concurrent.showcase1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raist on 2016/6/16.
 *
 */
class Memorizer1<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<A,V>();
    private final Computable<A,V> computable;

    Memorizer1(Computable<A, V> computable){
        this.computable = computable;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
