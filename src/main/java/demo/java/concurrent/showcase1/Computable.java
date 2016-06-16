package demo.java.concurrent.showcase1;

/**
 * Created by raist on 2016/6/16.
 *
 */
interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
