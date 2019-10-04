package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NextBiggestNumberTest {

    @Test
    public void basicTests() {
        assertEquals(21, NextBiggestNumber.nextBiggerNumber(12));
        assertEquals(531, NextBiggestNumber.nextBiggerNumber(513));
        assertEquals(2071, NextBiggestNumber.nextBiggerNumber(2017));
        assertEquals(441, NextBiggestNumber.nextBiggerNumber(414));
        assertEquals(414, NextBiggestNumber.nextBiggerNumber(144));
    }

    @Test
    public void test1() {
        assertEquals(267891647, NextBiggestNumber.nextBiggerNumber(267891476));
        assertEquals(59884848483559L, NextBiggestNumber.nextBiggerNumber(59884848459853L));
    }

    @Test
    public void test2() {
        assertEquals(1290329090, NextBiggestNumber.nextBiggerNumber(1290320990L));
    }

}
