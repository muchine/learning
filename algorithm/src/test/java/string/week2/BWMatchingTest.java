package string.week2;

import org.junit.Assert;
import org.junit.Test;
import string.week2.BWMatching;

public class BWMatchingTest {

    @Test
    public void testSample1() {
        String bwt = "AGGGAA$";
        String[] patterns = { "GA" };
        int[] expected = { 3 };

        doTest(bwt, patterns, expected);
    }

    @Test
    public void testSample2() {
        String bwt = "ATT$AA";
        String[] patterns = { "ATA", "A" };
        int[] expected = { 2, 3 };

        doTest(bwt, patterns, expected);
    }

    @Test
    public void testSample3() {
        String bwt = "AT$TCTATG";
        String[] patterns = { "TCT", "TATG" };
        int[] expected = { 0, 0 };

        doTest(bwt, patterns, expected);
    }

    @Test
    public void testCase2() {
        String bwt = "G$";
        String[] patterns = { "T", "G" };
        int[] expected = { 0, 1 };

        doTest(bwt, patterns, expected);
    }

    private void doTest(String bwt, String[] patterns, int[] expected) {
        BWMatching m = new BWMatching(bwt);

        for (int i = 0; i < patterns.length; i++) {
            String pattern = patterns[i];
            int count = m.countOccurrences(pattern);

            if (count == expected[i]) continue;

            System.out.printf("Assertion failed. i: %d, c: %d, e: %d\n", i, count, expected[i]);
            Assert.fail();
        }
    }

}