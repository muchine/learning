package coding.codewars.level4;

import java.util.ArrayList;
import java.util.List;

/**
 * Range Extraction
 * https://www.codewars.com/kata/range-extraction/train/java
 */
public class RangeExtraction {

    private int[] arr;

    List<Integer> queue = new ArrayList<>();
    List<String> buffer = new ArrayList<>();

    RangeExtraction(int[] arr) {
        this.arr = arr;
    }

    public String perform() {
        for (int i : arr) {
            if (!queue.isEmpty() && i != last() + 1) flush();
            queue.add(i);
        }

        flush();
        return String.join(",", buffer);
    }

    private int last() {
        return queue.get(queue.size() - 1);
    }

    private void flush() {
        if (queue.size() < 3) {
            queue.forEach(e -> buffer.add(e.toString()));
        } else {
            buffer.add(queue.get(0).toString() + "-" + queue.get(queue.size() - 1).toString());
        }

        queue.clear();
    }

    public static String rangeExtraction(int[] arr) {
        RangeExtraction extractor = new RangeExtraction(arr);
        return extractor.perform();
    }

}
