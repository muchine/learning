package data.week3;

import org.junit.Test;
import util.Helper;

import static org.junit.Assert.assertEquals;

public class BuildHeapTest {

    @Test
    public void testSample1() {
        int[] data = { 5, 4, 3, 2, 1 };
        doTest(data, 3);
    }

    @Test
    public void testSample2() {
        int[] data = { 1, 2, 3, 4, 5 };
        doTest(data, 0);
    }

    private void doTest(int[] data, int expected) {
        BuildHeap heap = new BuildHeap(data);
        heap.generateSwaps();
        Helper.printArray(heap.data);
        System.out.printf("swap count: %d", heap.swaps.size());
        assertEquals(expected, heap.swaps.size());
    }

}