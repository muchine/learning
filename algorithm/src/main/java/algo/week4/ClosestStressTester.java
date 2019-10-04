package algo.week4;

import util.Helper;

import java.util.Random;

public class ClosestStressTester {

    private int[] x;
    private int[] y;
    private int max;

    private Random random = new Random();

    public ClosestStressTester(int max) {
        int n = 30;

        x = new int[n];
        y = new int[n];
        this.max = max;
    }

    public void begin() {
        while(true) {
            executeOnce();
        }
    }

    private void executeOnce() {
        System.out.println("------------------------------------------------------");
        System.out.println("begin execution");
        System.out.println("------------------------------------------------------");

        initialize(max);

        Closest c = new Closest(x, y);

        double fastDistance = c.fastMinimalDistance();
        double naiveDistance = c.naiveMinimalDistance();

        if (fastDistance - naiveDistance > 0.001) {
            throw new RuntimeException("distances do not match: fastDistance: " + fastDistance + ", naiveDistance: " + naiveDistance);
        }
    }

    private void initialize(int max) {
        for (int i = 0; i < x.length; i++) {
            x[i] = random.nextInt(max);
        }

        for (int i = 0; i < y.length; i++) {
            y[i] = random.nextInt(max);
        }

        Helper.printArray(x);
        Helper.printArray(y);
    }

    public static void main(String[] args) {
        ClosestStressTester tester = new ClosestStressTester(100);
        tester.begin();
    }
}
