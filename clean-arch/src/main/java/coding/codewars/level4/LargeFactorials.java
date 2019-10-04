package coding.codewars.level4;

/**
 * Large Factorials
 * https://www.codewars.com/kata/large-factorials/train/java
 */
public class LargeFactorials {

    public static String Factorial(int n) {
        Digit digits = new Digit(1);
        for (int i = 1; i <= n; i++) digits.multiply(i, 0);

        StringBuilder sb = new StringBuilder();
        digits.print(sb);

        return sb.toString();
    }

    static class Digit {

        private int value;

        private Digit next;

        Digit(int carry) {
            this.value = carry % 10;
            createNextDigit(carry);
        }

        void multiply(int n, int carry) {
            int product = value * n + carry;
            this.value = product % 10;

            if (next != null) {
                next.multiply(n, product / 10);
            } else {
                createNextDigit(product);
            }
        }

        void print(StringBuilder sb) {
            sb.insert(0, value);
            if (next != null) next.print(sb);
        }

        private void createNextDigit(int carry) {
            if (next != null || carry < 10) return;
            next = new Digit(carry / 10);
        }
    }

}
