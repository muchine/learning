package string.week1;

import org.junit.Assert;
import org.junit.Test;
import string.week1.TrieMatchingExtended;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TrieMatchingExtendedTest {

    @Test
    public void testSample1() {
        String text = "AAA";
        String[] patterns = { "AA" };
        int[] expected = { 0, 1 };

        doTest(text, patterns, expected);
    }

    @Test
    public void testSample2() {
        String text = "ACATA";
        String[] patterns = { "AT", "A", "CA" };
        int[] expected = { 0, 1, 2, 4 };

        doTest(text, patterns, expected);
    }

    @Test
    public void testSample3() {
        String text = "AATCGGGTTCAATCGGGGT";
        String[] patterns = { "ATCG", "GGGT" };
        int[] expected = { 1, 4, 11, 15 };

        doTest(text, patterns, expected);
    }

    private void doTest(String text, String[] patterns, int[] expected) {
        TrieMatchingExtended m = new TrieMatchingExtended();
        List<Integer> positions = m.solve(text, patterns.length, Arrays.asList(patterns));

        assertEquals(expected.length, positions.size());

        for (int i = 0; i < expected.length; i++) {
            int e = expected[i];
            int p = positions.get(i);

            if (e == p) continue;

            System.out.printf("assertion failed. i: %d, e: %d, p: %d", i, e, p);
            Assert.fail();
        }

    }

}