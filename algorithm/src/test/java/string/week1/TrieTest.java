package string.week1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrieTest {

    @Test
    public void testSample1() {
        String[] patterns = { "ATA" };

        List<Trie.Edge> expected = new ArrayList<>();
        expected.add(new Trie.Edge(0, 1, 'A'));
        expected.add(new Trie.Edge(1, 2, 'T'));
        expected.add(new Trie.Edge(2, 3, 'A'));

        doTest(patterns, expected);
    }

    @Test
    public void testSample2() {
        String[] patterns = { "AT", "AG", "AC" };

        List<Trie.Edge> expected = new ArrayList<>();
        expected.add(new Trie.Edge(0, 1, 'A'));
        expected.add(new Trie.Edge(1, 2, 'T'));
        expected.add(new Trie.Edge(1, 3, 'G'));
        expected.add(new Trie.Edge(1, 4, 'C'));

        doTest(patterns, expected);
    }

    @Test
    public void testSample3() {
        String[] patterns = { "ATAGA", "ATC", "GAT" };

        List<Trie.Edge> expected = new ArrayList<>();
        expected.add(new Trie.Edge(0, 1, 'A'));
        expected.add(new Trie.Edge(1, 2, 'T'));
        expected.add(new Trie.Edge(2, 3, 'A'));
        expected.add(new Trie.Edge(3, 4, 'G'));
        expected.add(new Trie.Edge(4, 5, 'A'));
        expected.add(new Trie.Edge(2, 6, 'C'));
        expected.add(new Trie.Edge(0, 7, 'G'));
        expected.add(new Trie.Edge(7, 8, 'A'));
        expected.add(new Trie.Edge(8, 9, 'T'));

        doTest(patterns, expected);
    }

    private void doTest(String[] patterns, List<Trie.Edge> expected) {
        Trie t = new Trie(patterns);
        List<Trie.Edge> edges = t.edges();

        for (Trie.Edge e : expected) {
            if (isContained(e, edges)) continue;

            System.out.printf("assertion failed. e: %s\n", e);
            Assert.fail();
        }
    }

    private boolean isContained(Trie.Edge e, List<Trie.Edge> edges) {
        for (Trie.Edge edge : edges) {
            if (edge.equals(e)) return true;
        }

        return false;
    }

}