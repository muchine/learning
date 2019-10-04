package coding.codewars.level4;

import java.util.HashMap;
import java.util.Map;

/**
 * Codewars style ranking system
 * https://www.codewars.com/kata/codewars-style-ranking-system/train/java
 */
public class RankingSystem {

    private Map<Integer, Integer> grades = new HashMap<>();

    int rank = -8;
    int progress = 0;

    public RankingSystem() {
        int[] ranks = new int[] { -8,-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7,8 };
        for (int i = 0; i < ranks.length; i++) {
            grades.put(ranks[i], i);
        }
    }

    public void incProgress(int activityRank) {
        if (activityRank < -8 || activityRank == 0 || activityRank > 8) throw new IllegalArgumentException();
        if (rank == 8) return;

        int increase = progressIncrease(activityRank);
        progress = progress + increase;
        while (progress >= 100) {
            rank = nextRank();
            progress -= 100;

            if (rank == 8) {
                progress = 0;
                break;
            }
        }
    }

    private int nextRank() {
        int grade = grades.get(rank);
        for (Map.Entry<Integer, Integer> entry : grades.entrySet()) {
            if (entry.getValue() == grade + 1) return entry.getKey();
        }

        throw new IllegalStateException("already reached highest rank.");
    }

    private int progressIncrease(int rank) {
        int grade = grades.get(rank);
        int currentGrade = grades.get(this.rank);

        if (currentGrade == grade) return 3;
        if (currentGrade - grade == 1) return 1;
        if (currentGrade - grade > 1) return 0;
        if (currentGrade < grade) {
            int diff = grade - currentGrade;
            return 10 * diff * diff;
        }

        throw new IllegalArgumentException();
    }

}
