import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CircuitDesignTest {

    @Test
    public void testDefaultCase() {
        List<CNF> clauses = new ArrayList<>();

        clauses.add(new CNF(-1, 2));
        clauses.add(new CNF(-2, 3));
        clauses.add(new CNF(1, -3));
        clauses.add(new CNF(2, 3));

        doTest(3, clauses, new int[] { 1, 2, 3 });

    }

    @Test
    public void testSample1() {
        List<CNF> clauses = new ArrayList<>();

        clauses.add(new CNF(1, -3));
        clauses.add(new CNF(-1, 2));
        clauses.add(new CNF(-2, -3));

        doTest(3, clauses, new int[] { 1, 2, -3 });
    }

    @Test
    public void testSample2() {
        List<CNF> clauses = new ArrayList<>();

        clauses.add(new CNF(1, 1));
        clauses.add(new CNF(-1, -1));

        doTest(1, clauses, null);
    }

    private void doTest(int numOfVariables, List<CNF> clauses, int[] expected) {
        CircuitDesign cd = new CircuitDesign(numOfVariables, clauses.size());

        for (int i = 0; i < clauses.size(); i++) {
            CNF c = clauses.get(i);
            cd.add(i, c.first, c.second);
        }

        int[] result = cd.run();

        assertEquals(expected != null, cd.satisfied);
        if (cd.satisfied) {
            for (int i = 0 ; i < numOfVariables; i++) {
                if (expected[i] == result[i]) continue;

                System.out.printf("Assertion failed. i: %d, e: %d, r: %d", i, expected[i], result[i]);
                Assert.fail();
            }
        }
    }

    class CNF {

        int first, second;

        public CNF(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

}