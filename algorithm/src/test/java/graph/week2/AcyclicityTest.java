package graph.week2;

import graph.week2.Acyclicity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AcyclicityTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(3, 1));

        Acyclicity a = prepare(4, edges);
        doTest(a, 1);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(1, 4));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(3, 5));

        Acyclicity a = prepare(5, edges);
        doTest(a, 0);
    }

    private void doTest(Acyclicity a, int expected) {
        assertEquals(expected, a.run());
    }

    private Acyclicity prepare(int numberOfVertices, List<Edge> edges) {
        Acyclicity a = new Acyclicity(numberOfVertices);
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