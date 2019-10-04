package algo.week2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sejoonlim on 07/09/2017.
 */
public class FibonacciSumLastDigitTest {

    private final FibonacciSumLastDigit f = new FibonacciSumLastDigit();

    @Test
    public void test_3() {
        doTest(3, 4);
    }

    @Test
    public void test_7() {
        doTest(7, 3);
    }

    @Test
    public void test_100() {
        doTest(100, 5);
    }

    private void doTest(long n, int expected) {
        long digit = f.getFibonacciSum(n);
        assertEquals(expected, digit);
    }

}