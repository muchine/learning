package advanced.week2;

import util.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Diet {

    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;
    static boolean eof;

    int solveDietProblem(double[][] a, double[] b, double[] c, double[] x) {
        Matrix matrix = iterate(augmentA(a, c.length), augmentB(b), c);
        Log.d("print matrix...");
        matrix.print();

        // if no solution exists
        if (matrix.noSolution) return -1;

        double optima = matrix.fetchOptima();
        if (optima >= Matrix.INFINITY) return 1;

        double[] answers = matrix.answers();
        for (int i = 0; i < x.length; i++) {
            x[i] = answers[i];
        }

        return 0;
    }

    double[][] augmentA(double[][] a, int m) {
        int rowCount = a.length + 1;
        double[][] result = new double[rowCount][m];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = a[i][j];
            }
        }

        // augmented row
        for (int j = 0; j < m; j++) {
            result[rowCount - 1][j] = 1;
        }

        return result;
    }

    double[] augmentB(double[] b) {
        int rowCount = b.length + 1;
        double[] result = new double[rowCount];

        for (int i = 0; i < b.length; i++) {
            result[i] = b[i];
        }

        result[rowCount - 1] = Matrix.INFINITY;

        return result;
    }

    Matrix iterate(double[][] a, double[] b, double[] c) {
        Matrix matrix = new Matrix(a, b, c);
        Position p = matrix.findPivotPosition();

        while (p != null) {
            Log.d("pivot row: %d, column: %d", p.x, p.y);

            matrix.pivot(p);

            p = matrix.findPivotPosition();
        }

        return matrix;
    }

    public static class Matrix {

        public static final double INFINITY = 1000000000.0;

        private final int n;  // Number of equations

        private final int m;  // Number of variables

        private final int numOfCoefficients;

        private final double[][] matrix;

        private final BasicVariable[] basicVariables;

        boolean noSolution = false;

        Matrix(double[][] a, double[] b, double[] c) {
            this.n = b.length;
            this.m = c.length;
            this.numOfCoefficients = n + m + 1;

            this.matrix = new double[n + 1][n + m + 2];
            this.basicVariables = new BasicVariable[n];

            // Initialize basic variable coefficients
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = b[i] >= 0 ? a[i][j] : -a[i][j];
                }
            }

            // Initialize slack variables
            for (int i = 0; i < n; i++) {
                matrix[i][m + i] = b[i] >= 0 ? 1 : -1;
            }

            // Initialize beta values
            for (int i = 0; i < n; i++) {
                matrix[i][n + m + 1] = b[i] >= 0 ? b[i] : -b[i];
            }

            // Initialize objective function coefficients
            for (int i = 0; i < m; i++) {
                matrix[n][i] = -c[i];
            }

            matrix[n][numOfCoefficients - 1] = 1;

            // Initialize basic variables
            for (int i = 0; i < n; i++) {
                basicVariables[i] = new BasicVariable(m + i, matrix[i][m + i]);  // slack variables
            }

            print();
        }

        double[] answers() {
            double[] answers = new double[numOfCoefficients];

            for (int i = 0; i < n; i++) {
                answers[basicVariables[i].index] = matrix[i][numOfCoefficients];
            }

            return answers;
        }

        double fetchOptima() {
            return matrix[n][numOfCoefficients];
        }

        Position findPivotPosition() {
            Position p = findPivotByNegativeRHS();
            Log.d("phase 1 pivot: %s", p);
            if (p != null || this.noSolution) return p;

            return findPivotByNonBasicVariable();
        }

        Position findPivotByNonBasicVariable() {
            int pivotColumn = findNonBasicVariableColumn();
            if (pivotColumn < 0) return null;

            int pivotRow = findPivotRowByRatio(pivotColumn);
            if (pivotRow < 0) return null;

            return new Position(pivotRow, pivotColumn);
        }

        int findNonBasicVariableColumn() {
            double minValue = 0;
            int column = -1;

            for (int i = 0; i < numOfCoefficients; i++) {
                if (matrix[n][i] >= minValue) continue;

                minValue = matrix[n][i];
                column = i;
            }

            return column;
        }

        Position findPivotByNegativeRHS() {
            int row = findStarredRow();
            if (row < 0) return null;

            int pivotColumn = findLargestPositiveColumn(row);
            if (pivotColumn < 0) {
                this.noSolution = true;
                return null;
            }

            int pivotRow = findPivotRowByRatio(pivotColumn);
            if (pivotRow < 0) {
                this.noSolution = true;
                return null;
            }

            return new Position(pivotRow, pivotColumn);
        }

        int findStarredRow() {
            for (int i = 0; i < n; i++) {
                if (basicVariables[i].negative) return i;
            }

            return -1;
        }

        int findLargestPositiveColumn(int row) {
            int column = -1;
            double maxValue = 0.0;

            for (int i = 0; i < numOfCoefficients; i++) {
                double value = matrix[row][i];
                if (value <= maxValue) continue;

                column = i;
                maxValue = value;
            }

            return column;
        }

        int findPivotRowByRatio(int pivotColumn) {
            double minValue = Double.MAX_VALUE;
            int row = -1;

            for (int i = 0; i < n; i++) {
                double pivotCandidate = matrix[i][pivotColumn];

                // we can't divide by 0 or allow negative pivot element
                if (pivotCandidate <= 0) continue;

                double ratio = matrix[i][numOfCoefficients] / pivotCandidate;
                Log.d("ratio.. i: %d, value: %s", i, ratio);
                if (ratio < 0 || ratio > minValue) continue;
                if (ratio == minValue && basicVariables[row].negative) continue;

                minValue = ratio;
                row = i;
            }

            return row;
        }

        void pivot(Position p) {
            scaleRow(p.x, p.y);
            reduce(p.x, p.y);
            basicVariables[p.x] = new BasicVariable(p.y, false);
            print();
        }

        private void scaleRow(int pivotRow, int pivotColumn) {
            double scaleFactor = matrix[pivotRow][pivotColumn];

            for (int i = 0; i <= numOfCoefficients; i++) {
                matrix[pivotRow][i] /= scaleFactor;
                calibrate(pivotRow, i);
            }
        }

        private void reduce(int pivotRow, int pivotColumn) {
            for (int i = 0; i <= n; i++) {
                if (i == pivotRow) continue;

                double scaleFactor = -matrix[i][pivotColumn];
                Log.d("scale factor: %s", scaleFactor);
                for (int j = 0; j <= numOfCoefficients; j++) {
                    matrix[i][j] += matrix[pivotRow][j] * scaleFactor;
                    calibrate(i, j);
                }
            }
        }

        void calibrate(int row, int column) {
            if (Math.abs(matrix[row][column]) < 0.0000000000001) matrix[row][column] = 0.0;
        }

        void print() {
            for (double[] e : matrix) {
                Helper.printArray(e);
            }

            Log.d("basic variables");
            Helper.printArray(basicVariables);

            Log.d("answers:");
            Helper.printArray(answers());

            Log.d("optima: %s", fetchOptima());
        }

    }

    static class BasicVariable {

        int index;  // column index of the corresponding variable in the matrix

        boolean negative;

        public BasicVariable(int index, boolean negative) {
            this.index = index;
            this.negative = negative;
        }

        public BasicVariable(int index, double value) {
            this(index, value < 0.0);
        }

        @Override
        public String toString() {
            return index + "(" + negative + ")";
        }
    }

    static class Position {

        private final int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" + "x=" + x + ", y=" + y + '}';
        }
    }

    private static class Log {

        static int level = 5;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    static String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public static void main(String[] args) throws IOException {
        Diet d = new Diet();

        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

        int n = nextInt();
        int m = nextInt();
        double[][] A = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = nextInt();
            }
        }
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = nextInt();
        }
        double[] c = new double[m];
        for (int i = 0; i < m; i++) {
            c[i] = nextInt();
        }
        double[] ansx = new double[m];
        int anst = d.solveDietProblem(A, b, c, ansx);

        if (anst == -1) {
            System.out.printf("No solution\n");
            return;
        }
        if (anst == 0) {
            System.out.printf("Bounded solution\n");
            for (int i = 0; i < m; i++) {
                System.out.printf("%.18f%c", ansx[i], i + 1 == m ? '\n' : ' ');
            }
            return;
        }
        if (anst == 1) {
            System.out.printf("Infinity\n");
            return;
        }
    }

}
