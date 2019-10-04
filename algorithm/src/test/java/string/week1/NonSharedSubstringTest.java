package string.week1;

import org.junit.Test;
import string.week1.NonSharedSubstring;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class NonSharedSubstringTest {

    @Test
    public void testSample1() {
        doTest("A", "T", "A");
    }

    @Test
    public void testSample2() {
        doTest("AAAAAAAAAAAAAAAAAAAA", "TTTTTTTTTTTTTTTTTTTT", "A");
    }

    @Test
    public void testSample3() {
        doTest("CCAAGCTGCTAGAGG", "CATGCTGGGCTGGCT", "AA");
    }

    @Test
    public void testSample4() {
        doTest("ATGCGATGACCTGACTGA", "CTCAACGTATTGGCCAGA", "ACC");
    }

    @Test
    public void testDebugging1() {
        doTest("ATAAATG","AAAAAAA", "T");
    }

    @Test
    public void testCase9() {
        doTest("AAAAA", "AAAAC", "AAAAA");
    }

    @Test(timeout = 3000)
    public void testMax() {
        char[] chars = { 'A', 'C', 'G', 'T' };
        Random r = new Random();

        StringBuilder p = new StringBuilder();
        StringBuilder q = new StringBuilder();
        for (int i = 0; i < 1999; i++) {
            p.append(chars[r.nextInt(4)]);
            q.append(chars[r.nextInt(4)]);
        }

        NonSharedSubstring nss = new NonSharedSubstring();
        String result = nss.solve(p.toString(), q.toString());
        System.out.println(result);
    }

    private void doTest(String p, String q, String expected) {
        NonSharedSubstring nss = new NonSharedSubstring();
        String result = nss.solve(p, q);
        assertEquals(expected, result);
    }

}