package advanced.week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class BudgetAllocation {

    private final int[][] A;
    private final int[] b;

    public BudgetAllocation(int[][] A, int[] b) {
        this.A = A;
        this.b = b;
    }

    public List<Clause> toCNF() {
        List<Clause> cnf = new ArrayList<>();

        for (int i = 0; i < b.length; i++) {
            int[] variables = A[i];

            Coefficient[] coefficients = findPositiveCoefficients(variables);
            List<Inspector> inspectors = createInspectors();

            for (Inspector inspector : inspectors) {
                if (!inspector.inspect(coefficients, b[i])) continue;


            }
        }

        return cnf;
    }

    private Coefficient[] findPositiveCoefficients(int[] variables) {
        Coefficient[] coefficients = new Coefficient[3];

        int index = 0;
        for (int i = 0; i < variables.length; i++) {
            if (variables[i] == 0) continue;

            coefficients[index] = new Coefficient(i, variables[i]);
            index++;
        }

        return coefficients;
    }

    private List<Inspector> createInspectors() {
        List<Inspector> inspectors = new ArrayList<>();
        inspectors.add(new Inspector(0, 0, 0));
        inspectors.add(new Inspector(0, 0, 1));
        inspectors.add(new Inspector(0, 1, 0));
        inspectors.add(new Inspector(0, 1, 1));
        inspectors.add(new Inspector(1, 0, 0));
        inspectors.add(new Inspector(1, 0, 1));
        inspectors.add(new Inspector(1, 1, 0));
        inspectors.add(new Inspector(1, 1, 1));

        return inspectors;
    }

    class Inspector {

        int[] booleans = new int[3];

        Inspector(int x, int y, int z) {
            booleans[0] = x;
            booleans[1] = y;
            booleans[2] = z;
        }

        boolean inspect(Coefficient[] coefficients, int b) {
            return booleans[0] * coefficients[0].value +
                    booleans[1] * coefficients[1].value +
                    booleans[2] * coefficients[2].value <= b;
        }

    }

    class Coefficient {

        int position = -1;

        int value = 0;

        public Coefficient(int position, int value) {
            this.position = position;
            this.value = value;
        }
    }

    class Clause {

        private final List<Integer> variables = new ArrayList<>();

        Clause(int... args) {
            for (int a : args) variables.add(a);
        }

        void print(OutputWriter writer) {
            StringBuilder builder = new StringBuilder();
            for (int v : variables) {
                builder.append(v + " ");
            }

            builder.append("0\n");
            writer.printf(builder.toString());
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

        int n = reader.nextInt();
        int m = reader.nextInt();

        int[][] A = new int[n][m];
        int[] b = new int[n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                A[i][j] = reader.nextInt();
            }
        }
        for (int i = 0; i < n; ++i) {
            b[i] = reader.nextInt();
        }

        BudgetAllocation ba = new BudgetAllocation(A, b);

//        converter.printEquisatisfiableSatFormula();

        writer.writer.flush();
    }

}
