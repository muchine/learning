package coding.codewars.level3;

@SuppressWarnings("Duplicates")
public class FindDigit {

    public int findDecimalDigit(long position) {
        long base = 1;
        while (position(base * 10) < position) {
            base *= 10;
        }

        long basePosition = position(base);
        int length = String.valueOf(base).length();
//        System.out.printf("base: %s, basePosition: %s, position: %s, length: %s \n", base, basePosition, position, length);
        long number = base + ((position - basePosition) / length);
        long delta = (position - basePosition) % length;
//        System.out.printf("number: %s, decimal delta: %s\n", number, delta);

        return Integer.parseInt("" + String.valueOf(number).charAt((int) delta));
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

    public int findSquareDigit(long position) {
        long base = 1;
        long basePosition = 0;

        while (true) {
            long baseNumber = (long) Math.ceil(Math.sqrt(base));
            int length = String.valueOf(baseNumber * baseNumber).length();
            long increment = ((long) Math.sqrt(base * 10 - 1) - baseNumber + 1) * length;

//            System.out.printf("base position: %s, baseNumber: %s, length: %s, increment %s\n", basePosition, baseNumber, length, increment);
            if (basePosition + increment > position) break;
            base = base * 10;
            basePosition += increment;
        }

        long baseNumber = (long) Math.ceil(Math.sqrt(base));
        int length = String.valueOf(baseNumber * baseNumber).length();
        long number = baseNumber + ((position - basePosition) / length);
        long delta = (position - basePosition) % length;
        long numberSquare = number * number;
//        System.out.printf("number: %s, number square: %s, square delta: %s\n", number, numberSquare, delta);
//        System.out.printf("baseNumber: %s, basePos: %s, length: %s, number: %s\n", baseNumber, basePosition, length, number);
        return Integer.parseInt("" + String.valueOf(numberSquare).charAt((int) delta));
    }

    public int findSum(int n) {
        int decimal = findDecimalDigit(n);
        int square = findSquareDigit(n);
        System.out.printf("decimal: %s, square: %s\n", decimal, square);
        return decimal + square;
    }

    public int find(int n) {
        int digit = findSum(n);
        int nextDigit = findSum(n + 1);
        System.out.printf("n: %s, digit: %s, nextDigit: %s\n", n, digit, nextDigit);

        if (nextDigit >= 10) {
            return digit + 1;
        } else if (nextDigit <= 8) {
            return digit;
        } else {
            nextDigit = find(n + 1);
            return nextDigit >= 10 ? digit + 1 : digit;
        }
    }

    public static int findDigit(int n) {
        System.out.println("input: " + n);
        FindDigit fd = new FindDigit();
        return fd.find(n) % 10;
    }

    private static void testDecimal() {
        FindDigit fd = new FindDigit();

        long number = 100000000;
        long basePos = 788888889;
        String decimal = String.valueOf(number);
        int pos = 0;
        long currentPos = basePos;
        long start = System.nanoTime();
        while (number < 221484643) {
//            int found = fd.findSquareDigit(1553752483 + count++);
            int found = fd.findDecimalDigit(currentPos);

            int digit = Integer.parseInt("" + decimal.charAt(pos++));
//            System.out.printf("found: %s, digit: %s\n", found, digit);

            if (found != digit) throw new RuntimeException(String.format("position: %s, found: %s, decimal: %s\n", currentPos, found, digit));
            if (currentPos == 1882250674) System.out.println("digit: " + digit);

            if (pos >= decimal.length()) {
                number += 1;
                decimal = String.valueOf(number);
                if (number % 1000000 == 0) System.out.println("number: " + number);
//                System.out.printf("number: %s, decimal: %s\n", number, decimal);
                pos = 0;
            }

            currentPos++;
        }
        long end = System.nanoTime();
        System.out.printf("position: %s\n", currentPos);
        System.out.println("time elapsed: " + (end - start) / 1000000);
    }

    private static void testSquare() {
        FindDigit fd = new FindDigit();

        long number = 110000000;
//        long basePos = 1553752483;
        long basePos = 1723752483;
        String square = String.valueOf(number * number);
        int pos = 0;
        long currentPos = basePos;
        long start = System.nanoTime();
        while (number < 119323424) {
//            int found = fd.findSquareDigit(1553752483 + count++);
            int found = fd.findSquareDigit(currentPos);
            if (pos >= square.length()) {
                number += 1;
                square = String.valueOf(number * number);
//                System.out.printf("number: %s, square: %s\n", number, square);
                pos = 0;
            }

            int digit = Integer.parseInt("" + square.charAt(pos++));
//            System.out.printf("found: %s, square: %s\n", found, digit);

            if (found != digit) throw new RuntimeException(String.format("position: %s, found: %s, square: %s\n", currentPos, found, digit));
            if (currentPos == 1882250674) System.out.println("digit: " + digit);
            currentPos++;
        }
        long end = System.nanoTime();
        System.out.printf("position: %s\n", currentPos - 1);
        System.out.println("time elapsed: " + (end - start) / 1000000);
    }

    public static void main(String[] args) {
        testDecimal();

//        System.out.println(Long.MAX_VALUE);
//        System.out.println(fd.position(1));
//        System.out.println(fd.findDecimalDigit(1));
//
//        System.out.println(Math.sqrt(100));
//        System.out.println(Math.sqrt(999));
//        System.out.println(fd.findSquareDigit(22));
    }

}
