package coding.codewars.level4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TenPinBolwingTest {

    @Test
    public void BasicTests() {
        // assertEquals("expected", "actual");
        System.out.println("Maybe this bowler should put bumpers on...\n ");
        assertEquals(20, TenPinBolwing.bowling_score("11 11 11 11 11 11 11 11 11 11"));

        System.out.println("Woah! Perfect game!");
        assertEquals(300, TenPinBolwing.bowling_score("X X X X X X X X X XXX"));
    }
}
