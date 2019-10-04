package algo.week2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sejoonlim on 07/09/2017.
 */
public class FibonacciLastDigitTest {

    private FibonacciLastDigit f;

    @Before
    public void setUp() {
        f = new FibonacciLastDigit();
    }

    @Test
    public void testNis3() {
        int d = f.getFibonacciLastDigit(3);
        assertTrue(d == 2);
    }

    @Test
    public void testNis331() {
        int d = f.getFibonacciLastDigit(331);
        assertTrue(d == 9);
    }

    @Test(timeout = 1000)
    public void testNis327305() {
        int d = f.getFibonacciLastDigit(327305);
        assertTrue(d == 5);
    }

    @Test(timeout = 1000)
    public void testNisMax() {
        int d = f.getFibonacciLastDigit(10000000);
        System.out.println("d: " + d);
        assertTrue(d > 0);
    }

}