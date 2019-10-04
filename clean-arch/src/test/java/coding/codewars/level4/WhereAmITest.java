package coding.codewars.level4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class WhereAmITest {

    @Parameters
    public static Collection<Object[]> prepTests() {
        return Arrays.asList( new Object[] {"",       new Point(  0, 0)},
                new Object[] {"RLrl",   new Point(  0, 0)},
                new Object[] {"r5L2l4", new Point(  4, 3)},
                new Object[] {"r5L2l4", new Point(  0, 0)},
                new Object[] {"10r5r0", new Point(-10, 5)},
                new Object[] {"10r5r0", new Point(  0, 0)});
    }

    private String path;
    private Point  you;

    public WhereAmITest(String path, Point you) {
        this.path = path;  this.you = you;
    }

    @Test public void test() {
        assertEquals("With path=\""+path+"\":", you, WhereAmI.iAmHere(path));
    }
}
