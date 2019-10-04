package algo.week3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DifferentSummandsTest {

    private final DifferentSummands s = new DifferentSummands();

    @Test
    public void test_1() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);

        doTest(1, expected);
    }

    @Test
    public void test_2() {
        List<Integer> expected = new ArrayList<>();
        expected.add(2);

        doTest(2, expected);
    }

    @Test
    public void test_6() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        doTest(6, expected);
    }

    @Test
    public void test_8() {
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(5);

        doTest(8, expected);
    }

    @Test
    public void testMax() {
        List<Integer> result = s.optimalSummands(1000000000);
        System.out.println("result size: " + result.size());

        int sum = result.stream().mapToInt(n -> n).sum();
        System.out.println("sum: " + sum);

        assertTrue(result.size() > 0);
    }

    private void doTest(int n, List<Integer> expected) {
        List<Integer> result = s.optimalSummands(n);
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

}