package graph.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StronglyConnected {

    private final Vertex[] vertices;

    private final Vertex[] reverse;

    private final Vertex[] orders;

    private int postorder = 0;

    private int component = 0;

    public StronglyConnected(int numberOfVertices) {
        vertices = new Vertex[numberOfVertices];
        reverse = new Vertex[numberOfVertices];
        orders = new Vertex[numberOfVertices];

        initialize(numberOfVertices);
    }

    public int run() {
//        Helper.printArray(reverse);
        for (Vertex v : reverse) {
            if (v.visited) continue;
            explore(v, true);
        }

//        Helper.printArray(orders);

        component = 0;
        for (int i = orders.length - 1; i >= 0; i--) {
            int id = orders[i].id;
            Vertex v = vertices[id];

            if (v.visited) continue;

            explore(v, false);
            component++;
        }

        return component;
    }

    private void explore(Vertex v, boolean reverse) {
//        System.out.printf("explore vertex v: %d\n", v.id);
        v.visited = true;
        v.component = this.component;

        for (Vertex adjacent : v.out) {
//            System.out.printf("v id: %d, adjacent id: %d\n", v.id, adjacent.id);
            if (!adjacent.visited) explore(adjacent, reverse);
        }

        if (reverse) {
            orders[postorder] = v;
            postorder++;
        }
    }

    public void add(int v1, int v2) {
        vertices[v1].add(vertices[v2]);
        reverse[v2].add(reverse[v1]);
    }

    private void initialize(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            vertices[i] = new Vertex(i);
            reverse[i] = new Vertex(i);
        }
    }

    class Vertex {

        int id;

        boolean visited;

        int component;

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

        StronglyConnected sc = new StronglyConnected(n);

        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();

            sc.add(x - 1, y - 1);
        }

        System.out.println(sc.run());
    }
}

