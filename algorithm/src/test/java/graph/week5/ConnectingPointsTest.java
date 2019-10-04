package graph.week5;

import graph.week5.ConnectingPoints;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConnectingPointsTest {

    ConnectingPoints cp = new ConnectingPoints();

    @Test
    public void testSample1() {
        int[] x = { 0, 0, 1, 1};
        int[] y = { 0, 1, 0, 1 };

        doTest(x, y, 3.0);
    }

    @Test
    public void testSample2() {
        int[] x = { 0, 0, 1, 3, 3};
        int[] y = { 0, 2, 1, 0, 2 };

        doTest(x, y, 7.064495102);
    }

    private void doTest(int[] x, int[] y, double expected) {
        double result = cp.minimumDistance(x, y);
        System.out.printf("result: %f, expected: %f\n", result, expected);

        double delta = Math.abs(result - expected);
        assertTrue(delta <= 0.000001);
    }

}