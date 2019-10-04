package string.week4;

import java.util.*;
import java.io.*;

public class SuffixArrayLong {

    public int[] computeSuffixArray(String text) {
        char[] chars = text.toCharArray();

        int[] order = sortCharacters(chars);
        int[] classes = computeCharClasses(chars, order);
        int length = 1;

        while (length < chars.length) {
            order = sortDoubled(chars, length, order, classes);
            classes = updateClasses(order, classes, length);
            length *= 2;
        }

        return order;
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

    public void print(int[] x) {
        for (int a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        int[] suffix_array = computeSuffixArray(text);
        print(suffix_array);
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

        static int level = 5;

        static void d(String s, Object... o) {
            if (level >= 5) System.out.printf(s + "\n", o);
        }

        static void i(String s, Object... o) {
            if (level >= 4) System.out.printf(s + "\n", o);
        }

    }

    public static void main(String[] args) throws IOException {
        new SuffixArrayLong().run();
    }

}
