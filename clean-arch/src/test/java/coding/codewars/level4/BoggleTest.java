package coding.codewars.level4;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class BoggleTest {

    final private static char[][] board = {
            {'E','A','R','A'},
            {'N','L','E','C'},
            {'I','A','I','S'},
            {'B','Y','O','R'}
    };

    private static String[]  toCheck   = {"C", "EAR","EARS","BAILER","RSCAREIOYBAILNEA" ,"CEREAL" ,"ROBES"};
    private static boolean[] expecteds = {true, true, false, true,    true,               false,    false };

    @Test
    public void sampleTests() {
        for (int i=0 ; i < toCheck.length ; i++) {
            assertEquals( expecteds[i], new Boggle(deepCopy(board), toCheck[i]).check() );
        }
    }

    @Test
    public void test1() {
        char[][] board = {
                {'Y', 'Z', 'U', 'U', 'F', 'R', 'X', 'M', 'L', 'U', 'S', 'J', 'T', 'V', 'A', 'G', 'J', 'Z', 'J', 'Y'},
                {'Y', 'N', 'U', 'O', 'G', 'H', 'J', 'W', 'J', 'Y', 'F', 'U', 'L', 'C', 'Q', 'D', 'A', 'G', 'V', 'W'},
                {'T', 'Z', 'S', 'P', 'O', 'V', 'N', 'C', 'X', 'Y', 'R', 'Z', 'H', 'K', 'U', 'L', 'W', 'R', 'E', 'C'},
                {'T', 'A', 'I', 'K', 'F', 'Q', 'Z', 'F', 'L', 'B', 'W', 'O', 'L', 'O', 'A', 'E', 'R', 'B', 'R', 'Q'},
                {'Z', 'G', 'T', 'A', 'C', 'Q', 'X', 'M', 'A', 'V', 'D', 'M', 'B', 'R', 'T', 'I', 'S', 'U', 'V', 'X'},
                {'U', 'Z', 'C', 'T', 'J', 'L', 'N', 'V', 'P', 'W', 'D', 'T', 'R', 'Z', 'K', 'F', 'Y', 'X', 'T', 'N'},
                {'O', 'N', 'S', 'D', 'W', 'R', 'F', 'V', 'E', 'G', 'L', 'D', 'Y', 'D', 'R', 'I', 'L', 'Y', 'K', 'A'},
                {'I', 'S', 'L', 'U', 'F', 'L', 'C', 'O', 'R', 'H', 'T', 'T', 'I', 'Q', 'H', 'J', 'R', 'C', 'Y', 'G'},
                {'U', 'A', 'B', 'H', 'M', 'N', 'K', 'P', 'N', 'L', 'E', 'D', 'F', 'P', 'A', 'C', 'N', 'H', 'T', 'X'},
                {'E', 'E', 'Q', 'R', 'N', 'N', 'M', 'G', 'L', 'C', 'X', 'A', 'M', 'A', 'S', 'S', 'Q', 'K', 'H', 'P'},
                {'J', 'G', 'J', 'K', 'F', 'Y', 'L', 'F', 'Q', 'F', 'S', 'Q', 'L', 'V', 'V', 'W', 'R', 'Z', 'S', 'E'},
                {'K', 'K', 'X', 'K', 'Q', 'P', 'O', 'J', 'B', 'K', 'E', 'S', 'C', 'J', 'X', 'F', 'J', 'O', 'W', 'Q'},
                {'Y', 'V', 'T', 'B', 'G', 'Z', 'C', 'L', 'H', 'D', 'M', 'R', 'L', 'T', 'M', 'T', 'W', 'K', 'L', 'M'},
                {'N', 'T', 'P', 'X', 'M', 'V', 'O', 'A', 'G', 'Z', 'C', 'Z', 'B', 'A', 'E', 'O', 'E', 'H', 'U', 'N'},
                {'J', 'G', 'W', 'V', 'L', 'C', 'M', 'Y', 'G', 'D', 'Q', 'J', 'Y', 'T', 'H', 'G', 'N', 'W', 'Z', 'L'},
                {'G', 'S', 'U', 'L', 'U', 'E', 'A', 'Z', 'R', 'B', 'S', 'T', 'X', 'Q', 'A', 'A', 'L', 'B', 'P', 'A'},
                {'G', 'H', 'D', 'O', 'S', 'E', 'B', 'M', 'N', 'Q', 'W', 'I', 'O', 'X', 'Q', 'S', 'P', 'J', 'B', 'T'},
                {'S', 'T', 'Q', 'S', 'V', 'S', 'S', 'G', 'M', 'W', 'Z', 'U', 'X', 'C', 'U', 'S', 'X', 'Q', 'S', 'D'},
                {'T', 'T', 'N', 'T', 'N', 'Z', 'V', 'T', 'I', 'J', 'H', 'U', 'J', 'Z', 'P', 'V', 'O', 'I', 'K', 'H'},
                {'I', 'X', 'W', 'F', 'T', 'Y', 'H', 'H', 'M', 'E', 'S', 'P', 'D', 'I', 'J', 'M', 'U', 'J', 'S', 'J'}
        };

        String word = "HUWBQZRCZHJO";
        assertEquals(true, new Boggle(board, word).check(18, 10, 0));
    }


    private char[][] deepCopy(char[][] arr) {
        return Arrays.stream(arr)
                .map( a -> Arrays.copyOf(a, a.length) )
                .toArray(char[][]::new);
    }
}
