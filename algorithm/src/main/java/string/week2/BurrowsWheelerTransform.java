package string.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {

    String BWT(String text) {
        StringBuilder result = new StringBuilder();

        List<Entry> matrix = new ArrayList<Entry>();
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            matrix.add(new Entry(chars, i));
        }

        Collections.sort(matrix);

        for (Entry e : matrix) {
            Log.d("entry e: %s", e);
            result.append(e.chars[e.chars.length - 1]);
        }

        return result.toString();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }

    class Entry implements Comparable<Entry> {

        private final char[] chars;

        public Entry(char[] chars, int index) {
            this.chars = new char[chars.length];

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

    private static class Log {

        static int level = 0;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    public static void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

}
