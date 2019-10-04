package coding.codewars.level2;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class SudokuSolver {

    private static final String digits = "123456789";
    private static final String rows = "ABCDEFGHI";
    private static final String cols = digits;

    private final int[][] grid;
    private final List<Map<String, String>> solved = new ArrayList<>();

    private String[] squares;
    private List<List<String>> unitList = new ArrayList<>();
    private Map<String, List<List<String>>> units = new HashMap<>();
    private Map<String, Set<String>> peers = new HashMap<>();

    private Map<String, String> values = new HashMap<>();

    public SudokuSolver(int[][] grid) {
        this.grid = grid;

        initSqaures();
        initUnitLists();
        initUnits();
        initPeers();
    }

    public int[][] solve() {
        parseGrid(grid);
        search(values);

        if (solved.size() > 1) throw new IllegalArgumentException();

        Map<String, String> solvedValues = solved.get(0);
        int[][] solvedArray = new int[9][9];
        for (int i = 0; i < squares.length; i++) {
            solvedArray[i / 9][i % 9] = Integer.parseInt(solvedValues.get(squares[i]));
        }

        return solvedArray;
    }

    private void parseGrid(int[][] grid) {
        // convert grid to a map of possible values
        // or throw exception if a contradiction is detected.
        // to start, every square can be any digit, then assign values from the grid
        if (grid.length != 9) throw new IllegalArgumentException();

        List<Integer> gridValues = gridValues(grid);
        if (gridValues.size() != 81) throw new IllegalArgumentException();
        for (int value : gridValues) {
            if (value < 0 || 9 < value) throw new IllegalArgumentException();
        }

        for (String s : squares) values.put(s, digits);
        for (int i = 0; i < 81; i++) {
            if (gridValues.get(i) > 0) {
                Map<String, String> assigned = assign(values, squares[i], String.valueOf(gridValues.get(i)));
                if (assigned == null) throw new IllegalArgumentException();
            }
        }

//        display(values);
    }

    // used constraint propagation technique. refer to https://towardsdatascience.com/peter-norvigs-sudoku-solver-25779bb349ce
    private void search(Map<String, String> values) {
        // using depth-first search and propagation, try all possible values
        if (values == null) return;
        if (isSolved(values)) {
            solved.add(values);
            return;
        }

        // chose the unfilled square s with the fewest possibilities
        int min = 10;
        String ms = "";
        for (String s : squares) {
            int length = values.get(s).length();
            if (length > 1 && length < min) {
                min = length;
                ms = s;
            }
        }

        for (char d : values.get(ms).toCharArray()) {
            Map<String, String> copied = new HashMap<>(values);
            search(assign(copied, ms, "" + d));
        }
    }

    private boolean isSolved(Map<String, String> values) {
        for (String value : values.values()) {
            if (value.length() != 1) return false;
        }

        return true;
    }

    private List<Integer> gridValues(int[][] grid) {
        List<Integer> gridValues = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (grid[i].length != 9) throw new IllegalArgumentException();
            List<Integer> gridRow = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                gridValues.add(grid[i][j]);
                gridRow.add(grid[i][j]);
            }

            System.out.println(gridRow);
        }

        return gridValues;
    }

    private Map<String, String> assign(Map<String, String> values, String s, String d) {
        String otherValues = values.get(s).replace(d, "");
        for (char d2 : otherValues.toCharArray()) {
            if (eliminate(values, s, "" + d2) == null) return null;
        }

        return values;
    }

    private Map<String, String> eliminate(Map<String, String> values, String s, String d) {
        if (!values.get(s).contains(d)) return values;  // already eliminated

        values.put(s, values.get(s).replace(d, ""));

        // (1) if a square s is reduced to one value d2, then eliminate d2 from the peers
        if (values.get(s).isEmpty()) {
            return null;   // contradiction: removed last value
        } else if (values.get(s).length() == 1) {
            String d2 = values.get(s);
            for (String s2 : peers.get(s)) {
                if (eliminate(values, s2, d2) == null) return null;
            }
        }

        // (2) if a unit u is reduced to only one place for a value d, then put it there.
        for (List<String> u : units.get(s)) {
            List<String> dplaces = u.stream().filter(us -> values.get(us).contains(d)).collect(Collectors.toList());
            if (dplaces.size() == 0) {
                return null;    // contradiction: no place for this value
            } else if (dplaces.size() == 1) {
                // d can only be in one place in unit; assign it there
                if (assign(values, dplaces.get(0), d) == null) return null;
            }
        }

        return values;
    }

    private void initSqaures() {
        List<String> sqauresList = cross(rows, cols);
        squares = sqauresList.toArray(new String[0]);
    }

    private void initUnitLists() {
        for (char r : rows.toCharArray()) {
            unitList.add(cross("" + r, cols));
        }

        for (char c : cols.toCharArray()) {
            unitList.add(cross(rows, "" + c));
        }

        for (String rs : new String[] {"ABC", "DEF", "GHI"}) {
            for (String cs : new String[] {"123", "456", "789"}) {
                unitList.add(cross(rs, cs));
            }
        }
    }

    private void initUnits() {
        for (String s : squares) {
            for (List<String> u : unitList) {
                if (u.contains(s)) {
                    if (!units.containsKey(s)) units.put(s, new ArrayList<>());
                    units.get(s).add(u);
                }
            }
        }
    }

    private void initPeers() {
        for (String s : squares) {
            Set<String> unitSet = new HashSet<>();
            for (List<String> unit : units.get(s)) {
                for (String square : unit) {
                    if (!square.equals(s)) unitSet.add(square);
                }
            }

            peers.put(s, unitSet);
        }
    }

    private List<String> cross(String rows, String cols) {
        List<String> crosses = new ArrayList<>();
        for (char r : rows.toCharArray()) {
            for (char c : cols.toCharArray()) {
                crosses.add("" + r + c);
            }
        }

        return crosses;
    }

    private void display(Map<String, String> values) {
        for (int i = 0; i < values.size(); i++) {
            if (i % 3 == 0) System.out.printf(" | ");
            System.out.printf(" %9s ", values.get(squares[i]));
            if (i % 9 == 8) System.out.printf(" |\n");

            if (i == 26 || i == 53) {
                System.out.printf(" ");
                for (int j = 0; j < 12; j++) System.out.printf("---------");
                System.out.printf("\n");
            }
        }
    }

    public static void main(String[] args) {
        int[][] puzzle   = {{0, 0, 6, 1, 0, 0, 0, 0, 8},
                {0, 8, 0, 0, 9, 0, 0, 3, 0},
                {2, 0, 0, 0, 0, 5, 4, 0, 0},
                {4, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 3, 0, 0, 7, 0, 0, 4, 0},
                {0, 0, 7, 9, 0, 0, 0, 0, 3},
                {0, 0, 8, 4, 0, 0, 0, 0, 6},
                {0, 2, 0, 0, 5, 0, 0, 8, 0},
                {1, 0, 0, 0, 0, 2, 5, 0, 0}};

        SudokuSolver ss = new SudokuSolver(puzzle);
        System.out.println(Arrays.asList(ss.squares));
        System.out.println(ss.unitList);
        System.out.println(ss.unitList.size());

        System.out.println(ss.units.get("C2"));
        System.out.println(ss.peers.get("C2"));

        ss.solve();
        for (Map<String, String> values : ss.solved) {
            System.out.println("solved!!");
            ss.display(values);
        }
    }

}
