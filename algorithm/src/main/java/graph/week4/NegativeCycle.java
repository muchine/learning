package graph.week4;

import java.util.*;

public class NegativeCycle {

    private final Vertex[] vertices;

    public NegativeCycle(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public int negativeCycle() {
        ComponentDetector cd = new ComponentDetector(vertices);
        Map<Integer, List<Integer>> components = cd.run();

        for (int key : components.keySet()) {
            List<Integer> ids = components.get(key);
//            Helper.printCollection("components", ids);

            boolean negative = run(ids);
            if (negative) return 1;
        }

        return 0;
    }

    private boolean run(List<Integer> ids) {
        if (ids.size() == 1) return false;

        for (Vertex v : this.vertices) {
            v.distance = Vertex.INFINITE;
            v.prev = null;
        }

        vertices[ids.get(0)].distance = 0;

        for (int i = 0; i < vertices.length - 1; i++) {
            executeBF();
        }

        Log.i("---------------------------------");
        Log.i("execute last BF...");
        Log.i("---------------------------------");
        return executeBF();
    }

    // return whether relaxation is occurred
    private boolean executeBF() {
        boolean relaxed = false;

        for (Vertex u : vertices) {
            Log.i("relaxing edges of vertex: %d", u.id);

            if (u.isInfinite()) continue;

            for (Edge e : u.out) {
                Vertex v = e.to;
                Log.d("out edge: %d\n", v.id);
                if (v.compare(u.distance + e.weight) <= 0) continue;

                Log.d("set new distances... v: %d, od: %d, nd: %d, weight: %d", v.id, v.distance, u.distance + e.weight, e.weight);

                v.prev = u;
                v.distance = u.distance + e.weight;

                relaxed = true;
            }
        }

        return relaxed;
    }

    public void add(int v1, int v2, int weight) {
        vertices[v1].add(vertices[v2], weight);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    private class Edge {

        Vertex to;

        int weight;

        Edge(Vertex to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    private class ComponentDetector {

        private final Vertex[] vertices;

        private final Vertex[] reverse;

        private final Vertex[] orders;

        private Map<Integer, List<Integer>> components = new HashMap<>();

        private int postorder = 0;

        private int component = 0;

        ComponentDetector(Vertex[] vertices) {
            this.vertices = new Vertex[vertices.length];
            this.reverse = new Vertex[vertices.length];
            this.orders = new Vertex[vertices.length];

            initialize(vertices);
        }

        public Map<Integer, List<Integer>> run() {
            for (Vertex v : reverse) {
                if (v.visited) continue;
                explore(v, true);
            }

            component = 0;
            for (int i = orders.length - 1; i >= 0; i--) {
                int id = orders[i].id;
                Vertex v = vertices[id];

                if (v.visited) continue;

                explore(v, false);
                component++;
            }

            return components;
        }

        private void explore(Vertex v, boolean reverse) {
            v.visited = true;

            if (!reverse) {
                List<Integer> vertices = components.get(this.component);
                if (vertices == null) {
                    vertices = new ArrayList<>();
                    components.put(this.component, vertices);
                }

                vertices.add(v.id);
            }

            for (Edge e : v.out) {
                Vertex adjacent = e.to;
                if (!adjacent.visited) explore(adjacent, reverse);
            }

            if (reverse) {
                orders[postorder] = v;
                postorder++;
            }
        }

        public void add(int v1, int v2, int weight) {
            vertices[v1].add(vertices[v2], weight);
            reverse[v2].add(reverse[v1], weight);
        }

        private void initialize(Vertex[] vertices) {
            for (int i = 0; i < vertices.length; i++) {
                this.vertices[i] = new Vertex(i);
                this.reverse[i] = new Vertex(i);
            }

            for (Vertex v : vertices) {
                for (Edge e : v.out) {
                    add(v.id, e.to.id, e.weight);
                }
            }
        }

    }

    class Vertex {

        public static final int INFINITE = 1000000;

        int id;

        int distance = INFINITE;

        Vertex prev;

        int location;

        boolean visited;

        List<Edge> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        boolean isInfinite() {
            return distance == INFINITE;
        }

        void add(Vertex v, int weight) {
            this.out.add(new Edge(v, weight));
        }

        int compare(int distance) {
            Log.d("compare... this.d: %d, o.d: %d", distance, distance);

            if (this.distance == distance) return 0;

            if (this.distance == INFINITE) return 1;
            if (distance == INFINITE) return -1;

            int compared = this.distance - distance;
            return compared > 0 ? 1 : -1;
        }

        int compare(Vertex o) {
            return compare(o.distance);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "id=" + id +
                    ", distance=" + distance +
                    ", location=" + location +
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        NegativeCycle c = new NegativeCycle(n);

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();

            c.add(x -1, y -1, w);
        }

        System.out.println(c.negativeCycle());
    }
}

