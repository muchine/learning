package data.week4;

import data.week4.HashSubstring;
import org.junit.Test;
import util.Helper;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HashSubstringTest {

    @Test
    public void testSample1() {
        HashSubstring.Data input = new HashSubstring.Data("aba", "abacaba");
        int[] expected = { 0, 4 };

        doTest(input, expected);
    }

    @Test
    public void testSample2() {
        HashSubstring.Data input = new HashSubstring.Data("Test", "testTesttesT");
        int[] expected = { 4 };

        doTest(input, expected);
    }

    @Test
    public void testSample3() {
        HashSubstring.Data input = new HashSubstring.Data("aaaaa", "baaaaaaa");
        int[] expected = { 1, 2, 3 };

        doTest(input, expected);
    }

    @Test(timeout = 5000)
    public void testSampe4() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            builder.append("abcde");
        }

        String text = builder.toString();
        System.out.printf("text length: %d\n", text.length());

        HashSubstring.Data input= new HashSubstring.Data("abcd", builder.toString());
        HashSubstring s = new HashSubstring();
        List<Integer> result =  s.findOccurrences(input);

        assertEquals(100000, result.size());
    }

    private void doTest(HashSubstring.Data input, int[] expected) {
        HashSubstring s = new HashSubstring();
        List<Integer> result =  s.findOccurrences(input);
        Helper.printCollection("result", result);

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] == result.get(i)) continue;

            System.out.printf("assertion failed. i: %d, expected: %d, result: %d\n", i, expected[i], result.get(i));
        }
    }


}