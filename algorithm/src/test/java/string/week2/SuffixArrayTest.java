package string.week2;

import org.junit.Assert;
import org.junit.Test;
import string.week2.SuffixArray;

public class SuffixArrayTest {

    private final SuffixArray sa = new SuffixArray();

    @Test
    public void testSample1() {
        doTest("GAC$", new int[] { 3, 1, 2, 0 });
    }

    @Test
    public void testSample2() {
        doTest("GAGAGAGA$", new int[] { 8, 7, 5, 3, 1, 6, 4, 2, 0 });
    }

    @Test
    public void testSample3() {
        doTest("AACGATAGCGGTAGA$", new int[] { 15, 14, 0, 1, 12, 6, 4, 2, 8, 13, 3, 7, 9, 10, 11, 5 });
    }

    private void doTest(String text, int[] expected) {
        int[] result = sa.computeSuffixArray(text);
        for (int i = 0; i < expected.length; i++) {
            if (result[i] == expected[i]) continue;

            System.out.printf("Assertion failed. i: %d, r: %d, e: %d\n", i, result[i], expected[i]);
            Assert.fail();
        }
    }

}