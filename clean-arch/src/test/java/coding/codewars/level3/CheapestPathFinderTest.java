package coding.codewars.level3;

import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheapestPathFinderTest {

    @Test
    public void sampleTest1() {

        int[][] tollMap = {{1, 9, 1}, {2, 9, 1}, {2, 1, 1}};
        Point start = new Point(0, 0),
                finish = new Point(0, 2);
        List<String> expected = Arrays.asList("down", "down", "right", "right", "up", "up");

        assertEquals(expected, CheapestPathFinder.cheapestPath(tollMap, start, finish));
    }

    @Test
    public void sampleTest2() {

        int[][] tollMap = {{1, 4, 1}, {1, 9, 1}, {1, 1, 1}};
        Point start = new Point(0, 0),
                finish = new Point(0, 2);
        List<String> expected = Arrays.asList("right", "right");

        assertEquals(expected, CheapestPathFinder.cheapestPath(tollMap, start, finish));
    }

    @Test
    public void sampleTest3_NoCost() {

        int[][] tollMap = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        Point start = new Point(1, 1),
                finish = new Point(1, 1);
        List<String> expected = Arrays.asList();

        assertEquals(expected, CheapestPathFinder.cheapestPath(tollMap, start, finish));
    }

}
