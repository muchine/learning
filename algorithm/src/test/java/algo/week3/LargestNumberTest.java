package algo.week3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LargestNumberTest {

    private final LargestNumber ln = new LargestNumber();

    @Test
    public void testSimple1() {
        doTest(new String[] { "21", "2" }, "221");
    }

    @Test
    public void testSimple2() {
        doTest(new String[] { "3", "23" }, "323");
    }

    @Test
    public void testIntermediate1() {
        doTest(new String[] { "9", "4", "6", "1", "9" }, "99641");
    }

    @Test
    public void testIntermediate2() {
        doTest(new String[] { "23", "39", "92" }, "923923");
    }

    @Test
    public void test_98_998() {
        doTest(new String[] { "98", "998" }, "99898");
    }

    @Test
    public void test_99_998() {
        doTest(new String[] { "99", "998" }, "99998");
    }

    @Test
    public void test_96_907() {
        doTest(new String[] { "96", "907" }, "96907");
    }

    @Test
    public void test_20_202() {
        doTest(new String[] { "20", "202" }, "20220");
    }

    @Test
    public void test_85_858() {
        doTest(new String[] { "85", "858" }, "85885");
    }

    @Test
    public void test_10_100() {
        doTest(new String[] { "10", "100" }, "10100");
    }

    @Test
    public void test_10_101() {
        doTest(new String[] { "10", "101" }, "10110");
    }

    @Test
    public void testMax() {
        String[] numbers = new String[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = "1000";
        }

        String result = ln.largestNumber(numbers);
        System.out.println("result: " + result);
    }

    private void doTest(String[] numbers, String expected) {
        String result = ln.largestNumber(numbers);
        System.out.println("result: " + result);
        assertEquals(expected, result);
    }

}