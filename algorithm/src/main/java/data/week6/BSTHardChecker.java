package data.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BSTHardChecker {

    int n;
    int[] key, left, right;

    Node[] nodes;
    Node root;

    public BSTHardChecker() {}

    public BSTHardChecker(int[] key, int[] left, int[] right) {
        if (key == null) return;

        this.n = key.length;

        this.key = key;
        this.left = left;
        this.right = right;

        initializeNodes();
    }

    public boolean solve() {
        if (n == 0) return true;

        List<Node> sorted = sort();

        for (int i = 0; i < n - 1; i++) {
            Node current = sorted.get(i);
            Node next = sorted.get(i + 1);

            if (current.key > next.key) return false;
            if (current.key == next.key && current.depth > next.depth) return false;
        }

        return true;
    }

    private List<Node> sort() {
        List<Node> result = new ArrayList<>();

        Node next = root;
        root.depth = 0;
        int depth = 0;

        while(true) {
//				System.out.printf("next key: %d\n", next.key);
            if (next.left != null && !next.left.done) {
                next = next.left;
                next.depth = ++depth;
                continue;
            }

            if (!next.done) {
                result.add(next);
                next.done = true;
            }

            if (next.right != null && !next.right.done) {
                next = next.right;
                next.depth = ++depth;
                continue;
            }

            if (next.key == root.key && root.isAllDone()) {
                break;
            }

            next = next.parent;
            depth--;
        }

        return result;
    }

    private Node initializeNodes() {
        this.nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(key[i]);
        }

        for (int i = 0; i < n; i++) {
            if (left[i] > -1) {
                nodes[i].left = nodes[left[i]];
                nodes[left[i]].parent = nodes[i];
            }

            if (right[i] > -1) {
                nodes[i].right = nodes[right[i]];
                nodes[right[i]].parent = nodes[i];
            }
        }

        this.root = nodes[0];
        return root;
    }

    void read() throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt();

        if (n == 0) return;

        key = new int[n];
        left = new int[n];
        right = new int[n];

        for (int i = 0; i < n; i++) {
            key[i] = in.nextInt();
            left[i] = in.nextInt();
            right[i] = in.nextInt();
        }

        initializeNodes();
    }

    public void run() throws IOException {
        read();
        if (solve()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }

    class Node {
        int key;

        int depth;

        Node parent;

        Node left;

        Node right;

        boolean done = false;

        Node(int key) {
            this.key = key;
        }

        boolean isAllDone() {
            if (left != null && !left.done) return false;
            if (right != null && !right.done) return false;
            if (!done) return false;

            return true;
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
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new BSTHardChecker().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

}
