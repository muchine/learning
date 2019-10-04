package algo.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciHuge {

    public long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    public long getFibonacciHuge(long n, long m) {
        List<Integer> period = getPisanoPeriod((int) m);
        long p = (long) period.size();
        return (long) period.get((int) (n % p));
    }

    public List<Integer> getPisanoPeriod(int m) {
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
        long n = scanner.nextLong();
        long m = scanner.nextLong();

        FibonacciHuge f = new FibonacciHuge();
        System.out.println(f.getFibonacciHuge(n, m));
    }
}

