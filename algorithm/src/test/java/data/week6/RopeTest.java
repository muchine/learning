package data.week6;

import data.week6.Rope;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RopeTest {

    @Test
    public void testSample1() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(1, 1, 2));
        operations.add(new Operation(6, 6, 7));

        doTest(operations, "hlelowrold", "helloworld");
    }

    @Test
    public void testSample2() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(0, 1, 1));
        operations.add(new Operation(4, 5, 0));

        doTest(operations, "abcdef", "efcabd");
    }

    @Test
    public void testDebugging1() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(0, 2, 6));
        operations.add(new Operation(0, 2, 6));
        operations.add(new Operation(0, 2, 6));

        doTest(operations, "abcdefghi", "abcdefghi");
    }

    @Test
    public void testDebugging2() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation(6, 8, 0));
        operations.add(new Operation(6, 8, 0));
        operations.add(new Operation(6, 8, 0));

        doTest(operations, "abcdefghi", "abcdefghi");
    }

    @Test(timeout = 6000)
    public void testMax() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 30000; i++) {
            builder.append("abcdefghij");
        }

        String text = builder.toString();
        System.out.printf("text length: %d\n", text.length());

        List<Operation> operations = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            operations.add(new Operation(1, 200000, 0));
        }

        Rope rope = new Rope(text);
        for (Operation op : operations) {
            rope.process(op.i, op.j, op.k);
        }
    }

    private void doTest(List<Operation> operations, String text, String expected) {
        Rope rope = new Rope(text);

        for (Operation op : operations) {
            rope.process(op.i, op.j, op.k);
        }

        assertEquals(expected, rope.stringify());
    }

    class Operation {
        int i, j, k;

        Operation(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }
    }
}