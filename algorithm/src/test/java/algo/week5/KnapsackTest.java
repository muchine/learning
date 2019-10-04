package algo.week5;

import algo.week5.Knapsack;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KnapsackTest {

    @Test
    public void testSample1() {
        int[] weights = { 1, 4, 8 };
        doTest(10, weights, 9);
    }

    @Test
    public void testSample2() {
        int[] weights = { 1, 4, 8, 10 };
        doTest(20, weights, 19);
    }

    @Test
    public void testMax() {
        Random random = new Random();
        int[] weights = new int[100000];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextInt(300);
        }

        Knapsack k = new Knapsack(10000, weights);
        int result = k.optimalWeight();
        System.out.println(result);
    }


    private void doTest(int capacity, int[] weights, int expected) {
        Knapsack k = new Knapsack(capacity, weights);
        int result = k.optimalWeight();
        assertEquals(expected, result);
    }

}