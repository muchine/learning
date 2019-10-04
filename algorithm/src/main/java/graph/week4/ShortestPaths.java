package graph.week4;

import java.util.*;

public class ShortestPaths {

    private final Vertex[] vertices;

    public ShortestPaths(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public Vertex[] run(int s) {
        vertices[s].distance = 0;

        for (int i = 0; i < vertices.length - 1; i++) {
            executeBF();
        }

        Log.i("---------------------------------");
        Log.i("execute last BF...");
        Log.i("---------------------------------");
        Map<Integer, Vertex> relaxed = executeBF();
        detectNegativeInfiniteNode(relaxed);

        return vertices;
    }

    private Map<Integer, Vertex> executeBF() {
        Map<Integer, Vertex> relaxed = new HashMap<>();

        for (Vertex u : vertices) {
            Log.i("relaxing edges of vertex: %d", u.id);

            if (u.distance == Vertex.INFINITE) continue;

            for (Edge e : u.out) {
                Vertex v = e.to;
                Log.d("trying to relaxing a vertex... id: %d\n", v.id);
                if (v.compare(u.distance + e.weight) <= 0) continue;

                Log.d("set new distances... v: %d, od: %d, nd: %d, weight: %d", v.id, v.distance, u.distance + e.weight, e.weight);

                v.prev = u;
                v.distance = u.distance + e.weight;

                relaxed.put(v.id, v);
            }
        }

        return relaxed;
    }

    private void detectNegativeInfiniteNode(Map<Integer, Vertex> relaxed) {
        List<Vertex> queue = new ArrayList<>();

        for (Vertex v : relaxed.values()) {
            queue.add(v);
        }

        while (!queue.isEmpty()) {
            Vertex u = queue.remove(0);
            if (u.visited) continue;

            u.visited = true;
            u.distance = -Vertex.INFINITE;

            for (Edge e : u.out) {
                Vertex adjacent = e.to;
                if (adjacent.visited) continue;

                queue.add(adjacent);
            }
        }
    }

    public void add(int v1, int v2, int weight) {
        vertices[v1].add(vertices[v2], weight);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    class Vertex {

        public static final long INFINITE = 100000000000L;

        int id;

        long distance = INFINITE;

        Vertex prev;

        int location;

        boolean visited;

        List<Edge> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v, int weight) {
            this.out.add(new Edge(v, weight));
        }

        int compare(long distance) {
            Log.d("compare... this.d: %d, o.d: %d", distance, distance);

            if (this.distance == distance) return 0;

            if (this.distance == INFINITE) return 1;
            if (distance == INFINITE) return -1;

            long compared = this.distance - distance;
            return compared > 0 ? 1 : -1;
        }

        int compare(Vertex o) {
            return compare(o.distance);
        }

        @Override
        public String toString() {
            if (distance == INFINITE) return "*";
            if (distance == -INFINITE) return "-";

            return distance + "";
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
        ShortestPaths p = new ShortestPaths(n);

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();

            p.add(x - 1, y - 1, w);
        }

        int s = scanner.nextInt() - 1;

        Vertex[] vertices = p.run(s);
        for (int i = 0; i < n; i++) {
            Vertex v = vertices[i];

            if (v.distance == Vertex.INFINITE) {
                System.out.println('*');
            } else if (v.distance == -Vertex.INFINITE) {
                System.out.println('-');
            } else {
                System.out.println(v.distance);
            }
        }
    }

}

