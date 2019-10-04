package string.week3;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class KnuthMorrisPrattTest {

    private final KnuthMorrisPratt kmp = new KnuthMorrisPratt();

    @Test
    public void testSample1() {
        doTest("TACG", "GT", new int[] {});
    }

    @Test
    public void testSample2() {
        doTest("ATA", "ATATA", new int[] { 0, 2 });
    }

    @Test
    public void testSample3() {
        doTest("ATAT", "GATATATGCATATACTT", new int[] { 1, 3, 9 });
    }

    private void doTest(String pattern, String text, int[] expected) {
        List<Integer> result = kmp.findPattern(pattern, text);

        for (int i = 0; i < expected.length; i++) {
            int r = result.get(i);
            int e = expected[i];

            if (r == e) continue;

            System.out.printf("Assertion failed. i: %d, r: %d, e: %d\n", i, r, e);
            Assert.fail();
        }

    }

}