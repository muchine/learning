package graph.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Toposort {

    private final Vertex[] vertices;

    private List<Integer> order = new ArrayList<Integer>();

    public Toposort(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public List<Integer> run() {
        for (Vertex v : vertices) {
            if (v.visited) continue;
            explore(v);
        }

        return order;
    }

    private void explore(Vertex v) {
//        System.out.printf("explore vertex v: %d\n", v.id);
        v.visited = true;
        if (v.sinked) {
            order.add(0,v.id + 1);
            return;
        }

        int sinkedCount = 0;
        for (Vertex adjacent : v.out) {
//            System.out.printf("v id: %d, adjacent id: %d\n", v.id, adjacent.id);
            if (!adjacent.visited) {
                explore(adjacent);
            }

            if (adjacent.sinked) sinkedCount++;
        }

        if (sinkedCount == v.out.size()) {
            v.sinked = true;
            order.add(0,v.id + 1);
        }
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    class Vertex {

        int id;

        boolean visited;

        boolean sinked = true;

        List<Vertex> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v) {
            this.out.add(v);
            if (sinked) sinked = false;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Toposort a = new Toposort(n);

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();

            a.add(x - 1, y - 1);
        }

        List<Integer> order = a.run();
        for (int x : order) {
            System.out.print((x) + " ");
        }
    }
}

