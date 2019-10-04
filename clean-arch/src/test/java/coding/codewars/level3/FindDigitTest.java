package coding.codewars.level3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindDigitTest {
    
    @Test
    public void basicTest() {

        for (int i = 0; i < 30; i++) // Testing for 0 to 29 digits
            assertEquals("272619325597593231536305887388".charAt(i) - '0', FindDigit.findDigit(i));

        assertEquals(1, FindDigit.findDigit(1000));
        assertEquals(4, FindDigit.findDigit(10000));
        assertEquals(4, FindDigit.findDigit(100000));
        assertEquals(5, FindDigit.findDigit(1000000));
        assertEquals(3, FindDigit.findDigit(10000000));
        assertEquals(9, FindDigit.findDigit(100000000));
        assertEquals(1, FindDigit.findDigit(1000000000));
    }

    @Test
    public void test1() {
        assertEquals(2, FindDigit.findDigit(58));
        assertEquals(3, FindDigit.findDigit(619515055));
        assertEquals(1, FindDigit.findDigit(1501574994));
        assertEquals(0, FindDigit.findDigit(1514496850));
        assertEquals(5, FindDigit.findDigit(1736180996));
    }

    @Test
    public void test2() {
        assertEquals(3, FindDigit.findDigit(1882250674));
        assertEquals(5, FindDigit.findDigit(1941900172));
        assertEquals(1, FindDigit.findDigit(2014332908));
    }

    @Test
    public void test3() {
        assertEquals(8, FindDigit.findDigit(2083978501));
    }
}
