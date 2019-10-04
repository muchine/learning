package advanced.week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class GSMNetwork {

    private static final int COLOR_COUNT = 3;

    private final int numOfVertices;

    private final Edge[] edges;

    public GSMNetwork(int numOfVertices, int numOfEdges) {
        this.numOfVertices = numOfVertices;
        this.edges = new Edge[numOfEdges];
    }

    public void addEdge(int i, int from, int to) {
        edges[i] = new Edge(from, to);
    }

    public List<Clause> toCNF() {
        List<Clause> cnf = new ArrayList<>();

        // convert vertices to CNF
        for (int i = 1; i <= numOfVertices; i++) {
            int c1 = (i - 1) * COLOR_COUNT + 1;
            int c2 = (i - 1) * COLOR_COUNT + 2;
            int c3 = (i - 1) * COLOR_COUNT + 3;

            cnf.add(new Clause(c1, c2, c3));
            cnf.add(new Clause(-c1, -c2));
            cnf.add(new Clause(-c1, -c3));
            cnf.add(new Clause(-c2, -c3));
        }

        // convert edges to CNF
        for (Edge e : edges) {
            cnf.addAll(e.toCNF());
        }

        return cnf;
    }

    class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        List<Clause> toCNF() {
            List<Clause> cnf = new ArrayList<>();

            int begin = Math.min(from, to);
            int end = Math.max(from, to);

            int beginVariable = (begin - 1) * COLOR_COUNT + 1;
            int endVariable = (end - 1) * COLOR_COUNT + 1;

            for (int j = 0; j < COLOR_COUNT; j++) {
                Clause c = new Clause(-(beginVariable + j), -(endVariable + j));
                cnf.add(c);
            }

            return cnf;
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
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);

        int n = reader.nextInt();
        int m = reader.nextInt();

        GSMNetwork g = new GSMNetwork(n, m);

        for (int i = 0; i < m; ++i) {
            int from = reader.nextInt();
            int to = reader.nextInt();
            g.addEdge(i, from, to);
        }

        List<Clause> cnf = g.toCNF();
        writer.printf(cnf.size() + " " + n * 3 + "\n");
        for (Clause c : cnf) {
            c.print(writer);
        }

        writer.writer.flush();
    }

}
