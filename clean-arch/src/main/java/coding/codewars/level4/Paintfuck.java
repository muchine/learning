package coding.codewars.level4;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class Paintfuck {

    private char[] code;
    private final int width;
    private final int height;
    private final int[][] grid;
    private int iteration;

    private final Map<Integer, Integer> forwards = new HashMap<>();
    private final Map<Integer, Integer> backwards = new HashMap<>();

    private int x, y = 0;

    public Paintfuck(String code, int iteration, int width, int height) {
        System.out.printf("code: %s, iteration: %s, width: %d, height: %d\n", code, iteration, width, height);
        initCode(code);
        this.iteration = iteration;
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];

        inspectBrackets();
    }

    private void initCode(String code) {
        List<Character> chars = code.chars()
                .mapToObj(c -> Character.valueOf((char) c))
                .filter(c -> "nesw*[]".contains(c.toString()))
                .collect(Collectors.toList());
        this.code = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) this.code[i] = chars.get(i);
    }

    public String interpret() {
        int index = 0;
        int count = 0;
        while (index < code.length && count < iteration) {
            char c = code[index];
//            System.out.printf("c: %c\n", c);
            if (c == '[') {
                index = grid[x][y] == 0 ? forwards.get(index) + 1 : index + 1;
            } else if (c == ']') {
                index = grid[x][y] != 0 ? backwards.get(index) + 1 : index + 1;
            } else {
                process(index);
                index++;
            }

//            System.out.printf("index: %d\n", index);
            count++;
        }

        return format();
    }

    private void process(int index) {
        char c = code[index];
        if (c == 'n') {
            x--;
            if (x < 0) x = height - 1;
        } else if (c == 's') {
            x++;
            if (x >= height) x = 0;
        } else if (c == 'w') {
            y--;
            if (y < 0) y = width - 1;
        } else if (c == 'e') {
            y++;
            if (y >= width) y = 0;
        } else if (c == '*') {
            grid[x][y] = grid[x][y] == 0 ? 1 : 0;
        }
    }

    private String format() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(grid[i][j]);
            }

            if (i < height - 1) sb.append("\r\n");
        }

        return sb.toString();
    }

    private void inspectBrackets() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < code.length; i++) {
            if (code[i] == '[') stack.push(i);
            if (code[i] == ']') {
                int open = stack.pop();
                forwards.put(open, i);
                backwards.put(i, open);
            }
        }
    }

    public static String interpreter(String code, int iterations, int width, int height) {
        return new Paintfuck(code, iterations, width, height).interpret();
    }

    public static void main(String[] args) {
        Paintfuck pf = new Paintfuck("*e*e*e*es*12311aqqes*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 100, 10, 10);
        System.out.println(Arrays.asList(pf.code));
    }
}