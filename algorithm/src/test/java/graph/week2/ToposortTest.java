package graph.week2;

import org.junit.Assert;
import org.junit.Test;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

public class ToposortTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(3, 1));

        int[] expected = { 4, 3, 1, 2 };

        Toposort a = prepare(4, edges);
        doTest(a, expected);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(3, 1));

        int[] expected = { 4, 3, 2, 1 };

        Toposort a = prepare(4, edges);
        doTest(a, expected);
    }

    @Test
    public void testSample3() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(2, 1));
        edges.add(new Edge(3, 2));
        edges.add(new Edge(3, 1));
        edges.add(new Edge(4, 3));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(5, 2));
        edges.add(new Edge(5, 3));

        int[] expected = { 5, 4, 3, 2, 1 };

        Toposort a = prepare(5, edges);
        doTest(a, expected);
    }

    private void doTest(Toposort a, int[] expected) {
        List<Integer> order = a.run();
        Helper.printCollection("order", order);

        for (int i = 0; i < expected.length; i++) {
            int o = order.get(i);
            int e = expected[i];

            if (o == e) continue;

            System.out.printf("assertion failed. i: %d, result: %d, expected: %d\n", i, o, e);
            Assert.fail();
        }
    }

    private Toposort prepare(int numberOfVertices, List<Edge> edges) {
        Toposort a = new Toposort(numberOfVertices);
        for (Edge e : edges) {
            a.add(e.x - 1, e.y - 1);
        }

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