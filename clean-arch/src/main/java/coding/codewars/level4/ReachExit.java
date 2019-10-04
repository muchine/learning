package coding.codewars.level4;

/**
 * Can you reach the exit?
 * https://www.codewars.com/kata/5765870e190b1472ec0022a2
 */
@SuppressWarnings("Duplicates")
public class ReachExit {

    private int length;
    private Node[][] position;

    ReachExit(String maze) {
        String[] rows = maze.split("\n");
        this.length = rows.length;

        position = new Node[length][length];

        for (int i = 0; i < length; i++) {
            char[] values = rows[i].toCharArray();
            for (int j = 0; j < values.length; j++) {
                position[i][j] = new Node(values[j] == 'W');
            }
        }
    }

    private boolean find(int x, int y) {
        if (x < 0 || y < 0 || x >= length || y >= length) return false;
        if (x == length - 1 && y == length - 1) return true;

        Node node = position[x][y];
        if (node.visited || node.isWall) return false;

        node.visited = true;
        return find(x - 1, y) || find(x + 1, y) || find(x, y - 1) || find(x, y + 1);
    }

    static boolean pathFinder(String maze) {
        System.out.println(maze);
        ReachExit finder = new ReachExit(maze);
        return finder.find(0, 0);
    }

    class Node {

        boolean visited = false;

        boolean isWall = false;

        Node(boolean isWall) {
            this.isWall = isWall;
        }

    }

}