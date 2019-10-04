package coding.codewars.level4;

import java.util.*;

/**
 * Shortest Path
 * https://www.codewars.com/kata/path-finder-number-2-shortest-path/train/java
 */
@SuppressWarnings("Duplicates")
public class ShortestPathFinder {

    private int length;
    private Node[][] position;
    private List<Node> unvisited = new ArrayList<>();

    ShortestPathFinder(String maze) {
        String[] rows = maze.split("\n");
        this.length = rows.length;

        position = new Node[length][length];

        for (int i = 0; i < length; i++) {
            char[] values = rows[i].toCharArray();
            for (int j = 0; j < values.length; j++) {
                Node node = new Node(i, j, values[j] == 'W');
                position[i][j] = node;
                if (!node.isWall) unvisited.add(position[i][j]);
            }
        }
    }

    // Use Dijkstra's shortest path algorithm
    // https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    private int find(int x, int y) {
        Node source = position[x][y];
        source.distance = 0;

        while (!unvisited.isEmpty()) {
            Collections.sort(unvisited);
            Node node = unvisited.remove(0);

            if (node.distance == Integer.MAX_VALUE) return -1;

            for (Node neighbor : findNeighbors(node.x, node.y)) {
                int distance = node.distance + 1;
                if (distance < neighbor.distance) neighbor.distance = distance;
            }

            node.visited = true;
            if (node.x == length - 1 && node.y == length - 1) return node.distance;
        }

        return -1;
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
//        System.out.println(maze);
        ShortestPathFinder finder = new ShortestPathFinder(maze);
        return finder.find(0, 0);
    }

    class Node implements Comparable<Node> {

        int x, y;

        boolean visited = false;

        boolean isWall = false;

        int distance = Integer.MAX_VALUE;

        Node(int x, int y, boolean isWall) {
            this.x = x;
            this.y = y;
            this.isWall = isWall;
            this.visited = isWall;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance == o.distance) return 0;
            if (this.distance < o.distance) return -1;
            return 1;
        }
    }

}