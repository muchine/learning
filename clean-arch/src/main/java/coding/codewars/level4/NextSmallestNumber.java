package coding.codewars.level4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Next smaller number with the same digits
 * https://www.codewars.com/kata/next-smaller-number-with-the-same-digits/train/java
 */
@SuppressWarnings("Duplicates")
public class NextSmallestNumber {

    int[] numbers;

    NextSmallestNumber(long n) {
        char[] chars = (n + "").toCharArray();
        numbers = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            numbers[i] = Integer.parseInt("" + chars[i]);
        }
    }

    public long perform() {
        int position = findPosition();
        if (position == -1) return -1;

        int current = numbers[position];
        int swapPosition = findPositionToSwap(position);
        int swap = numbers[swapPosition];

        numbers[position] = swap;
        numbers[swapPosition] = current;

        System.out.printf("position: %s, swap position: %s, current: %s, swap: %s\n", position, swapPosition, current, swap);

        List<String> digits = new ArrayList<>();
        for (int j = 0; j <= position; j++) {
            digits.add(numbers[j] + "");
        }

        List<String> remained = new ArrayList<>();
        for (int j = position + 1; j < numbers.length; j++) {
            remained.add(numbers[j] + "");
        }

        Collections.sort(remained, Collections.reverseOrder());

        digits.addAll(remained);
        return Long.parseLong(String.join("", digits));
    }

    private int findPosition() {
        int position = -1;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (i == 0 && numbers[j] == 0) continue;
                if (numbers[i] > numbers[j]) position = i;
            }
        }

        System.out.println(position);

        return position;
    }

    private int findPositionToSwap(int position) {
        int anchor = numbers[position];
        int maxDigit = -1;
        int swapPosition = -1;

        for (int i = position + 1; i < numbers.length; i++) {
            int digit = numbers[i];
            System.out.printf("pos: %s, digit: %s\n", i, digit);
            if (digit <= maxDigit || anchor <= digit) continue;

            maxDigit = digit;
            swapPosition = i;
        }

        return swapPosition;
    }

    public static long nextSmaller(long n) {
        System.out.println("n: " + n);
        NextSmallestNumber instance = new NextSmallestNumber(n);
        return instance.perform();
    }
}
