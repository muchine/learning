import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlanPartyTest {

    @Test
    public void testSample1() {
        int[] factors = { 1000 };

        List<Edge> edges = new ArrayList<>();

        doTest(factors, edges, 1000);
    }

    @Test
    public void testSample2() {
        int[] factors = { 1, 2 };

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));

        doTest(factors, edges, 2);
    }

    @Test
    public void testSample3() {
        int[] factors = { 1, 5, 3, 7, 5 };

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(5, 4));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(4, 2));
        edges.add(new Edge(1, 2));

        doTest(factors, edges, 11);
    }

    @Test
    public void testDebugging1() {
        int[] factors = { 1, 1, 1 };

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 3));
        edges.add(new Edge(2, 3));

        doTest(factors, edges, 2);
    }

    private void doTest(int[] factors, List<Edge> edges, int expected) {
        PlanParty pp = new PlanParty(factors.length);

        for (int i = 0; i < factors.length; i++) {
            pp.setWeight(i, factors[i]);
        }

        for (Edge e : edges) {
            pp.add(e.from - 1, e.to - 1);
        }

        int maxWeight = pp.maxWeightIndependentTreeSubset();
        assertEquals(expected, maxWeight);
    }

    class Edge {

        private int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

}