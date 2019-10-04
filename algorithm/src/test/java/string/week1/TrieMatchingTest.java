package string.week1;

import org.junit.Assert;
import org.junit.Test;
import string.week1.TrieMatching;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TrieMatchingTest {

    @Test
    public void testSample1() {
        String text = "AAA";
        String[] patterns = { "AA" };
        int[] expected = { 0, 1 };

        doTest(text, patterns, expected);
    }

    @Test
    public void testSample2() {
        String text = "AA";
        String[] patterns = { "T" };

        doTest(text, patterns, new int[0]);
    }

    @Test
    public void testSample3() {
        String text = "AATCGGGTTCAATCGGGGT";
        String[] patterns = { "ATCG", "GGGT" };
        int[] expected = { 1, 4, 11, 15 };

        doTest(text, patterns, expected);
    }

    private void doTest(String text, String[] patterns, int[] expected) {
        TrieMatching m = new TrieMatching();
        List<Integer> positions = m.solve(text, Arrays.asList(patterns));

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