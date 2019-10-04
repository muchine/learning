package data.week6;

import org.junit.Assert;
import org.junit.Test;
import util.Helper;

import java.util.List;

public class TreeNodeTest {

    @Test
    public void testSample1() {
        int[] keys = { 4, 2, 5, 1, 3 };
        int[] left = { 1, 3, -1, -1, -1 };
        int[] right = { 2, 4, -1, -1, -1 };

        int[] inOrder = { 1, 2, 3, 4, 5 };
        int[] preOrder = { 4, 2, 1, 3, 5 };
        int[] postOrder = { 1, 3, 2, 5, 4 };

        doTestInOrder(keys, left, right, inOrder);
        doTestPreOrder(keys, left, right, preOrder);
        doTestPostOrder(keys, left, right, postOrder);
    }

    @Test
    public void testSample2() {
        int[] keys = { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90 };
        int[] left = { 7, -1, -1, 8, 3, -1, 1, 5, -1, -1 };
        int[] right = { 2, -1, 6, 9, -1, -1, -1, 4, -1, -1 };

        int[] inOrder = { 50, 70, 80, 30, 90, 40, 0, 20, 10, 60 };
        int[] preOrder = { 0, 70, 50, 40, 30, 80, 90, 20, 60, 10 };
        int[] postOrder = { 50, 80, 90, 30, 40, 70, 10, 60, 20, 0 };

        doTestInOrder(keys, left, right, inOrder);
        doTestPreOrder(keys, left, right, preOrder);
        doTestPostOrder(keys, left, right, postOrder);
    }

    @Test
    public void testMax() {
        int max = 100000;
        int[] keys = new int[max];
        int[] left = new int[max];
        int[] right = new int[max];

        for (int i = 0; i < max; i++) {
            keys[i] = max - i;
            left[i] = i + 1;
            right[i] = -1;
        }

        left[max - 1] = -1;

        TreeTraverser traverser = new TreeTraverser(keys, left, right);
        List<Integer> printed = traverser.printInOrder();
        Helper.printCollection("result", printed);

    }

    private void doTestInOrder(int[] keys, int[] left, int[] right, int[] expected) {
        TreeTraverser traverser = new TreeTraverser(keys, left, right);
        List<Integer> printed = traverser.printInOrder();

        doAssertion(printed, expected);
    }

    private void doTestPreOrder(int[] keys, int[] left, int[] right, int[] expected) {
        TreeTraverser traverser = new TreeTraverser(keys, left, right);
        List<Integer> printed = traverser.printPreOrder();

        doAssertion(printed, expected);
    }

    private void doTestPostOrder(int[] keys, int[] left, int[] right, int[] expected) {
        TreeTraverser traverser = new TreeTraverser(keys, left, right);
        List<Integer> printed = traverser.printPostOrder();

        doAssertion(printed, expected);
    }

    private void doAssertion(List<Integer> printed, int[] expected) {
        Helper.printCollection("result", printed);

        for (int i = 0; i < expected.length; i++) {
            if (printed.get(i).equals(expected[i])) continue;

            System.out.printf("assertion failed. i: %d, result: %d, expected: %d\n", i, printed.get(i), expected[i]);
            Assert.fail();
        }
    }


}