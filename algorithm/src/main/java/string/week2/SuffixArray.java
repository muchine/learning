package string.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SuffixArray {

    // Build suffix array of the string text and
    // return an int[] result of the same length as the text
    // such that the value result[i] is the index (0-based)
    // in text where the i-th lexicographically smallest
    // suffix of text starts.
    public int[] computeSuffixArray(String text) {
        List<Entry> matrix = new ArrayList<Entry>();
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            matrix.add(new Entry(chars, i));
        }

        Collections.sort(matrix);

        int[] result = new int[matrix.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = matrix.get(i).suffix;
        }

        return result;
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] SuffixArray = computeSuffixArray(text);
        print(SuffixArray);
    }

    class Entry implements Comparable<Entry> {

        private final char[] chars;

        private final int suffix;

        public Entry(char[] chars, int index) {
            this.chars = new char[chars.length];
            this.suffix = index;

            for (int i = 0; i < chars.length; i++) {
                int j = i + index;
                if (j >= chars.length) j -= chars.length;

                this.chars[i] = chars[j];
            }
        }

        @Override
        public String toString() {
            return "Entry{" + "chars=" + Arrays.toString(chars) + '}';
        }

        @Override
        public int compareTo(Entry o) {
            for (int i = 0; i < chars.length; i++) {
                int compared = chars[i] - o.chars[i];
                if (compared != 0) return compared;
            }

            return 0;
        }
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
        new SuffixArray().run();
    }

}
