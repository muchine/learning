package coding.codewars.level4;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Path Finder #4: Where Am I?
 * https://www.codewars.com/kata/5a0573c446d8435b8e00009f
 */
public class WhereAmI {

    private static Pattern digits = Pattern.compile("^[0-9]*");

    private static int direction = 270;
    private static Point position = new Point(0, 0);

    private static void right() {
        direction = (direction + 90) % 360;
    }

    private static void left() {
        direction = (direction + 270) % 360;
    }

    private static void opposite() {
        direction = (direction + 180) % 360;
    }

    private static void go(int number) {
        switch (direction) {
            case 0: position.y += number; break;
            case 90: position.x += number; break;
            case 180: position.y -= number; break;
            case 270: position.x -= number; break;
        }
    }

    public static Point iAmHere(String path) {
        System.out.println(path);

        while (!path.isEmpty()) {
            if (path.startsWith("r")) {
                right();
                path = path.substring(1);
            } else if (path.startsWith("l")) {
                left();
                path = path.substring(1);
            } else if (path.startsWith("L") || path.startsWith("R")) {
                opposite();
                path = path.substring(1);
            } else {
                Matcher m = digits.matcher(path);
                m.find();
                String number = m.group();
                path = path.replaceFirst(number, "");
                go(Integer.parseInt(number));
            }
        }

        System.out.printf("position: %s, direction: %s\n", position, direction);
        return position;
    }

    public static void main(String[] args) {
        WhereAmI.iAmHere("12341r11LLRlr2133");
    }

}
