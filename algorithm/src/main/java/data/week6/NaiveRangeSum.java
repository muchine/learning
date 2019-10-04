package data.week6;

import java.util.HashSet;
import java.util.Set;

public class NaiveRangeSum {

    public static final int MODULO = 1000000001;

    Set<Long> set = new HashSet<>();

    private long lastSum = 0;

    Set<Long> sums = new HashSet<>();

    public void add(long i) {
        set.add(parse(i));
    }

    public void remove(long i) {
        set.remove(parse(i));
    }

    public boolean find(long i) {
        return set.contains(parse(i));
    }

    public long sum(long l, long r) {
        long left = parse(l);
        long right = parse(r);

//        System.out.printf("naive from: %d, to: %d, last sum: %d\n", left, right, lastSum);

        long sum = 0;
        for (Long e : set) {
            if (e >= left && e <= right) {
                sum += e;
                sums.add(e);
            }
        }

        lastSum = sum;

        return sum;
    }

    private long parse(long i) {
        return i;
//        return (i + lastSum) % MODULO;
    }

}
