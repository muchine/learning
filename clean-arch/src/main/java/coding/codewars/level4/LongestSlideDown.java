package coding.codewars.level4;

import java.util.Arrays;

/**
 * Pyramid Slide Down
 * https://www.codewars.com/kata/pyramid-slide-down/train/java
 */
public class LongestSlideDown {

    public int execute(int[][] pyramid) {
        if (pyramid.length == 1) return pyramid[0][0];

        int[] sum = pyramid[0];
        for (int i = 1; i < pyramid.length; i++) {
            sum = sum(sum, pyramid[i]);
        }

        return Arrays.stream(sum).max().getAsInt();
    }

    private int[] sum(int[] sum, int[] row) {
        int[] newSum = new int[row.length];
        newSum[0] = sum[0] + row[0];
        newSum[newSum.length - 1] = sum[sum.length - 1] + row[row.length - 1];
        for (int i = 1; i < newSum.length - 1; i++) {
            newSum[i] = Math.max(sum[i - 1] + row[i], sum[i] + row[i]);
        }

//        System.out.println(Arrays.stream(newSum).mapToObj(i -> "" + i).collect(Collectors.joining(", ")));
        return newSum;
    }

    public static int longestSlideDown(int[][] pyramid) {
        return new LongestSlideDown().execute(pyramid);
    }

}
