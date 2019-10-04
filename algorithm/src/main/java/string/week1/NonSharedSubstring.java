package string.week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class NonSharedSubstring implements Runnable {

    private char[] pChars;
    private char[] qChars;

    private Node pRoot;
    private Node qRoot;

    private Map<String, Pointer> pQueue = new HashMap<>();
    private Map<String, Pointer> qQueue = new HashMap<>();

    public String solve(String p, String q) {
//        p = p + "$";
//        q = q + "$";

        pChars = p.toCharArray();
        qChars = q.toCharArray();

        pRoot = new Node(pChars, -1, -1);
        qRoot = new Node(qChars, -1, -1);

        buildTree(pChars, pRoot, p);
        buildTree(qChars, qRoot, q);

        // do bread-first search
        for (Node n : pRoot.children.values()) {
            Pointer pt = new Pointer(n.firstChar() + "", n);
            pQueue.put(pt.s, pt);
        }

        for (Node n : qRoot.children.values()) {
            Pointer pt = new Pointer(n.firstChar() + "", n);
            qQueue.put(pt.s, pt);
        }

        while (!pQueue.isEmpty()) {
            String u = uniqueString();
            if (u != null) return u;

            pQueue = doBTS(pQueue);
            qQueue = doBTS(qQueue);
        }

        return null;
    }

    private String uniqueString() {
        for (String key : pQueue.keySet()) {
            if (!qQueue.containsKey(key)) return key;
        }

        return null;
    }

    private Map<String, Pointer> doBTS(Map<String, Pointer> queue) {
        Map<String, Pointer> temp = new HashMap<>();

        for (Map.Entry<String, Pointer> entry : queue.entrySet()) {
            String s = entry.getKey();
            Pointer pt = entry.getValue();

            Log.d("s: %s", s);

            if (pt.isEnd()) {
                Log.d("children: %s", pt.node.children.keySet());
                for (Node child : pt.node.children.values()) {
                    Pointer npt = new Pointer(s + child.firstChar() + "", child);
                    temp.put(s + child.firstChar(), npt);
                    Log.d("child: %s", s + npt.firstChar());
                }
            } else {
                pt.position += 1;
                temp.put(s + pt.currentChar(), pt);
                Log.d("current: %s", s + pt.currentChar());
            }
        }

        return temp;
    }

    private void buildTree(char[] chars, Node root, String s) {
        for (int start = 0; start < chars.length; start++) {
            addToTree(chars, root, new Part(chars, start, chars.length - 1));
        }
    }

    private void addToTree(char[] chars, Node root, Part part) {
        if (root.get(part.start) == null) {
            Node node = new Node(chars, part);
            root.children.put(chars[part.start], node);
            return;
        }

        Node node = root;

        int cursor = part.start;
        while (cursor <= part.end) {
            Node next = node.get(cursor);
            if (next != null) {
                if (next.isContained(cursor, part.end)) {
                    node = next;
                    cursor += next.part.length();
                    continue;
                } else {
                    next.add(cursor, part.end);
                    break;
                }
            } else {
                Part newPart = new Part(chars, cursor, part.end);
                node.children.put(chars[cursor], new Node(chars, newPart));
                break;
            }
        }
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String p = in.readLine();
            String q = in.readLine();

            String ans = solve(p, q);

            System.out.println(ans);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    class Pointer {

        String s;

        Node node;

        int position;

        public Pointer(String s, Node node) {
            this.s = s;
            this.node = node;
            this.position = node.part.start;
        }

        char firstChar() {
            return node.firstChar();
        }

        char currentChar() {
            return node.chars[position];
        }

        boolean isEnd() {
            return node.part.end == position;
        }

    }

    class Node {

        private final char[] chars;

        private final Map<Character, Node> children = new HashMap<>();

        private final Part part;

        public Node(char[] chars, int start, int end) {
            this.chars = chars;
            this.part = new Part(chars, start, end);
        }

        public Node(char[] chars, Part part) {
            this(chars, part.start, part.end);
        }

        boolean isRoot() {
            return part.start == -1;
        }

        char firstChar() {
            return chars[part.start];
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

            // relocate all children nodes
            Node node = new Node(chars, part.start + i, part.end);
            for (Node n : children.values()) {
                node.children.put(chars[n.part.start], n);
            }

            children.clear();
            children.put(chars[part.start + i], node);

            // put new node to the map
            if (s + i < chars.length) {
                node = new Node(chars, s + i, e);
                children.put(chars[s + i], node);
            }

            part.end = part.start + i - 1;
        }

        boolean isContained(int s, int e) {
            int i = 0;
            while (part.start + i <= part.end) {
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

        private final char[] chars;

        public Part(char[] chars, int start, int end) {
            this.chars = chars;
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

    public static void main(String[] args) {
        new Thread(new NonSharedSubstring()).start();
    }
}
