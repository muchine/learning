package coding.codewars.level3;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class AnagramsTest {
    @Test
    public void testKnownInputs() {
        Anagrams anagram = new Anagrams();

        assertEquals("Position for 'A' incorrect", BigInteger.ONE, anagram.listPosition("A"));
        assertEquals("Position for 'ABAB' incorrect", BigInteger.valueOf(2), anagram.listPosition("ABAB"));
        assertEquals("Position for 'AAAB' incorrect", BigInteger.ONE, anagram.listPosition("AAAB"));
        assertEquals("Position for 'BAAA' incorrect", BigInteger.valueOf(4), anagram.listPosition("BAAA"));
        assertEquals("Position for 'QUESTION' incorrect", BigInteger.valueOf(24572), anagram.listPosition("QUESTION"));
    }

    @Test
    public void test1() {
        Anagrams anagram = new Anagrams();
        assertEquals("Position for 'BOOKKEEPER' incorrect", BigInteger.valueOf(10743), anagram.listPosition("BOOKKEEPER"));
    }

    @Test(timeout = 16000)
    public void test2() {
        Anagrams anagram = new Anagrams();
        BigInteger position = anagram.listPosition("ANTROPOLIGISTOFSCIENCES");
        System.out.println(position);
//        assertEquals(BigInteger.valueOf(10743), anagram.listPosition("BOOKKEEPER"));
    }
}
