package data.week3;

import org.junit.Assert;
import org.junit.Test;
import util.Helper;

public class MergingTablesTest {

    @Test
    public void testSample1() {
        int[] rows = { 1, 1, 1, 1, 1 };
        int[] sources = { 3, 2, 1, 5, 5 };
        int[] destinations = { 5, 4, 4, 4, 3 };
        int[] expected = { 2, 2, 3, 5, 5 };

        doTest(rows, sources, destinations, expected);
    }

    @Test
    public void testSample2() {
        int[] rows = { 10, 0, 5, 0, 3, 3 };
        int[] sources = { 6, 6, 5, 4 };
        int[] destinations = { 6, 5, 4, 3 };
        int[] expected = { 10, 10, 10, 11 };

        doTest(rows, sources, destinations, expected);
    }

    @Test(timeout = 10000)
    public void testMax() {
        int max = 100000;
        int[] rows = new int[max];
        for (int i = 0; i < max; i++) {
            rows[i] = i;
        }

        int[] sources = new int[max - 1];
        int[] destinations = new int[max - 1];

        for (int i = 0; i < max - 1; i++) {
            sources[i] = i + 2;
            destinations[i] = i + 1;
        }

        MergingTables t = new MergingTables();
        t.execute(rows, sources, destinations);
    }

    private void doTest(int[] rows, int[] sources, int[] destinations, int[] expected) {
        MergingTables t = new MergingTables();
        t.execute(rows, sources, destinations);
        Helper.printArray(t.maxNumbers);

        for (int i = 0; i < t.maxNumbers.length; i++) {
            if (t.maxNumbers[i] != expected[i]) {
                System.out.printf("different number. i: %d, result: %d, expected: %d\n", i, t.maxNumbers[i], expected[i]);
                Assert.fail();
            }
        }
    }

}