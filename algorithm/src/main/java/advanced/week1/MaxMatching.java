package advanced.week1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MaxMatching {

    private final Vertex[] vertices;

    private final Vertex[] flights;

    private final Vertex[] crews;

    public MaxMatching(int numberOfFlights, int numberOfCrews) {
        this.vertices = new Vertex[numberOfFlights + numberOfCrews + 2];
        this.flights = new Vertex[numberOfFlights];
        this.crews = new Vertex[numberOfCrews];

        initialize(numberOfFlights, numberOfCrews);
    }

    private void initialize(int numberOfFlights, int numberOfCrews) {
        int numberOfVertices = numberOfFlights + numberOfCrews + 2;

        this.vertices[0] = new Vertex(0);
        this.vertices[numberOfVertices - 1] = new Vertex(numberOfVertices - 1);

        int index = 1;
        for (int i = 0; i < numberOfFlights; i++) {
            this.vertices[index] = new Vertex(index);
            this.flights[i] = this.vertices[index];
            add(0, index);

            index++;
        }

        for (int i = 0; i < numberOfCrews; i++) {
            this.vertices[index] = new Vertex(index);
            this.crews[i] = this.vertices[index];
            add(index, numberOfVertices - 1);

            index++;
        }
    }

    public void add(int v1, int v2) {
        Log.d("add v1: %d, v2: %d", v1, v2);
        vertices[v1].add(vertices[v2], 1);
    }

    public int[] findMatching() {
        maxFlow();

        int[] matching = new int[flights.length];

        for (int i = 0; i < matching.length; i++) {
            Vertex v = flights[i];
            matching[i] = -1;

            for (Edge e : v.out) {
                if (e.capacity > 0 || !e.forward) continue;

                matching[i] = e.to.id - flights.length;
                break;
            }
        }

        return matching;
    }

    public void maxFlow() {
        while (true) {
            Log.d("find path...");

            List<Edge> path = findPath(0, vertices.length - 1);
//            Helper.printCollection("path", path);
            if (path.isEmpty()) return;

            int minimum = minimumCapacity(path);
            Log.i("minimun capacity: %d", minimum);

            for (Edge e : path) {
                e.capacity -= minimum;
                e.paired.capacity += minimum;

                Log.d("edge: %s", e);
            }
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


    private void writeResponse(int[] matching, PrintWriter out) {
        for (int i = 0; i < matching.length; ++i) {
            if (i > 0) {
                out.print(" ");
            }
            if (matching[i] == -1) {
                out.print("-1");
            } else {
                out.print(matching[i]);
            }
        }
        out.println();
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
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int numLeft = in.nextInt();
        int numRight = in.nextInt();

        MaxMatching m = new MaxMatching(numLeft, numRight);

        for (int i = 0; i < numLeft; ++i)
            for (int j = 0; j < numRight; ++j)
                if (in.nextInt() == 1) {
                    m.add(i + 1, numLeft + 1 + j);
                }

        int[] matching = m.findMatching();

        m.writeResponse(matching, out);
        out.close();
    }

}
