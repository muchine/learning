package data.week1;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TreeHeightCheckerTest {

    @Test
    public void testSample1() {
        int[] parents = { 4, -1, 4, 1, 1 };
        doTest(parents, 3);
    }

    @Test
    public void testSample2() {
        int[] parents = { -1, 0, 4, 0, 3 };
        doTest(parents, 4);
    }

    @Test
    public void testSample3() {
        int[] parents = { -1 };
        doTest(parents, 1);
    }

    @Test
    public void testMax() {
        int maxNumber = 100000;
        int[] parents = new int[maxNumber];

        Random random = new Random();
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i - 1;
        }

        TreeHeightChecker c = new TreeHeightChecker(parents);
        System.out.println(c.height());
    }

    private void doTest(int[] parents, int expected) {
        TreeHeightChecker c = new TreeHeightChecker(parents);
        int result = c.height();
        assertEquals(expected, result);
    }

}