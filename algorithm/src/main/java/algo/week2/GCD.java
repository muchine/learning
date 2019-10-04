package algo.week2;

import java.util.*;

public class GCD {

    public int gcd_naive(int a, int b) {
        int current_gcd = 1;
        for (int d = 2; d <= a && d <= b; ++d) {
            if (a % d == 0 && b % d == 0) {
                if (d > current_gcd) {
                    current_gcd = d;
                }
            }
        }

        return current_gcd;
    }

    public int calculate(int a, int b) {
        int x = a;
        int y = b;

        while(y > 0) {
            int mod = x % y;
            x = y;
            y = mod;
        }

        return x;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        GCD g = new GCD();
        System.out.println(g.calculate(a, b));
    }
}
