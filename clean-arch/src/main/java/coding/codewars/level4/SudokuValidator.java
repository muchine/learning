package coding.codewars.level4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sudoku Solution Validator
 * https://www.codewars.com/kata/529bf0e9bdf7657179000008
 */
@SuppressWarnings("Duplicates")
public class SudokuValidator {

    private final int[][] sudoku;

    public SudokuValidator(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public boolean perform() {
        for (int x = 0; x < 9; x++) {
            List<Integer> row = new ArrayList<>();
            for (int y = 0; y < 9; y++) {
                row.add(sudoku[x][y]);
            }
            if (!isValid(row)) return false;
        }

        for (int y = 0; y < 9; y++) {
            List<Integer> column = new ArrayList<>();
            for (int x = 0; x < 9; x++) {
                column.add(sudoku[x][y]);
            }
            if (!isValid(column)) return false;
        }

        Point[] anchors = new Point[] {
                new Point(0, 0), new Point(0, 3), new Point(0, 6),
                new Point(3, 0), new Point(3, 3), new Point(3, 6),
                new Point(6, 0), new Point(6, 3), new Point(6, 6),
        };

        for (Point anchor : anchors) {
            if (!isValid(subgrid(anchor.x, anchor.y))) return false;
        }

        return true;
    }

    private List<Integer> subgrid(int x, int y) {
        List<Integer> grid = new ArrayList<>();
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                grid.add(sudoku[i][j]);
            }
        }

        return grid;
    }

    private boolean isValid(List<Integer> numbers) {
        String s = numbers.stream().sorted().map(i -> i.toString()).collect(Collectors.joining(""));
        return "123456789".equals(s);
    }

    public static boolean check(int[][] sudoku) {
        return new SudokuValidator(sudoku).perform();
    }
}
