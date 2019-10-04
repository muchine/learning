package data.week4;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    public List<Integer> findOccurrences(Data input) {
        List<Integer> result = new ArrayList<>();
        String text = input.text;
        String pattern = input.pattern;

        BigDecimal prime = new BigDecimal(1000000000039L);
        Random random = new Random();
        long rand = random.nextLong();
        if (rand < 0) rand = -rand;
        BigDecimal x = new BigDecimal(rand % prime.longValue());

        BigDecimal patternHash = polyHash(pattern, prime, x);
//        System.out.printf("pattern hash: %s\n", patternHash);
        BigDecimal[] textHashes = precomputeHashes(input, prime, x);
//        Helper.printArray(textHashes);

        for (int i = 0; i < textHashes.length; i++) {
            if (!patternHash.equals(textHashes[i])) continue;

            String sub = text.substring(i, i + pattern.length());
            if (sub.equals(pattern)) result.add(i);
        }

        return result;
    }

    private BigDecimal[] precomputeHashes(Data data, BigDecimal prime, BigDecimal x) {
        String text = data.text;
        String pattern = data.pattern;

        int lastIndex = text.length() - pattern.length();
        int length = lastIndex + 1;
//        System.out.printf("hash length: %d\n", length);

        BigDecimal[] hashes = new BigDecimal[length];
        String lastPart = text.substring(lastIndex, lastIndex + pattern.length());
//        System.out.printf("last part: %s\n", lastPart);
        hashes[lastIndex] = polyHash(lastPart, prime, x);

        BigDecimal y = new BigDecimal(1);
        for (int i = 1; i <= pattern.length(); i++) {
            y = y.multiply(x).remainder(prime);
        }

        for (int i = length - 2; i >= 0; i--) {
            BigDecimal subtractor = y.multiply(new BigDecimal(text.charAt(i + pattern.length())));
            hashes[i] = hashes[i + 1].multiply(x)
                    .add(new BigDecimal(text.charAt(i) + 0))
                    .subtract(subtractor)
                    .remainder(prime);
        }

        return hashes;
    }

    private BigDecimal polyHash(String s, BigDecimal prime, BigDecimal x) {
//        System.out.printf("x = %s\n", x);
        BigDecimal hash = new BigDecimal(0);
        for (int i = s.length() - 1; i >= 0 ; i--) {
            BigDecimal product = hash.multiply(x);
//            System.out.printf("i = %d,  hash * x = %s, hash = %s\n", i, product, hash);
            hash = product.add(new BigDecimal(s.charAt(i) + 0)).remainder(prime);
//            System.out.printf("computed hash: %s\n", hash);
        }

        return hash;
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));

        HashSubstring s = new HashSubstring();
        printOccurrences(s.findOccurrences(readInput()));
        out.close();
    }

}

