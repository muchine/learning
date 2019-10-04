package algo.week2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sejoonlim on 07/09/2017.
 */
public class FibonacciTest {

    private Fibonacci f;

    @Before
    public void setUp() {
        f = new Fibonacci();
    }

    @Test
    public void testMinimumN() {
        long result = f.calculate(2);
        assertTrue(result == 1);
    }

    @Test
    public void testNis0() {
        long result = f.calculate(0);
        assertTrue(result == 0);
    }

    @Test
    public void testNis10() {
        long result = f.calculate(10);
        assertTrue(result == 55);
    }

    @Test(timeout = 1000)
    public void testBigN() {
        long result = f.calculate(100);
        System.out.println("result: " + result);
        assertTrue(result > 0);
    }

}