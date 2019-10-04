package data.week1;

import data.week1.CheckBrackets;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckBracketsTest {

    private final CheckBrackets cb = new CheckBrackets();

    @Test
    public void testSampe1() {
        doTest("[]", "Success");
    }

    @Test
    public void testSampe2() {
        doTest("{}[]", "Success");
    }

    @Test
    public void testSampe3() {
        doTest("[()]", "Success");
    }

    @Test
    public void testSampe4() {
        doTest("(())", "Success");
    }

    @Test
    public void testSampe5() {
        doTest("{[]}()", "Success");
    }

    @Test
    public void testSampe6() {
        doTest("{", "1");
    }

    @Test
    public void testSampe7() {
        doTest("{[}", "3");
    }

    @Test
    public void testSampe8() {
        doTest("foo(bar)", "Success");
    }

    @Test
    public void testSampe9() {
        doTest("foo(bar[i)", "10");
    }

    @Test
    public void testDebugging1() {
        doTest("}", "1");
    }

    private void doTest(String text, String expected) {
        String result = cb.check(text);
        assertEquals(expected, result);
    }

}