package coding.codewars.level4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Snail
 * https://www.codewars.com/kata/521c2db8ddc89b9b7a0000c1
 */
public class Snail {

    int direction = 0; // 0: right, 1: down, 2: left, 3: up

    int[][] array;

    int maxUp = -1;
    int maxDown;
    int maxLeft = -1;
    int maxRight;

    Snail(int[][] array) {
        this.array = array;
        maxDown = array.length;
        maxRight = array.length;
//        System.out.println(maxDown + ", " + maxRight);
    }

    public int[] perform() {
        List<Integer> numbers = new ArrayList<>();
        Point p = new Point(0, 0);
        while (p != null) {
            numbers.add(array[p.x][p.y]);
            p = move(p.x, p.y);
//            System.out.println("p: " + p);
        }

//        System.out.println(numbers);

        return numbers.stream().mapToInt(i -> i).toArray();
    }

    private Point move(int x, int y) {
        if (canMove(x, y)) {
            return next(x, y);
        } else {
            changeDirection();
            if (!canMove(x, y)) return null;
            return next(x, y);
        }
    }

    private Point next(int x, int y) {
        switch (direction) {
            case 0: return new Point(x, y + 1);
            case 1: return new Point(x + 1, y);
            case 2: return new Point(x, y - 1);
            case 3: return new Point(x - 1, y);
        }

        throw new IllegalStateException();
    }

    private boolean canMove(int x, int y) {
//        System.out.printf("directino: %s, x: %s, y: %s\n", direction, x, y);
        if (direction == 0) return y + 1 < maxRight;
        if (direction == 1) return x + 1 < maxDown;
        if (direction == 2) return y - 1 > maxLeft;
        if (direction == 3) return x - 1 > maxUp;

        throw new IllegalStateException();
    }

    private void changeDirection() {
        switch (direction) {
            case 0: maxRight--; break;
            case 1: maxDown--; break;
            case 2: maxUp++; break;
            case 3: maxLeft++; break;
        }

        direction = (direction + 1) % 4;
//        System.out.println("direction: " + direction);
    }

    public static int[] snail(int[][] array) {
        if (array.length == 0 || array[0].length == 0) return new int[]{};
        return new Snail(array).perform();
    }
}