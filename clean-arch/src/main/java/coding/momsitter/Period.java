package coding.momsitter;

public class Period {

    private final int start;

    private final int end;

    public Period(int start, int end) {
        if (start > end) throw new IllegalArgumentException("start must be less than end");

        this.start = start;
        this.end = end;
    }

    public int amount() {
        return end - start;
    }

    public int getOverlappedAmount(Period period) {
        if (!isOverlapped(period)) return 0;
        return 3;
    }

    public boolean isOverlapped(Period period) {
        return this.start < period.end && this.end > period.start;
    }

}
