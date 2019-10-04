package graph.week5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConnectingPoints {

    private Point[] points;

    private List<Edge> edges = new ArrayList<>();

    public double minimumDistance(int[] x, int[] y) {
        points = new Point[x.length];
        initialize(x, y);

        return run();
    }

    private double run() {
        double result = 0.0;

        Collections.sort(edges);

        for (Edge e : edges) {
            Point u = e.o;
            Point v = e.p;

            Point ur = u.root();
            Point vr = v.root();

            if (ur == vr) continue;

            result += e.distance;
            if (ur.rank > vr.rank)
                ur.merge(vr);
            else
                vr.merge(ur);
        }

        return result;
    }

    private void initialize(int[] x, int[] y) {
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(new Edge(points[i], points[j]));
            }
        }
    }

    class Point {

        int x, y;

        Point parent;

        int rank;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void merge(Point p) {
            if (this.rank < p.rank)
                throw new RuntimeException("Rank is smaller than the merging point");

            p.parent = this;
            if (this.rank == p.rank) this.rank += 1;
        }

        Point root() {
            if (parent == null) return this;

            Point root = parent.root();
            this.parent = root;

            return root;
        }

        @Override
        public String toString() {
            return "Point(" + x + "," + y + ")";
        }
    }

    class Edge implements Comparable<Edge> {

        Point o;

        Point p;

        double distance;

        public Edge(Point o, Point p) {
            this.o = o;
            this.p = p;
            distance = Math.sqrt(Math.pow(o.x - p.x, 2) + Math.pow(o.y - p.y, 2));
        }

        @Override
        public int compareTo(Edge o) {
            if (distance == o.distance) return 0;
            return distance - o.distance < 0 ? -1 : 1;
        }

        @Override
        public String toString() {
            return "Edge(" + o.x + "," + o.y + " - " + p.x + "," + p.y + ", " + distance + ")";
        }
    }

    private static class Log {

        static int level = 5;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }

        ConnectingPoints cp = new ConnectingPoints();
        System.out.println(cp.minimumDistance(x, y));
    }
}

