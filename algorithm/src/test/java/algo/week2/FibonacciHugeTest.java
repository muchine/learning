package algo.week2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sejoonlim on 07/09/2017.
 */
public class FibonacciHugeTest {

    private final FibonacciHuge f = new FibonacciHuge();

    @Test
    public void test_N_1_M_239() {
        long number = f.getFibonacciHuge(1, 239);
        assertTrue(number == 1);
    }

    @Test
    public void test_N_239_M_1000() {
        long number = f.getFibonacciHuge(239, 1000);
        System.out.println(number);
        assertTrue(number == 161);
    }

    @Test
    public void test_N_2816213588_M_30524() {
        long number = f.getFibonacciHuge(2816213588L, 30524L);
        System.out.println(number);
        assertTrue(number == 10249);
    }

    @Test(timeout = 1000)
    public void test_N_99999999999999999_M_100000() {
        long number = f.getFibonacciHuge(99999999999999999L, 100000);
        System.out.println(number);
        assertTrue(number > 0);
    }

}