package algo.week3;

import java.util.*;

public class LargestNumber {

    public String largestNumber(String[] a) {
        List<String> numbers = Arrays.asList(a);
        numbers.sort(new NumberComparator());

        String result = "";
        for (String n : numbers) {
            result += n;
        }

        return result;
    }

    static class NumberComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String s1 = o1 + o2;
            String s2 = o2 + o1;

            return s2.compareTo(s1);

//            if (o1.length() <= o2.length()) {
//                return doCompare(o1, o2);
//            } else {
//                return -doCompare(o2, o1);
//            }
        }

        // The length of s1 should be less than or equal to s2's
        private int doCompare(String s1, String s2) {
            for (int i = 0; i < s1.length(); i++) {
                Character c1 = s1.charAt(i);
                Character c2 = s2.charAt(i);

                int compared = c2.compareTo(c1);
//                System.out.println("c1: " + c1 + ", c2: " + c2 + ", compared: " + compared);
                if (compared != 0) return compared;
            }

            if (s1.length() == s2.length()) {
                return 0;
            }

            // if all the elements of s1 is the same as s2's, s1 has the higher order, e.g. 99 > 998
            return -1;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }

        LargestNumber ln = new LargestNumber();
        System.out.println(ln.largestNumber(a));
    }

}

