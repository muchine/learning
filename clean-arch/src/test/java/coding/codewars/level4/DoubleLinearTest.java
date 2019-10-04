package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DoubleLinearTest {

    private static void testing(int actual, int expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests dblLinear");
        testing(DoubleLinear.dblLinear(10), 22);
        testing(DoubleLinear.dblLinear(20), 57);
        testing(DoubleLinear.dblLinear(30), 91);
        testing(DoubleLinear.dblLinear(50), 175);
    }

    @Test(timeout = 16000)
    public void test1() {
        int value = DoubleLinear.dblLinear(1000000);
        System.out.println(value);
    }
}

