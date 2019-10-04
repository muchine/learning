package string.week1;

import java.util.*;
import java.io.*;

public class SuffixTree {

    private final Node root = new Node(-1, -1);

    private char[] chars;

    // Build a suffix tree of the string text and return a list
    // with all of the labels of its edges (the corresponding 
    // substrings of the text) in any order.
    public List<String> computeSuffixTreeEdges(String text) {
        List<String> result = new ArrayList<String>();

        chars = text.toCharArray();

        for (int start = 0; start < chars.length; start++) {
            Log.d("----------------------------------------------");
            Log.d("start new iteration: i: %d", start);
            Log.d("----------------------------------------------");
            addToTree(new Part(start, chars.length - 1));
        }

        root.print(result);

        return result;
    }

    private void addToTree(Part part) {
        Log.d("adding part: %s", part);

        if (root.get(part.start) == null) {
            Node node = new Node(part);
            root.children.put(chars[part.start], node);
            Log.d("add to root.. key: %s, node: %s, part: %s", chars[part.start], node, part);
            return;
        }

        Node node = root;

        int cursor = part.start;
        while(cursor <= part.end) {
            Node next = node.get(cursor);
            if (next != null) {
                Log.d("next: %s", next);
                if (next.isContained(cursor, part.end)) {
                    Log.d("next is contained: %s", next);
                    node = next;
                    cursor += next.part.length();
                    Log.d("next cursor: %d, c: %s", cursor, chars[cursor]);
                    continue;
                } else {
                    Part newPart = new Part(cursor, part.end);
                    Log.d("add to existing node: %s, string: %s", next, newPart);
                    next.add(cursor, part.end);
                    break;
                }
            } else {
                Part newPart = new Part(cursor, part.end);
                Log.d("add a new node: %s, string: %s", node, newPart);
                node.children.put(chars[cursor], new Node(newPart));
                break;
            }
        }
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
    }

    class Node {

        private final Map<Character, Node> children = new HashMap<>();

        private final Part part;

        public Node(int start, int end) {
            this.part = new Part(start, end);
        }

        public Node(Part part) {
            this.part = new Part(part.start, part.end);
        }

        boolean isRoot() {
            return part.start == -1;
        }

        Node get(int position) {
            return children.get(chars[position]);
        }

        void add(int s, int e) {
            int i = 0;

            int start = part.start;
            int end = part.end;

            while (start + i <= end && s + i <= e) {
                char a = chars[s + i];
                char o = chars[part.start + i];

                if (a != o) break;
                i++;
            }

            Log.d("first diff char: %s, i: %d", chars[s + i], i);

            // relocate all children nodes
            Node node = new Node(part.start + i, part.end);
            for (Node n : children.values()) {
                Log.d("adding children... node: %s", n);
                node.children.put(chars[n.part.start], n);
            }

            children.clear();
            children.put(chars[part.start + i], node);
            Log.d("original node: %s", node);

            // put new node to the map
            node = new Node(s + i, e);
            children.put(chars[s + i], node);
            Log.d("new node: %s", node);

            part.end = part.start + i - 1;
            Log.d("parent: %s", this);
        }

        void print(List<String> out) {
            if (!isRoot()) out.add(toString());

            for (Node child : children.values()) {
                child.print(out);
            }
        }

        boolean isContained(int s, int e) {
            int i = 0;
            while(part.start + i <= part.end) {
                if (s + i > e || chars[part.start + i] != chars[s + i]) return false;
                i++;
            }

            return true;
        }

        @Override
        public String toString() {
            if (isRoot()) return "root";
            return part.toString();
        }
    }

    class Part {

        private int start;

        private int end;

        public Part(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int length() {
            return end - start + 1;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i = start; i <= end; i++) {
                builder.append(chars[i]);
            }

            return builder.toString();
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

    class FastScanner {
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
        new SuffixTree().run();
    }

}
