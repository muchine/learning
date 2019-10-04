package algo.week5;

import java.util.Scanner;

public class PlacingParentheses {

    private final long[] numbers;
    private final char[] operators;

    private final long[][] min;
    private final long[][] max;

    public PlacingParentheses(String exp) {
        int length = exp.length();
        int digitLength = (length + 1) / 2;
        int opLength = (length - 1) / 2;

        this.numbers = new long[digitLength];
        this.operators = new char[opLength];

        this.min = new long[digitLength][digitLength];
        this.max = new long[digitLength][digitLength];

        initialize(exp);
    }

    public long getMaximValue() {
      return max[0][numbers.length - 1];
    }

    private void initialize(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            if (i % 2 == 0) {
                numbers[i / 2] = Character.getNumericValue(exp.charAt(i));
            } else {
                operators[i / 2] = exp.charAt(i);
            }
        }

//        Helper.printArray(numbers);
//        Helper.printArray(operators);

        initMinMax();
    }

    private void initMinMax() {
        for (int i = 0; i < numbers.length; i++) {
            min[i][i] = numbers[i];
            max[i][i] = numbers[i];
         }

         int n = numbers.length;
         for (int s = 1; s < n; s++) {
            for (int i = 0; i < n - s; i++) {
                int j = i + s;
                minMax(i, j);
            }
         }

//        System.out.println("min matrix");
//        Helper.printArray(min);
//        System.out.println("max matrix");
//        Helper.printArray(max);
    }

    private void minMax(int i, int j) {
        long minVal = Long.MAX_VALUE;
        long maxVal = Long.MIN_VALUE;

        for (int k = i; k < j; k++) {
            char op = operators[k];

            long a = eval(max[i][k], max[k + 1][j], op);
            long b = eval(max[i][k], min[k + 1][j], op);
            long c = eval(min[i][k], max[k + 1][j], op);
            long d = eval(min[i][k], min[k + 1][j], op);

//            System.out.printf("i: %s, j: %s, a: %s, b: %s, c: %s, d: %s\n", i, j, a, b, c, d);

            minVal = minValue(minVal, a, b, c, d);
            maxVal = maxValue(maxVal, a, b, c, d);
        }

        min[i][j] = minVal;
        max[i][j] = maxVal;
    }

    private long minValue(long... numbers) {
        long minValue = Long.MAX_VALUE;

        for (long n : numbers)
            if (n < minValue) minValue = n;

        return minValue;
    }

    private long maxValue(long... numbers) {
        long maxValue = Long.MIN_VALUE;

        for (long n : numbers)
            if (n > maxValue) maxValue = n;

        return maxValue;
    }

    private long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();

        PlacingParentheses p = new PlacingParentheses(exp);
        System.out.println(p.getMaximValue());
    }
}

