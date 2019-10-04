package advanced.week2;

import java.io.IOException;
import java.util.Scanner;

class EnergyValues {

    private final double[][] a;

    private final double[] b;

    private final int size;

    private int pos = 0;

    public EnergyValues(double[][] a, double[] b) {
        this.a = a;
        this.b = b;
        this.size = a.length;
    }

    public double[] solveEquation() {
        int size = a.length;
        Log.d("size: %d", size);

        for (int step = 0; step < size; ++step) {
            Position pivot_element = selectPivotElement();
            swapLines(pivot_element);
            processPivotElement();

            pos++;
        }

        return b;
    }

    Position selectPivotElement() {
        for (int i = pos; i < size; i++) {
            if (a[i][pos] != 0) return new Position(i, pos);
        }

        throw new RuntimeException("cannot find leftmost non-zero element");
    }

    void swapLines(Position pivot) {
        for (int column = 0; column < size; ++column) {
            double tmpa = a[pivot.column][column];
            a[pivot.column][column] = a[pivot.raw][column];
            a[pivot.raw][column] = tmpa;
        }

        double tmpb = b[pivot.column];
        b[pivot.column] = b[pivot.raw];
        b[pivot.raw] = tmpb;
    }

    void processPivotElement() {
        rescalePivot();

        for (int i = 0; i < size; i++) {
            if (i == pos) continue;

            double scale = isSameSign(a[pos][pos], a[i][pos]) ? -Math.abs(a[i][pos]) : Math.abs(a[i][pos]);
            for (int c = 0; c < size; c++) {
                a[i][c] = a[i][c] + scale * a[pos][c];
            }

            b[i] = b[i] + scale * b[pos];
        }
    }

    private boolean isSameSign(double x, double y) {
        return (x > 0 && y > 0) || (x < 0 && y < 0);
    }

    void rescalePivot() {
        double scale = a[pos][pos];

        for (int i = 0; i < size; i++) {
            a[pos][i] /= scale;
        }

        b[pos] /= scale;
    }

    private static class Log {

        static int level = 0;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    static class Position {
        Position(int column, int raw) {
            this.column = column;
            this.raw = raw;
        }

        int column;
        int raw;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        if (size == 0) return;

        double a[][] = new double[size][size];
        double b[] = new double[size];
        for (int raw = 0; raw < size; ++raw) {
            for (int column = 0; column < size; ++column)
                a[raw][column] = scanner.nextInt();
            b[raw] = scanner.nextInt();
        }

        EnergyValues ev = new EnergyValues(a, b);
        double[] solution = ev.solveEquation();

        for (int raw = 0; raw < solution.length; ++raw)
            System.out.printf("%.20f\n", solution[raw]);
    }

}
