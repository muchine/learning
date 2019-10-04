import java.io.*;
import java.util.*;

class PlanParty {

    private final Vertex[] vertices;

    public PlanParty(int numOfVertices) {
        this.vertices = new Vertex[numOfVertices];
        initialize();
    }

    public int maxWeightIndependentTreeSubset() {
        if (vertices.length == 0) return 0;
        return findMaxWeight(vertices[0], null);
    }

    public void setWeight(int vertexId, int weight) {
        vertices[vertexId].weight = weight;
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
        vertices[v2].add(vertices[v1]);
    }

    private void initialize() {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    private int findMaxWeight(Vertex vertex, Vertex parent) {
        if (vertex.totalWeight > 0) return vertex.totalWeight;

        List<Vertex> children = vertex.children(parent);

        if (children.isEmpty()) {
            vertex.totalWeight = vertex.weight;
            return vertex.totalWeight;
        }

        int m0 = 0;
        int m1 = vertex.weight;

        for (Vertex c : children) {
            List<Vertex> grandChildren = c.children(vertex);
            for (Vertex gc : grandChildren) {
                m1 += findMaxWeight(gc, c);
            }

            m0 += findMaxWeight(c, vertex);
        }

        vertex.totalWeight = Math.max(m0, m1);
        return vertex.totalWeight;
    }

    static class Vertex {

        private final int id;

        private final List<Vertex> adjacent = new ArrayList<Vertex>();

        private int weight;

        private int totalWeight;

        public Vertex(int id) {
            this.id = id;
        }

        public void add(Vertex v) {
            adjacent.add(v);
        }

        public List<Vertex> children(Vertex parent) {
            List<Vertex> vertices = new ArrayList<>();

            for (Vertex v : adjacent) {
                if (parent != null && v.id == parent.id) continue;
                vertices.add(v);
            }

            return vertices;
        }

    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    InputStreamReader input_stream = new InputStreamReader(System.in);
                    BufferedReader reader = new BufferedReader(input_stream);
                    StreamTokenizer tokenizer = new StreamTokenizer(reader);

                    tokenizer.nextToken();
                    int verticesCount = (int) tokenizer.nval;

                    PlanParty pp = new PlanParty(verticesCount);

                    for (int i = 0; i < verticesCount; ++i) {
                        tokenizer.nextToken();
                        pp.setWeight(i, (int) tokenizer.nval);
                    }

                    for (int i = 1; i < verticesCount; ++i) {
                        tokenizer.nextToken();
                        int from = (int) tokenizer.nval;

                        tokenizer.nextToken();
                        int to = (int) tokenizer.nval;
                        pp.add(from - 1, to - 1);
                    }

                    int weight = pp.maxWeightIndependentTreeSubset();

                    System.out.println(weight);
                } catch(IOException e) {}
            }
        }, "1", 1 << 26).start();
    }

}
