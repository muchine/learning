package algo.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Closest {

    private final Point[] pointsByX;
    private final Point[] pointsByY;

    private final YComparator comparator = new YComparator();

    public Closest(int[] x, int[] y) {
        pointsByX = new Point[x.length];
        pointsByY = new Point[x.length];

        for (int i = 0; i < x.length; i++) {
            Point p = new Point(x[i], y[i]);
            pointsByX[i] = p;
            pointsByY[i] = p;
        }

        Arrays.sort(pointsByX);
        Arrays.sort(pointsByY, comparator);
//        util.Helper.printCollection("pointsByX", pointsByX);
//        util.Helper.printArray(pointsByY);
    }

    double fastMinimalDistance() {
        if (isCoincidentPointsExist()) return 0.0;

        return closest(0, pointsByX.length, pointsByY);
    }

    private double closest(int lo, int hi, Point[] ysorted) {
        if (hi <= lo + 1)
            return Double.POSITIVE_INFINITY;

        int mid = (lo + hi) / 2;

        Point[][] ySplited = splitYsorted(lo, mid, hi, ysorted);

        double delta1 = closest(lo, mid, ySplited[0]);
        double delta2 = closest(mid, hi, ySplited[1]);
        double delta = Math.min(delta1, delta2);

        return stripClosest(pointsByX[mid], delta, ysorted);
    }

    private Point[][] splitYsorted(int lo, int mid, int hi, Point[] ysorted) {
        Point[] left = new Point[mid - lo];
        Point[] right = new Point[hi - mid];

        Point median = pointsByX[mid];
        int m = 0;
        int n = 0;
        for (Point p : ysorted) {
            if (p.x < median.x)
                left[m++] = p;
            else if (p.x > median.x)
                right[n++] = p;
            else if (p.y < median.y)
                left[m++] = p;
            else
                right[n++] = p;
        }

        return new Point[][] { left, right };
    }

    private double stripClosest(Point median, double delta, Point[] ysorted) {
//        System.out.println("lo: " + lo + ", mid: " + mid + ", hi: " + hi + ", delta: " + delta);

        List<Point> within = new ArrayList<>();

        for (Point p : ysorted) {
            if (Math.abs(p.x - median.x) < delta) within.add(p);
        }

        for (int i = 0; i < within.size() - 1; i++) {
            Point p = within.get(i);

            for (int j = i + 1; j < within.size(); j++) {
                Point q = within.get(j);
                if (q.y - p.y > delta) break;
//                if (isInSamePlain(p, q, median)) continue;

                double distance = p.distanceTo(q);
//                System.out.println("dis: " + distance);
                if (distance < delta) {
                    delta = distance;
                }
            }
        }

        return delta;
    }

    private boolean isInSamePlain(Point p, Point q, Point median) {
        int pd = p.compareTo(median);
        int qd = q.compareTo(median);

        return (pd >=0 && qd >= 0) || (pd < 0 && qd < 0);
    }

    private boolean isCoincidentPointsExist() {
        for (int i = 0; i < pointsByX.length - 1; i++) {
            if (pointsByX[i].equals(pointsByX[i + 1])) {
                return true;
            }
        }

        return false;
    }

    double naiveMinimalDistance() {
        double ans = Double.POSITIVE_INFINITY;
        int minI = 0;
        int minJ = 0;

        for (int i = 0; i < pointsByX.length; i++) {
            Point x = pointsByX[i];
            for (int j = i + 1; j < pointsByX.length; j++) {
                Point y = pointsByX[j];
                double distance = x.distanceTo(y);
                if (distance < ans) {
                    ans = distance;
                    minI = i;
                    minJ = j;
                }
            }
        }

//        System.out.println("min i: " + minI + ", min j: " + minJ + ", ans: " + ans);
        return ans;
    }

    static class YComparator implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return o1.y - o2.y;
        }
    }

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isClose(Point p, double delta) {
            return Math.abs(this.x - p.x) <= delta && Math.abs(this.y - p.y) <= delta;
        }

        public double distanceTo(Point p) {
            double dx = new Double(x - p.x);
            double dy = new Double(y - p.y);
            double squaredSum = Math.pow(dx, 2) + Math.pow(dy, 2);

            return Math.sqrt(squaredSum);
        }

        public boolean lessThanByY(Point p) {
            return this.y != p.y ? this.y < p.y : this.x < p.x;
        }

        @Override
        public int compareTo(Point o) {
            return o.x == x ? Long.signum(y - o.y) : Long.signum(x - o.x);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Point)) return false;

            Point o = (Point) obj;
            return this.x == o.x && this.y == o.y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }

        Closest c = new Closest(x, y);
        System.out.println(c.naiveMinimalDistance());
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
