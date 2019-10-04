package coding.codewars.level3;

import java.util.ArrayList;
import java.util.List;

/**
 * The Alpimnist
 * https://www.codewars.com/kata/path-finder-number-3-the-alpinist/train/java
 */
@SuppressWarnings("Duplicates")
public class Alpinest {

    private int length;
    private Node[][] position;
    private List<Node> unvisited = new ArrayList<>();

    Alpinest(String maze) {
        String[] rows = maze.split("\n");
        this.length = rows.length;

        position = new Node[length][length];

        for (int i = 0; i < length; i++) {
            char[] values = rows[i].toCharArray();
            for (int j = 0; j < values.length; j++) {
                Node node = new Node(i, j, values[j]);
                position[i][j] = node;
            }
        }
    }

    // Use Dijkstra's shortest path algorithm
    // https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    private int find(int x, int y) {
        Node source = position[x][y];
        source.distance = 0;
        enqueue(source);

        while (!unvisited.isEmpty()) {
            Node node = findMinimumDistanceNode();
            unvisited.remove(node);

            if (node.distance == Integer.MAX_VALUE) return -1;

            for (Node neighbor : findNeighbors(node.x, node.y)) {
                int distance = node.distance + Math.abs(node.altitude - neighbor.altitude);
                if (distance < neighbor.distance) neighbor.distance = distance;

                if (!neighbor.isQueued) {
                    unvisited.add(neighbor);
                    neighbor.isQueued = true;
                }
            }

            node.visited = true;
            if (node.x == length - 1 && node.y == length - 1) return node.distance;
        }

        return -1;
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
        if (x < 0 || y < 0 || x >= length || y >= length) return false;
        return !position[x][y].visited;
    }

    static int pathFinder(String maze) {
        Alpinest alpinest = new Alpinest(maze);
        int result = alpinest.find(0, 0);
        if (result == -1) System.out.println(maze);
        return result;
    }

    class Node implements Comparable<Node> {

        int x, y;
        int altitude;

        boolean isQueued = false;
        boolean visited = false;
        int distance = Integer.MAX_VALUE;

        Node(int x, int y, int altitude) {
            this.x = x;
            this.y = y;
            this.altitude = altitude;
        }

        @Override
        public int compareTo(Node o) {
            int compared = Integer.compare(this.distance, o.distance);
            if (compared != 0) return compared;

            return (x + "" + y).compareTo(o.x + "" + o.y);
        }
    }

}
