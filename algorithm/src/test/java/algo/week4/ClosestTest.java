package algo.week4;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class ClosestTest {

    @Test
    public void testSample1() {
        int[] x = { 0, 3 };
        int[] y = { 0, 4 };

        doTest(x, y, 5.0);
    }

    @Test
    public void testSampe2() {
        int[] x = { 7, 1, 4, 7 };
        int[] y = { 7, 100, 8, 7 };

        doTest(x, y, 0.0);
    }

    @Test
    public void testSampe3() {
        int[] x = { 4, -2, -3, -1, 2, -4, 1, -1, 3, -4, -2 };
        int[] y = { 4, -2, -4, 3, 3, 0, 1, -1, -1, 2, 4 };

        doTest(x, y, 1.414);
    }

    @Test
    public void testDebuggingSample1() {
        int[] x = { 79, 38, 47, 15, 99, 98, 1, 2, 91, 45, 45, 16, 57, 83, 34, 85, 38, 67, 34, 35, 30, 43, 87, 76, 63, 98, 31, 81, 22, 64 };
        int[] y = { 88, 51, 2, 40, 90, 37, 81, 61, 83, 20, 25, 43, 42, 23, 0, 58, 11, 6, 45, 84, 62, 17, 44, 99, 99, 15, 40, 50, 23, 50 };

        doTest(x, y, 3.162);
    }

    @Test
    public void testMax() {
        int[] x = new int[100000];
        int[] y = new int[100000];

        Random random = new Random();

        for (int i = 0; i < x.length; i++) {
            x[i] = random.nextInt(1000000000) * (random .nextBoolean() ? -1 : 1);
//            x[i] = 0;
            y[i] = random.nextInt(1000000000) * (random .nextBoolean() ? -1 : 1);
//            y[i] = i;
        }

        Closest c = new Closest(x, y);
        long beginTime = System.nanoTime();
        double distance = c.fastMinimalDistance();
        long endTime = System.nanoTime();

        double elapsed = ((endTime - beginTime) / 1000000000);
        System.out.println("distance: " + distance + ", elapsed: " + elapsed);
    }

    private void doTest(int[] x, int[] y, double expected) {
        Closest c = new Closest(x, y);
        double result = c.fastMinimalDistance();
//        double result = c.naiveMinimalDistance();
        System.out.println("result: " + result);
        assertTrue(Math.abs(expected  - result) <= 0.001);
    }

}