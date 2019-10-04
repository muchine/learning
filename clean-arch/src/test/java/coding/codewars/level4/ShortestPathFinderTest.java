package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShortestPathFinderTest {

    @Test
    public void sampleTests() {

        String a = ".W.\n"+
                ".W.\n"+
                "...",

                b = ".W.\n"+
                        ".W.\n"+
                        "W..",

                c = "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        "......",

                d = "......\n"+
                        "......\n"+
                        "......\n"+
                        "......\n"+
                        ".....W\n"+
                        "....W.";

        assertEquals(a, 4,  ShortestPathFinder.pathFinder(a));
        assertEquals(b, -1, ShortestPathFinder.pathFinder(b));
        assertEquals(c, 10,  ShortestPathFinder.pathFinder(c));
        assertEquals(d, -1, ShortestPathFinder.pathFinder(d));
    }

    @Test
    public void test1() {
        String a = "..W.WW....\n" +
                ".....W..W.\n" +
                "......W..W\n" +
                "WW.W...W..\n" +
                "..W..W..W.\n" +
                "......W..W\n" +
                ".....W....\n" +
                "...WWW...W\n" +
                ".......W..\n" +
                "W...W..WW.";

        assertEquals(a, 18, ShortestPathFinder.pathFinder(a));
    }

}
