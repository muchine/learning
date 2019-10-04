package data.week6;

import data.week6.BSTChecker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BSTCheckerTest {

    @Test
    public void testSample1() {
        int[] keys = { 2, 1, 3 };
        int[] left = { 1, -1, -1 };
        int[] right = { 2, -1, -1 };

        doTest(keys, left, right, true);
    }

    @Test
    public void testSample2() {
        int[] keys = { 1, 2, 3 };
        int[] left = { 1, -1, -1 };
        int[] right = { 2, -1, -1 };

        doTest(keys, left, right, false);
    }

    @Test
    public void testSample3() {
        doTest(null, null, null, true);
    }

    @Test
    public void testSample4() {
        int[] keys = { 1, 2, 3, 4, 5 };
        int[] left = { -1, -1, -1, -1, -1 };
        int[] right = { 1, 2, 3, 4, -1 };

        doTest(keys, left, right, true);
    }

    @Test
    public void testSample5() {
        int[] keys = { 4, 2, 6, 1, 3, 5, 7 };
        int[] left = { 1, 3, 5, -1, -1, -1, -1 };
        int[] right = { 2, 4, 6, -1, -1, -1, -1 };

        doTest(keys, left, right, true);
    }

    @Test
    public void testSample6() {
        int[] keys = { 4, 2, 1, 5 };
        int[] left = { 1, 2, -1, -1 };
        int[] right = { -1, 3, -1, -1 };

        doTest(keys, left, right, false);
    }

    private void doTest(int[] keys, int[] left, int[] right, boolean expected) {
        BSTChecker c = new BSTChecker(keys, left, right);
        boolean result = c.solve();

        assertEquals(expected, result);
    }

}