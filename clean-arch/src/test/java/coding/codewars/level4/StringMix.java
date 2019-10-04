package coding.codewars.level4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Strings Mix
 * https://www.codewars.com/kata/5629db57620258aa9d000014
 */
public class StringMix {

    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private char[] letters = alphabet.toCharArray();

    private Map<Character, Integer> counter1 = new HashMap<>();
    private Map<Character, Integer> counter2 = new HashMap<>();

    StringMix() {
        for (char c : letters) {
            counter1.put(c, 0);
            counter2.put(c, 0);
        }
    }

    public String perform(String s1, String s2) {
        initialize(s1, counter1);
        initialize(s2, counter2);

        List<Count> counts = new ArrayList<>();
        for (char c : letters) {
            int count1 = counter1.get(c);
            int count2 = counter2.get(c);

            if (count1 <= 1 && count2 <= 1) continue;

            String header = header(count1, count2);
            counts.add(new Count(header, c, Math.max(count1, count2)));
        }

        Collections.sort(counts);
        List<String> tokens = counts.stream().map(c -> c.toString()).collect(Collectors.toList());
        return String.join("/", tokens);
    }

    private String header(int count1, int count2) {
        if (count1 == count2) return "=";
        return count1 > count2 ? "1" : "2";
    }

    private void initialize(String s, Map<Character, Integer> counter) {
        for (char c : s.toCharArray()) {
            if (alphabet.contains("" + c)) counter.put(c, counter.get(c) + 1);
        }
    }

    public static String mix(String s1, String s2) {
        return new StringMix().perform(s1, s2);
    }

    class Count implements Comparable<Count> {
        String header;
        char c;
        int count;

        public Count(String header, char c, int count) {
            this.header = header;
            this.c = c;
            this.count = count;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(header + ":");
            for (int i = 0; i < count; i++) sb.append(c);
            return sb.toString();
        }

        @Override
        public int compareTo(Count o) {
            if (count != o.count) return Integer.compare(o.count, count);
            return toString().compareTo(o.toString());
        }
    }
}