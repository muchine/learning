package graph.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acyclicity {

    private final Vertex[] vertices;

    private int exploreCount = 1;

    private boolean isCyclic;

    public Acyclicity(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public int run() {
        for (Vertex v : vertices) {
            if (v.visited) continue;

            explore(v);
            exploreCount++;

            if (isCyclic) return 1;
        }

        return 0;
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    private void explore(Vertex v) {
//        System.out.printf("explore vertex v: %d, count: %d\n", v.id, exploreCount);
        v.visited = true;
        v.exploreCount = exploreCount;

        if (v.sinked) return;

        int sinkedCount = 0;
        for (Vertex adjacent : v.out) {
//            System.out.printf("v id: %d, adjacent id: %d, count: %d\n", v.id, adjacent.id, adjacent.exploreCount);
            if (!adjacent.sinked && adjacent.visited && adjacent.exploreCount == exploreCount) {
                isCyclic = true;
                return;
            } else if (!adjacent.visited) {
                explore(adjacent);
            }

            if (adjacent.sinked) sinkedCount++;
        }

        if (sinkedCount == v.out.size()) v.sinked = true;
    }

    class Vertex {

        int id;

        boolean visited;

        boolean sinked = true;

        int exploreCount;

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

        Acyclicity a = new Acyclicity(n);

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

            a.add(x, y);
        }

        System.out.println(a.run());
    }
}

