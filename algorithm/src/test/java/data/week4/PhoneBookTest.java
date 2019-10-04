package data.week4;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookTest {

    private final PhoneBook b = new PhoneBook();

    @Test
    public void testSample1() {
        List<PhoneBook.Query> queries = new ArrayList<>();
        queries.add(new PhoneBook.Query("add", "police", 911));
        queries.add(new PhoneBook.Query("add", "Mom", 76213));
        queries.add(new PhoneBook.Query("add", "Bob", 17239));
        queries.add(new PhoneBook.Query("find", 76213));
        queries.add(new PhoneBook.Query("find", 910));
        queries.add(new PhoneBook.Query("find", 911));
        queries.add(new PhoneBook.Query("del", 910));
        queries.add(new PhoneBook.Query("del", 911));
        queries.add(new PhoneBook.Query("find", 911));
        queries.add(new PhoneBook.Query("find", 76213));
        queries.add(new PhoneBook.Query("add", "daddy", 76213));
        queries.add(new PhoneBook.Query("find", 76213));

        String[] expected = { "Mom", "not found", "police", "not found", "Mom", "daddy" };

        doTest(queries, expected);
    }

    @Test
    public void testSample2() {
        List<PhoneBook.Query> queries = new ArrayList<>();
        queries.add(new PhoneBook.Query("find", 3839442));
        queries.add(new PhoneBook.Query("add", "me", 123456));
        queries.add(new PhoneBook.Query("add", "granny", 0));
        queries.add(new PhoneBook.Query("find", 0));
        queries.add(new PhoneBook.Query("find", 123456));
        queries.add(new PhoneBook.Query("del", 0));
        queries.add(new PhoneBook.Query("del", 0));
        queries.add(new PhoneBook.Query("find", 0));

        String[] expected = { "not found", "granny", "me", "not found" };

        doTest(queries, expected);
    }

    private void doTest(List<PhoneBook.Query> queries, String[] expected) {
        for (PhoneBook.Query query : queries) {
            b.processQuery(query);
        }

        for (int i = 0; i < expected.length; i++) {
            String result = b.findResults.get(i);
            if (!expected[i].equals(result)) {
                System.out.printf("assertion failed: i: %d, expected: %s, result: %s\n", i, expected[i], result);
                Assert.fail();
            }
        }

    }

}