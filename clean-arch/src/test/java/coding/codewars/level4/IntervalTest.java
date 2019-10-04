package coding.codewars.level4;

import org.junit.Test;

import static coding.codewars.level4.Interval.sumIntervals;
import static org.junit.Assert.assertEquals;

public class IntervalTest {

    @Test
    public void shouldHandleNullOrEmptyIntervals() {
        assertEquals(0, sumIntervals(null));
        assertEquals(0, sumIntervals(new int[][]{}));
        assertEquals(0, sumIntervals(new int[][]{{4, 4}, {6, 6}, {8, 8}}));
    }

    @Test
    public void shouldAddDisjoinedIntervals() {
        assertEquals(9, sumIntervals(new int[][]{{1, 2}, {6, 10}, {11, 15}}));
        assertEquals(11, sumIntervals(new int[][]{{4, 8}, {9, 10}, {15, 21}}));
        assertEquals(7, sumIntervals(new int[][]{{-1, 4}, {-5, -3}}));
        assertEquals(78, sumIntervals(new int[][]{{-245, -218}, {-194, -179}, {-155, -119}}));
    }

    @Test
    public void shouldAddAdjacentIntervals() {
        assertEquals(54, sumIntervals(new int[][]{{1, 2}, {2, 6}, {6, 55}}));
        assertEquals(23, sumIntervals(new int[][]{{-2, -1}, {-1, 0}, {0, 21}}));
    }

    @Test
    public void shouldAddOverlappingIntervals() {
        assertEquals(7, sumIntervals(new int[][]{{1, 4}, {7, 10}, {3, 5}}));
        assertEquals(6, sumIntervals(new int[][]{{5, 8}, {3, 6}, {1, 2}}));
        assertEquals(19, sumIntervals(new int[][]{{1, 5}, {10, 20}, {1, 6}, {16, 19}, {5, 11}}));
    }

    @Test
    public void shouldHandleMixedIntervals() {
        assertEquals(13, sumIntervals(new int[][]{{2, 5}, {-1, 2}, {-40, -35}, {6, 8}}));
        assertEquals(1234, sumIntervals(new int[][]{{-7, 8}, {-2, 10}, {5, 15}, {2000, 3150}, {-5400, -5338}}));
        assertEquals(158, sumIntervals(new int[][]{{-101, 24}, {-35, 27}, {27, 53}, {-105, 20}, {-36, 26},}));
    }

    @Test
    public void test1() {
        int[][] intervals = new int[][] {
                {-9266, 1847}, {-7851, -5304}, {5531, 5758}, {-7179, -3370}, {-5238, -1029}, {-6798, -1306}, {6503, 9651}, {1884, 3759}, {-2049, 8441}, {-6980, 7753}, {-8858, -2187}, {-7149, 881}, {-5102, 1434}, {-6892, -2680}, {4790, 7783}, {-3431, 4376}, {-8645, -592}, {-7500, -6592}, {-6707, 4649}, {386, 8226}, {-7430, 4067}
        };

        assertEquals(18917, sumIntervals(intervals));
    }

}
