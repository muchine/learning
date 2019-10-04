package coding.codewars.level5;

import java.util.ArrayList;
import java.util.List;

public class RemovedNumbers {

    public static List<long[]> removNb(long n) {
        List<long[]> result = new ArrayList<>();

        long sum = 0;
        for (long i = 1; i <= n; i++) {
            sum += i;
        }

        for (long i = 1; i < n; i++) {
            long total = sum - i;

            long min = 0;
            long max = n + 1;
            long position = (min + max) / 2;

            while (min < position && position < max) {
                long product = i * position;

                if (product < total - position) {
                    min = position;
                    position = (min + max) / 2;
                } else if (product > total - position) {
                    max = position;
                    position = (min + max) / 2;
                } else {
                    result.add(new long[]{i, position});
                    break;
                }
            }
        }

        return result;
    }

}