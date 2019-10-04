package advanced.week1;

import advanced.week1.MaxMatching;
import org.junit.Assert;
import org.junit.Test;
import util.Helper;

public class MaxMatchingTest {

    @Test
    public void testSample1() {
        boolean[][] edges = {
                { true, true, false, true },
                { false, true, false, false},
                { false, false, false, false}
        };

        MaxMatching m = prepare(3, 4, edges);
        doTest(m, new int[] { 1, 2, -1 });
    }

    @Test
    public void testSample2() {
        boolean[][] edges = {
                { true, true },
                { true, false }
        };

        MaxMatching m = prepare(2, 2, edges);
        doTest(m, new int[] { 2, 1 });
    }

    private void doTest(MaxMatching a, int[] expected) {
        int[] result = a.findMatching();

        Helper.printArray(result);

        for (int i = 0; i < expected.length; i++) {
            int e = expected[i];
            int r = result[i];

            if (e == r) continue;

            System.out.printf("Assertion failed. i: %d, e: %d, r: %d\n", i, e, r);
            Assert.fail();
        }
    }

    private MaxMatching prepare(int numberOfFlights, int numberOfCrews, boolean[][] edges) {
        MaxMatching a = new MaxMatching(numberOfFlights, numberOfCrews);

        for (int i = 0; i < numberOfFlights; ++i)
            for (int j = 0; j < numberOfCrews; ++j)
                if (edges[i][j]) a.add(i + 1, numberOfFlights + 1 + j);

        return a;
    }

    class Edge {

        int x;

        int y;

        Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}