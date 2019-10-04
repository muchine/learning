import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SchoolBusTest {

    @Test
    public void testSample1() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 20));
        edges.add(new Edge(1, 3, 42));
        edges.add(new Edge(1, 4, 35));
        edges.add(new Edge(2, 3, 30));
        edges.add(new Edge(2, 4, 34));
        edges.add(new Edge(3, 4, 12));

        int[] path = { 1, 4, 3, 2 };

        doTest(4, edges, 97, path);
    }

    @Test
    public void testSample2() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(2, 3, 4));
        edges.add(new Edge(3, 4, 5));
        edges.add(new Edge(4, 2, 1));

        doTest(4, edges, -1, null);
    }


    @Test
    public void testSample3() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 10));

        int[] path = { 1, 2 };

        doTest(2, edges, 20, path);
    }

    @Test
    public void testSample4() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 10));
        edges.add(new Edge(1, 3, 20));
        edges.add(new Edge(2, 3, 10));

        int[] path = { 1, 3, 2 };

        doTest(3, edges, 40, path);
    }

    private void doTest(int numOfVertices, List<Edge> edges, int minWeight, int[] path) {
        SchoolBus sb = new SchoolBus(numOfVertices);

        for (Edge e : edges) {
            sb.add(e.from -1, e.to - 1, e.weight);
        }

        SchoolBus.Answer answer = sb.run();
        assertEquals(minWeight, answer.weight);

        if (minWeight == -1) return;

        for (int i = 0; i < path.length; i++) {
            if (path[i] == answer.path.get(i)) continue;

            System.out.printf("Assertion failed. i: %d, e: %d, r: %d\n", i, path[i], answer.path.get(i));
            Assert.fail();
        }
    }

    class Edge {

        private final int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}