package graph.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectedComponents {

    private final Vertex[] vertices;

    private int component = 0;

    public ConnectedComponents(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
        vertices[v2].add(vertices[v1]);
    }

    public int numberOfComponents() {
        component = 0;

        for (Vertex v : vertices) {
            if (v.visited) continue;

            explore(v);
            component++;
        }

        return component;
    }

    private void explore(Vertex v) {
        v.visited = true;
        v.component = this.component;

        for (Vertex adjacent : v.adjacencies) {
            if (!adjacent.visited) explore(adjacent);
        }
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    class Vertex {

        int id;

        boolean visited;

        int component;

        List<Vertex> adjacencies = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v) {
            adjacencies.add(v);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ConnectedComponents cc = new ConnectedComponents(n);

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

            cc.add(x, y);
        }

        System.out.println(cc.numberOfComponents());
    }
}

