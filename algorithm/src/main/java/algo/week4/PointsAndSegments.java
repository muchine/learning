package algo.week4;

import java.util.*;

public class PointsAndSegments {

    private final int[] starts;

    private final int[] ends;

    PointsAndSegments(int[] starts, int[] ends) {
        this.starts = starts;
        this.ends = ends;

        Arrays.sort(starts);
        Arrays.sort(ends);
    }

    int[] fastCountSegments(int[] points) {
        List<Point> pointList = initPoints(points);
        Collections.sort(pointList);

        int[] cnt = new int[points.length];

        SegmentBuilder builder = new SegmentBuilder(starts, ends);

        int pointer = 0;
        for (int i = 0; i < pointList.size(); i++) {
            Point p = pointList.get(i);
            int index = builder.getWeightIndex(p.point, pointer);
//            System.out.println("point: " + points[i] + " index: " + index + ", pointer: " + pointer);
            if (index >= 0) {
                cnt[p.index] = builder.weights[index];
                pointer = index;
            } else {
                cnt[p.index] = 0;
                if (pointer > 0) {
                    pointer = builder.breaks.length;
                }
            }
        }

        return cnt;
    }

    int[] naiveCountSegments(int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    private List<Point> initPoints(int[] points) {
        List<Point> result = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            result.add(new Point(i, points[i]));
        }

        return result;
    }

    static class Point implements Comparable<Point> {

        int index;

        int point;

        public Point(int index, int point) {
            this.index = index;
            this.point = point;
        }

        @Override
        public int compareTo(Point o) {
            return this.point - o.point;
        }
    }

    static class SegmentBuilder {

        int[] starts;

        int[] ends;

        int[] breaks;

        int[] weights;

        int s = 0;

        int e = 0;

        SegmentBuilder(int[] starts, int[] ends) {
            this.starts = starts;
            this.ends = ends;

            this.breaks = new int[starts.length + ends.length];
            this.weights = new int[breaks.length];

            initialize();
        }

        int getWeightIndex(int point, int l) {
            if (l < 0 || l >= breaks.length) return -1;

            if (breaks[l] > point) return -1;

            for (int i = l; i < breaks.length - 1; i++) {
                if (breaks[i] <= point && breaks[i + 1] > point) {
                    return i;
                }
            }

            return -1;
        }

        private void initialize() {
            int weight = 0;

            for (int i = 0; i < breaks.length; i++) {
                if (!isEndedStartArray() && (isEndedEndArray() || starts[s] < ends[e])) {
                    breaks[i] = starts[s];
                    weights[i] = ++weight;
                    s++;
                } else if (!isEndedEndArray() && (isEndedStartArray() || starts[s] >= ends[e])) {
                    breaks[i] = ends[e];
                    weights[i] = --weight;
                    e++;
                }
            }

//            print();
        }

        private void print() {
            for (int i = 0; i < breaks.length; i++) {
                System.out.println(breaks[i] + ": " + weights[i]);
            }
        }

        private boolean isEndedStartArray() {
            return s >= starts.length;
        }

        private boolean isEndedEndArray() {
            return e >= ends.length;
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();

            starts[i] = Math.min(first, second);
            ends[i] = Math.max(first, second) + 1;
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        //use fastCountSegments
        PointsAndSegments ps = new PointsAndSegments(starts, ends);

        int[] cnt = ps.fastCountSegments(points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

