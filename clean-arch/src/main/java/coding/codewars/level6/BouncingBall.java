package coding.codewars.level6;

public class BouncingBall {
    public static int bouncingBall(double h, double bounce, double window) {
        if (h <= 0 || bounce <= 0 || bounce >= 1 || window >= h) return -1;

        int sum = bounce(h, bounce, window);
        while (true) {
            h = h * bounce;
            int count = bounce(h, bounce, window);

            if (count == 0) break;
            sum += count;
        }

        return sum;
    }

    private static int bounce(double h, double bounce, double window) {
        int sum = 0;
        if (h > window) sum++;

        double bounced = h * bounce;
        if (bounced > window) sum++;

        return sum;
    }

}
