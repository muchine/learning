package coding.codewars.level4;

import java.util.HashSet;
import java.util.Set;

/**
 * Boggle Word Checker
 * https://www.codewars.com/kata/boggle-word-checker/java
 */
public class Boggle {

    int rowCount;
    int colCount;

    char[][] board;
    Set<String> used = new HashSet<>();

    char[] chars;

    public Boggle(final char[][] board, final String word) {
//        print(board);
//        System.out.println("word: " + word);
        this.chars = word.toCharArray();

        rowCount = board.length;
        colCount = board[0].length;

        this.board = board;
    }

    public boolean check() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                used.clear();
                if (check(i, j, 0)) return true;
            }
        }

        return false;
    }

    public boolean check(int x, int y, int index) {
        if (x < 0 || rowCount <= x || y < 0 || colCount <= y) return false;

        String point = x + "," + y;
        if (used.contains(point) || board[x][y] != chars[index]) return false;
        used.add(point);

        int nextIndex = index + 1;
        if (nextIndex >= chars.length) return true;

        if (check(x - 1, y - 1, nextIndex)) return true;
        if (check(x - 1, y, nextIndex)) return true;
        if (check(x - 1, y + 1, nextIndex)) return true;
        if (check(x, y - 1, nextIndex)) return true;
        if (check(x, y + 1, nextIndex)) return true;
        if (check(x + 1, y - 1, nextIndex)) return true;
        if (check(x + 1, y, nextIndex)) return true;
        if (check(x + 1, y + 1, nextIndex)) return true;

        used.remove(point);

        return false;
    }

    private void print(char[][] board) {
        for (char[] row : board) {
            StringBuilder sb = new StringBuilder();
            for (char c : row) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(c);
            }

            System.out.println(sb.toString());
        }
    }

}


