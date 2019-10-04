package string.week2;

import org.junit.Test;
import string.week2.BurrowsWheelerTransform;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BurrowsWheelerTransformTest {

    private final BurrowsWheelerTransform bwt = new BurrowsWheelerTransform();

    @Test
    public void testSample1() {
        doTest("AA$", "AA$");
    }

    @Test
    public void testSample2() {
        doTest("ACACACAC$", "CCCC$AAAA");
    }

    @Test
    public void testSample3() {
        doTest("AGACATA$", "ATG$CAAA");
    }

    @Test(timeout = 750)
    public void testMax() {
        char[] chars = { 'A', 'C', 'G', 'T' };
        Random r = new Random();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            builder.append(chars[r.nextInt(4)]);
        }

        bwt.BWT(builder.toString());
    }

    private void doTest(String text, String expected) {
        String transformed = bwt.BWT(text);
        assertEquals(expected, transformed);
    }

}