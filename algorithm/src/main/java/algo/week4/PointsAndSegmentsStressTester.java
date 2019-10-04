package algo.week4;

import util.Helper;

import java.util.Random;

public class PointsAndSegmentsStressTester {

    private int[] starts;
    private int[] ends;
    private int[] points;

    private Random random = new Random();

    public PointsAndSegmentsStressTester(int max) {
        int s = random.nextInt(30);
        int p = random.nextInt(30);

        starts = new int[s];
        ends = new int[s];
        points = new int[p];

        initialize(max);
    }

    public void begin() {
        while(true) {
            executeOnce();
        }
    }

    private void executeOnce() {
        System.out.printf("------------------------------------------------------");
        System.out.println("begin execution");
        System.out.printf("------------------------------------------------------");

        PointsAndSegments ps = new PointsAndSegments(starts, ends);
        int[] fastCount = ps.fastCountSegments(points);
        Helper.printArray(fastCount);

        int[] naiveCount = ps.naiveCountSegments(points);
        Helper.printArray(naiveCount);

        for (int i = 0; i < fastCount.length; i++) {
            if (fastCount[i] != naiveCount[i]) {
                String message = "count doesn't match! i: " + i +
                        ", fastCount[i]: " + fastCount[i] +
                        ", naiveCount[i]: " + naiveCount[i];
                throw new RuntimeException(message);
            }
        }
    }

    private void initialize(int max) {
        for (int i = 0; i < starts.length; i++) {
            starts[i] = random.nextInt(max);
        }

        for (int i = 0; i < starts.length; i++) {
            ends[i] = random.nextInt(max - starts[i]) + starts[i];
        }

        for (int i = 0; i < points.length; i++) {
            points[i] = random.nextInt(max);
        }

        Helper.printArray(starts);
        Helper.printArray(ends);
        Helper.printArray(points);
    }

    public static void main(String[] args) {
        final int max = 10000;

        PointsAndSegmentsStressTester tester = new PointsAndSegmentsStressTester(max);
        tester.begin();
    }

}
