package graph.week4;

import org.junit.Assert;
import org.junit.Test;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

public class ShortestPathsTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 10));
        edges.add(new Edge(2, 3, 5));
        edges.add(new Edge(1, 3, 100));
        edges.add(new Edge(3, 5, 7));
        edges.add(new Edge(5, 4, 10));
        edges.add(new Edge(4, 3, -18));
        edges.add(new Edge(6, 1, -1));

        String[] expected = { "0", "10", "-", "-", "-", "*" };

        ShortestPaths a = prepare(6, edges);
        doTest(a, 1, expected);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(4, 1, 2));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 1, -5));

        String[] expected = { "-", "-", "-", "0", "*" };

        ShortestPaths a = prepare(5, edges);
        doTest(a, 4, expected);
    }

    private void doTest(ShortestPaths a, int s, String[] expected) {
        ShortestPaths.Vertex[] result = a.run(s - 1);
        Helper.printArray(result);

        for (int i = 0; i < expected.length; i++) {
            String r = result[i].toString();
            String e = expected[i];

            if (e.equals(r)) continue;

            System.out.printf("assertion failed. i: %d, result: %s, expected: %s", i, r, e);
            Assert.fail();
        }
    }

    private ShortestPaths prepare(int numberOfVertices, List<Edge> edges) {
        ShortestPaths a = new ShortestPaths(numberOfVertices);
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