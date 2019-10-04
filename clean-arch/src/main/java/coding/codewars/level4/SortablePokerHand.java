package coding.codewars.level4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sortable Poker Hand
 * https://www.codewars.com/kata/sortable-poker-hands/train/java
 */
@SuppressWarnings("Duplicates")
public class SortablePokerHand implements Comparable<SortablePokerHand> {

    private int rank = 0;
    private String hand;

    private Map<Character, Integer> suitCounts = new HashMap<>();
    private Map<Integer, Integer> valueCounts = new HashMap<>();

    SortablePokerHand(String hand) {
        this.hand = hand;
        System.out.println("hand: " + hand);

        String[] cards = hand.split(" ");
        for (String card : cards) {
            char[] tokens = card.toCharArray();
            char suit = tokens[1];
            int value = value(tokens[0]);

            int suitCount = suitCounts.getOrDefault(suit, 0);
            suitCounts.put(suit, ++suitCount);

            int valueCount = valueCounts.getOrDefault(value, 0);
            valueCounts.put(value, ++valueCount);
        }

//        System.out.printf("hand: %s, suitCounts: %s, valueCounts: %s\n", hand, suitCounts, valueCounts);

        this.rank = rank();
//        System.out.println("rank: " + rank);

        if (valueCounts.containsKey(13)) {
            valueCounts.put(0, valueCounts.get(13));
            valueCounts.remove(13);
            int rank = rank();
            if (rank > this.rank) this.rank = rank;
        }
    }

    private int rank() {
        int rank = 0;
        boolean isStraight = isStraight();

        List<Integer> sortedValues = valueCounts.keySet().stream().sorted((o1, o2) -> {
            int count1 = valueCounts.get(o1);
            int count2 = valueCounts.get(o2);
            return count1 != count2 ? count1 - count2 : o1.compareTo(o2);
        }).collect(Collectors.toList());

        for (int i = 0; i < sortedValues.size(); i++) {
//            System.out.printf("sorted value: %s, i: %s\n", sortedValues.get(i), i);
            rank += sortedValues.get(i) * Math.pow(14, i);
        }

//        System.out.println("rank intermediate: " + rank);

        if (suitCounts.containsValue(5) && isStraight) rank += 8 * Math.pow(14, 5);
        else if (valueCounts.containsValue(4)) rank += 7 * Math.pow(14, 5);
        else if (valueCounts.containsValue(3) && valueCounts.containsValue(2)) rank += 6 * Math.pow(14, 5);
        else if (suitCounts.containsValue(5)) rank += 5 * Math.pow(14, 5);
        else if (isStraight) rank += 4 * Math.pow(14, 5);
        else if (valueCounts.containsValue(3)) rank += 3 * Math.pow(14, 5);
        else if (valueCounts.containsValue(2) && valueCounts.size() == 3) rank += 2 * Math.pow(14, 5);
        else if (valueCounts.containsValue(2) && valueCounts.size() == 4) rank += 1 * Math.pow(14, 5);

        return rank;
    }

    private boolean isStraight() {
        Set<Integer> keys = valueCounts.keySet();
        if (keys.size() != 5) return false;

        Set<Integer> straight = new HashSet<>();
        int minValue = Collections.min(keys);
        for (int i = 0; i < 5; i++) straight.add(minValue++);

        return keys.equals(straight);
    }

    private int value(char s) {
        if (s == 'T') return 9;
        if (s == 'J') return 10;
        if (s == 'Q') return 11;
        if (s == 'K') return 12;
        if (s == 'A') return 13;

        return Integer.parseInt("" + s) - 1;
    }

    @Override
    public int compareTo(SortablePokerHand o) {
        return o.rank - rank;
    }

    @Override
    public String toString() {
        return hand;
    }
}
