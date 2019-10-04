package advanced.week1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class StockCharts {

    private final Vertex[] vertices;

    private final Vertex[] left;

    private final Vertex[] right;

    public StockCharts(int numberOfStocks) {
        vertices = new Vertex[numberOfStocks * 2 + 2];
        left = new Vertex[numberOfStocks];
        right = new Vertex[numberOfStocks];

        initialize(numberOfStocks);
    }

    private void initialize(int numberOfStocks) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < numberOfStocks; i++) {
            left[i] = vertices[i + 1];
            right[i] = vertices[i + 1 + numberOfStocks];

            add(0, i + 1);
            add(i + 1 + numberOfStocks, vertices.length - 1);
        }
    }

    public void add(int v1, int v2) {
        Log.d("add v1: %d, v2: %d", v1, v2);
        vertices[v1].add(vertices[v2], 1);
    }

    public int minCharts(int[][] stockData) {
        setup(stockData);
        int flow = maxFlow();

        return left.length - flow;
    }

    private void setup(int[][] stockData) {
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right.length; j++) {
                if (!compare(stockData[i], stockData[j])) continue;

                add(i + 1, j + 1 + left.length);
            }
        }
    }

    private boolean compare(int[] stock1, int[] stock2) {
        for (int i = 0; i < stock1.length; ++i)
            if (stock1[i] >= stock2[i])
                return false;
        return true;
    }

    public int maxFlow() {
        int flow = 0;

        while (true) {
            Log.d("find path...");

            List<Edge> path = findPath(0, vertices.length - 1);
//            Helper.printCollection("path", path);
            Log.d("flow: %d", flow);
            if (path.isEmpty()) return flow;

            int minimum = 1;
            for (Edge e : path) {
                e.capacity -= minimum;
                e.paired.capacity += minimum;

                Log.d("edge: %s", e);
            }

            flow += minimum;
        }
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

    private static class Log {

        static int level = 0;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
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

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int numStocks = in.nextInt();
        int numPoints = in.nextInt();
        int[][] stockData = new int[numStocks][numPoints];
        for (int i = 0; i < numStocks; ++i)
            for (int j = 0; j < numPoints; ++j)
                stockData[i][j] = in.nextInt();

        StockCharts s = new StockCharts(numStocks);
        int result = s.minCharts(stockData);

        out.println(result);
        out.close();
    }

}
