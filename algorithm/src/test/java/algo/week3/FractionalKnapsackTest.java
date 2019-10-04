package algo.week3;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class FractionalKnapsackTest {

    @Test
    public void testSimple() {
        doTest(10, new int[] { 500}, new int[] { 30 }, 166.6666);
    }

    @Test
    public void testIntermediate() {
        int[] values = new int[] { 60, 100, 120 };
        int[] weights = new int[] { 20, 50, 30 };
        doTest(50, values, weights, 180.0000);
    }

    private void doTest(int capacity, int[] values, int[] weights, double expected) {
        FractionalKnapsack k = new FractionalKnapsack(capacity, values, weights);

        double result = k.getOptimalValue();
        System.out.println("result: " + result);

        double diff = expected - result;
        assertTrue(diff > -0.001 && diff < 0.001);
    }

}