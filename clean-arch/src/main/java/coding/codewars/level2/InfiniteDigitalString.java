package coding.codewars.level2;

public class InfiniteDigitalString {

    private final String s;

    public InfiniteDigitalString(String s) {
        this.s = s;
    }

    public long findPosition() {
        long position = Long.MAX_VALUE;
        for (int a = 0; a <= s.length() - 1; a++) {
            for (int b = a + 1; b <= s.length(); b++) {
                String beginning = a == 0 ? "" : s.substring(0, a);
                String token = s.substring(a, b);
                String end = b == s.length() ? "" : s.substring(b, s.length());
//                System.out.printf("%s | %s | %s\n", beginning, token, end);

                if (token.startsWith("0")) continue;

                if (isBeginningMatched(token, beginning) && isEndMatched(token, end)) {
                    long newPosition = position(Long.parseLong(token));
                    if (newPosition < position) {
                        System.out.printf("token: %s, new position: %s\n", token, newPosition);
                        position = newPosition - a;
                    }
                }
            }
        }

        for (int i = 1; i < s.length(); i++) {
            long newPosition = findOverlappingPosition(s.substring(0, i), s.substring(i, s.length()));
            if (newPosition < position) {
                System.out.printf("i: %s, new position: %s\n", i, newPosition);
                position = newPosition;
            }
        }

        if (position == Long.MAX_VALUE) position = position(Long.parseLong("1" + s)) + 1;

        return position;
    }

    private long findOverlappingPosition(String beginning, String end) {
        if (end.startsWith("0")) return Long.MAX_VALUE;
        String incremented = increment(beginning);
        if (incremented.length() != beginning.length()) throw new IllegalStateException("Incremented is invalid.");

        long position = position(Long.parseLong(end + incremented)) - beginning.length();

        for (int i = 1; i < incremented.length(); i++) {
            String head = incremented.substring(0, i);
            String tail = incremented.substring(i, incremented.length());

            if (end.endsWith(head)) {
                if (beginning.startsWith("0") && (end.equals(head) || end.equals("1"))) continue;
                String token = end + tail;
                long newPosition = position(Long.parseLong(token)) - beginning.length();
                if (newPosition < position) position = newPosition;
            }
        }

        return position;
    }

    private String increment(String token) {
        String result = String.valueOf(Long.parseLong(token) + 1);
        if (result.length() == token.length()) {
            return result;
        } else if (result.length() > token.length()) {
            return result.substring(1);
        } else {
            for (int i = result.length(); i < token.length(); i++) result = "0" + result;
            return result;
        }
    }

    private boolean isBeginningMatched(String token, String beginning) {
        String s = beginning;
        if (beginning.isEmpty()) return true;

        long t = Long.parseLong(token) - 1;
        String tail = String.valueOf(t);
        while (tail.length() <= s.length() && t >= 1) {
            if (t <= 0) return false;
            if (s.endsWith(tail)) {
                s = s.substring(0, s.length() - tail.length());
                t--;
                tail = String.valueOf(t);
            } else {
                return false;
            }
        }

        return s.isEmpty() || (t > 0 && String.valueOf(t).endsWith(s));
    }

    private boolean isEndMatched(String token, String end) {
        String s = end;
        if (end.isEmpty()) return true;

        long t = Long.parseLong(token) + 1;
        String head = String.valueOf(t);
        while (head.length() <= s.length()) {
            if (s.startsWith(head)) {
                s = s.replaceFirst(head, "");
                t++;
                head = String.valueOf(t);
            } else {
                return false;
            }
        }

//        System.out.printf("s: %s, t: %s\n", s, t);
        return s.isEmpty() || String.valueOf(t).startsWith(s);
    }

    private long position(long n) {
        int length = String.valueOf(n).length();
        long position = 0;
        for (int i = 1; i <= length - 1; i++) {
            position += i * (9 * Math.pow(10, i - 1));
        }

        position += length * (n - Math.pow(10, length - 1));
        return position;
    }

    public static long findPosition(final String s) {
        System.out.println("input: " + s);
        return new InfiniteDigitalString(s).findPosition();
    }

    public long findNumber(long position) {
        long base = 1;
        while (position(base * 10) < position) {
            base *= 10;
        }

        long basePosition = position(base);
        System.out.printf("base: %s, basePosition: %s, position: %s \n", base, basePosition, position);
        return base + ((position - basePosition) / String.valueOf(base).length());
    }

    public static void main(String[] args) {
        InfiniteDigitalString idc = new InfiniteDigitalString("10110");
        System.out.println(idc.position(1230));
        System.out.println(idc.findNumber(3812));
    }

}
