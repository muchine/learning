package algo.week2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sejoonlim on 07/09/2017.
 */
public class LCMTest {

    private final LCM l = new LCM();

    @Test
    public void test_A_1_B_8() {
        long lcm = l.calculateLCM(1, 8);
        assertTrue(lcm == 8);
    }

    @Test
    public void test_A_6_B_8() {
        long lcm = l.calculateLCM(6, 8);
        assertTrue(lcm == 24);
    }

    @Test
    public void test_A_28851538_B_1183019() {
        long lcm = l.calculateLCM(28851538, 1183019);
        assertTrue(lcm == 1933053046);
    }

    @Test
    public void test_A_28851538_B_Max() {
        long lcm = l.calculateLCM(28851538, 2000000000);
        System.out.println("lcm: " + lcm);
        assertTrue(lcm > 0);
    }

}