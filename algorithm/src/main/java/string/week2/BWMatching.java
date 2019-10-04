package string.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BWMatching {

    // Start of each character in the sorted list of characters of bwt,
    // see the description in the comment about function PreprocessBWT
    private final Map<Character, Integer> firstOccurrences = new HashMap<Character, Integer>();

    // Occurrence counts for each character and each position in bwt,
    // see the description in the comment about function PreprocessBWT
    private final Map<Character, int[]> counters = new HashMap<Character, int[]>();

    private final char[] chars;

    public BWMatching(String bwt) {
        this.chars = bwt.toCharArray();

        // Preprocess the BWT once to get starts and occ_count_before.
        // For each pattern, we will then use these precomputed values and
        // spend only O(|pattern|) to find all occurrences of the pattern
        // in the text instead of O(|pattern| + |text|).
        PreprocessBWT();
    }

    // Compute the number of occurrences of string pattern in the text
    // given only Burrows-Wheeler Transform bwt of the text and additional
    // information we get from the preprocessing stage - starts and occ_counts_before.
    int countOccurrences(String pattern) {
        int top = 0;
        int bottom = chars.length - 1;
        char[] p = pattern.toCharArray();
        int index = p.length - 1;

        while (top <= bottom) {
            if (index < 0) return bottom - top + 1;

            char c = p[index];
            index--;

            Integer position = firstOccurrences.get(c);
            if (position == null) return 0;

            top = position + counters.get(c)[top];
            bottom = position + counters.get(c)[bottom + 1] - 1;
        }

        return 0;
    }

    // Preprocess the Burrows-Wheeler Transform bwt of some text
    // and compute as a result:
    //   * starts - for each character C in bwt, starts[C] is the first position
    //       of this character in the sorted array of
    //       all characters of the text.
    //   * occ_count_before - for each character C in bwt and each position P in bwt,
    //       occ_count_before[C][P] is the number of occurrences of character C in bwt
    //       from position 0 to position P inclusive.
    private void PreprocessBWT() {
        processFirstOccurrences();
        processCounters();
    }

    private void processFirstOccurrences() {
        char[] sorted = chars.clone();
        Arrays.sort(sorted);

        char c = '!';
        for (int i = 0; i < sorted.length; i++) {
            if (c == sorted[i]) continue;

            c = sorted[i];
            firstOccurrences.put(c, i);
        }
    }

    private void processCounters() {
        Map<Character, Integer> counts = new HashMap<>();

        counts.put('A', 0);
        counts.put('C', 0);
        counts.put('G', 0);
        counts.put('T', 0);
        counts.put('$', 0);

        counters.put('A', new int[chars.length + 1]);
        counters.put('C', new int[chars.length + 1]);
        counters.put('G', new int[chars.length + 1]);
        counters.put('T', new int[chars.length + 1]);
        counters.put('$', new int[chars.length + 1]);

        for (int i = 0; i < chars.length + 1; i++) {
            counters.get('A')[i] = counts.get('A');
            counters.get('C')[i] = counts.get('C');
            counters.get('G')[i] = counts.get('G');
            counters.get('T')[i] = counts.get('T');
            counters.get('$')[i] = counts.get('$');

            if (i < chars.length) {
                int count = counts.get(chars[i]);
                counts.put(chars[i], ++count);
            }
        }
    }

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    static class FastScanner {
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

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();

        BWMatching bw = new BWMatching(bwt);


        int patternCount = scanner.nextInt();
        String[] patterns = new String[patternCount];
        int[] result = new int[patternCount];
        for (int i = 0; i < patternCount; ++i) {
            patterns[i] = scanner.next();
            result[i] = bw.countOccurrences(patterns[i]);
        }

        bw.print(result);
    }

}
