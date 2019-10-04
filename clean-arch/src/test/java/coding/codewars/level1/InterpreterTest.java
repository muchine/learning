package coding.codewars.level1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@SuppressWarnings("Duplicates")
public class InterpreterTest {

    @Test
    public void basicTests() {
        Interpreter interpreter = new Interpreter();

        // Basic arithmetic
        assertEquals(2, interpreter.input("1 + 1"), 0.0);
        assertEquals(1, interpreter.input("2 - 1"), 0.0);
        assertEquals(6, interpreter.input("2 * 3"), 0.0);
        assertEquals(2, interpreter.input("8 / 4"), 0.0);
        assertEquals(3, interpreter.input("7 % 4"), 0.0) ;


        // Variables
        assertEquals(1, interpreter.input("x = 1"), 0.0);
        assertEquals(1, interpreter.input("x"), 0.0);
        assertEquals(4, interpreter.input("x + 3"), 0.0);
        assertFail("input: 'y'", () -> interpreter.input("y"));

        // Functions
        interpreter.input("fn avg x y => (x + y) / 2");
        assertEquals(3, interpreter.input("avg 4 2"), 0.0);
        assertFail("input: 'avg 7'", () -> interpreter.input("avg 7"));
        assertFail("input: 'avg 7 2 4'", () -> interpreter.input("avg 7 2 4"));

        // Conflicts
        assertFail("input: 'fn x => 0'", () -> interpreter.input("fn x => 0"));
        assertFail("input: 'avg = 5'", () -> interpreter.input("avg = 5"));
    }

    @Test
    public void basicTests2() {
        Interpreter interpreter = new Interpreter();

        // Basic arithmetic
        assertEquals(2, interpreter.input("1 + 1"), 0.0);
        assertEquals(1, interpreter.input("2 - 1"), 0.0);
        assertEquals(6, interpreter.input("2 * 3"), 0.0);
        assertEquals(2, interpreter.input("8 / 4"), 0.0);
        assertEquals(3, interpreter.input("7 % 4"), 0.0) ;


        // Variables
        assertEquals(1, interpreter.input("x = 1"), 0.0);
        assertEquals(1, interpreter.input("x"), 0.0);
        assertEquals(4, interpreter.input("x + 3"), 0.0);
        assertFail("input: 'y'", () -> interpreter.input("y"));
    }

    @Test
    public void test1() {
        Interpreter interpreter = new Interpreter();
        assertNull(interpreter.input("    "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test2() {
        Interpreter interpreter = new Interpreter();
        interpreter.input("1 2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test3() {
        Interpreter interpreter = new Interpreter();
        interpreter.input("1two");
    }

    @Test
    public void test4() {
        Interpreter interpreter = new Interpreter();
        assertEquals(18.0, interpreter.input("(4 + 2) * 3"), 0.01);
    }

    @Test
    public void test5() {
        Interpreter interpreter = new Interpreter();
        assertEquals(7, interpreter.input("x = y = 7"), 0.01);
    }

    @Test
    public void test6() {
        Interpreter interpreter = new Interpreter();
        assertEquals(16, interpreter.input("x = 13 + (y = 3)"), 0.01);
    }

    @Test
    public void test7() {
        Interpreter interpreter = new Interpreter();

        interpreter.input("x = 3");
        interpreter.input("fn add x y => x + y * 3");
        assertEquals(21.0, interpreter.input("add 4 * 3 5 - 2"), 0.01);

        interpreter.input("fn inc x => x + 1");
        assertEquals(35.0, interpreter.input("add inc 4 4 * 3 - 2"), 0.01);
        assertEquals(38.0, interpreter.input("add inc 4 inc 4 * x - 2"), 0.01);
        assertEquals(88.0, interpreter.input("4 + 3 * (add inc 4 3) * 2"), 0.01);
        assertEquals(73.0, interpreter.input("4 + 3 * add inc 4 3 * 2"), 0.01);
    }

    @Test
    public void test8() {
        Interpreter interpreter = new Interpreter();

        assertEquals(2.0, interpreter.input("x = 3 * 4 - 2 * 5"), 0.01);
        assertEquals(10.0, interpreter.input("y = x + 4 * 2"), 0.01);
        assertEquals(8.0, interpreter.input("y - x"), 0.01);
    }

    @Test
    public void test9() {
        Interpreter interpreter = new Interpreter();
        System.out.println(interpreter.input("fn f => 1"));
        assertEquals(3.0, interpreter.input("f + 2"), 0.01);
    }

    @Test
    public void test10() {
        Interpreter interpreter = new Interpreter();
        assertEquals(7.0, interpreter.input("x = 7"), 0.01);
        assertEquals(7.0, interpreter.input("x"), 0.01);
        assertEquals(10.0, interpreter.input("x + 3"), 0.01);
    }

    @Test
    public void test11() {
        Interpreter interpreter = new Interpreter();
        assertEquals(23.0, interpreter.input("x = 23"), 0.01);
        assertEquals(25.0, interpreter.input("y = 25"), 0.01);
        assertEquals(0.0, interpreter.input("z = 0"), 0.01);
        assertNull(interpreter.input("fn one => 1"));
        assertNull(interpreter.input("fn avg x y => (x + y) / 2"));
        assertNull(interpreter.input("fn echo x => x"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test12() {
        Interpreter interpreter = new Interpreter();
        interpreter.input("fn add x y => x + z");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test13() {
        Interpreter interpreter = new Interpreter();
        interpreter.input("fn add x x => x + x");
    }

    private static void assertFail(String msg, Runnable runnable) {
        try {
            runnable.run();
            fail(msg);
        } catch (Exception e) {
            // Ok
        }
    }
}
