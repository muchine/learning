package coding.codewars.level4;

import java.util.Arrays;

/**
 * Ten-Pin Bowling
 * https://www.codewars.com/kata/ten-pin-bowling/train/java
 */
public class TenPinBolwing {

    String[] rolls = new String[21];

    public TenPinBolwing(String frames) {
        for (int i = 0; i < rolls.length; i++) rolls[i] = "";

        String[] tokens = frames.split(" ");
        int index = 0;
        for (String token : tokens) {
            char[] chars = token.toCharArray();
            for (char c : chars) rolls[index++] = "" + c;
            if (chars.length == 1) index++;
        }

        System.out.println(Arrays.asList(rolls));
    }

    public int score() {
        int sum = 0;
        for (int i = 0; i < 21; i++) {
            if (rolls[i].equals("")) continue;
            if (rolls[i].equals("X")) {
                sum += 10;

                if (i >= 18) continue;

                int next = nextIndex(i);
                sum += nextScore(next);
                sum += nextScore(nextIndex(next));
            } else if (rolls[i].equals("/")) {
                sum +=  10 - Integer.parseInt(rolls[i - 1]);

                if (i >= 18) continue;
                sum += nextScore(nextIndex(i));
            } else {
                sum += Integer.parseInt(rolls[i]);
            }

//            System.out.printf("sum[%s]: %s\n", i, sum);
        }

        return sum;
    }

    private int nextScore(int next) {
        if (rolls[next].equals("X")) return 10;
        if (rolls[next].equals("/")) return 10 - Integer.parseInt(rolls[next - 1]);
        return Integer.parseInt(rolls[next]);
    }

    private int nextIndex(int index) {
        while (index < rolls.length) {
            index++;
            if (!rolls[index].equals("")) return index;
        }

        return -1;
    }

    public static int bowling_score(String frames) {
        return new TenPinBolwing(frames).score();
    }

}
