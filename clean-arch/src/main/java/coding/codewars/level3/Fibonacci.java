package coding.codewars.level3;

import java.math.BigInteger;

public class Fibonacci {

    public static BigInteger fib(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) return BigInteger.ZERO;
        if (n.equals(BigInteger.ONE)) return BigInteger.ONE;
        if (n.equals(BigInteger.valueOf(2))) return BigInteger.ONE;
        if (n.equals(BigInteger.valueOf(3))) return BigInteger.valueOf(2);
        if (n.equals(BigInteger.valueOf(-1))) return BigInteger.ONE;
        if (n.equals(BigInteger.valueOf(-2))) return BigInteger.valueOf(-1);
        if (n.equals(BigInteger.valueOf(-3))) return BigInteger.valueOf(2);


        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            BigInteger n2 = n.divide(BigInteger.valueOf(2));
            BigInteger fn2 = fib(n2);
            BigInteger fn2p1 = fib(n2.add(BigInteger.ONE));
            BigInteger fn2m1 = fn2p1.subtract(fn2);

            return fn2p1.add(fn2m1).multiply(fn2);
        } else {
            BigInteger n2 = n.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
            BigInteger fn2 = fib(n2);
            BigInteger fn2p1 = fib(n2.add(BigInteger.ONE));

            return fn2p1.pow(2).add(fn2.pow(2));
        }
    }

}
