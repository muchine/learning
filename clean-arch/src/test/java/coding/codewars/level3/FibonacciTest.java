package coding.codewars.level3;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {

    @Test
    public void testFib0() {
        testFib(0, 0);
    }

    @Test
    public void testFib1() {
        testFib(1, 1);
    }

    @Test
    public void testFib2() {
        testFib(1, 2);
    }

    @Test
    public void testFib3() {
        testFib(2, 3);
    }

    @Test
    public void testFib4() {
        testFib(3, 4);
    }

    @Test
    public void testFib5() {
        testFib(5, 5);
    }

    @Test(timeout = 16000)
    public void test1() {
        System.out.println(Fibonacci.fib(BigInteger.valueOf(1390509)));
    }

    @Test
    public void test2() {
        testFib(1, -1);
    }

    @Test
    public void test3() {
        testFib(-1, -2);
    }

    @Test
    public void test4() {
        testFib(2, -3);
    }

    @Test
    public void test5() {
        System.out.println(Fibonacci.fib(BigInteger.valueOf(-101)));
    }

    private static void testFib(long expected, long input) {
        BigInteger found;
        try {
            found = Fibonacci.fib(BigInteger.valueOf(input));
        }
        catch (Throwable e) {
            // see https://github.com/Codewars/codewars.com/issues/21
            throw new AssertionError("exception during test: "+e, e);
        }
        assertEquals(BigInteger.valueOf(expected), found);
    }

}
