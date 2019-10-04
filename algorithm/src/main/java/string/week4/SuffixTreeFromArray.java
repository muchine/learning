package string.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SuffixTreeFromArray {

    private final int[] suffixArray;

    private final int[] lcpArray;

    private final char[] text;

    private int nodeIds = 0;

    private List<Output> output = new ArrayList<>();

    SuffixTreeFromArray(String s, int[] suffixArray, int[] lcpArray) {
        this.suffixArray = suffixArray;
        this.lcpArray = lcpArray;
        this.text = s.toCharArray();
    }

    // Build suffix tree of the string text given its suffix array suffix_array
    // and LCP array lcp_array. Return the tree as a mapping from a node ID
    // to the list of all outgoing edges of the corresponding node. The edges in the
    // list must be sorted in the ascending order by the first character of the edge label.
    // Root must have node ID = 0, and all other node IDs must be different
    // nonnegative integers.
    //
    // For example, if text = "ACACAA$", an edge with label "$" from root to a node with ID 1
    // must be represented by new Edge(1, 6, 7). This edge must be present in the list tree.get(0)
    // (corresponding to the root node), and it should be the first edge in the list
    // (because it has the smallest first character of all edges outgoing from the root).
    Map<Integer, List<Edge>> SuffixTreeFromSuffixArray() {
        Map<Integer, List<Edge>> tree = new HashMap<Integer, List<Edge>>();

        Node root = createSuffixTree();

        List<Node> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove(0);
            List<Edge> edges = node.toEdgeList();

            Log.d("node: %d", node.id);
//            Helper.printCollection("edges", edges);

            if (edges.isEmpty()) continue;
            tree.put(node.id, edges);

            for (Edge e : edges) {
                queue.add(e.node);
            }
        }

        return tree;
    }

    Node createSuffixTree() {
        Node root = new Node(nodeIds++);
        int lcpPrev = 0;
        Node current = root;

        for (int i = 0; i < text.length; i++) {
            int suffix = suffixArray[i];

//            printSuffix(suffix);
            while (current.depth > lcpPrev) {
                current = current.parent;
            }

            Log.d("current depth: %d, lcpPrev: %d", current.id, lcpPrev);

            if (current.depth == lcpPrev) {
                current = createNewLeaf(current, suffix);
            } else {
                int start = suffixArray[i - 1] + current.depth;
                int offset = lcpPrev - current.depth;
                Node midNode = breakEdge(current, start, offset);

                current = createNewLeaf(midNode, suffix);
            }

            if (i < this.text.length - 1) {
                lcpPrev = lcpArray[i];
            }
        }

        return root;
    }

    Node createNewLeaf(Node node, int suffix) {
        Node leaf = new Node(nodeIds++);
        leaf.depth = text.length - suffix;

        node.addEdge(leaf, suffix + node.depth, text.length);
        return leaf;
    }

    private Node breakEdge(Node node, int start, int offset) {
        Log.d("break edge... node: %d, start: %d, offset: %d", node.id, start, offset);
        char startChar = text[start];

        Edge childEdge = node.out.get(startChar);
        Node child = childEdge.node;

        Node midNode = new Node(nodeIds++);
        midNode.depth = node.depth + offset;
        node.addEdge(midNode, start, start + offset);

        midNode.addEdge(child, childEdge.start + offset, childEdge.end);
        child.parent = midNode;

        return midNode;
    }

    private void printSuffix(int suffix) {
        StringBuilder b = new StringBuilder();
        for (int i = suffix; i < text.length; i++) {
            b.append(text[i]);
        }

        Log.d("suffix: %s", b.toString());
    }

    class Node {

        final int id;

        Node parent;

        Map<Character, Edge> out = new HashMap<>();

        int depth;

        Node(int id) {
            this.id = id;
        }

        List<Edge> toEdgeList() {
            List<Edge> edges = new ArrayList<>();

            char[] chars = { '$', 'A', 'C', 'G', 'T' };
            for (char c : chars) {
                Edge e = out.get(c);
                if (e != null) edges.add(e);
            }

            return edges;
        }

        Edge addEdge(Node child, int start, int end) {
            if (start == end) throw new RuntimeException("start and end are same");

            child.parent = this;

            Edge e = new Edge(child, start, end);
            out.put(text[start], e);

            return e;
        }

    }

    // Data structure to store edges of a suffix tree.
    public class Edge {
        // The ending node of this edge.
        Node node;
        // Starting position of the substring of the text
        // corresponding to the label of this edge.
        int start;
        // Position right after the end of the substring of the text
        // corresponding to the label of this edge.
        int end;

        Edge(Node node, int start, int end) {
            this.node = node;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Edge{node=" + node.id + ", start=" + start + ", end=" + end + '}';
        }
    }

    public static class Output {

        private final char[] text;

        private final int start, end;

        public Output(char[] text, int start, int end) {
            this.text = text;
            this.start = start;
            this.end = end;
        }


        public String print() {
            StringBuilder b = new StringBuilder();
            for (int i = start; i < end; i++) {
                b.append(text[i]);
            }

            return b.toString();
        }

        public int size() {
            return end - start;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Output output = (Output) o;

            return print().equals(output.print());
        }

        @Override
        public String toString() {
            return "Output(" + start + ", " + end + ')';
        }
    }

    public void print() {
        for (Output o : output) {
            System.out.println(o.start + " " + o.end);
        }
    }

    public List<Output> run() {
        // Build the suffix tree and get a mapping from 
        // suffix tree node ID to the list of outgoing Edges.
        Map<Integer, List<Edge>> suffixTree = SuffixTreeFromSuffixArray();
        ArrayList<String> result = new ArrayList<String>();
        // Output the edges of the suffix tree in the required order.
        // Note that we use here the contract that the root of the tree
        // will have node ID = 0 and that each vector of outgoing edges
        // will be sorted by the first character of the corresponding edge label.
        //
        // The following code avoids recursion to avoid stack overflow issues.
        // It uses two stacks to convert recursive function to a while loop.
        // This code is an equivalent of 
        //
        //    OutputEdges(tree, 0);
        //
        // for the following _recursive_ function OutputEdges:
        //
        // public void OutputEdges(Map<Integer, List<Edge>> tree, int nodeId) {
        //     List<Edge> edges = tree.get(nodeId);
        //     for (Edge edge : edges) {
        //         System.out.println(edge.start + " " + edge.end);
        //         OutputEdges(tree, edge.node);
        //     }
        // }
        //
        int[] nodeStack = new int[text.length];
        int[] edgeIndexStack = new int[text.length];
        nodeStack[0] = 0;
        edgeIndexStack[0] = 0;
        int stackSize = 1;
        while (stackSize > 0) {
            int node = nodeStack[stackSize - 1];
            int edgeIndex = edgeIndexStack[stackSize - 1];
            stackSize -= 1;

            if (suffixTree.get(node) == null) {
                continue;
            }
            if (edgeIndex + 1 < suffixTree.get(node).size()) {
                nodeStack[stackSize] = node;
                edgeIndexStack[stackSize] = edgeIndex + 1;
                stackSize += 1;
            }

//            Helper.printCollection("get nodes", suffixTree.get(node));

            int start = suffixTree.get(node).get(edgeIndex).start;
            int end = suffixTree.get(node).get(edgeIndex).end;
            output.add(new Output(text, start, end));

            nodeStack[stackSize] = suffixTree.get(node).get(edgeIndex).node.id;
            edgeIndexStack[stackSize] = 0;
            stackSize += 1;
        }

        return output;
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

    private static class Log {

        static int level = 0;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffixArray = new int[text.length()];
        for (int i = 0; i < suffixArray.length; ++i) {
            suffixArray[i] = scanner.nextInt();
        }
        int[] lcpArray = new int[text.length() - 1];
        for (int i = 0; i + 1 < text.length(); ++i) {
            lcpArray[i] = scanner.nextInt();
        }
        System.out.println(text);

        SuffixTreeFromArray st = new SuffixTreeFromArray(text, suffixArray, lcpArray);
        st.run();
        st.print();
    }

}
