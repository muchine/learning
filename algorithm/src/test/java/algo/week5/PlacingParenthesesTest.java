package algo.week5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlacingParenthesesTest {

    @Test
    public void testSample1() {
        doTest("1+5", 6);
    }

    @Test
    public void testSample2() {
        doTest("5-8+7*4-8+9", 200);
    }

    @Test
    public void testMax() {
        String exp = "5-8+7*4-8+9*3-8+7-3*3+4-7+5";
        PlacingParentheses p = new PlacingParentheses(exp);
        System.out.println("exp.length" + exp.length());
        long result = p.getMaximValue();
    }

    private void doTest(String exp, long expected) {
        PlacingParentheses p = new PlacingParentheses(exp);
        long result = p.getMaximValue();
        assertEquals(expected, result);
    }

}