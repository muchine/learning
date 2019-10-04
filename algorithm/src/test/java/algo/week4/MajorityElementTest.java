package algo.week4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MajorityElementTest {

    private final MajorityElement m = new MajorityElement();

    @Test
    public void testExample1() {
        int[] sequence = new int[] { 2, 3, 9, 2, 2 };
        doTest(sequence, 1);
    }

    @Test
    public void testExample2() {
        int[] sequence = new int[] { 1, 2, 3, 4 };
        doTest(sequence, 0);
    }

    @Test
    public void testExample3() {
        int[] sequence = new int[] { 1, 2, 3, 1 };
        doTest(sequence, 0);
    }

    @Test
    public void testWrongAnswer() {
        int[] sequence = new int[] { 512766168, 717383758, 5, 126144732, 5, 573799007, 5, 5, 5, 405079772 };
        doTest(sequence, 0);
    }

    @Test(timeout = 1500)
    public void testPathologicalCase() {
        int[] sequence = new int[100000];
        for (int i = 0; i < sequence.length; i++) {
            if (i <= 50000)
                sequence[i] = 2000;
            else
                sequence[i] = i + 1;
        }

        doTest(sequence, 1);
    }

    private void doTest(int[] sequence, int expected) {
        int result = m.getMajorityElement(sequence);
        assertEquals(expected, result);
    }

}