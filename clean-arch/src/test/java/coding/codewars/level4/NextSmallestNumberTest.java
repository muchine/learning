package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NextSmallestNumberTest {

    @Test
    public void basicTests() {
        assertEquals(12, NextSmallestNumber.nextSmaller(21));
        assertEquals(790, NextSmallestNumber.nextSmaller(907));
        assertEquals(513, NextSmallestNumber.nextSmaller(531));
        assertEquals(-1, NextSmallestNumber.nextSmaller(1027));
        assertEquals(414, NextSmallestNumber.nextSmaller(441));
        assertEquals(123456789, NextSmallestNumber.nextSmaller(123456798));
    }

    @Test
    public void test1() {
        assertEquals(497704300, NextSmallestNumber.nextSmaller(497730004));
        assertEquals(1072, NextSmallestNumber.nextSmaller(1207));
    }
}
