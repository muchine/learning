package data.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SetRangeSum {

    public static final int MODULO = 1000000001;

    Node root = null;
//    NaiveRangeSum naive = new NaiveRangeSum();

    private long last_sum_result;
    List<String> outputs = new ArrayList<>();

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    SetRangeSum() {}
    SetRangeSum(BufferedReader br, PrintWriter out) {
        this.br = br;
        this.out = out;
    }

    void solve() throws IOException {
        int n = nextInt();
        last_sum_result = 0;

        for (int i = 0; i < n; i++) {
            char type = nextChar();
            int op1 = nextInt();
            int op2 = type == 's' ? nextInt() : 0;

            String result = parse(type, op1, op2);
            if (!result.isEmpty()) out.println(result);
        }
    }

    public String parse(char type, int op1, int op2) {
        String result = "";
//        System.out.printf("operation: %s, op1: %s, op2: %s, i: %d\n", type, op1, op2, outputs.size());

        switch (type) {
            case '+' : {
                insert((op1 + last_sum_result) % MODULO);
            } break;
            case '-' : {
                erase((op1 + last_sum_result) % MODULO);
            } break;
            case '?' : {
                result = find((op1 + last_sum_result) % MODULO) ? "Found" : "Not found";
            } break;
            case 's' : {
//                System.out.printf("last sum result: %d\n", last_sum_result);
//                System.out.printf("op1: %d, op2: %d\n", (op1 + last_sum_result) % MODULO, (op2 + last_sum_result) % MODULO);
                long res = sum((op1 + last_sum_result) % MODULO, (op2 + last_sum_result) % MODULO);
//                last_sum_result = (int)(res % MODULO);
                last_sum_result = res;
                result = String.valueOf(res);
            }
        }

        if (!result.isEmpty()) outputs.add(result);
        return result;
    }

    void update(Node v) {
        if (v != null) v.update();
    }

    void smallRotation(Node v) {
        Node parent = v.parent;
        if (parent == null) return;

//        System.out.printf("v.parent: %s\n", v.parent != null ? v.parent.key : null);
//        System.out.printf("v.key: %s\n", v.key);

        Node grandparent = v.parent.parent;

        if (parent.left == v) {
            Node m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Node m = v.left;
            v.left = parent;
            parent.right = m;
        }

//        System.out.printf("v.left: %s\n", v.left != null ? v.left.key : null);
//        System.out.printf("v.right: %s\n", v.right != null ? v.right.key : null);

        update(parent);
        update(v);

        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }

            update(grandparent);
        }
    }

    void bigRotation(Node v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given node and returns the new root.
    Node splay(Node v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    NodePair find(Node root, long key) {
        Node v = root;
        Node last = root;
        Node next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }

            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }

        root = splay(last);
        return new NodePair(root, next);
    }

    NodePair split(Node root, long key) {
        NodePair result = new NodePair();
        NodePair findAndRoot = find(root, key);
        root = findAndRoot.left;
        result.right = findAndRoot.right;
        if (result.right == null) {
            result.left = root;
            return result;
        }

        result.right = splay(result.right);
        result.left = result.right.left;

        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }

        update(result.left);
        update(result.right);

        return result;
    }

    Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        while (right.left != null) {
            right = right.left;
        }

        right = splay(right);
        right.left = left;

        update(right);
        return right;
    }

    // Code that uses splay tree to solve the problem

    void insert(long x) {
        Node left = null;
        Node right = null;
        Node new_vertex = null;
        NodePair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Node(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
        if (root != null) root.parent = null;
    }

    void erase(long x) {
        NodePair pair = find(root, x);
        root = pair.left;

        if (root == null || root.key != x) return;

        pair = split(root, x);
        root = merge(pair.left, pair.right.right);
        if (root != null) root.parent = null;
    }

    boolean find(long x) {
        NodePair pair = find(root, x);
        this.root = pair.left;

        boolean result = root != null && root.key == x;

        return result;
    }

    long sum(long from, long to) {
        NodePair leftMiddle = split(root, from);
        Node left = leftMiddle.left;
        Node middle = leftMiddle.right;
        NodePair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Node right = middleRight.right;

        long sum = middle != null ? middle.sum : 0;

        // merge back
        Node merged = merge(middle, right);
        root = merge(left, merged);
        if (root != null) root.parent = null;

        return sum;
    }

    // Node of a splay tree
    class Node {
        long key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Node left;
        Node right;
        Node parent;

        Node(long key, long sum, Node left, Node right, Node parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        void update() {
            long leftSum = left != null ? left.sum : 0;
            long rightSum = right != null ? right.sum : 0;
            sum = key + leftSum + rightSum;

            if (left != null) left.parent = this;
            if (right != null) right.parent = this;
        }

//        void toSet(Set<Long> set) {
//            set.add(this.key);
//            if (left != null) {
//                if (left.parent != this) throw new RuntimeException("invalid left pointer");
//                left.toSet(set);
//            }
//            if (right != null) {
//                if (right.parent != this) throw new RuntimeException("invalid right pointer");
//                right.toSet(set);
//            }
//        }

    }

    class NodePair {
        Node left;
        Node right;
        NodePair() {
        }
        NodePair(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        SetRangeSum s = new SetRangeSum(reader, out);

        s.solve();
        out.close();
    }

}
