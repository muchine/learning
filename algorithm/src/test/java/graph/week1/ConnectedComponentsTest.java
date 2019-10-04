package graph.week1;

import graph.week1.ConnectedComponents;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConnectedComponentsTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(3, 2));

        ConnectedComponents cc = prepare(4, edges);
        doTest(cc, 2);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));

        ConnectedComponents cc = prepare(4, edges);
        doTest(cc, 3);
    }

    private ConnectedComponents prepare(int numberOfVertices, List<Edge> edges) {
        ConnectedComponents r = new ConnectedComponents(numberOfVertices);
        for (Edge e : edges) {
            r.add(e.x - 1, e.y - 1);
        }

        return r;
    }

    private void doTest(ConnectedComponents c, int expected) {
        assertEquals(expected, c.numberOfComponents());
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