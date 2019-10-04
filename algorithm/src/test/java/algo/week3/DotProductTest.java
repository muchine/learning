package algo.week3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DotProductTest {

    @Test
    public void testSimple() {
        long[] profits = new long[] { 23 };
        long[] clicks = new long[] { 39 };
        doTest(profits, clicks, 897);
    }

    @Test
    public void testIntermediate() {
        long[] profits = new long[] { 1, 3, -5 };
        long[] clicks = new long[] { -2, 4, 1 };
        doTest(profits, clicks, 23);
    }

    @Test
    public void testMax() {
        long[] profits = new long[1000];
        long[] clicks = new long[1000];

        for (int i = 0; i < 1000; i++) {
            profits[i] = 100000;
            clicks[i] = 100000;
        }

        DotProduct p = new DotProduct(profits, clicks);
        long result = p.maxDotProduct();
        System.out.println("result: " + result);
    }

    private void doTest(long[] profits, long[] clicks, long expected) {
        DotProduct p = new DotProduct(profits, clicks);
        long result = p.maxDotProduct();

        System.out.println("result: " + result);
        assertEquals(expected, result);
    }

}