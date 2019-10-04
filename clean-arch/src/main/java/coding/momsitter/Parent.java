package coding.momsitter;

import java.util.ArrayList;
import java.util.List;

public class Parent {

    private final List<Period> schedule = new ArrayList();

    public int findCoverage(List<Period> sitterSchedule) {
        int overrapedSum = 0;
        for (Period p : schedule) {
            for (Period sitterPeriod : sitterSchedule) {
                overrapedSum += p.getOverlappedAmount(sitterPeriod);
            }
        }

        return overrapedSum / getPeriodSum();
    }

    public int getPeriodSum() {
        int sum = 0;
        for (Period p : schedule) {
            sum += p.amount();
        }

        return sum
    }

}
