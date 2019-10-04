package algo;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrimitiveCalculatorTest {

    @Test
    public void testSample1() {
        int[] expected = { 1 };
        doTest(1, expected);
    }

    @Test
    public void testSample2() {
        int[] expected = { 1, 2, 4, 5 };
        doTest(5, expected);
    }

    @Test
    public void testSample3() {
        int[] expected = { 1, 3 };
        doTest(3, expected);
    }

    @Test
    public void testSample4() {
        int[] expected = { 1, 2, 4 };
        doTest(4, expected);
    }

    @Test
    public void testSample5() {
        int[] expected = { 1, 3, 9, 10, 11, 22, 66, 198, 594, 1782, 5346, 16038, 16039, 32078, 96234 };
        doTest(96234, expected);
    }

    @Test(timeout = 1500)
    public void testMax() {
        PrimitiveCalculator pc = new PrimitiveCalculator(989382);
        System.out.println(pc.optimalSequence());
    }

    private void doTest(int n, int[] expected) {
        PrimitiveCalculator pc = new PrimitiveCalculator(n);
        List<Integer> result = pc.optimalSequence();
//        Helper.printCollection("result", result);

        assertEquals(expected.length, result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(new Integer(expected[i]), result.get(i));
        }
    }


}