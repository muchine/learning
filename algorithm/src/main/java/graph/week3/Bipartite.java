package graph.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bipartite {

    private final Vertex[] vertices;

    private final List<Vertex> queue = new ArrayList<>();

    public Bipartite(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        initialize(numberOfVertices);
    }

    public int bipartite() {
        Vertex s = vertices[0];
        s.color = 0;
        queue.add(s);

        while (!queue.isEmpty()) {
            Vertex u = queue.remove(0);
            for (Vertex adjacent : u.out) {
                if (adjacent.color >= 0) {
                    if (adjacent.color + u.color == 1)
                        continue;
                    else
                        return 0;
                }

                adjacent.color = u.color == 1 ? 0 : 1;
                queue.add(adjacent);
            }
        }

        return 1;
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
        vertices[v2].add(vertices[v1]);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    class Vertex {

        int id;

        int color = -1;

        List<Vertex> out = new ArrayList<>();

        Vertex(int id) {
            this.id = id;
        }

        void add(Vertex v) {
            this.out.add(v);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Bipartite b = new Bipartite(n);

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();

            b.add(x - 1, y - 1);
        }


        System.out.println(b.bipartite());
    }
}

