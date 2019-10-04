package algo.week5;

import algo.week5.LCS3;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class LCS3Test {

    @Test
    public void testSample1() {
        int[] a = { 1, 2, 3 };
        int[] b = { 2, 1, 3 };
        int[] c = { 1, 3, 5 };

        doTest(a, b, c, 2);
    }

    @Test
    public void testSample2() {
        int[] a = { 8, 3, 2, 1, 12 };
        int[] b = { 8, 2, 1, 3, 8, 10, 12 };
        int[] c = { 6, 8, 3, 1, 4, 12 };

        doTest(a, b, c, 3);
    }

    @Test
    public void testDebuggingSampe1() {
        int[] a = { 1, 2, 3 };
        int[] b = { 1, 3, 2 };
        int[] c = { 2, 1, 3 };

        doTest(a, b, c, 2);
    }

//    @Test
    public void testMax() {
        final int max = 100;
        Random random = new Random();

        int[] a = new int[max];
        int[] b = new int[max];
        int[] c = new int[max];

        for (int i = 0; i < max; i++) {
            a[i] = random.nextInt(100);
            b[i] = random.nextInt(100);
            c[i] = random.nextInt(100);
        }

        LCS3 l = new LCS3(a, b, c);
        int result = l.execute();
        System.out.printf("result: %s\n", result);
    }

    private void doTest(int[] a, int[] b, int[] c, int expected) {
        LCS3 l = new LCS3(a, b, c);

        int result = l.execute();
        assertEquals(expected, result);
    }

}