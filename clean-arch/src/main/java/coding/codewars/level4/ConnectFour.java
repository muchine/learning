package coding.codewars.level4;

import java.util.*;

/**
 * Connect Four
 * https://www.codewars.com/kata/56882731514ec3ec3d000009
 */
public class ConnectFour {

    private int[] rowIndexes = new int[7];
    private String[][] boards = new String[6][7];

    ConnectFour() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) boards[i][j] = "O";
        }
    }

    public String perform(List<String> piecesPositionList) {
        System.out.println(piecesPositionList);
        for (int i = 0; i < piecesPositionList.size(); i++) {
            String piece = piecesPositionList.get(i);
            String[] tokens = piece.split("_");
            int column = "ABCDEFG".indexOf(tokens[0]);
            String c = tokens[1].substring(0, 1);
            boards[rowIndexes[column]++][column] = c;

            if (isConnectFour(c)) {
                print();
                System.out.printf("%sth move in total %s\n", i, piecesPositionList.size());
                return c.equals("R") ? "Red" : "Yellow";
            }
        }

        print();
        return "Draw";
    }

    public boolean isConnectFour(String s) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (!boards[i][j].equals(s)) continue;
                if (isVerticalFour(i, j) || isHorizontalFour(i, j) || isUpperDiagonalFour(i, j) || isBelowDiagonalFour(i, j)) {
                    System.out.println("(i, j): " + i + ", " + j);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isVerticalFour(int x, int y) {
        if (x > 2) return false;

        String c = boards[x][y];
        for (int i = 1; i < 4; i++) if (!boards[x + i][y].equals(c)) return false;
        return true;
    }

    private boolean isHorizontalFour(int x, int y) {
        if (y > 3) return false;

        String c = boards[x][y];
        for (int i = 1; i < 4; i++) if (!boards[x][y + i].equals(c)) return false;
        return true;
    }

    private boolean isUpperDiagonalFour(int x, int y) {
        if (x > 2 || y > 3) return false;

        String c = boards[x][y];
        for (int i = 1; i < 4; i++) if (!boards[x + i][y + i].equals(c)) return false;
        return true;
    }

    private boolean isBelowDiagonalFour(int x, int y) {
        if (x < 3 || y > 3) return false;

        String c = boards[x][y];
        for (int i = 1; i < 4; i++) if (!boards[x - i][y + i].equals(c)) return false;
        return true;
    }

    public void print() {
        for (int i = 5; i >= 0; i--) {
            System.out.println(Arrays.asList(boards[i]));
        }
    }

    public static String whoIsWinner(List<String> piecesPositionList) {
        return new ConnectFour().perform(piecesPositionList);
    }
}
