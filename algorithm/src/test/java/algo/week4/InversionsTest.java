package algo.week4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InversionsTest {

    private final Inversions i = new Inversions();

    @Test
    public void testExample1() {
        int[] sequence = new int[] { 2, 3, 9, 2, 9 };
        doTest(sequence, 2);
    }

    @Test
    public void testExample2() {
        int[] sequence = new int[] { 5, 4, 3, 2, 1 };
        doTest(sequence, 10);
    }

    @Test
    public void testExample3() {
        int[] sequence = new int[] { 1, 2, 3, 4, 5 };
        doTest(sequence, 0);
    }

    @Test
    public void testExample4() {
        int[] sequence = new int[] { 1, 1, 1, 1, 1 };
        doTest(sequence, 0);
    }

    @Test
    public void testExample5() {
        int[] sequence = new int[] { 1, 1, 1, 2, 2 };
        doTest(sequence, 0);
    }

    @Test
    public void testExample6() {
        int[] sequence = new int[] { 2, 2, 1, 1, 1 };
        doTest(sequence, 6);
    }

    @Test
    public void testExample7() {
        int[] sequence = new int[] { 2, 9, 3, 2};
        doTest(sequence, 3);
    }

    private void doTest(int[] sequence, long expected) {
        long result = i.getNumberOfInversions(sequence, 0, sequence.length);
        assertEquals(expected, result);
    }

}