package graph.week4;

import graph.week4.Dijkstra;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DijkstraTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(4, 1, 2));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(1, 3, 5));

        Dijkstra d = prepare(4, edges);
        doTest(d, 1, 3, 3);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 4));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 2, 1));
        edges.add(new Edge(2, 4, 2));
        edges.add(new Edge(3, 5, 4));
        edges.add(new Edge(5, 4, 1));
        edges.add(new Edge(2, 5, 3));
        edges.add(new Edge(3, 4, 4));

        Dijkstra d = prepare(5, edges);
        doTest(d, 1, 5, 6);
        doTest(d, 1, 2, 3);
        doTest(d, 1, 3, 2);
        doTest(d, 1, 4, 5);
    }

    @Test
    public void testSample3() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 7));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 2));

        Dijkstra d = prepare(3, edges);
        doTest(d, 3, 2, -1);
    }

    private void doTest(Dijkstra a, int s, int t, int expected) {
        int distance = a.distance(s - 1, t - 1);
        assertEquals(expected, distance);
    }

    private Dijkstra prepare(int numberOfVertices, List<Edge> edges) {
        Dijkstra a = new Dijkstra(numberOfVertices);
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