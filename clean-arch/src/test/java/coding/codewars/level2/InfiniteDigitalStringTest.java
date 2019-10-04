package coding.codewars.level2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InfiniteDigitalStringTest {

    private static Object[][] DATA = new Object[][] {new Object[] {"456",        3L,          "...3456..."},
            new Object[] {"454",        79L,         "...444546..."},
            new Object[] {"455",        98L,         "...545556..."},
            new Object[] {"910",        8L,          "...7891011..."},
            new Object[] {"9100",       188L,        "...9899100..."},
            new Object[] {"99100",      187L,        "...9899100..."},
            new Object[] {"00101",      190L,        "...9899100..."},
            new Object[] {"001",        190L,        "...9899100..."},
            new Object[] {"00",         190L,        "...9899100..."},
            new Object[] {"123456789",  0L,          ""},
            new Object[] {"1234567891", 0L,          ""},
            new Object[] {"123456798",  1000000071L, ""},
            new Object[] {"10",         9L,          ""},
            new Object[] {"53635",      13034L,      ""},
            new Object[] {"040",        1091L,       ""},
            new Object[] {"11",         11L,         ""},
            new Object[] {"99",         168L,        ""},
            new Object[] {"667",        122L,        ""},
            new Object[] {"0404",       15050L,      ""},
            new Object[] {"949225100",  382689688L,  ""},
            new Object[] {"58257860625",24674951477L,""},
            new Object[] {"3999589058124"  , 6957586376885L   , ""},
            new Object[] {"555899959741198", 1686722738828503L, ""},
            new Object[] {"01",         10L,         ""},
            new Object[] {"091",        170L,        ""},
            new Object[] {"0910",       2927L,       ""},
            new Object[] {"0991",       2617L,       ""},
            new Object[] {"09910",      2617L,       ""},
            new Object[] {"09991",      35286L,      ""}};

    @Test
    public void sampleTests() {
        for (Object[] t: DATA) {
            String s      = (String) t[0],
                    msg    = (String) t[2];
            long expected = (long)   t[1];
            assertEquals(msg, expected, InfiniteDigitalString.findPosition(s));
        }
        assertNotEquals(-1L, InfiniteDigitalString.findPosition("040"));
    }

    @Test
    public void test1() {
        doTest(9, "1011");
        doTest(192, "10110");
        doTest(192, "101102");
        doTest(192, "1011021");
        doTest(192, "10110210");
        doTest(192, "101102103");
        doTest(192, "1011021031");
        doTest(495507, "101103");
    }

    @Test
    public void test2() {
        doTest(3, "456");
        doTest(79, "454");
        doTest(98, "455");
        doTest(8, "910");
        doTest(188, "9100");
        doTest(187, "99100");
        doTest(190, "00101");
        doTest(190, "001");
        doTest(187, "991");
    }

    @Test
    public void test3() {
        doTest(0, "123456789");
        doTest(0, "12345678910");
        doTest(1000000071, "123456798");
    }

    @Test
    public void test4() {
        doTest(3812, "0123");
    }

    private void doTest(long expected, String s) {
        assertEquals(expected, new InfiniteDigitalString(s).findPosition());
    }

}
