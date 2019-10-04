package algo.week2;

import java.math.BigDecimal;
import java.util.Scanner;

public class LCM {

    public long lcm_naive(int a, int b) {
        for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
    }

    public long calculateLCM(int a, int b) {
        int gcd = calculateGCD(a, b);
        BigDecimal product = new BigDecimal(a).multiply(new BigDecimal(b));
        BigDecimal result = product.divide(new BigDecimal(gcd));
        return result.longValue();
    }

    private int calculateGCD(int a, int b) {
        int x = a;
        int y = b;

        while (y > 0) {
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

        LCM l = new LCM();
        System.out.println(l.calculateLCM(a, b));
    }
}
