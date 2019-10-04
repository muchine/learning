package algo.week4;

public class Segment implements Comparable<Segment> {

    int start;

    int end;

    int weight;

    Segment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    boolean include(int point) {
        return this.start <= point && this.end > point;
    }

    @Override
    public int compareTo(Segment o) {
        return this.start - o.start;
    }

}
