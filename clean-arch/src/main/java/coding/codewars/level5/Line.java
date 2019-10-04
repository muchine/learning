package coding.codewars.level5;

public class Line {

    public static String WhoIsNext(String[] names, long n) {
        int index = perform(names.length, n);
        return names[index];
    }

    private static int perform(int nameCount, long n) {
        long itemCount = 1;
        while (true) {
            long chunkCount = itemCount * nameCount;
            if (n < chunkCount) {
                for (int i = 0; i < nameCount; i++) {
                    if (itemCount * (i + 1) >= n) return i;
                }
            } else {
                n = n - chunkCount;
                itemCount *= 2;
            }
        }
    }

}
