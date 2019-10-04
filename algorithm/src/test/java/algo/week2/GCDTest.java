package algo.week2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sejoonlim on 07/09/2017.
 */
public class GCDTest {

    private GCD g = new GCD();

    @Test
    public void test_A_18_B_35() {
        int gcd = g.calculate(18, 35);
        assertTrue(gcd == 1);
    }

    @Test(timeout = 1000)
    public void test_A_28851538_B_1183019() {
        int gcd = g.calculate(28851538, 1183019);
        assertTrue(gcd == 17657);
    }

    @Test
    public void test_A_1_B_MAX() {
        int gcd = g.calculate(1, 2000000000);
        assertTrue(gcd == 1);
    }

}