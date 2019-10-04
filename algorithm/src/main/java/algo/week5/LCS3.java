package algo.week5;

import java.util.*;

public class LCS3 {

    private final int[] a;
    private final int[] b;
    private final int[] c;

    private final int[][][] matrix;

    public LCS3(int[] a, int[] b, int[] c) {
        this.a = a;
        this.b = b;
        this.c = c;

        this.matrix = new int[a.length + 1][b.length + 1][c.length + 1];
    }

    public int execute() {
        int m = a.length;
        int n = b.length;
        int l = c.length;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= l; k++) {
                    if (i == 0 || j == 0 || k == 0)
                        matrix[i][j][k] = 0;
                    else if (a[i - 1] == b[j - 1] && b[j - 1] == c[k - 1])
                        matrix[i][j][k] = matrix[i - 1][j - 1][k - 1] + 1;
                    else
                        matrix[i][j][k] = max(matrix[i - 1][j][k], matrix[i][j - 1][k], matrix[i][j][k - 1]);
                }
            }
        }

        return matrix[m][n][l];

//        return lcs3(a.length, b.length, c.length);
    }

    public int lcs3(int m, int n, int l) {
        if (m == 0 || n == 0 || l == 0)
            return 0;

        if (a[m - 1] == b[n - 1] && b[n - 1] == c[l - 1]) {
            return 1 + lcs3(m - 1, n - 1, l - 1);
        }

        return max(lcs3(m - 1, n, l), lcs3(m, n - 1, l), lcs3(m, n, l - 1));
    }

    private int max(int... numbers) {
        int maxValue = Integer.MIN_VALUE;

        for (int n : numbers)
            if (n > maxValue) maxValue = n;

        return maxValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }

        LCS3 l = new LCS3(a, b, c);
        System.out.println(l.execute());
    }
}

