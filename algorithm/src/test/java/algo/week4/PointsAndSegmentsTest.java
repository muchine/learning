package algo.week4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointsAndSegmentsTest {

    @Test
    public void testExample1() {
        int[] starts = { 0, 7 };
        int[] ends = { 6, 11 };
        int[] points = { 1, 6, 11 };
        int[] expected = { 1, 0, 0 };

        doTest(starts, ends, points, expected);
    }

    @Test
    public void testExample2() {
        int[] starts = { -10 };
        int[] ends = { 11 };
        int[] points = { -100, 100, 0 };
        int[] expected = { 0, 0, 1 };

        doTest(starts, ends, points, expected);
    }

    @Test
    public void testExample3() {
        int[] starts = { 0 , -3, 7 };
        int[] ends = { 6, 3, 11 };
        int[] points = { 1, 6 };
        int[] expected = { 2, 0 };

        doTest(starts, ends, points, expected);
    }

    @Test
    public void testExample4() {
        int[] starts = { 0 , 2, 6 };
        int[] ends = { 6, 6, 8 };
        int[] points = { 1, 3, 6, 8, 9, 10 };
        int[] expected = { 1, 2, 1, 0, 0, 0 };

        doTest(starts, ends, points, expected);
    }

    @Test
    public void testDebugSampe1() {
        int[] starts = { 8295, 2967, 7194, 7221, 2802, 8533, 2345, 9314, 6842, 7247, 2528, 9968 };
        int[] ends = { 8921, 9007, 7742, 9324, 4101, 8838, 8216, 9886, 8415, 7818, 7570, 9984 };
//        int[] points = { 3180 };
//        int[] expected = { 4 };
        int[] points = { 394, 9181, 9726, 9768, 1411, 1745, 3180, 7952, 8085, 6981, 2331, 1877, 6631, 4147, 9314, 2030, 9035, 15, 5809, 481, 4179, 7375, 4371, 4757, 2416 };
        int[] expected = { 0, 1, 1, 1, 0, 0, 4, 4, 4, 4, 0, 0, 3, 3, 2, 0, 1, 0, 3, 0, 3, 7, 3, 3, 1 };

        doTest(starts, ends, points, expected);
    }

    private void doTest(int[] starts, int[] ends, int[] points, int[] expected) {
        PointsAndSegments ps = new PointsAndSegments(starts, ends);
        int[] result = ps.fastCountSegments(points);

        for (int i = 0; i < result.length; i++) {
            System.out.println("i: " + i);
            assertEquals(expected[i], result[i]);
        }
    }


}