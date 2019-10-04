package graph.week4;

import graph.week4.NegativeCycle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NegativeCycleTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, -5));
        edges.add(new Edge(4, 1, 2));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 1, 1));

        NegativeCycle c = prepare(4, edges);
        doTest(c, 1);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, -2));
        edges.add(new Edge(4, 1, 2));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 1, 1));

        NegativeCycle c = prepare(4, edges);
        doTest(c, 0);
    }

    @Test
    public void testSample3() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(3, 4, -5));
        edges.add(new Edge(4, 5, 2));
        edges.add(new Edge(5, 3, 2));

        NegativeCycle c = prepare(5, edges);
        doTest(c, 1);
    }

    @Test
    public void testDebugging1() {
        List<Edge> edges = new ArrayList<>();

        NegativeCycle c = prepare(1000, edges);
        doTest(c, 0);
    }

    @Test
    public void testDebugging2() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= 999; i++) {
            edges.add(new Edge(i, i + 1, 1));
        }

        edges.add(new Edge(1000, 1, -5000));

        NegativeCycle c = prepare(1000, edges);
        doTest(c, 1);
    }

    @Test
    public void testDebugging3() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, -10));
        edges.add(new Edge(1, 3, 3));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 2, 8));
        edges.add(new Edge(2, 4, 5));
        edges.add(new Edge(3, 4, -5));
        edges.add(new Edge(3, 5, 3));
        edges.add(new Edge(5, 4, 1));
        edges.add(new Edge(5, 2, 3));
        edges.add(new Edge(5, 6, 2));
        edges.add(new Edge(4, 6, 0));

        NegativeCycle c = prepare(6, edges);
        doTest(c, 0);
    }

    @Test
    public void testDebugging4() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 3, 1));
        edges.add(new Edge(1, 5, 1));
        edges.add(new Edge(5, 3, 1));
        edges.add(new Edge(4, 1, 1));
        edges.add(new Edge(3, 4, 1));
        edges.add(new Edge(4, 6, 1));
        edges.add(new Edge(3, 2, -5));
        edges.add(new Edge(2, 6, 1));
        edges.add(new Edge(6, 3, 1));

        NegativeCycle c = prepare(6, edges);
        doTest(c, 1);
    }

    private void doTest(NegativeCycle a, int expected) {
        assertEquals(expected, a.negativeCycle());
    }

    private NegativeCycle prepare(int numberOfVertices, List<Edge> edges) {
        NegativeCycle a = new NegativeCycle(numberOfVertices);
        for (Edge e : edges) {
            a.add(e.x - 1, e.y - 1, e.weight);
        }

        return a;
    }

    class Edge {

        int x;

        int y;

        int weight;

        Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

    }

}