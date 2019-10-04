package coding.codewars.level4;

public class Matrix {

    public int det(int[][] matrix) {
        if (matrix.length == 1) return matrix[0][0];
        if (matrix.length == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            int product = matrix[0][i] * det(minor(matrix, i));
            sum += i % 2 == 0 ? product : -product;
        }

        return sum;
    }

    private int[][] minor(int[][] matrix, int column) {
        int[][] minor = new int[matrix.length - 1][matrix.length - 1];

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j != column) minor[i - 1][j < column ? j : j - 1] = matrix[i][j];
            }
        }

        return minor;
    }

    public static int determinant(int[][] matrix) {
        for (int[] row : matrix) {
            StringBuilder sb = new StringBuilder();
            for (int v : row) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(v);
            }

            System.out.println(sb.toString());
        }

        return new Matrix().det(matrix);
    }
}
