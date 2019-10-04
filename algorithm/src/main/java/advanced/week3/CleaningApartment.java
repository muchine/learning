package advanced.week3;

import java.io.*;
import java.util.*;

public class CleaningApartment {

    private final int numOfVertices;

    private final Set<String> edges = new HashSet<>();

    public CleaningApartment(int numOfVertices, int numOfEdges) {
        this.numOfVertices = numOfVertices;
    }

    public void addEdge(int i, int from, int to) {
        edges.add(hash(from, to));
        Log.d("add edge.. size: %d", edges.size());
    }

    public List<Clause> toCNF() {
        List<Clause> cnf = new ArrayList<>();

        // Each node j must appear in the path
        for (int j = 1; j <= numOfVertices; j++) {
            int[] variables = new int[numOfVertices];
            for (int i = 1; i <= numOfVertices; i++) {
                variables[i - 1] = index(i, j);
            }
            cnf.add(new Clause(variables));
        }

        // No node j appears twice in the path
        for (int j = 1; j <= numOfVertices; j++) {
            for (int i = 1; i <= numOfVertices - 1; i++) {
                for (int k = i + 1; k <= numOfVertices; k++) {
                    cnf.add(new Clause(-index(i, j), -index(k, j)));
                }
            }
        }

        // Every position i on the path must be occupied
        for (int i = 1; i <= numOfVertices; i++) {
            int[] variables = new int[numOfVertices];
            for (int j = 1; j <= numOfVertices; j++) {
                variables[j - 1] = index(i, j);
            }
            cnf.add(new Clause(variables));
        }

        // No two nodes j and k occupy the same position in the path
        for (int i = 1; i <= numOfVertices; i++) {
            for (int j = 1; j <= numOfVertices - 1; j++) {
                for (int k = j + 1; k <= numOfVertices; k++) {
                    cnf.add(new Clause(-index(i, j), -index(i, k)));
                }
            }
        }

        // Nonadjacent nodes i and j cannot be adjacent in the path
        for (int i = 1; i <= numOfVertices; i++) {
            for (int j = 1; j <= numOfVertices; j++) {
                if (i == j || isAdjacent(i, j)) {
                    Log.d("adjacent nodes: %d, %d", i, j);
                    continue;
                }

                for (int k = 1; k <= numOfVertices - 1; k++) {
                    cnf.add(new Clause(-index(k, i), -index(k + 1, j)));
                }
            }
        }

        return cnf;
    }

    private int index(int i, int j) {
        return (i - 1) * numOfVertices + j;
    }

    private String hash(int u, int v) {
        int min = Math.min(u, v);
        int max = Math.max(u, v);

        return min + ":" + max;
    }

    private boolean isAdjacent(int u, int v) {
        return edges.contains(hash(u, v));
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

    class Clause {

        private final List<Integer> variables = new ArrayList<>();

        Clause(int... args) {
            for (int a : args) variables.add(a);
        }

        void print(OutputWriter writer) {
            StringBuilder builder = new StringBuilder();
            for (int v : variables) {
                builder.append(v + " ");
            }

            builder.append("0\n");
            writer.printf(builder.toString());
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

    private static class Log {

        static int level = 0;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }


    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);

        int n = reader.nextInt();
        int m = reader.nextInt();

        CleaningApartment ca = new CleaningApartment(n, m);

        for (int i = 0; i < m; ++i) {
            int from = reader.nextInt();
            int to = reader.nextInt();
            ca.addEdge(i, from, to);
        }

        List<Clause> cnf = ca.toCNF();
        writer.printf(cnf.size() + " " + n * n + "\n");
        for (Clause c : cnf) {
            c.print(writer);
        }

        writer.writer.flush();
    }

}
