package algo.week3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sejoonlim on 08/09/2017.
 */
public class ChangeTest {

    private final Change c = new Change();

    @Test
    public void test_2() {
        doTest(2, 2);
    }

    @Test
    public void test_28() {
        doTest(28, 6);
    }

    @Test
    public void test_1000() {
        doTest(1000, 100);
    }

    @Test
    public void test_999() {
        doTest(999, 104);
    }

    private void doTest(int m, int expected) {
        int result = c.getChange(m);
        System.out.println("result: " + result);
        assertEquals(expected, result);
    }

}