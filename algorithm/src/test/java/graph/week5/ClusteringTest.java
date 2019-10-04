package graph.week5;

import graph.week5.Clustering;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClusteringTest {

    Clustering c = new Clustering();

    @Test
    public void testSample1() {
        int[] x = { 7, 4, 5, 1, 2, 5, 3, 7, 2, 4, 6, 2 };
        int[] y = { 6, 3, 1, 7, 7, 7, 3, 8, 8, 4, 7, 6 };
        int k = 3;
        double expected = 2.828427124746;

        doTest(x, y, k, expected);
    }

    @Test
    public void testSample2() {
        int[] x = { 3, 1, 4, 9, 9, 8, 3, 4 };
        int[] y = { 1, 2, 6, 8, 9, 9, 11, 12 };
        int k = 4;
        double expected = 5.0;

        doTest(x, y, k, expected);
    }

    private void doTest(int[] x, int[] y, int k, double expected) {
        double result = c.clustering(x, y, k);
        System.out.printf("result: %f, expected: %f\n", result, expected);

        double delta = Math.abs(result - expected);
        assertTrue(delta <= 0.000001);
    }

}