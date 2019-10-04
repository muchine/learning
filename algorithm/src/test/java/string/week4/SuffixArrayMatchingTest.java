package string.week4;

import org.junit.Assert;
import org.junit.Test;
import string.week4.SuffixArrayMatching;
import util.Helper;

import java.util.List;

public class SuffixArrayMatchingTest {

    @Test
    public void testSample1() {
        doTest("AAA", new String[] { "A" }, new int[] { 0, 1, 2 });
    }

    @Test
    public void testSample2() {
        doTest("ATA", new String[] { "C", "G", "C" }, new int[] {});
    }

    @Test
    public void testSample3() {
        String[] patterns = { "ATA", "C", "TATAT" };
        int[] expected = { 0, 1, 2, 4 };

        doTest("ATATATA", patterns, expected);
    }

    @Test
    public void testSample4() {
        String[] patterns = { "A", "AC", "TAG" };
        int[] expected = { 0, 1, 4, 5, 6, 11, 12, 14 };

        doTest("AACGATAGCGGTAGA", patterns, expected);
    }

    private void doTest(String text, String[] patterns, int[] expected) {
        SuffixArrayMatching m = new SuffixArrayMatching(text);

        List<Integer> positions =  m.findOccurrences(patterns);
        Helper.printCollection("positions", positions);

        for (int i = 0; i < expected.length; i++) {
            int p = positions.get(i);
            int e = expected[i];

            if (p == e) continue;

            System.out.printf("Assertion failed. i: %d, p: %d, e: %d\n", i, p, e);
            Assert.fail();
        }
    }

}