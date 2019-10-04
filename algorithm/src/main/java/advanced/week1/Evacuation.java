package advanced.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Evacuation {

    private final Vertex[] vertices;

    public Evacuation(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public int maxFlow(int from, int to) {
        int flow = 0;

        while (true) {
            Log.d("find path...");

            List<Edge> path = findPath(from, to);
//            Helper.printCollection("path", path);
            if (path.isEmpty()) return flow;

            int minimum = minimumCapacity(path);
            Log.i("minimun capacity: %d", minimum);

            for (Edge e : path) {
                e.capacity -= minimum;
                e.paired.capacity += minimum;

                Log.d("edge: %s", e);
            }

            flow += minimum;
        }
    }

    private int minimumCapacity(List<Edge> edges) {
        int min = Integer.MAX_VALUE;

        for (Edge e : edges) {
            if (min > e.capacity) min = e.capacity;
        }

        return min;
    }

    private List<Edge> findPath(int from, int to) {
        for (Vertex v : vertices) v.discovered = false;

        List<Edge> queue = new ArrayList<>();

        Vertex source = vertices[from];
        source.discovered = true;

        for (Edge e : source.out) {
            if (e.capacity == 0 || !e.forward) continue;

            e.prev = null;
            queue.add(e);
        }

        Edge destination = null;
        while (!queue.isEmpty()) {
            Edge p = queue.remove(0);
            Log.d("inspecting edge: %s", p);

            if (p.to.id == to) {
                destination = p;
                break;
            }

            for (Edge e : p.to.out) {
                if (e.capacity == 0 || e.to.discovered) continue;

                e.prev = p;
                e.to.discovered = true;

                queue.add(e);
            }
        }

        Edge p = destination;
        List<Edge> path = new ArrayList<>();

        while (p != null) {
            path.add(p);
            p = p.prev;
        }

        Collections.reverse(path);
        return path;
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    public void add(int v1, int v2, int capacity) {
        vertices[v1].add(vertices[v2], capacity);
    }

    class Vertex {

        int id;

        boolean discovered;

        List<Edge> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v, int capacity) {
            Edge forward = new Edge(this, v, capacity, true);
            this.out.add(forward);

            Edge backward = new Edge(v, this, 0, false);
            v.out.add(backward);

            forward.pair(backward);
        }

    }

    private class Edge {

        final Vertex from;

        final Vertex to;

        final boolean forward;

        int capacity;

        int flow;

        Edge prev;

        Edge paired;

        Edge(Vertex from, Vertex to, int capacity, boolean forward) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.forward = forward;
        }

        void pair(Edge e) {
            this.paired = e;
            e.paired = this;
        }

        @Override
        public String toString() {
            return "Edge{" +"from=" + from.id + ", to=" + to.id + ", capacity=" + capacity + '}';
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private static class Log {

        static int level = 0;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();

        int vertex_count = in.nextInt();
        int edge_count = in.nextInt();

        Evacuation e = new Evacuation(vertex_count);

        for (int i = 0; i < edge_count; ++i) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, capacity = in.nextInt();
            e.add(from, to, capacity);
        }

        System.out.println(e.maxFlow(0, vertex_count - 1));
    }

}
