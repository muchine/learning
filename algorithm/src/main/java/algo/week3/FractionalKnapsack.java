package algo.week3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FractionalKnapsack {

    private int capacity;

    private int[] values;

    private int[] weights;

    private List<Ratio> ratios = new ArrayList<>();

    FractionalKnapsack(int capacity, int[] values, int[] weights) {
        this.capacity = capacity;
        this.values = values;
        this.weights = weights;

        initializeRatios();
    }

    public double getOptimalValue() {
        double value = 0;

        // Take greedy choices
        for (Ratio r : ratios) {
            if (capacity == 0) break;

            if (capacity >= weights[r.index]) {
                value += values[r.index];
                capacity -= weights[r.index];
            } else {
                value += capacity * r.ratio;
                capacity = 0;
            }
        }

        return value;
    }

    private void initializeRatios() {
        for (int i = 0; i < values.length; i++) {
            ratios.add(new Ratio(i, values[i], weights[i]));
        }

        // Sort ratis
        ratios.sort(new Comparator<Ratio>() {
            @Override
            public int compare(Ratio o1, Ratio o2) {
                double diff = o1.ratio - o2.ratio;
                if (diff > 0) return -1;
                if (diff < 0) return 1;

                return 0;
            }
        });
    }

    public List<Ratio> getRatios() {
        return ratios;
    }

    class Ratio {

        int index;

        double ratio;

        Ratio(int index, int value, int weight) {
            this.index = index;
            this.ratio = (double) value / weight;
        }

    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }

        FractionalKnapsack k = new FractionalKnapsack(capacity, values, weights);
        System.out.println(k.getOptimalValue());
    }
} 
