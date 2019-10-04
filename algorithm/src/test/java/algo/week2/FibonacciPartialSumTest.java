package algo.week2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sejoonlim on 07/09/2017.
 */
public class FibonacciPartialSumTest {

    private final FibonacciPartialSum f = new FibonacciPartialSum();

    @Test
    public void test_3_7() {
        doTest(3, 7, 1);
    }

    @Test
    public void test_10_10() {
        doTest(10, 10, 5);
    }

    @Test
    public void test_10_200() {
        doTest(10, 200, 2);
    }

    private void doTest(long from, long to, long expected) {
        long sum = f.getFibonacciPartialSum(from, to);
        System.out.println("sum: " + sum);
        assertEquals(expected, sum);
    }

}