import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SchoolBus {

    private static final int INFINITY = Integer.MAX_VALUE;

    private static FastScanner in;

    private final Vertex[] vertices;

    private final Path[][] C;

    public SchoolBus(int numOfVertices) {
        this.vertices = new Vertex[numOfVertices];
        initialize();

        int size = (int) Math.pow(2, numOfVertices);
        C = new Path[size][numOfVertices];

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                C[i][j] = new Path(INFINITY);
            }
        }
    }

    public void add(int v1, int v2, int weight) {
        vertices[v1].add(vertices[v2], weight);
        vertices[v2].add(vertices[v1], weight);
    }

    private void initialize() {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    public Answer run() {
        int minWeight = INFINITY;
        List<Integer> path = new ArrayList<>();

        C[1][0] = new Path(1, 0, 0, null, null);

        for (int s = 2; s <= vertices.length; s++) {
            int begin = (int) Math.pow(2, s - 1);
            int end = (int) Math.pow(2, s);

//            Log.d("begin: %d, end: %d", begin, end);

            for (int k = begin; k < end; k++) {
                for (int i = 1; i < s; i++) {
                    for (int j = 0; j < s; j++) {
                        if (i == j) continue;

                        Vertex v = vertices[j];
                        Edge e = v.findVertex(i);

                        if (e == null) continue;

//                        Log.d("k: %d, i: %d, j: %d, weight: %d", k, i, j, e.weight);
//                        Log.d("k ^ (1 << j): %d", k ^ (1 << i));

                        Path last = C[k ^ (1 << i)][j];
                        int totalWeight = sum(last, e.weight);

                        if (totalWeight < C[k][i].totalWeight) {
                            C[k][i] = new Path(k, i, totalWeight, e, last);
//                            Log.d("last [%d, %d]: %s", last.k, last.i, last.e);
//                            Log.d("new [%d, %d]: %s", C[k][i].k, C[k][i].i, C[k][i].e);
                        }
                    }
                }
            }
        }

//        print();

        Path p = null;

        for (int i = 1; i < vertices.length; i++) {
            Vertex v = vertices[i];
            Edge e = v.findVertex(0);

            if (e == null) continue;

            Path last = C[C.length - 1][i];
            int totalWeight = sum(last, e.weight);
            if (totalWeight < minWeight) {
                minWeight = totalWeight;
                p = last;
            }
        }

        if (p == null) {
            return new Answer(-1, null);
        }

        while (p.e != null) {
            path.add(p.e.to.id + 1);
            Log.d("path: [%d, %d], e: %s", p.k, p.i, p.e.toString());
            p = p.parent;
        }

        path.add(1);
        Collections.reverse(path);
//        Helper.printCollection("path", path);

        return new Answer(minWeight, path);
    }

    private void print() {
        for (int i = 0; i < C.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < C[i].length; j++) {
                sb.append(C[i][j] + " ");
            }

            Log.d(sb.toString());
        }
    }

    private int sum(Path x, int y) {
        if (x.totalWeight == INFINITY) return INFINITY;
        return  x.totalWeight + y;
    }

    class Path {

        private int k, i;

        private int totalWeight;

        private Edge e;

        private Path parent;

        public Path(int k, int i, int totalWeight, Edge e, Path parent) {
            this(totalWeight);
            this.k = k;
            this.i = i;
            this.e = e;
            this.parent = parent;
        }

        public Path(int totalWeight) {
            this.totalWeight = totalWeight;
        }

        @Override
        public String toString() {
            return "Path{" + "totalWeight=" + totalWeight + '}';
        }
    }

    class Vertex {

        private final int id;

        private final Map<Integer, Edge> out = new HashMap<>();

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v, int weight) {
            out.put(v.id, new Edge(v, weight));
        }

        public Edge findVertex(int vertexId) {
            return out.get(vertexId);
        }
    }

    class Edge {

        private final Vertex to;

        private final int weight;

        public Edge(Vertex to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return " -> " + to.id;
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

    static class Answer {
        int weight;
        List<Integer> path;

        public Answer(int weight, List<Integer> path) {
            this.weight = weight;
            this.path = path;
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

    public static void main(String[] args) {
        in = new FastScanner();
        try {
            int n = in.nextInt();
            int m = in.nextInt();

            SchoolBus sb = new SchoolBus(n);

            for (int i = 0; i < m; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int weight = in.nextInt();
                sb.add(u, v, weight);
            }

            Answer answer = sb.run();

            System.out.println(answer.weight);
            if (answer.weight == -1)
                return;
            for (int x : answer.path)
                System.out.print(x + " ");
            System.out.println();
        } catch (IOException exception) {
            System.err.print("Error during reading: " + exception.toString());
        }
    }

}
