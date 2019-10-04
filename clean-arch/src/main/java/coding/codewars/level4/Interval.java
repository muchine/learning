package coding.codewars.level4;

import java.util.HashSet;
import java.util.Set;

/**
 * Sum of Intervals
 * https://www.codewars.com/kata/sum-of-intervals/train/java
 */
public class Interval {

    private Set<Range> ranges = new HashSet<>();

    Interval(int[][] intervals) {
        if (intervals == null) return;

        for (int[] interval : intervals) {
            Range range = new Range(interval[0], interval[1]);
            Set<Range> overlapped = getOverlappedRanges(range);
            if (overlapped.isEmpty()) {
                ranges.add(range);
            } else {
                ranges.removeAll(overlapped);

                overlapped.add(range);
                ranges.add(merge(overlapped));
            }
        }

        System.out.println(ranges);
    }

    public int sum() {
        return ranges.stream().mapToInt(r -> r.length()).sum();
    }

    private Range merge(Set<Range> ranges) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;

        for (Range range : ranges) {
            if (range.start < start) start = range.start;
            if (range.end > end) end = range.end;
        }

        return new Range(start, end);
    }

    private Set<Range> getOverlappedRanges(Range range) {
        Set<Range> overlapped = new HashSet<>();

        for (Range r : ranges) {
            if (range.isOverlapped(r)) overlapped.add(r);
        }

        return overlapped;
    }

    class Range {
        int start, end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int length() {
            return end - start;
        }

        public boolean contains(int n) {
            return start <= n && n <= end;
        }

        public boolean isOverlapped(Range range) {
            return range.contains(start) || range.contains(end) || contains(range.start) || contains(range.end);
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", start, end);
        }
    }

    public static int sumIntervals(int[][] intervals) {
        return new Interval(intervals).sum();
    }
}
