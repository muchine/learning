package data.week3;

import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {
    private InputReader reader;
    private OutputWriter writer;

    int[] maxNumbers;

    public MergingTables() {}

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    int maximumNumberOfRows = -1;

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            writer.printf("%d\n", maximumNumberOfRows);
        }
    }

    public void execute(int[] rows, int[] sources, int[] destinations) {
        Table[] tables = new Table[rows.length];
        for (int i = 0; i < rows.length; i++) {
            tables[i] = new Table(rows[i]);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, rows[i]);
        }

        maxNumbers = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            int destination = destinations[i] - 1;
            int source = sources[i] - 1;

            if (destination == 0 || source == 0) {
//                System.out.printf("i: %d, dest: %d, source: %d\n", i, destination, source);
            }

            merge(tables[destination], tables[source]);
            maxNumbers[i] = maximumNumberOfRows;
        }
    }

    void merge(Table destination, Table source) {
        Table destRoot = destination.root();
        Table sourceRoot = source.root();
        if (destRoot == sourceRoot) {
            return;
        }

        if (destRoot.rank > sourceRoot.rank) {
            destRoot.merge(sourceRoot);
        } else {
            sourceRoot.merge(destRoot);
        }

        maximumNumberOfRows = max(maximumNumberOfRows, sourceRoot.numberOfRows, destRoot.numberOfRows);
    }

    private int max(int... numbers) {
        int maxValue = Integer.MIN_VALUE;

        for (int n : numbers)
            if (n > maxValue) maxValue = n;

        return maxValue;
    }

    class Table {
        Table parent;
        int rank;
        int numberOfRows;

        Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
        }

        void merge(Table source) {
            if (this.rank < source.rank)
                throw new RuntimeException("Rank is smaller than the merging table");

            source.parent = this;
            this.numberOfRows += source.numberOfRows;
            source.numberOfRows = 0;

            if (this.rank == source.rank)
                this.rank += 1;
        }

        Table root() {
            if (parent == null) return this;

            Table root = parent.root();
            this.parent = root;

            return root;
        }

    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).run();
        writer.writer.flush();
    }

}
