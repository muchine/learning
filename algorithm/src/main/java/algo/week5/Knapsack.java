package algo.week5;

import java.util.Scanner;

public class Knapsack {

    private final int capacity;
    private final int[] weights;

    private final int[][] values;

    public Knapsack(int capacity, int[] weights) {
        this.capacity = capacity;
        this.weights = weights;

        values = new int[2][capacity + 1];
    }

    public int optimalWeight() {
        for (int i = 1; i <= weights.length; i++) {
            for (int c = 1; c <= capacity; c++) {
                int currentRow = i % 2;
                int previousRow = Math.abs(currentRow - 1);

                values[currentRow][c] = values[previousRow][c];
                if (weights[i - 1] <= c) {
                    int val = values[previousRow][c - weights[i - 1]] + weights[i - 1];
                    if (values[currentRow][c] < val)
                        values[currentRow][c] = val;
                }
            }
        }

//        Helper.printArray(values);
        return values[weights.length % 2][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }

        Knapsack k = new Knapsack(W, w);
        System.out.println(k.optimalWeight());
    }
}

