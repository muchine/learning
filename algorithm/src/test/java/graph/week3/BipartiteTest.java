package graph.week3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BipartiteTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(3, 1));

        Bipartite b = prepare(4, edges);
        doTest(b, 0);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(5, 2));
        edges.add(new Edge(4, 2));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(1, 4));

        Bipartite b = prepare(5, edges);
        doTest(b, 1);
    }

    private void doTest(Bipartite a, int expected) {
        assertEquals(expected, a.bipartite());
    }

    private Bipartite prepare(int numberOfVertices, List<Edge> edges) {
        Bipartite a = new Bipartite(numberOfVertices);
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