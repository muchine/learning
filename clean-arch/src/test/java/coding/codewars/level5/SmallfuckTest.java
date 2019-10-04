package coding.codewars.level5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmallfuckTest {

    @Test
    public void testExamples() {
        // Flips the leftmost cell of the tape
        assertEquals("10101100", Smallfuck.interpreter("*", "00101100"));

        // Flips the second and third cell of the tape
        assertEquals("01001100", Smallfuck.interpreter(">*>*", "00101100"));

        // Flips all the bits in the tape
        assertEquals("11010011", Smallfuck.interpreter("*>*>*>*>*>*>*>*", "00101100"));

        // Flips all the bits that are initialized to 0
        assertEquals("11111111", Smallfuck.interpreter("*>*>>*>>>*>*", "00101100"));

        // Goes somewhere to the right of the tape and then flips all bits that are initialized to 1, progressing leftwards through the tape
        assertEquals("00000000", Smallfuck.interpreter(">>>>>*<*<<*", "00101100"));
    }

    @Test(timeout = 16000)
    public void test1() {
        String tape = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        String code = "*[>*]";

        System.out.println(Smallfuck.interpreter(code, tape));

    }
}

