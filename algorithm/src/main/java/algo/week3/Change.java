package algo.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Change {

    private static final int[] DIVIDERS = new int[] { 10, 5, 1 };

    public int getChange(int m) {
        List<Integer> amounts = new ArrayList<>();
        int total = m;

        for (int d : DIVIDERS) {
            int amount = total / d;
            amounts.add(amount);
            total -= amount * d;

            if (total == 0) break;
        }

        return amounts.stream().mapToInt(n -> n).sum();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();

        Change c = new Change();
        System.out.println(c.getChange(m));
    }

}

