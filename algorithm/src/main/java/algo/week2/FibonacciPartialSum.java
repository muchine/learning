package algo.week2;

import java.util.*;

public class FibonacciPartialSum {

    public long getFibonacciPartialSum(long from, long to) {
        long fromSum = getFibonacciSum(from - 1);
        long toSum = getFibonacciSum(to);

        return (toSum + 10 - fromSum) % 10;
    }

    private long getFibonacciSum(long n) {
        List<Integer> period = getPisanoPeriod(10);

        int index = (int) ((n + 2) % period.size());
        return (period.get(index) + 9) % 10;
    }

    private List<Integer> getPisanoPeriod(int m) {
        int previous = 0;
        int current = 1;

        List<Integer> remainders = new ArrayList<Integer>();
        remainders.add(previous);

        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            int sum = previous + current;

            if (i > 2 && current % m == 0 && sum % m == 1) {
                return remainders;
            }

            remainders.add(current);

            previous = current % m;
            current = sum % m;
        }

        throw new RuntimeException("Pisano period not found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();

        FibonacciPartialSum f = new FibonacciPartialSum();
        System.out.println(f.getFibonacciPartialSum(from, to));
    }
}

