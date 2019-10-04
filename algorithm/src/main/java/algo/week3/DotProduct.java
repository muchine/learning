package algo.week3;

import java.util.*;

public class DotProduct {

    List<Long> profits = new ArrayList<>();

    List<Long> clicks = new ArrayList<>();

    DotProduct(long[] a, long[] b) {
        for (int i = 0; i < a.length; i++) {
            profits.add(a[i]);
        }


        for (int i = 0; i < b.length; i++) {
            clicks.add(b[i]);
        }

        DescendingComparator comparator = new DescendingComparator();

        profits.sort(comparator);
        clicks.sort(comparator);
    }

    public long maxDotProduct() {
        long sum = 0;
        for (int i = 0; i < profits.size(); i++) {
            sum += profits.get(i) * clicks.get(i);
        }

        return sum;
    }

    private class DescendingComparator implements Comparator<Long> {

        @Override
        public int compare(Long o1, Long o2) {
            return (int) (o2 - o1);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }

        DotProduct p = new DotProduct(a, b);
        System.out.println(p.maxDotProduct());
    }

}

