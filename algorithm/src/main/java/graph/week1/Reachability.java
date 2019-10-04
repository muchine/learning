package graph.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reachability {

    private final Vertex[] vertices;

    public Reachability(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
        vertices[v2].add(vertices[v1]);
    }

    public int reach(int x, int y) {
        explore(vertices[x]);
        return vertices[y].visited ? 1 : 0;
    }

    private void explore(Vertex v) {
        v.visited = true;

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

        Reachability r = new Reachability(n);

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();

            r.add(x - 1, y - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;

        System.out.println(r.reach(x, y ));
    }
}

