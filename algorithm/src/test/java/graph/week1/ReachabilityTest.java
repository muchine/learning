package graph.week1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReachabilityTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(3, 2));
        edges.add(new Edge(4, 3));
        edges.add(new Edge(1, 4));

        Reachability r = prepare(4, edges);
        doTest(r, 1, 4, 1);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(3, 2));

        Reachability r = prepare(4, edges);
        doTest(r, 1, 4, 0);
    }

    private Reachability prepare(int numberOfVertices, List<Edge> edges) {
        Reachability r = new Reachability(numberOfVertices);
        for (Edge e : edges) {
            r.add(e.x - 1, e.y - 1);
        }

        return r;
    }

    private void doTest(Reachability r, int x, int y, int expected) {
        assertEquals(expected, r.reach(x - 1, y - 1));
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