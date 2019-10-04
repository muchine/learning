package advanced.week2;

import advanced.week2.Diet;
import org.junit.Assert;
import org.junit.Test;
import util.Helper;

import static org.junit.Assert.assertEquals;

public class DietTest {

    @Test
    public void testSample1() {
        double[][] a = {
                { -1, -1 },
                { 1, 0 },
                { 0, 1}
        };

        double[] b = { -1, 2, 2 };
        double[] c = { -1, 2 };
        double[] expected = { 0.0, 2.0 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testSample2() {
        double[][] a = {
                { 1, 1 },
                { -1, -1 }
        };

        double[] b = { 1, -2 };
        double[] c = { 1, 1 };
        double[] expected = { 0.0, 0.0 };

        doTest(a, b, c, -1, expected);
    }

    @Test
    public void testSample3() {
        double[][] a = {
                { 0, 0, 1 }
        };

        double[] b = { 3 };
        double[] c = { 1, 1, 1 };
        double[] expected = { 0.0, 0.0, 0.0 };

        doTest(a, b, c, 1, expected);
    }

    @Test
    public void testSample4() {
        double[][] a = {
                { 1, 3, 2 },
                { 1, 5, 1 }
        };

        double[] b = { 10, 8 };
        double[] c = { 8, 10, 7 };
        double[] expected = { 8.0, 0.0, 0.0 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testSample5() {
        double[][] a = {
                { 2, 1 },
                { 2, 3 },
                { 3, 1 }
        };

        double[] b = { 18, 42, 24 };
        double[] c = { 3, 2 };
        double[] expected = { 3, 12 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testSample6() {
        double[][] a = {
                { 10, 20 },
                { 8, 8 }
        };

        double[] b = { 120, 80 };
        double[] c = { 12, 16 };
        double[] expected = { 8, 2 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testSample7() {
        double[][] a = {
                { 1, 1, 1 },
                { -2, -1, 1 },
                { 0, 1, -1 }
        };

        double[] b = { 40, -10, -10 };
        double[] c = { 2, 3, 1 };
        double[] expected = { 10, 10, 20 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testSample8() {
        double[][] a = {
                { 1, 0 },
                { 0, 1 },
                { 2, 1 },
                { -1, 2 },
                { 5, -1 }
        };

        double[] b = { 7, 10, 21, 12, 30 };
        double[] c = { 1, 1 };
        double[] expected = { 10, 10 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testSample9() {
        double[][] a = {
                { 1, 0 },
                { -1, 0 },
                { 0, 1 },
                { 0, -1 }
        };

        double[] b = { 10, -10, 10, -10 };
        double[] c = { 1, 1 };
        double[] expected = { 10, 10 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testCase8() {
        double[][] a = {
                { -66 }
        };

        double[] b = { 2645 };
        double[] c = { 52 };
        double[] expected = { 0.0 };

        doTest(a, b, c, 1, expected);
    }

    @Test
    public void testCase14() {
        double[][] a = {
                { -1 },
                { 1 }
        };

        double[] b = { -39, 86 };
        double[] c = { -20 };
        double[] expected = { 39.0 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testCase17() {
        double[][] a = {
                { -1 },
                { 1 }
        };

        double[] b = { -42, 42 };
        double[] c = { 1 };
        double[] expected = { 42.0 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testCase20() {
        double[][] a = {
                { 50 },
                { -70 }
        };

        double[] b = { 1600, -2240 };
        double[] c = { 8 };
        double[] expected = { 32.0 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testCase35() {
        double[][] a = {
                { 0, 0 },
                { 0, 0 }
        };

        double[] b = { 0, 0 };
        double[] c = { 0, 0 };
        double[] expected = { 0.0, 0.0 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testCase37() {
        double[][] a = {
                { -78 },
                { -24 },
                { 10 }
        };

        double[] b = { -4898, -499, -8710 };
        double[] c = { 44 };
        double[] expected = { 0.0 };

        doTest(a, b, c, -1, expected);
    }

    @Test
    public void testCase68() {
        double[][] a = {
                { 3, 6 },
                { -89, 20 },
                { -53, -39 },
                { -2, 18 }
        };

        double[] b = { 570, -6218, -6452, 808 };
        double[] c = { 37, -87 };
        double[] expected = { 190.0, 0.0 };

        doTest(a, b, c, 0, expected);
    }

    @Test
    public void testCase120() {
        double[][] a = {
                { 90, 61, 70 },
                { 29, -14, 41 },
                { 74, -76, -58 },
                { -57, 34, 2 },
                { -71, -6, -97 },
                { 5, 58, -8 }
        };

        double[] b = { 8909, 1051, -1158, -940, -4919, 3610 };
        double[] c = { -72, -91, -16 };
        double[] expected = { 52.0, 59.0, 9.0 };

        doTest(a, b, c, 0, expected);
    }

    /*
     * Ad Allocation
     */

    @Test
    public void testCase49() {
        double[][] a = {
                { 15, 18, 10, 36, 27, 98, 44, 58, 66, 28 },
                { -69, -29, 64, -29, 19, -95, 65, 92, 14, 79 },
                { -29, 25, -20, 61, -39, 72, 100, -41, -14, -17 },
                { 31, 37, -56, 16, -60, 68, 34, 43, 97, -22 },
                { 39, -99, 41, -44, 67, 13, 79, 77, 61, 37 },
                { 20, -97, 62, 83, 96, -72, 45, -72, -6, -30 },
                { -11, -43, -77, -62, -73, 72, -67, -69, 55, 68 },
                { 35, 29, -14, -80, -65, 78, 72, -92, -92, -80 },
                { 81, 68, 22, -37, 12, -91, -91, 16, 3, -93 },
                { 62, -93, 10, 66, 21, 84, -6, 13, 77, -7 },
                { 38, -8, 1, 83, 82, -65, -88, 100, -54, -56 },
                { 12, 93, 64, 22, -25, 62, 93, 93, -72, -92 },
                { -19, -67, -18, 41, 38, 3, -35, -45, 46, 41 },
                { -34, 55, -65, -27, -7, 18, 62, 40, -40, 17 },
                { 33, -80, -32, -26, -54, 100, -75, -35, -59, 45 },
                { -14, -24, 98, 60, -26, 41, 50, 21, -67, 88 },
                { -43, -57, -34, 17, -1, -19, -69, 49, 40, -92 },
                { 62, -86, -72, -30, -1, -41, 15, 59, -84, -70 },
                { 11, -94, -18, 90, -7, 84, -29, -54, 93, 64 },
                { -100, -54, 94, -57, -48, -77, 15, 13, -50, -58 },
                { -7, -22, 25, 28, 10, 46, -19, 27, 39, 27 },
                { 88, -25, -47, 15, 73, 81, -59, 42, -100, -7 },
                { -67, -28, 55, -48, 79, 81, 68, 81, -46, -34 },
                { 3, -73, 98, 90, -53, -77, 39, 39, -72, -4 },
                { -64, 37, 72, -84, -67, 42, 22, 59, 79, -20 },
                { 37, 60, -21, -34, -58, -27, -82, 99, 77, -59 },
                { 59, 71, -35, -27, -92, 34, -17, -9, -53, 31 },
                { 66, 51, -53, -1, 43, 25, 24, -76, -100, -3 },
                { -90, 40, -98, 71, 64, 78, 20, 90, -72, 59 },
                { 61, -39, 32, 18, -99, 13, -13, -92, 43, -14 },
                { 99, 60, 48, -36, 32, -74, -34, -95, -39, -55 },
                { 30, 89, -46, -8, -19, -50, -69, -34, 17, 67 },
                { -46, 39, 6, -8, -6, -42, -92, -95, -92, -8 },
                { -69, 89, -87, -3, 70, -14, -43, -57, -22, 16 },
                { 98, -2, -35, -22, 49, 11, 30, -23, -94, -99 },
                { -76, 88, -98, 90, -36, 31, -22, -5, -6, -99 },
                { -21, 26, 79, 57, 22, 34, 53, 27, -65, 76 },
                { -80, 80, 47, 26, -1, 20, -58, 40, 83, 42 },
                { -82, -16, 66, -11, 2, 70, -46, -27, 99, 15 },
                { -92, 17, 39, 69, 62, 72, -76, -57, -37, -16 },
                { -74, 19, 11, -84, -30, -69, 65, 29, -9, -67 },
                { 19, -17, -37, -84, 49, 72, 27, -60, -96, -30 },
                { -13, 46, -44, 8, -93, 39, 57, -98, 5, -24 },
                { -65, 11, -99, -35, -9, 73, 90, -51, -53, 100 },
                { -48, 35, -41, 54, -96, -38, -44, 40, -38, -42 },
                { -26, 55, -27, 53, -56, -42, 77, -38, 20, -33 },
                { -96, -32, 30, 38, 95, 89, -45, 64, 61, -60 },
                { 23, 18, -26, 5, 77, 100, -70, -73, 75, -36 },
                { -57, -66, 81, 29, 58, -69, -67, 96, 48, 50 },
                { -51, 85, -76, -11, -29, 96, -24, 79, -12, -63 }
        };
        double[] b = { 24484, 4961, 1465, 11546, 14854, -8767, -11396, -11298, -2433, 9900, 7031, 18846, -4929, 5671, -7192, 10624, -11261, -10520, 1504, -21741, 8852, 12747, 13006, -7090, 5830, 5600, 2389, 1201, 17397, -12734, -6576, 980, -19210, -3319, -1756, -6346, 16403, 12892, 1383, -1049, -12166, -4626, -11462, -869, -11310, -6971, 10760, 6479, 5583, 9129 };
        double[] c = { -4, -91, -63, 43, 55, 86, -9, 11, 71, -64 };
        double[] expected = { 64, 82, 38, 27, 72, 80, 30, 100, 32, 60 };

        doTest(a, b, c, 0, expected);
    }

    private void doTest(double[][] a, double[] b, double[] c, int code, double[] expected) {
        Diet d = new Diet();
        double[] x = new double[expected.length];
        int result = d.solveDietProblem(a, b, c, x);
        Helper.printArray(x);

        assertEquals(code, result);

        if (result != 0) return;

        for (int i = 0; i < expected.length; i++) {
            double e = expected[i];
            double r = x[i];

            if (Math.abs(e -r) < 0.001) continue;

            System.out.printf("Assertion failed. i: %d, e: %s, r: %s\n", i, e, r);
            Assert.fail();
        }

    }

}