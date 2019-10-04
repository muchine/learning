package coding.codewars.level7;

@SuppressWarnings("Duplicates")
public class ColouredTriangles {

    public static char triangle(final String row) {
        System.out.println(row);
        return process(row);
    }

    private static char process(final String row) {
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
