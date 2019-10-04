package algo.week3;

import java.util.*;

public class CoveringSegments {

    public List<Integer> optimalPoints(List<Segment> segments) {
        List<Integer> points = new ArrayList<>();

        Collections.sort(segments);

        int maxIterationSize = segments.size();

        for (int i = 0; i < maxIterationSize; i++) {
            GreedyResult result = doGreedyIteration(segments);
//            System.out.println("result point: " + result.point);
//            System.out.println("result segments: " + result.segments.size());

            points.add(result.point);
            segments = result.segments;

            if (result.segments.isEmpty()) break;
        }

        return points;
    }

    // Assume that the segments are sorted in the order of minimum right end
    private GreedyResult doGreedyIteration(List<Segment> segments) {
        Segment segment = segments.get(0);
        List<Segment> notOverlapped = new ArrayList<>();

        for (int i = 1; i < segments.size(); i++) {
            Segment compared = segments.get(i);

            if (!segment.isOverlapped(compared)) {
                notOverlapped.add(compared);
            }
        }

        return new GreedyResult(segment.end, notOverlapped);
    }

    static class Segment implements Comparable<Segment> {

        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        boolean isOverlapped(Segment s) {
            return this.end >= s.start && this.start <= s.end;
        }

        @Override
        public int compareTo(Segment o) {
            return this.end - o.end;
        }
    }

    private class GreedyResult {

        int point;

        List<Segment> segments;

        public GreedyResult(int point, List<Segment> segments) {
            this.point = point;
            this.segments = segments;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }

        CoveringSegments s = new CoveringSegments();
        List<Integer> points = s.optimalPoints(segments);
        System.out.println(points.size());
        for (int point : points) {
            System.out.print(point + " ");
        }
    }

}
 
