package coding.codewars.level2;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("Duplicates")
public class InsaneColouredTrianglesTest {

    @Test
    public void examples() {
        assertEquals('B', InsaneColouredTriangles.triangle("B"));
        assertEquals('R', InsaneColouredTriangles.triangle("GB"));
        assertEquals('R', InsaneColouredTriangles.triangle("RRR"));
        assertEquals('B', InsaneColouredTriangles.triangle("RGBG"));
        assertEquals('G', InsaneColouredTriangles.triangle("RBRGBRB"));
        assertEquals('G', InsaneColouredTriangles.triangle("RBRGBRBGGRRRBGBBBGG"));

    }

    @Test(timeout = 16000)
    public void test1() {
        String generated = generate(100000);
        System.out.println(InsaneColouredTriangles.triangle(generated));
    }

    @Test
    public void test2() {
        for (int i = 0; i < 1000; i++) {
            String s = generate(16);
            assertEquals("" + process(s), "" + InsaneColouredTriangles.triangle(s));
        }
    }

    @Test
    public void test3() {
        String s = "GGBBGGBGGBBGBBGB";
        assertEquals("" + process(s), "" + InsaneColouredTriangles.triangle(s));
    }

    @Test
    public void test4() {
        String s = "BGBGGBBGRGGBRGGB";
        System.out.println(InsaneColouredTriangles.triangle(s));
        assertEquals("" + process(s), "" + InsaneColouredTriangles.triangle(s));
    }

    @Test(timeout = 16000)
    public void test5() {
        String generated = generate(10000000);
        System.out.println(InsaneColouredTriangles.triangle(generated));
    }

    private String generate(int length) {
        Random random = new Random();
        char[] letters = new char[] { 'R', 'G', 'B'};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(letters[random.nextInt(3)]);
        }

        String generated = sb.toString();
        System.out.println(generated);

        return generated;
    }

    public static char process(final String row) {
//        System.out.println("row: " + row);
        char[] chars = row.toCharArray();
        if (chars.length == 1) return chars[0];

        char[] result = new char[chars.length - 1];

        for (int i = 0; i < chars.length - 1; i++) {
            char c0 = chars[i];
            char c1 = chars[i + 1];
            if (c0 == c1) {
                result[i] = c0;
            }
            else {
                result[i] = "RGB"
                        .replace(String.valueOf(c0), "")
                        .replace(String.valueOf(c1), "")
                        .toCharArray()[0];
            }
        }

        return process(new String(result));
    }

}
