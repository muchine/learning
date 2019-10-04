package coding.codewars.level4;

/**
 * Catching Car Mileage Numbers
 * https://www.codewars.com/kata/catching-car-mileage-numbers/train/java
 */
public class CarMileage {

    private int[] awesumePhrases;

    public CarMileage(int[] awesomePhrases) {
        this.awesumePhrases = awesomePhrases;
    }

    public int execute(int number) {
        if (isInteresting(number)) return 2;
        if (isInteresting(number + 1) || isInteresting(number + 2)) return 1;

        return 0;
    }

    private boolean isInteresting(int number) {
        if (number < 100) return false;
        if (isAwesumePhrase(number)) return true;

        int[] digits = toDigits(number);
        return isFollowingAllZeros(digits) || isSameDigit(digits) || isIncrementing(digits) || isDecrementing(digits) || isPalindrome(digits);
    }

    private int[] toDigits(int number) {
        String value = String.valueOf(number);
        int[] digits = new int[value.length()];
        for (int i = 0; i < value.length(); i++) digits[i] = Integer.parseInt("" + value.charAt(i));

        return digits;
    }

    private boolean isFollowingAllZeros(int[] digits) {
        for (int i = 1; i < digits.length; i++) if (digits[i] != 0) return false;
        return true;
    }

    private boolean isSameDigit(int[] digits) {
        int digit = digits[0];
        for (int i = 1; i < digits.length; i++) if (digits[i] != digit) return false;
        return true;
    }

    private boolean isIncrementing(int[] digits) {
        int digit = digits[0];
        for (int i = 1; i < digits.length; i++) {
            if (digit == 0) return false;

            int nextDigit = digit == 9 ? 0 : digit + 1;
            if (digits[i] != nextDigit) return false;

            digit = nextDigit;
        }

        return true;
    }

    private boolean isDecrementing(int[] digits) {
        int digit = digits[0];
        for (int i = 1; i < digits.length; i++) {
            if (digit == 0) return false;

            int nextDigit = digit - 1;
            if (digits[i] != nextDigit) return false;

            digit = nextDigit;
        }

        return true;
    }

    private boolean isPalindrome(int[] digits) {
        for (int i = 0; i < digits.length / 2; i++) {
            if (digits[i] != digits[digits.length - 1 - i]) return false;
        }

        return true;
    }

    private boolean isAwesumePhrase(int number) {
        for (int phrase : awesumePhrases) {
            if (number == phrase) return true;
        }

        return false;
    }

    public static int isInteresting(int number, int[] awesomePhrases) {
        StringBuilder sb = new StringBuilder();
        for (int p : awesomePhrases) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(p);
        }

        System.out.printf("number: %s, pharses: %s\n", number, sb.toString());
        return new CarMileage(awesomePhrases).execute(number);
    }

}
