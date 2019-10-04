package advanced.week2;

import org.junit.Assert;
import org.junit.Test;

public class EnergyValuesTest {

    @Test
    public void testSample1() {
        double[][] a = {};
        double[] b = {};

        doTest(a, b, new double[] {});
    }

    @Test
    public void testSample2() {
        double[][] a = {
                { 1, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 1 }
        };
        double[] b = { 1, 5, 4, 3 };

        doTest(a, b, new double[] { 1, 5, 4, 3 });
    }

    @Test
    public void testSample3() {
        double[][] a = {
                { 1, 1 },
                { 2, 3 }
        };
        double[] b = { 3, 7 };

        doTest(a, b, new double[] { 2, 1 });
    }

    @Test
    public void testSample4() {
        double[][] a = {
                { 5, -5 },
                { -1, -2 }
        };
        double[] b = { -1, -1 };

        doTest(a, b, new double[] { 0.2, 0.4 });
    }

    private void doTest(double[][] a, double[] b, double[] expected) {
        EnergyValues ev = new EnergyValues(a, b);
        double[] result = ev.solveEquation();

        for (int i = 0; i < expected.length; i++) {
            double r = result[i];
            double e = expected[i];

            if (Math.abs(r - e) < 0.01) continue;

            System.out.printf("Assertion failed. i: %d, e: %s, r: %s\n", i, e, r);
            Assert.fail();
        }
    }

}