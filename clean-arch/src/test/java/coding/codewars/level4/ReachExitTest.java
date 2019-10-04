package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReachExitTest {

    @Test public void sampleTests() {

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

        assertEquals(true,  ReachExit.pathFinder(a));
        assertEquals(false, ReachExit.pathFinder(b));
        assertEquals(true,  ReachExit.pathFinder(c));
        assertEquals(false, ReachExit.pathFinder(d));
    }
}
