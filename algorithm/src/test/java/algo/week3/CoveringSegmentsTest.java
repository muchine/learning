package algo.week3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CoveringSegmentsTest {

    private final CoveringSegments s = new CoveringSegments();

    @Test
    public void testSimple() {
        List<CoveringSegments.Segment> segments = new ArrayList<>();
        segments.add(new CoveringSegments.Segment(3, 6));
        segments.add(new CoveringSegments.Segment(1, 3));
        segments.add(new CoveringSegments.Segment(2, 5));

        List<Integer> expected = new ArrayList<>();
        expected.add(3);

        doTest(segments, expected);
    }

    @Test
    public void testIntermediate() {
        List<CoveringSegments.Segment> segments = new ArrayList<>();
        segments.add(new CoveringSegments.Segment(7, 8));
        segments.add(new CoveringSegments.Segment(4, 7));
        segments.add(new CoveringSegments.Segment(1, 3));
        segments.add(new CoveringSegments.Segment(2, 5));
        segments.add(new CoveringSegments.Segment(5, 6));

        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(6);
        expected.add(8);

        doTest(segments, expected);
    }

    private void doTest(List<CoveringSegments.Segment> segments, List<Integer> expected) {
        List<Integer> result = s.optimalPoints(segments);

        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

}