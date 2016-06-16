package demo.java.concurrent.showcase1;

import java.math.BigInteger;

/**
 * Created by raist on 2016/6/16.
 *
 */
public class MemorizerTest {

    public static void main(String[] args) throws InterruptedException {
        Computable<String,BigInteger> computable = new ExpensiveFunction();
        Memorizer1<String,BigInteger> memorizer1 = new Memorizer1<String,BigInteger>(computable);
        System.out.println(memorizer1.compute("10000000"));
        Memorizer2<String,BigInteger> memorizer2 = new Memorizer2<String,BigInteger>(computable);
        System.out.println(memorizer2.compute("10000000"));
        Memorizer3<String,BigInteger> memorizer3 = new Memorizer3<String,BigInteger>(computable);
        System.out.println(memorizer3.compute("10000000"));
        Memorizer4<String,BigInteger> memorizer4 = new Memorizer4<String,BigInteger>(computable);
        System.out.println(memorizer4.compute("10000000"));
    }

}
