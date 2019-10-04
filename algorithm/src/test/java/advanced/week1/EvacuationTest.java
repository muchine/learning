package advanced.week1;

import advanced.week1.Evacuation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EvacuationTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(2, 5, 5));
        edges.add(new Edge(1, 3, 6));
        edges.add(new Edge(3, 4, 2));
        edges.add(new Edge(4, 5, 1));
        edges.add(new Edge(3, 2, 3));
        edges.add(new Edge(2, 4, 1));

        Evacuation a = prepare(5, edges);
        doTest(a, 5, 6);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 10000));
        edges.add(new Edge(1, 3, 10000));
        edges.add(new Edge(2, 3, 1));
        edges.add(new Edge(3, 4, 10000));
        edges.add(new Edge(2, 4, 10000));

        Evacuation a = prepare(4, edges);
        doTest(a, 4, 20000);
    }

    @Test
    public void testCase5() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 10000));

        Evacuation a = prepare(2, edges);
        doTest(a, 2, 10000);
    }

    private void doTest(Evacuation a, int numberOfVertices, int expected) {
        int result = a.maxFlow(0, numberOfVertices - 1);
        assertEquals(expected, result);
    }

    private Evacuation prepare(int numberOfVertices, List<Edge> edges) {
        Evacuation a = new Evacuation(numberOfVertices);
        for (Edge e : edges) {
            a.add(e.x - 1, e.y - 1, e.c);
        }

        return a;
    }

    class Edge {

        int x;

        int y;

        int c;

        Edge(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

    }

}