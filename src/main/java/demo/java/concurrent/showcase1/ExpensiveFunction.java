package demo.java.concurrent.showcase1;

import java.math.BigInteger;

/**
 * Created by raist on 2016/6/16.
 *
 */
class ExpensiveFunction implements Computable<String,BigInteger>{

    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }

}
