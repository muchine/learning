package string.week4;

import org.junit.Assert;
import org.junit.Test;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

public class SuffixTreeFromArrayTest {

    @Test
    public void testSample1() {
        String text = "A$";
        char[] chars = text.toCharArray();
        int[] suffixArray = { 1, 0 };
        int[] lcpArray = { 0 };

        List<SuffixTreeFromArray.Output> expected = new ArrayList<>();
        expected.add(new SuffixTreeFromArray.Output(chars, 1, 2));
        expected.add(new SuffixTreeFromArray.Output(chars, 0, 2));

        doTest("A$", suffixArray, lcpArray, expected);
    }

    @Test
    public void testSample2() {
        String text = "AAA$";
        char[] chars = text.toCharArray();
        int[] suffixArray = { 3, 2, 1, 0 };
        int[] lcpArray = { 0, 1, 2 };

        List<SuffixTreeFromArray.Output> expected = new ArrayList<>();
        expected.add(new SuffixTreeFromArray.Output(chars, 3, 4));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 3));
        expected.add(new SuffixTreeFromArray.Output(chars, 3, 4));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 3));
        expected.add(new SuffixTreeFromArray.Output(chars, 3, 4));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 4));

        doTest("AAA$", suffixArray, lcpArray, expected);
    }

    @Test
    public void testSample3() {
        String text = "GTAGT$";
        char[] chars = text.toCharArray();
        int[] suffixArray = { 5, 2, 3, 0, 4, 1 };
        int[] lcpArray = { 0, 0, 2, 0, 1 };

        List<SuffixTreeFromArray.Output> expected = new ArrayList<>();
        expected.add(new SuffixTreeFromArray.Output(chars, 5, 6));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 6));
        expected.add(new SuffixTreeFromArray.Output(chars, 3, 5));
        expected.add(new SuffixTreeFromArray.Output(chars, 5, 6));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 6));
        expected.add(new SuffixTreeFromArray.Output(chars, 4, 5));
        expected.add(new SuffixTreeFromArray.Output(chars, 5, 6));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 6));

        doTest("GTAGT$", suffixArray, lcpArray, expected);
    }

    @Test
    public void testCase1() {
        String text = "ATAAATG$";
        char[] chars = text.toCharArray();
        int[] suffixArray = { 7, 2, 3, 0, 4, 6, 1, 5 };
        int[] lcpArray = { 0, 2, 1, 2, 0, 0, 1 };

        List<SuffixTreeFromArray.Output> expected = new ArrayList<>();
        expected.add(new SuffixTreeFromArray.Output(chars, 7, 8));
        expected.add(new SuffixTreeFromArray.Output(chars, 4, 5));
        expected.add(new SuffixTreeFromArray.Output(chars, 4, 5));
        expected.add(new SuffixTreeFromArray.Output(chars, 4, 8));
        expected.add(new SuffixTreeFromArray.Output(chars, 5, 8));
        expected.add(new SuffixTreeFromArray.Output(chars, 5, 6));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 8));
        expected.add(new SuffixTreeFromArray.Output(chars, 6, 8));
        expected.add(new SuffixTreeFromArray.Output(chars, 6, 8));
        expected.add(new SuffixTreeFromArray.Output(chars, 5, 6));
        expected.add(new SuffixTreeFromArray.Output(chars, 2, 8));
        expected.add(new SuffixTreeFromArray.Output(chars, 6, 8));

        doTest("ATAAATG$", suffixArray, lcpArray, expected);
    }

    private void doTest(String text, int[] suffixArray, int[] lcpArray, List<SuffixTreeFromArray.Output> expected) {
        SuffixTreeFromArray st = new SuffixTreeFromArray(text, suffixArray, lcpArray);
        List<SuffixTreeFromArray.Output> outputs = st.run();

        Helper.printCollection("output", outputs);

        for (int i = 0; i < expected.size(); i++) {
            SuffixTreeFromArray.Output o = outputs.get(i);
            SuffixTreeFromArray.Output e = expected.get(i);

            if (e.equals(o)) continue;

            System.out.printf("Assertion failed. i: %d, e: %s, o: %s\n", i, e, o);
            Assert.fail();
        }
    }

}