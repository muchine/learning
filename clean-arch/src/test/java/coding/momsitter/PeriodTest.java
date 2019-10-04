package coding.momsitter;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PeriodTest {

    @Test
    public void testCase1() {
        Period period = new Period(5, 10);
        Period other = new Period(3, 7);
        assertTrue(period.isOverlapped(other));
    }

    @Test
    public void testCase2() {
        Period period = new Period(5, 10);
        Period other = new Period(7, 12);
        assertTrue(period.isOverlapped(other));
    }

    @Test
    public void testCase3() {
        Period period = new Period(5, 10);
        Period other = new Period(2, 12);
        assertTrue(period.isOverlapped(other));
    }

    @Test
    public void testCase4() {
        Period period = new Period(5, 10);
        Period other = new Period(7, 8);
        assertTrue(period.isOverlapped(other));
    }

    @Test
    public void testException1() {
        Period period = new Period(5, 10);
        Period other = new Period(1, 2);
        assertFalse(period.isOverlapped(other));
    }

    @Test
    public void testException2() {
        Period period = new Period(5, 10);
        Period other = new Period(11, 12);
        assertFalse(period.isOverlapped(other));
    }

}
