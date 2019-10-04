package string.week1;

import org.junit.Assert;
import org.junit.Test;
import string.week1.SuffixTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SuffixTreeTest {

    @Test
    public void testSample1() {
        List<String> expected = new ArrayList<>();
        expected.add("$");
        expected.add("A$");

        doTest("A$", expected);
    }

    @Test
    public void testSample2() {
        List<String> expected = new ArrayList<>();
        expected.add("$");
        expected.add("A");
        expected.add("$");
        expected.add("CA$");
        expected.add("CA$");

        doTest("ACA$", expected);
    }

    @Test
    public void testSample3() {
        List<String> expected = new ArrayList<>();
        expected.add("AAATG$");
        expected.add("G$");
        expected.add("T");
        expected.add("ATG$");
        expected.add("TG$");
        expected.add("A");
        expected.add("A");
        expected.add("AAATG$");
        expected.add("G$");
        expected.add("T");
        expected.add("G$");
        expected.add("$");

        doTest("ATAAATG$", expected);
    }

    @Test
    public void testCase4() {
        List<String> expected = new ArrayList<>();
        expected.add("$");
        expected.add("$");
        expected.add("$");
        expected.add("A");
        expected.add("A");
        expected.add("A$");

        doTest("AAA$", expected);
    }

    @Test
    public void testCase5() {
        List<String> expected = new ArrayList<>();
        expected.add("$");
        expected.add("$");
        expected.add("A$");
        expected.add("A$");
        expected.add("A$");
        expected.add("A");
        expected.add("CA");
        expected.add("CA");
        expected.add("CAA$");
        expected.add("CAA$");

        doTest("ACACAA$", expected);
    }

    private void doTest(String text, List<String> expected) {
        SuffixTree t = new SuffixTree();
        List<String> result = t.computeSuffixTreeEdges(text);

        for (String s : result) {
            System.out.println(s);
        }

        Collections.sort(result);
        Collections.sort(expected);

        assertEquals(expected.size(), result.size());

        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i).equals(result.get(i))) continue;

            System.out.printf("assertion failed. i: %d, e: %s, r: %s\n", i, expected.get(i), result.get(i));
            Assert.fail();
        }
    }

}