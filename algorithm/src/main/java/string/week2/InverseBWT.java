package string.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {

    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();

        Matrix matrix = new Matrix(bwt);

        int pointer = matrix.lastToFirst(0);
        while (pointer != 0) {
            result.append(matrix.firstColumn[pointer]);
            pointer = matrix.lastToFirst(pointer);
        }

        return result.reverse().append("$").toString();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }

    class Matrix {

        private final char[] lastColumn;
        private final char[] firstColumn;

        private final Element[] firsts;
        private final Element[] lasts;

        private final Map<Character, List<Integer>> lastMap = new HashMap<>();
        private final Map<Character, List<Integer>> firstMap = new HashMap<>();

        public Matrix(String bwt) {
            this.lastColumn = bwt.toCharArray();
            this.firstColumn = lastColumn.clone();

            Arrays.sort(firstColumn);

            firsts = new Element[firstColumn.length];
            lasts = new Element[lastColumn.length];

            for (int i = 0; i < lastColumn.length; i++) {
                int firstCounter = addIndex(i, firstColumn, firstMap);
                int lastCounter = addIndex(i, lastColumn, lastMap);

                firsts[i] = new Element(firstColumn[i], firstCounter, i);
                lasts[i] = new Element(lastColumn[i], lastCounter);
            }

            for (int i = 0; i < lastColumn.length; i++) {
                Element e = lasts[i];
                int pointer = firstMap.get(e.c).get(e.counter);
                e.pointer = pointer;
            }
        }

        public int lastToFirst(int lastIndex) {
            return lasts[lastIndex].pointer;
        }

        private int addIndex(int index, char[] column, Map<Character, List<Integer>> map) {
            List<Integer> entries = map.get(column[index]);
            if (entries == null) {
                entries = new ArrayList<>();
                map.put(column[index], entries);
            }

            entries.add(index);

            return entries.size() - 1;
        }

    }

    class Element {

        char c;

        int counter;

        int pointer;

        public Element(char c, int counter) {
            this.c = c;
            this.counter = counter;
        }

        public Element(char c, int counter, int pointer) {
            this(c, counter);
            this.pointer = pointer;
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
        new InverseBWT().run();
    }

}
