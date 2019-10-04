package algo.week4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearchTest {

    private final BinarySearch s = new BinarySearch();

    @Test
    public void testSimple1() {
        int[] sequence = new int[] { 1, 5, 8, 12, 13 };
        int[] keys = new int[] { 8, 1, 23, 1, 11 };
        int[] expected = new int[] { 2, 0, -1, 0, -1 };

        doTest(sequence, keys, expected);
    }

    @Test
    public void testSimple2() {
        int[] sequence = new int[] { 1, 5, 8, 12, 13 };
        int[] keys = new int[] { 8, 1, 5, 13, 12, 22, 0 };
        int[] expected = new int[] { 2, 0, 1, 4, 3, -1, -1 };

        doTest(sequence, keys, expected);
    }

    @Test
    public void testPathologicalCase() {
        int[] sequence = new int[100000];

        for (int i = 0; i < sequence.length; i ++) {
            sequence[i] = i;
        }

        doTest(sequence, sequence, sequence);

        int[] keys = new int[] { -1, 100001 };
        int[] expected = new int[] { -1, -1 };
        doTest(sequence, keys, expected);
    }

    private void doTest(int[] sequence, int[] keys, int[] expected) {
        for (int i = 0; i < keys.length; i++) {
            int result = s.binarySearch(sequence, keys[i]);
            assertEquals(expected[i], result);
        }
    }

}