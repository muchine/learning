package coding.codewars.level2;

@SuppressWarnings("Duplicates")
public class InsaneColouredTriangles {

    /**
     * https://math.stackexchange.com/questions/2257158/three-color-triangle-challenge
     * https://stackoverflow.com/questions/53585022/three-colors-triangles
     */
    public char execute(String row) {
        char[] chars = row.toCharArray();
        char[] color = new char[] { 'R', 'G', 'B' };
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum += colorNumber(chars[i]) * lucas3(chars.length - 1, i);
        }

        if (chars.length % 2 == 0) sum = -sum;
        return sum >= 0 ? color[sum % 3] : color[((sum % 3) + 3) % 3];
    }

    private int colorNumber(char c) {
        switch(c) {
            case 'R': return 0;
            case 'G': return 1;
            case 'B': return 2;
            default: throw new IllegalArgumentException();
        }
    }

    private int lucas3(int n, int k) {
        char[] digN = base3(n);
        char[] digK = base3(k);

        int prod = 1;
        for (int i = 0; i < digN.length; i++) {
            int ni = Integer.parseInt(String.valueOf(digN[i])) ;
            int ki = (i < digK.length) ? Integer.parseInt(String.valueOf(digK[i])) : 0;
            prod = prod * binomMax(ni, ki);
        }

        return prod % 3;
    }

    private char[] base3(int n) {
        char[] chars = Integer.toString(n, 3).toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) result[i] = chars[chars.length - 1 - i];

        return result;
    }

    private int binomMax(int n, int k) {
        if (n < k) return 0;

        switch (n) {
            case 0:
            case 1:
                return 1;
            case 2:
                return k == 1 ? 2 : 1;
            default:
                return 0;
        }
    }

    public static char triangle(final String row) {
//        System.out.println(row);
        return new InsaneColouredTriangles().execute(row);
    }

}
