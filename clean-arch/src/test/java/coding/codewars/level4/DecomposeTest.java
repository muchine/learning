package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecomposeTest {

    Decompose d = new Decompose();

    @Test
    public void test1() {
        assertEquals("1 2 4 10",  d.decompose(11));
    }

    @Test
    public void test2() {
        System.out.println(d.decompose(50));
    }

}
