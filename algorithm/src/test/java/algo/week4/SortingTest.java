package algo.week4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortingTest {

    private final Sorting s = new Sorting();

    @Test
    public void testExample1() {
        int[] sequence = new int[] { 2, 3, 9, 2, 2 };
        int[] expected = new int[] { 2, 2, 2, 3, 9 };
        doTest(sequence, expected);
    }

    @Test
    public void testExample2() {
        int[] sequence = new int[] { 2, 2, 2, 2, 2 };
        doTest(sequence, sequence);
    }

    @Test
    public void testExample3() {
        int[] sequence = new int[] { 2, 2, 2, 2, 3 };
        doTest(sequence, sequence);
    }

    @Test
    public void testExample4() {
        int[] sequence = new int[] { 3, 2, 2, 2, 2 };
        int[] expected = new int[] { 2, 2, 2, 2, 3 };
        doTest(sequence, expected);
    }

    @Test
    public void testExample5() {
        int[] sequence = new int[] { 5, 4, 3, 2, 1 };
        int[] expected = new int[] { 1, 2, 3, 4, 5 };
        doTest(sequence, expected);
    }

    @Test
    public void testExample6() {
        int[] sequence = new int[] { 5, 1, 3, 2, 4 };
        int[] expected = new int[] { 1, 2, 3, 4, 5 };
        doTest(sequence, expected);
    }

    @Test(timeout = 3000)
    public void testPathologicalCase() {
        final int max = 100000;

        int[] sequence = new int[max];
        int[] expected = new int[max];

        for (int i = 0; i < max; i++) {
            if (i < 50000) {
                sequence[i] = 3000;
                expected[i] = 2000;
            } else {
                sequence[i] = 2000;
                expected[i] = 3000;
            }
        }

        doTest(sequence, expected);
    }

    private void doTest(int[] sequence, int[] expected) {
        s.randomizedQuickSort(sequence, 0, sequence.length - 1);

        for (int i = 0; i < sequence.length; i++) {
//            System.out.print(sequence[i] + " ");
            assertEquals(expected[i], sequence[i]);
        }
    }

}