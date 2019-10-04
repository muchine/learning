package algo.week5;

import java.util.Scanner;

public class EditDistance {

    private final String s;
    private final String t;

    private final int[][] matrix;

    public EditDistance(String s, String t) {
        this.s = s;
        this.t = t;
        this.matrix = new int[s.length() + 1][t.length() + 1];

        initialize();
    }

    public int calculate() {
        for (int j = 1; j <= t.length(); j++) {
            for (int i = 1; i <= s.length(); i++) {
                int insertion = matrix[i][j - 1] + 1;
                int deletion = matrix[i - 1][j] + 1;
                int match = matrix[i - 1][j - 1];
                int mismatch = matrix[i - 1][j - 1] + 1;

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    matrix[i][j] = Math.min(Math.min(insertion, deletion), match);
                } else {
                    matrix[i][j] = Math.min(Math.min(insertion, deletion), mismatch);
                }
            }
        }

//        Helper.printArray(matrix);
        return matrix[s.length()][t.length()];
    }

    private void initialize() {
        for (int i = 1; i <= s.length(); i++) {
            matrix[i][0] = i;
        }

        for (int j = 1; j <= t.length(); j++) {
            matrix[0][j] = j;
        }
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        EditDistance ed = new EditDistance(s, t);
        System.out.println(ed.calculate());
    }

}
