package graph.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BFS {

    private final Vertex[] vertices;

    final int[] distances;

    private final List<Vertex> queue = new ArrayList<>();

    public BFS(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        distances = new int[numberOfVertices];

        initialize(numberOfVertices);
    }

    public int distance(int s, int t) {
        buildPathTree(s);
        return distances[t];
    }

    private void buildPathTree(int s) {
        distances[s] = 0;

        queue.add(vertices[s]);
        while (!queue.isEmpty()) {
            Vertex u = queue.remove(0);
            for (Vertex adjacent : u.out) {
                if (distances[adjacent.id] >= 0) continue;

                queue.add(adjacent);
                distances[adjacent.id] = distances[u.id] + 1;
            }
        }
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
        vertices[v2].add(vertices[v1]);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
            distances[i] = -1;
        }
    }

    class Vertex {

        int id;

        boolean discovered;

        List<Vertex> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v) {
            this.out.add(v);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "id=" + id +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        BFS b = new BFS(n);

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();

            b.add(x - 1, y - 1);
        }

        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;

        System.out.println(b.distance(x, y));
    }
}

