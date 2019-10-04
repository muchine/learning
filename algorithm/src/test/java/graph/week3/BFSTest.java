package graph.week3;

import graph.week3.BFS;
import org.junit.Test;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BFSTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(3, 1));

        BFS b = prepare(4, edges);
        doTest(b, 2, 4, 2);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(5, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(1, 4));

        BFS b = prepare(5, edges);
        doTest(b, 3, 5, -1);
    }

    private void doTest(BFS a, int s, int t, int expected) {
        int distance = a.distance(s - 1, t - 1);
        Helper.printArray(a.distances);
        assertEquals(expected, distance);
    }

    private BFS prepare(int numberOfVertices, List<Edge> edges) {
        BFS a = new BFS(numberOfVertices);
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