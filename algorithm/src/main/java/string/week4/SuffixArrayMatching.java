package string.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SuffixArrayMatching {

    private final char[] text;

    private final int[] suffixArray;

    private final char[] bwt;

    private final Map<Character, Integer> firstOccurrences = new HashMap<Character, Integer>();

    private final Map<Character, int[]> counters = new HashMap<Character, int[]>();

    public SuffixArrayMatching(String text) {
        this.text = text.toCharArray();
        this.suffixArray = computeSuffixArray();
        this.bwt = doBWT();

        PreprocessBWT();
    }

    private int[] computeSuffixArray() {
        int[] order = sortCharacters(text);
        int[] classes = computeCharClasses(text, order);
        int length = 1;

        while (length < text.length) {
            order = sortDoubled(text, length, order, classes);
            classes = updateClasses(order, classes, length);
            length *= 2;
        }

        return order;
    }

    public List<Integer> findOccurrences(String[] patterns) {
        boolean[] occurs = new boolean[text.length];

        for (String pattern : patterns) {
            List<Integer> occurrences = findOccurrences(pattern);
            for (int x : occurrences) {
                occurs[x] = true;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < occurs.length; i++) {
            if (occurs[i]) result.add(i);
        }

        return result;
    }

    public List<Integer> findOccurrences(String pattern) {
        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = bwt.length - 1;
        char[] p = pattern.toCharArray();
        int index = p.length - 1;

        while (top <= bottom) {
            if (index < 0) {
                for (int i = top; i <= bottom; i++) {
                    result.add(suffixArray[i]);
                }

                return result;
            }

            char c = p[index];
            index--;

            Integer position = firstOccurrences.get(c);
            if (position == null) return result;

            top = position + counters.get(c)[top];
            bottom = position + counters.get(c)[bottom + 1] - 1;
        }

        return result;
    }

    private char[] doBWT() {
        char[] bwt = new char[text.length];

        for (int i = 0; i < text.length; i++) {
            int index = suffixArray[i] - 1;
            if (index < 0) index = text.length - 1;

            bwt[i] = text[index];
        }

        return bwt;
    }

    private void PreprocessBWT() {
        processFirstOccurrences();
        processCounters();
    }

    private void processFirstOccurrences() {
        char c = '!';
        for (int i = 0; i < suffixArray.length; i++) {
            int index = suffixArray[i];
            if (c == text[index]) continue;

            c = text[index];
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

        counters.put('A', new int[bwt.length + 1]);
        counters.put('C', new int[bwt.length + 1]);
        counters.put('G', new int[bwt.length + 1]);
        counters.put('T', new int[bwt.length + 1]);
        counters.put('$', new int[bwt.length + 1]);

        for (int i = 0; i < bwt.length + 1; i++) {
            counters.get('A')[i] = counts.get('A');
            counters.get('C')[i] = counts.get('C');
            counters.get('G')[i] = counts.get('G');
            counters.get('T')[i] = counts.get('T');
            counters.get('$')[i] = counts.get('$');

            if (i < bwt.length) {
                int count = counts.get(bwt[i]);
                counts.put(bwt[i], ++count);
            }
        }
    }

    public void print(List<Integer> positions) {
        for (int pos : positions) {
            System.out.print(pos + " ");
        }

        System.out.println();
    }

    public void run() throws IOException {

    }

    private int[] sortCharacters(char[] text) {
        int[] order = new int[text.length];
        int[] count = new int[5];

        for (int i = 0; i < text.length; i++) {
            int index = index(text[i]);
            count[index]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = text.length - 1; i >= 0; i--) {
            char c = text[i];

            int index = index(c);
            order[--count[index]] = i;
        }

        return order;
    }

    private int[] computeCharClasses(char[] text, int[] order) {
        int[] classes = new int[text.length];

        classes[order[0]] = 0;

        for (int i = 1; i < text.length; i++) {
            if (text[order[i]] != text[order[i - 1]]) {
                classes[order[i]] = classes[order[i - 1]] + 1;
            } else {
                classes[order[i]] = classes[order[i - 1]];
            }
        }

        return classes;
    }

    private int[] sortDoubled(char[] text, int length, int[] order, int[] classes) {
        int[] count = new int[text.length];
        int[] newOrder = new int[text.length];

        for (int i = 0; i < text.length; i++) {
            count[classes[i]]++;
        }

        for (int i = 1; i < text.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = text.length - 1; i >= 0; i--) {
            int start = (order[i] - length + text.length) % text.length;
            int cl = classes[start];
            count[cl]--;
            newOrder[count[cl]] = start;
        }

        return newOrder;
    }

    private int[] updateClasses(int[] order, int[] classes, int length) {
        int n = order.length;
        int[] newClasses = new int[n];
        newClasses[order[0]] = 0;

        for (int i = 1; i < n; i++) {
            int current = order[i];
            int prev = order[i - 1];
            int mid = (current + length) % n;
            int midPrev = (prev + length) % n;

            if (classes[current] != classes[prev] || classes[mid] != classes[midPrev]) {
                newClasses[current] = newClasses[prev] + 1;
            } else {
                newClasses[current] = newClasses[prev];
            }
        }

        return newClasses;
    }

    private int index(char c) {
        switch (c) {
            case '$': return 0;
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
        }

        throw new RuntimeException("Invalid character");
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

        int nextint() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next() + "$";

        SuffixArrayMatching m = new SuffixArrayMatching(text);

        int patternCount = scanner.nextint();
        String[] patterns = new String[patternCount];

        int i = 0;
        for (int patternIndex = 0; patternIndex < patternCount; ++patternIndex) {
            String pattern = scanner.next();
            patterns[i++] = pattern;
        }

        List<Integer> occurs = m.findOccurrences(patterns);
        m.print(occurs);
    }

}
