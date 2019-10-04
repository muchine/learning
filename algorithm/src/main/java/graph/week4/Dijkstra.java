package graph.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dijkstra {

    private final Vertex[] vertices;

    private final Vertex[] prev;

    Queue queue;

    public Dijkstra(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        prev = new Vertex[numberOfVertices];

        initialize(numberOfVertices);

        queue = new Queue(vertices);
    }

    public int distance(int s, int t) {
        queue = new Queue(vertices);
        queue.change(vertices[s], 0);

        while (!queue.isEmpty()) {
            Vertex u = queue.minimum();
            Log.d("min u: %d, dist: %d\n", u.id, u.distance);
            if (u.distance < 0) continue;

            for (Edge e : u.out) {
                Vertex v = e.to;
                Log.d("out edge: %d\n", v.id);
                if (v.compare(u.distance + e.weight) < 0) continue;

                v.prev = u;

                int distance = u.distance + e.weight;
                Log.d("set new distances... v: %d, distance: %d, weight: %d\n", v.id, distance, e.weight);
                queue.change(v, distance);
            }
        }

        return vertices[t].distance;
    }

    public void add(int v1, int v2, int weight) {
        vertices[v1].add(vertices[v2], weight);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    class Queue {

        private int numberOfVertices;

        Vertex[] vertices;

        Queue(Vertex[] vertices) {
            this.numberOfVertices = vertices.length;
            this.vertices = new Vertex[vertices.length];

            for (int i = 0; i < vertices.length; i++) {
                this.vertices[i] = vertices[i];
                this.vertices[i].location = i;
            }
        }

        boolean isEmpty() {
            return numberOfVertices == 0;
        }

        Vertex minimum() {
            Vertex min = vertices[0];

            swap(0, numberOfVertices - 1);
            vertices[numberOfVertices - 1] = null;
            numberOfVertices--;

            siftDown(0);

            return min;
        }

        void change(Vertex v, int distance) {
            Log.d("change queue.. v: %d, location: %d, distance: %d\n", v.id, v.location, distance);
            int prevDistance = v.distance;
            v.distance = distance;

            if (v.compare(prevDistance) < 0) {
                siftUp(v.location);
            } else {
                siftDown(v.location);
            }

//            Helper.printArray(queue.vertices);
        }

        private void siftUp(int i) {
            Log.d("sift up.. i: %d, parent: %d, compared: %d\n", i, parent(i), vertices[i].compare(vertices[parent(i)]));
            while (i > 0 && vertices[i].compare(vertices[parent(i)]) < 0) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

        private void siftDown(int i) {
            Log.d("sift down.. i: %d\n", i);
            int minIndex = i;

            int l = leftChild(i);
            if (l <= numberOfVertices - 1 && vertices[l].compare(vertices[minIndex]) < 0) {
                minIndex = l;
            }

            int r = rightChild(i);
            if (r <= numberOfVertices - 1 && vertices[r].compare(vertices[minIndex]) < 0) {
                minIndex = r;
            }

            if (i != minIndex) {
                swap(i, minIndex);
                siftDown(minIndex);
            }
        }

        private int parent(int i) {
            return (i - 1) / 2;
        }

        private int leftChild(int i) {
            return 2 * i + 1;
        }

        private int rightChild(int i) {
            return 2 * i + 2;
        }

        private void swap(int x, int y) {
            if (x == y) return;

            Log.d("swap vertices.. x: %d, y: %d\n", vertices[x].id, vertices[y].id);

            Vertex temp = vertices[x];
            vertices[x] = vertices[y];
            vertices[y] = temp;

            vertices[x].location = x;
            vertices[y].location = y;

            Log.d("swapped vertices. x: %d, y: %d\n", vertices[x].id, vertices[y].id);
//            Helper.printArray(vertices);
        }

    }

    class Vertex {

        int id;

        int distance = -1;

        Vertex prev;

        int location;

        List<Edge> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v, int weight) {
            this.out.add(new Edge(v, weight));
        }

        int compare(int distance) {
            Log.d("compare... this.d: %d, o.d: %d\n", distance, distance);

            if (this.distance == distance) return 0;

            if (this.distance == -1) return 1;
            if (distance == -1) return -1;

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

    private class Edge {

        Vertex to;

        int weight;

        Edge(Vertex to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    private static class Log {

        static boolean debug = false;

        static void d(String s, Object... o) {
            if (!debug) return;

            System.out.printf(s, o);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Dijkstra d = new Dijkstra(n);

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();

            d.add(x -1, y -1, w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;

        System.out.println(d.distance(x, y));
    }
}

