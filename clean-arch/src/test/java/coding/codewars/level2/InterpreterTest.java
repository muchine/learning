package coding.codewars.level2;

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
        System.out.println(interpreter.input("(4 + 2) * 3"));
//        assertEquals(18.0, interpreter.input("(4 + 2) * 3"), 0.01);
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