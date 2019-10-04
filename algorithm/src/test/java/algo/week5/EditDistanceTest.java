package algo.week5;

import algo.week5.EditDistance;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditDistanceTest {

    @Test
    public void testSample1() {
        doTest("ab", "ab", 0);
    }

    @Test
    public void testSample2() {
        doTest("short", "ports", 3);
    }

    @Test
    public void testSample3() {
        doTest("editing", "distance", 5);
    }

    @Test(timeout = 1500)
    public void testMax() {
        String s = "alskchqlqkalskchqlqkalskchqlqkalskchqlqkalskchqlqkalskchqlqkalskchqlqkalskchqlqkalskchqlqkalskchqlqk";
        String t = "elcktigkfjelcktigkfjelcktigkfjelcktigkfjelcktigkfjelcktigkfjelcktigkfjelcktigkfjelcktigkfjelcktigkfj";

        EditDistance ed = new EditDistance(s, t);
        int result = ed.calculate();
        System.out.println(result);
    }

    private void doTest(String s, String t, int expected) {
        EditDistance ed = new EditDistance(s, t);
        int result = ed.calculate();
        assertEquals(expected, result);
    }

}