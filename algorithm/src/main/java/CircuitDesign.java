import java.io.*;
import java.util.*;

public class CircuitDesign {

    private final Clause[] clauses;

    private final Graph graph;

    private final int[] result;

    boolean satisfied = false;

    public CircuitDesign(int n, int m) {
        clauses = new Clause[m];

        graph = new Graph(n);
        result = new int[n];
    }

    public void add(int i, int first, int second) {
        clauses[i] = new Clause(first, second);
    }

    class Clause {
        int first;
        int second;

        public Clause(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int[] run() {
        createImplicationGraph();

        graph.findSCC();
//        graph.print();

        this.satisfied = isSatisfiable();
        Log.d("satisfiable? %s", satisfied);

        assignVariables();

        return result;
    }

    private void createImplicationGraph() {
        for (Clause c : clauses) {
            graph.add(-c.first, c.second);
            graph.add(-c.second, c.first);
        }
    }

    private boolean isSatisfiable() {
        for (List<Vertex> vertices : graph.components.values()) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Vertex v : vertices) {
                if (map.containsKey(-v.id)) return false;
                map.put(v.id, v.id);
            }
        }

        return true;
    }

    private void assignVariables() {
        int numOfComponents = graph.components.keySet().size();

        for (int i = 0; i < numOfComponents; i++) {
            List<Vertex> vertices = graph.components.get(i);
            for (Vertex v : vertices) {
                int variable = v.id > 0 ? v.id : -v.id;
                if (result[variable - 1] == 0) result[variable - 1] = v.id;
            }
        }
    }

    class Graph {

        private final Map<Integer, Vertex> vertices = new HashMap<>();

        private final Map<Integer, Vertex> reverse = new HashMap<>();

        private final Vertex[] orders;

        private int postorder = 0;

        private int component = 0;

        private Map<Integer, List<Vertex>> components = new HashMap<>();

        Graph(int numOfVariables) {
            orders = new Vertex[numOfVariables * 2];
            initialize(numOfVariables);
        }

        public int findSCC() {
            for (Vertex v : reverse.values()) {
                if (v.visited) continue;
                explore(v, true);
            }

            component = 0;
            for (int i = orders.length - 1; i >= 0; i--) {
                int vertexId = orders[i].id;
                Vertex v = vertices.get(vertexId);

                if (v.visited) continue;

                explore(v, false);
                component++;
            }

            for (Vertex v : vertices.values()) {
                addComponent(v.component, v);
            }

            return component;
        }

        private void explore(Vertex v, boolean reverse) {
            v.visited = true;
            v.component = component;

            for (Vertex adjacent : v.out) {
                if (!adjacent.visited) explore(adjacent, reverse);
            }

            if (reverse) {
                orders[postorder] = v;
                postorder++;
            }
        }

        private void addComponent(int component, Vertex v) {
            List<Vertex> vertices = components.get(component);
            if (vertices == null) {
                vertices = new ArrayList<>();
                components.put(component, vertices);
            }

            vertices.add(v);
        }

        public void add(int v1, int v2) {
            vertices.get(v1).add(vertices.get(v2));
            reverse.get(v2).add(reverse.get(v1));
        }

        public void print() {
            for (Vertex v : vertices.values()) {
                for (Vertex c : v.out) {
                    Log.d("%d -> %d", v.id, c.id);
                }
            }

            Log.d("SCCs...");
            for (int key : components.keySet()) {
                List<Vertex> vertices = components.get(key);

                StringBuilder builder = new StringBuilder();
                for (Vertex v : vertices) {
                    if (builder.length() > 0) builder.append(", ");
                    builder.append(v.id);
                }

                Log.d("%d -> %s", key, builder.toString());
            }
        }

        private void initialize(int numOfVariables) {
            for (int i = 1; i <= numOfVariables; i++) {
                vertices.put(i, new Vertex(i));
                vertices.put(-i, new Vertex(-i));

                reverse.put(i, new Vertex(i));
                reverse.put(-i, new Vertex(-i));
            }
        }

    }

    class Vertex {

        int id;

        boolean visited;

        int component;

        List<Vertex> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v) {
            this.out.add(v);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "id=" + id +
                    '}';
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

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    InputReader reader = new InputReader(System.in);
                    OutputWriter writer = new OutputWriter(System.out);

                    int n = reader.nextInt();
                    int m = reader.nextInt();

                    CircuitDesign cd = new CircuitDesign(n, m);

                    for (int i = 0; i < m; ++i) {
                        cd.add(i, reader.nextInt(), reader.nextInt());
                    }

                    int[] result = cd.run();

                    if (cd.satisfied) {
                        writer.printf("SATISFIABLE\n");
                        for (int i = 1; i <= result.length; ++i) {
                            writer.printf("%d", result[i - 1]);

                            if (i < result.length) {
                                writer.printf(" ");
                            } else {
                                writer.printf("\n");
                            }
                        }
                    } else {
                        writer.printf("UNSATISFIABLE\n");
                    }

                    writer.writer.flush();
                } catch(Exception e) {}
            }
        }, "1", 1 << 26).start();
    }

}
