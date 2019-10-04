package data.week6;

import data.week6.SetRangeSum;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SetRangeSumTest {

    @Test
    public void testSample1() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation('?', 1));
        operations.add(new Operation('+', 1));
        operations.add(new Operation('?', 1));
        operations.add(new Operation('+', 2));
        operations.add(new Operation('s', 1, 2));
        operations.add(new Operation('+', 1000000000));
        operations.add(new Operation('?', 1000000000));
        operations.add(new Operation('-', 1000000000));
        operations.add(new Operation('?', 1000000000));
        operations.add(new Operation('s', 999999999, 1000000000));
        operations.add(new Operation('-', 2));
        operations.add(new Operation('?', 2));
        operations.add(new Operation('-', 0));
        operations.add(new Operation('+', 9));
        operations.add(new Operation('s', 0, 9));

        String[] expected = { "Not found", "Found", "3", "Found", "Not found", "1", "Not found", "10" };

        doTest(operations, expected);
    }

    @Test
    public void testSample2() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation('?', 0));
        operations.add(new Operation('+', 0));
        operations.add(new Operation('?', 0));
        operations.add(new Operation('-', 0));
        operations.add(new Operation('?', 0));

        String[] expected = { "Not found", "Found", "Not found" };

        doTest(operations, expected);
    }

    @Test
    public void testSample3() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation('+', 491572259));
        operations.add(new Operation('?', 491572259));
        operations.add(new Operation('?', 899375874));
        operations.add(new Operation('s', 310971296, 877523306));
        operations.add(new Operation('+', 352411209));

        String[] expected = { "Found", "Not found", "491572259" };

        doTest(operations, expected);
    }

    @Test
    public void testDebugging1() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation('+', 1));
        operations.add(new Operation('+', 10));
        operations.add(new Operation('s', 2, 9));

        String[] expected = { "0" };

        doTest(operations, expected);
    }

    @Test
    public void testDebugging2() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation('+', 1));
        operations.add(new Operation('+', 10));
        operations.add(new Operation('s', 0, 11));

        String[] expected = { "11" };

        doTest(operations, expected);
    }

    @Test
    public void testCase5() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation('s', 88127140, 859949755));
        operations.add(new Operation('s', 407584225, 906606553));
        operations.add(new Operation('+', 885530090));
        operations.add(new Operation('+', 234423189));
        operations.add(new Operation('s', 30746291, 664192454));
        operations.add(new Operation('+', 465752492));
        operations.add(new Operation('s', 848498590, 481606032));
        operations.add(new Operation('+', 844636782));
        operations.add(new Operation('+', 251529178));
        operations.add(new Operation('+', 182631153));

        String[] expected = { "0", "0", "234423189", "934598870" };

        doTest(operations, expected);
    }

    private void doTest(List<Operation> operations, String[] expected) {
        SetRangeSum s = new SetRangeSum();

        for (Operation op : operations) {
            s.parse(op.type, op.op1, op.op2);
        }

        List<String> output = s.outputs;
        for (int i = 0; i < expected.length; i++) {
            System.out.println(output.get(i));
            if (expected[i].equals(output.get(i))) continue;

            System.out.printf("assertion failed. i: %d, output: %s, expected: %s\n", i, output.get(i), expected[i]);
            Assert.fail();
        }
    }

    class Operation {

        char type;
        int op1;
        int op2;

        Operation(char type, int op1) {
            this(type, op1, 0);
        }

        Operation(char type, int op1, int op2) {
            this.type = type;
            this.op1 = op1;
            this.op2 = op2;
        }

    }

}