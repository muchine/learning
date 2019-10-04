package string.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KnuthMorrisPratt {

    public List<Integer> findPattern(String pattern, String text) {
        List<Integer> result = new ArrayList<Integer>();

        String s = pattern + "$" + text;
        int[] prefixes = computePrefixFunction(s.toCharArray());

        for (int i = pattern.length() + 1; i < s.length(); i++) {
            if (prefixes[i] == pattern.length()) {
                result.add(i - (2 * pattern.length()));
            }
        }

        return result;
    }

    private int[] computePrefixFunction(char[] s) {
        int[] prefixes = new int[s.length];

        int border = 0;
        for (int i = 1; i < s.length; i++) {
            while (border > 0 && s[i] != s[border]) {
                border = prefixes[border - 1];
            }

            if (s[i] == s[border]) {
                border += 1;
            } else {
                border = 0;
            }

            prefixes[i] = border;
        }

        return prefixes;
    }

    public void print(List<Integer> x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String pattern = scanner.next();
        String text = scanner.next();
        List<Integer> positions = findPattern(pattern, text);
        print(positions);
    }

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
    }

    public static void main(String[] args) throws IOException {
        new KnuthMorrisPratt().run();
    }

}
