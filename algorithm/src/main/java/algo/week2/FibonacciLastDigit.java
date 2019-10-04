package algo.week2;

import java.util.*;

public class FibonacciLastDigit {

    public int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

    public int getFibonacciLastDigit(int n) {
        if (n <= 1) return n;

        int previous = 0;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            int sum = (previous + current) % 10;

            previous = current;
            current = sum;
        }

        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        FibonacciLastDigit f = new FibonacciLastDigit();
        int c = f.getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

