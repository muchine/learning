package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RankingSystemTest {

    @Test
    public void test1() {
        RankingSystem user = new RankingSystem();
        assertEquals(-8, user.rank);
        assertEquals(0, user.progress);

        user.incProgress(-7);
        assertEquals(10, user.progress);

        user.incProgress(-5); // will add 90 progress
        assertEquals(-7, user.rank);
        assertEquals(0, user.progress);
    }

    @Test
    public void test2() {
        RankingSystem user = new RankingSystem();
        increase(user, -8);
        increase(user, -7);
        increase(user, -6);
        increase(user, -5);
        increase(user, -4);
        increase(user, -8);
        increase(user, 1);
        assertEquals(-2, user.rank);
    }

    private void increase(RankingSystem user, int rank) {
        user.incProgress(rank);
        System.out.println("rank: " + user.rank);
        System.out.println("progress: " + user.progress);
    }

}
