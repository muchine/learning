package coding.codewars.level4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Square into Squares, Protect trees!
 * https://www.codewars.com/kata/square-into-squares-protect-trees/train/java
 */
public class Decompose {

    public String decompose(long n) {
        System.out.println(n);

        List<Long> numbers = new ArrayList<>();
        boolean decomposed = decompose(n * n, n - 1, numbers);
        if (!decomposed) return null;

        return String.join(" ", numbers.stream().map(e -> e.toString()).collect(Collectors.toList()));
    }

    private boolean decompose(long value, long n, List<Long> numbers) {
        if (n * n == value) {
            numbers.add(n);
            return true;
        }

        for (long i = n; i >= 1; i--) {
            long nextValue = value - (i * i);
            long nextNumber = (long) Math.sqrt(nextValue);
            if (nextNumber >= i) return false;

//            System.out.printf("value: %s, n: %s, i: %s, next value: %s, next number: %s\n", value, n, i, nextValue, nextNumber);

            boolean decomposed = decompose(nextValue, nextNumber, numbers);
            if (decomposed) {
                numbers.add(i);
                return true;
            }
        }

        return false;
    }

}
