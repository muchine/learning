package algo.week2;

public class Fibonacci {

    private long[] sequence;

    public long calc_fib(int n) {
        if (n <= 1)
            return n;

        return calc_fib(n - 1) + calc_fib(n - 2);
    }

    public long calculate(int n) {
        if (n <= 1) return n;

        sequence = new long[n + 1];

        sequence[0] = 0L;
        sequence[1] = 1L;

        for (int i = 2; i <= n; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }

        return sequence[n];
    }

    public long[] getSequence() {
        return sequence;
    }

    public static void main(String args[]) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//
        Fibonacci f = new Fibonacci();
//        System.out.println(f.calculate(n));
//        System.out.println(f.calc_fib(n));

        f.calculate(15);
        long[] sequence = f.getSequence();
        for (long e : sequence) {
            System.out.println(e % 5);
        }
    }
}
