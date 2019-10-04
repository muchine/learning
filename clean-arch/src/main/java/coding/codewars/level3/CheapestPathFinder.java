package coding.codewars.level3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Find the cheapest path
 * https://www.codewars.com/kata/find-the-cheapest-path/train/java
 */
@SuppressWarnings("Duplicates")
public class CheapestPathFinder {

    private int width, height;

    private Node[][] position;
    private List<Node> unvisited = new ArrayList<>();

    public CheapestPathFinder(int[][] tollMap) {
        height = tollMap.length;
        width = tollMap[0].length;
        position = new Node[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                position[i][j] = new Node(i, j, tollMap[i][j]);
            }
        }
    }

    // Use Dijkstra's shortest path algorithm
    // https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    private List<String> find(Point start, Point end) {
        Node source = position[start.x][start.y];
        Node dest = position[end.x][end.y];

        source.distance = source.cost;
        enqueue(source);

        while (!unvisited.isEmpty()) {
            Node node = findMinimumDistanceNode();
            unvisited.remove(node);

            for (Node neighbor : findNeighbors(node.x, node.y)) {
                int distance = node.distance + neighbor.cost;
                if (distance < neighbor.distance) {
                    neighbor.distance = distance;
                    neighbor.previous = node;
                }

                enqueue(neighbor);
            }

            node.visited = true;
            if (node.equals(dest)) break;
        }

        List<String> path = new ArrayList<>();
        Node node = dest;
        while (node != source) {
            path.add(0, node.direction());
            node = node.previous;
        }

        return path;
    }

    private void enqueue(Node node) {
        if (node.isQueued) return;

        unvisited.add(node);
        node.isQueued = true;
    }

    private Node findMinimumDistanceNode() {
        return unvisited.stream().reduce((o1, o2) -> o1.distance < o2.distance ? o1 : o2).get();
    }

    private List<Node> findNeighbors(int x, int y) {
        List<Node> neighbors = new ArrayList<>();
        if (canVisit(x - 1, y)) neighbors.add(position[x - 1][y]);
        if (canVisit(x + 1, y)) neighbors.add(position[x + 1][y]);
        if (canVisit(x, y - 1)) neighbors.add(position[x][y - 1]);
        if (canVisit(x, y + 1)) neighbors.add(position[x][y + 1]);

        return neighbors;
    }

    private boolean canVisit(int x, int y) {
        if (x < 0 || y < 0 || x >= position.length || y >= position[0].length) return false;
        return !position[x][y].visited;
    }

    static List<String> cheapestPath(int[][] t, Point start, Point finish) {
        CheapestPathFinder finder = new CheapestPathFinder(t);
        for (int[] row : t) {
            for (int value : row) System.out.printf(value + " ");
            System.out.printf("\n");
        }
        return finder.find(start, finish);
    }

    class Node implements Comparable<Node> {

        int x, y;
        int cost;

        Node previous;
        boolean isQueued = false;
        boolean visited = false;
        int distance = Integer.MAX_VALUE;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        String direction() {
            if (previous == null) throw new IllegalStateException("previous node is null");

            if (x - previous.x == 1) return "down";
            if (x - previous.x == -1) return "up";
            if (y - previous.y == 1) return "right";
            if (y - previous.y == -1) return "left";

            throw new IllegalStateException("invalid previous node");
        }

        @Override
        public boolean equals(Object obj) {
            Node o = (Node) obj;
            return x == o.x && y == o.y;
        }

        @Override
        public int compareTo(Node o) {
            int compared = Integer.compare(this.distance, o.distance);
            if (compared != 0) return compared;

            return (x + "" + y).compareTo(o.x + "" + o.y);
        }
    }
}