package string.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie {

    private final Node root = new Node(0);

    private int nodeIndex = 1;

    public Trie(String[] patterns) {
        for (String pattern : patterns) {
            addTextToTrie(pattern);
        }
    }

    private void addTextToTrie(String pattern) {
        Node node = root;
        for (char c : pattern.toCharArray()) {
            Node next = node.get(c);
            if (next == null) next = node.add(c, new Node(nodeIndex++));

            node = next;
        }
    }

    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<>();
        root.visit(edges);

        return edges;
    }

    public void print() {
        for (Edge e : edges()) {
            e.print();
        }
    }

    class Node {

        private final Map<Character, Node> nodes = new HashMap<>();

        private final int index;

        public Node(int index) {
            this.index = index;
        }

        Node get(char c) {
            return nodes.get(c);
        }

        Node add(char c, Node node) {
            nodes.put(c, node);
            return node;
        }

        void visit(List<Edge> edges) {
            for (Map.Entry<Character, Node> entry : nodes.entrySet()) {
                Node child = entry.getValue();
                Edge e = new Edge(index, child.index, entry.getKey());
                edges.add(e);

                child.visit(edges);
            }
        }

    }

    public static class Edge {

        int source;

        int target;

        char c;

        public Edge(int source, int target, char c) {
            this.source = source;
            this.target = target;
            this.c = c;
        }

        void print() {
            System.out.println(source + "->" + target + ":" + c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (source != edge.source) return false;
            if (target != edge.target) return false;
            return c == edge.c;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "source=" + source +
                    ", target=" + target +
                    ", c=" + c +
                    '}';
        }
    }

    private static class Log {

        static int level = 5;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }

        Trie t = new Trie(patterns);
        t.print();
    }

}
