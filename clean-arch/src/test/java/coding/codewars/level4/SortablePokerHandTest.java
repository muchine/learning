package coding.codewars.level4;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class SortablePokerHandTest {
    @Test
    public void pokerHandSortTest() {
        // Arrange
        ArrayList<SortablePokerHand> expected = new ArrayList<SortablePokerHand>();
        expected.add(new SortablePokerHand("KS AS TS QS JS"));
        expected.add(new SortablePokerHand("2H 3H 4H 5H 6H"));
        expected.add(new SortablePokerHand("AS AD AC AH JD"));
        expected.add(new SortablePokerHand("JS JD JC JH 3D"));
        expected.add(new SortablePokerHand("2S AH 2H AS AC"));
        expected.add(new SortablePokerHand("AS 3S 4S 8S 2S"));
        expected.add(new SortablePokerHand("2H 3H 5H 6H 7H"));
        expected.add(new SortablePokerHand("2S 3H 4H 5S 6C"));
        expected.add(new SortablePokerHand("2D AC 3H 4H 5S"));
        expected.add(new SortablePokerHand("AH AC 5H 6H AS"));
        expected.add(new SortablePokerHand("2S 2H 4H 5S 4C"));
        expected.add(new SortablePokerHand("AH AC 5H 6H 7S"));
        expected.add(new SortablePokerHand("AH AC 4H 6H 7S"));
        expected.add(new SortablePokerHand("2S AH 4H 5S KC"));
        expected.add(new SortablePokerHand("2S 3H 6H 7S 9C"));

        Random random = new Random();
        ArrayList<SortablePokerHand> actual = createRandomOrderedList(random, expected);

        // Act
        Collections.sort(actual);
        System.out.println(actual);

        // Assert
        Iterator a = actual.iterator();
        for (SortablePokerHand e : expected) {
            assertEquals(e, a.next());
        }
    }

    @Test
    public void test1() {
        ArrayList<SortablePokerHand> expected = new ArrayList<SortablePokerHand>();
        expected.add(new SortablePokerHand("JH AH TH KH QH"));
        expected.add(new SortablePokerHand("JH 9H TH KH QH"));
        expected.add(new SortablePokerHand("5C 6C 3C 7C 4C"));
        expected.add(new SortablePokerHand("2D 6D 3D 4D 5D"));
        expected.add(new SortablePokerHand("2C 3C AC 4C 5C"));
        expected.add(new SortablePokerHand("AS AC AH KS AS"));
        expected.add(new SortablePokerHand("JC 7H JS JD JH"));
        expected.add(new SortablePokerHand("JC 6H JS JD JH"));
        expected.add(new SortablePokerHand("KH KC 3S 3H 3D"));
        expected.add(new SortablePokerHand("2H 2C 3S 3H 3D"));
        expected.add(new SortablePokerHand("3D 2H 3H 2C 2D"));
        expected.add(new SortablePokerHand("JH 8H AH KH QH"));
        expected.add(new SortablePokerHand("4C 5C 9C 8C KC"));
        expected.add(new SortablePokerHand("3S 8S 9S 5S KS"));
        expected.add(new SortablePokerHand("8C 9C 5C 3C TC"));
        expected.add(new SortablePokerHand("QC KH TS JS AH"));
        expected.add(new SortablePokerHand("JS QS 9H TS KH"));
        expected.add(new SortablePokerHand("6S 8S 7S 5H 9H"));
        expected.add(new SortablePokerHand("3C 5C 4C 2C 6H"));
        expected.add(new SortablePokerHand("2C 3H AS 4S 5H"));
        expected.add(new SortablePokerHand("AC KH QH AH AS"));
        expected.add(new SortablePokerHand("7C 7S KH 2H 7H"));
        expected.add(new SortablePokerHand("7C 7S 3S 7H 5S"));
        expected.add(new SortablePokerHand("6C 6S 3S 6H 5S"));
        expected.add(new SortablePokerHand("3C KH 5D 5S KC"));
        expected.add(new SortablePokerHand("5S 5D 2C KH KC"));
        expected.add(new SortablePokerHand("3H 4C 4H 3S 2H"));
        expected.add(new SortablePokerHand("AH 8S AH KC JH"));
        expected.add(new SortablePokerHand("KD 4S KD 3H 8S"));
        expected.add(new SortablePokerHand("KC 4H KS 2H 8D"));
        expected.add(new SortablePokerHand("QH 8H KD JH 8S"));
        expected.add(new SortablePokerHand("8C 4S KH JS 4D"));
        expected.add(new SortablePokerHand("KS 8D 4D 9S 4S"));
        expected.add(new SortablePokerHand("KD 6S 9D TH AD"));
        expected.add(new SortablePokerHand("TS KS 5S 9S AC"));
        expected.add(new SortablePokerHand("JH 8S TH AH QH"));
        expected.add(new SortablePokerHand("TC 8C 2S JH 6C"));
        expected.add(new SortablePokerHand("2D 6D 9D TH 7D"));
        expected.add(new SortablePokerHand("9D 8H 2C 6S 7H"));
        expected.add(new SortablePokerHand("4S 3H 2C 7S 5H"));

        Random random = new Random();
        ArrayList<SortablePokerHand> actual = createRandomOrderedList(random, expected);

        // Act
        Collections.sort(actual);

        // Assert
        Iterator a = actual.iterator();
        for (SortablePokerHand e : expected) {
            assertEquals(e, a.next());
        }
    }

    private ArrayList<SortablePokerHand> createRandomOrderedList(Random random, ArrayList<SortablePokerHand> expected){
        ArrayList<SortablePokerHand> actual = new ArrayList<SortablePokerHand>();
        for (SortablePokerHand pokerHand : expected) {
            int j = random.nextInt(actual.size()+1);
            actual.add(j, pokerHand);
        }
        return actual;
    }
}
