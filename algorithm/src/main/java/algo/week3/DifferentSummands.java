package algo.week3;

import java.util.*;

public class DifferentSummands {

    public List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<>();

        int max = n;

        for (int i = 1; i <= max; i++) {
            if (i * 2 < n) {
                summands.add(i);
                n -= i;
            } else {
                summands.add(n);
                break;
            }
        }

        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        DifferentSummands s = new DifferentSummands();
        List<Integer> summands = s.optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }

}

