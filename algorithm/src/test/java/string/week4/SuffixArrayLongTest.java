package string.week4;

import org.junit.Assert;
import org.junit.Test;
import string.week4.SuffixArrayLong;
import util.Helper;

public class SuffixArrayLongTest {

    private final SuffixArrayLong s = new SuffixArrayLong();

    @Test
    public void testSample1() {
        doTest("AAA$", new int[] { 3, 2, 1, 0 });
    }

    @Test
    public void testSample2() {
        doTest("GAC$", new int[] { 3, 1, 2, 0 });
    }

    @Test
    public void testSample3() {
        doTest("GAGAGAGA$", new int[] { 8, 7, 5, 3, 1, 6, 4, 2, 0 });
    }

    @Test
    public void testSample4() {
        doTest("AACGATAGCGGTAGA$", new int[] { 15, 14, 0, 1, 12, 6, 4, 2, 8, 13, 3, 7, 9, 10, 11, 5 });
    }

    private void doTest(String text, int[] expected) {
        int[] suffixes = s.computeSuffixArray(text);
        Helper.printArray(suffixes);

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] == suffixes[i]) continue;

            System.out.printf("Assertion failed. i: %d, e: %d, r: %d\n", i, expected[i], suffixes[i]);
            Assert.fail();
        }
    }

}