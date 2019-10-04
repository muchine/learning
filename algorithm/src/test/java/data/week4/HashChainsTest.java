package data.week4;

import data.week4.HashChains;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HashChainsTest {

    @Test
    public void testSample1() {
        List<HashChains.Query> queries = new ArrayList<>();
        queries.add(new HashChains.Query("add", "world"));
        queries.add(new HashChains.Query("add", "HellO"));
        queries.add(new HashChains.Query("check", 4));
        queries.add(new HashChains.Query("find", "World"));
        queries.add(new HashChains.Query("find", "world"));
        queries.add(new HashChains.Query("del", "world"));
        queries.add(new HashChains.Query("check", 4));
        queries.add(new HashChains.Query("del", "HellO"));
        queries.add(new HashChains.Query("add", "luck"));
        queries.add(new HashChains.Query("add", "GooD"));
        queries.add(new HashChains.Query("check", 2));
        queries.add(new HashChains.Query("del", "good"));

        String[] expected = { "HellO world", "no", "yes", "HellO", "GooD luck" };

        doTest(5, queries, expected);
    }

    @Test
    public void testSample2() {
        List<HashChains.Query> queries = new ArrayList<>();
        queries.add(new HashChains.Query("add", "test"));
        queries.add(new HashChains.Query("add", "test"));
        queries.add(new HashChains.Query("find", "test"));
        queries.add(new HashChains.Query("del", "test"));
        queries.add(new HashChains.Query("find", "test"));
        queries.add(new HashChains.Query("find", "Test"));
        queries.add(new HashChains.Query("add", "Test"));
        queries.add(new HashChains.Query("find", "Test"));

        String[] expected = { "yes", "no", "no", "yes" };

        doTest(4, queries, expected);
    }

    @Test
    public void testSampe3() {
        List<HashChains.Query> queries = new ArrayList<>();
        queries.add(new HashChains.Query("check", 0));
        queries.add(new HashChains.Query("find", "help"));
        queries.add(new HashChains.Query("add", "help"));
        queries.add(new HashChains.Query("add", "del"));
        queries.add(new HashChains.Query("add", "add"));
        queries.add(new HashChains.Query("find", "add"));
        queries.add(new HashChains.Query("find", "del"));
        queries.add(new HashChains.Query("del", "del"));
        queries.add(new HashChains.Query("find", "del"));
        queries.add(new HashChains.Query("check", 0));
        queries.add(new HashChains.Query("check", 1));
        queries.add(new HashChains.Query("check", 2));

        String[] expected = { " ", "no", "yes", "yes", "no", " ", "add help" };

        doTest(3, queries, expected);
    }

    private void doTest(int bucketCount, List<HashChains.Query> queries, String[] expected) {
        HashChains c = new HashChains(bucketCount);

        int index = 0;
        for (HashChains.Query query : queries) {
            c.processQuery(query);
        }

        for (int i = 0; i < expected.length; i++) {
            if (expected[i].equals(c.results.get(i))) continue;

            System.out.printf("assertion failed. result: %s, expected: %s\n", c.results.get(i), expected[i]);
            Assert.fail();
        }
    }

}