package advanced.week1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockChartsTest {

    @Test
    public void testSample1() {
        int[][] stocks = {
                { 1, 2, 3, 4 },
                { 2, 3, 4, 6 },
                { 6, 5, 4, 3 }
        };

        doTest(3, stocks, 2);
    }

    @Test
    public void testSample2() {
        int[][] stocks = {
                { 5, 5, 5 },
                { 4, 4, 6 },
                { 4, 5, 4 }
        };

        doTest(3, stocks, 3);
    }

    @Test
    public void testCase6() {
        int[][] stocks = {
                { 2, 1002, 2 },
                { 1005, 5, 1005 },
                { 6, 1006, 1006 },
                { 1001, 1, 1 },
                { 1007, 1007, 1007 },
                { 4, 4, 1004 },
                { 0, 0, 0 },
                { 1003, 1003, 3 }
        };

        doTest(8, stocks, 3);
    }

    private void doTest(int numberOfStocks, int[][] stocks, int expected) {
        StockCharts a = new StockCharts(numberOfStocks);
        int charts = a.minCharts(stocks);
        assertEquals(expected, charts);
    }

}