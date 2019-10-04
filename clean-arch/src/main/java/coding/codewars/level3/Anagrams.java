package coding.codewars.level3;

import java.math.BigInteger;
import java.util.*;

public class Anagrams {

    public BigInteger listPosition(String word) {
        if (word.length() == 1) return BigInteger.ONE;

        List<Character> sorted = new ArrayList<>();
        for (char c : word.toCharArray()) {
            if (!sorted.contains(c)) sorted.add(c);
        }

        Collections.sort(sorted);

        BigInteger sum = BigInteger.ZERO;
        Character firstChar = word.toCharArray()[0];
        for (Character s : sorted) {
            if (s.equals(firstChar)) break;

            String countingWord = word.replaceFirst(s.toString(), "");
            BigInteger counted = countAllPossibleWords(countingWord);
            sum = sum.add(counted) ;
        }

        BigInteger position = listPosition(word.replaceFirst(firstChar.toString(), ""));
        return sum.add(position);
    }

    private BigInteger countAllPossibleWords(String word) {
        Map<Character, Integer> counter = new HashMap<>();
        for (Character c : word.toCharArray()) {
            Integer count = counter.getOrDefault(c, 0);
            counter.put(c, count + 1);
        }

        BigInteger count = getFactorial(word.length());
        for (Integer c : counter.values()) {
            count = count.divide(getFactorial(c));
        }

        return count;
    }

    private BigInteger getFactorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (long i = 1; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        return factorial;
    }

}
