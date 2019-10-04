package coding.codewars.level6;

import java.util.ArrayList;
import java.util.List;

class Persist {
    public static int persistence(long n) {
        int count = 0;
        long number = n;
        while (number >= 10) {
            number = multiply(number);
            count++;
        }

        return count;
    }

    private static long multiply(long n) {
        System.out.println("n: " + n);
        List<Integer> integers = new ArrayList<>();

        long number = n;
        while (number > 0) {
            int integer = (int) (number % 10);
            integers.add(integer);
            number = number / 10;
//            System.out.println("number: " + number + ", integer: " + integer);
        }

        long multiplied = 1;
        for (Integer i : integers) {
            multiplied *= i;
        }

        return multiplied;
    }

}
