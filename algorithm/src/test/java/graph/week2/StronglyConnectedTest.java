package graph.week2;

import graph.week2.StronglyConnected;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StronglyConnectedTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(3, 1));

        StronglyConnected a = prepare(4, edges);
        doTest(a, 2);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(2, 1));
        edges.add(new Edge(3, 2));
        edges.add(new Edge(3, 1));
        edges.add(new Edge(4, 3));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(5, 2));
        edges.add(new Edge(5, 3));

        StronglyConnected a = prepare(5, edges);
        doTest(a, 5);
    }

    private void doTest(StronglyConnected a, int expected) {
        assertEquals(expected, a.run());
    }

    private StronglyConnected prepare(int numberOfVertices, List<Edge> edges) {
        StronglyConnected a = new StronglyConnected(numberOfVertices);
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