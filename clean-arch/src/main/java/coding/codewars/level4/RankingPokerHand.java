package coding.codewars.level4;

import java.util.stream.Collectors;
import java.util.*;

/**
 * Ranking Poker Hands
 * https://www.codewars.com/kata/5739174624fc28e188000465
 */
public class RankingPokerHand {
    public enum Result {TIE, WIN, LOSS}

    private List<HandValue> handValues = new ArrayList<>();

    RankingPokerHand(String hand) {
        Map<String, Integer> suits = new HashMap<>();
        Map<Integer, Integer> values = new HashMap<>();

        String[] cards = hand.split(" ");
        for (String card : cards) {
            char[] tokens = card.toCharArray();
            String suit = "" + tokens[1];
            int value = value("" + tokens[0]);

            Integer suitCount = suits.get(suit);
            if (suitCount == null) suitCount = 0;
            suits.put(suit, ++suitCount);

            Integer valueCount = values.get(value);
            if (valueCount == null) valueCount = 0;
            values.put(value, ++valueCount);
        }

        System.out.println("suits: " + suits);
        System.out.println("values: " + values);

        handValues.add(new RoyalFlush(suits, values));
        handValues.add(new StraightFlush(suits, values));
        handValues.add(new FourKind(values));
        handValues.add(new FullHouse(values));
        handValues.add(new Flush(suits, values));
        handValues.add(new Straight(values));
        handValues.add(new ThreeKind(values));
        handValues.add(new TwoPairs(values));
        handValues.add(new Pair(values));
        handValues.add(new HighCard(values));
    }

    public Result compareWith(RankingPokerHand hand) {
        int compared = getRank().compareTo(hand.getRank());

        if (compared == 0) return Result.TIE;
        return compared < 0 ? Result.WIN : Result.LOSS;
    }

    private int value(String s) {
        if ("T".equals(s)) return 10;
        if ("J".equals(s)) return 11;
        if ("Q".equals(s)) return 12;
        if ("K".equals(s)) return 13;
        if ("A".equals(s)) return 14;

        return Integer.parseInt(s);
    }

    public Rank getRank() {
        for (int i = 0; i < handValues.size(); i++) {
            HandValue handValue = handValues.get(i);
            if (handValue.isMatched()) {
                Rank rank = new Rank(i, handValue);
                System.out.println("rank: " + rank);
                return rank;
            }
        }

        throw new IllegalStateException();
    }

    class Rank implements Comparable<Rank> {
        private Integer rank;
        private HandValue handValue;

        public Rank(int rank, HandValue handValue) {
            this.rank = rank;
            this.handValue = handValue;
        }

        @Override
        public int compareTo(Rank o) {
            int compared = rank.compareTo(o.rank);
            if (compared != 0) return compared;

            return handValue.compareTo(o.handValue);
        }

        @Override
        public String toString() {
            return "rank: " + rank + ", handValue: " + handValue;
        }
    }

    abstract class HandValue implements Comparable<HandValue> {

        protected List<Integer> matched = new ArrayList<>();
        protected List<Integer> remained = new ArrayList<>();

        void sort() {
            matched.sort(Comparator.reverseOrder());
            remained.sort(Comparator.reverseOrder());
        }

        public boolean isMatched() {
            return !matched.isEmpty();
        }

        public int compareTo(HandValue o) {
            if (matched.size() != o.matched.size() || remained.size() != o.remained.size()) throw new IllegalArgumentException();

            for (int i = 0; i < matched.size(); i++) {
                int compared = o.matched.get(i).compareTo(matched.get(i));
                if (compared != 0) return compared;
            }

            for (int i = 0; i < remained.size(); i++) {
                int compared = o.remained.get(i).compareTo(remained.get(i));
                if (compared != 0) return compared;
            }

            return 0;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + ": " + matched;
        }
    }

    class HighCard extends HandValue {
        public HighCard(Map<Integer, Integer> values) {
            matched.addAll(values.keySet());
            sort();
        }
    }

    abstract class Pairs extends HandValue {
        public Pairs(Map<Integer, Integer> values) {
            for (int value : values.keySet()) {
                if (values.get(value) == 2) matched.add(value);
                else remained.add(value);
            }

            sort();
            System.out.println("matched: " + matched);
        }
    }

    class Pair extends Pairs {

        protected Pair(Map<Integer, Integer> values) {
            super(values);
        }

        @Override
        public boolean isMatched() {
            return matched.size() == 1;
        }
    }

    class TwoPairs extends Pairs {
        protected TwoPairs(Map<Integer, Integer> values) {
           super(values);
        }

        @Override
        public boolean isMatched() {
            return matched.size() == 2;
        }
    }

    class ThreeKind extends HandValue {
        public ThreeKind(Map<Integer, Integer> values) {
            for (int key : values.keySet()) {
                if (values.get(key) == 3) matched.add(key);
                else remained.add(key);
            }

            sort();
        }
    }

    class Straight extends HandValue {
        public Straight(Map<Integer, Integer> values) {
            if (values.size() != 5) return;

            List<Integer> keys = new ArrayList(values.keySet());
            Collections.sort(keys);

            int prev = keys.get(0);
            for (int i = 1; i < keys.size(); i++) {
                if (keys.get(i) != prev + 1) return;
                prev = keys.get(i);
            }

            matched.addAll(keys);
            sort();
        }
    }

    class Flush extends HandValue {
        public Flush(Map<String, Integer> suits, Map<Integer, Integer> values) {
            if (suits.size() != 1) return;
            matched.addAll(values.keySet());
            sort();
        }
    }

    class FullHouse extends HandValue {
        public FullHouse(Map<Integer, Integer> values) {
            int first = 0;
            int second = 0;

            for (int key : values.keySet()) {
                if (values.get(key) == 3) first = key;
                if (values.get(key) == 2) second = key;
            }

            if (first > 0 && second > 0) {
                matched.add(first);
                matched.add(second);
            }
        }
    }

    class FourKind extends HandValue {
        public FourKind(Map<Integer, Integer> values) {
            for (int key : values.keySet()) {
                if (values.get(key) == 4) matched.add(key);
                else remained.add(key);
            }

            sort();
        }
    }

    class StraightFlush extends HandValue {
        public StraightFlush(Map<String, Integer> suits, Map<Integer, Integer> values) {
            Straight straight = new Straight(values);
            Flush flush = new Flush(suits, values);

            if (straight.isMatched() && flush.isMatched()) matched.addAll(straight.matched);
            sort();
        }
    }

    class RoyalFlush extends HandValue {
        public RoyalFlush(Map<String, Integer> suits, Map<Integer, Integer> values) {
            Flush flush = new Flush(suits, values);
            if (!flush.isMatched()) return;

            String joined = values.keySet()
                    .stream()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            if ("10,11,12,13,14".equals(joined)) {
                matched.addAll(values.keySet());
                sort();
            }
        }
    }
}

