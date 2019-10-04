package string.week2;

import org.junit.Test;
import string.week2.InverseBWT;

import static org.junit.Assert.assertEquals;

public class InverseBWTTest {

    private final InverseBWT i = new InverseBWT();

    @Test
    public void testSample0() {
        doTest("smnpbnnaaaaa$a", "panamabananas$");
    }

    @Test
    public void testSample1() {
        doTest("AC$A", "ACA$");
    }

    @Test
    public void testSample2() {
        doTest("AGGGAA$", "GAGAGA$");
    }

    private void doTest(String bwt, String expected) {
        String inverted = i.inverseBWT(bwt);
        assertEquals(expected, inverted);
    }

}