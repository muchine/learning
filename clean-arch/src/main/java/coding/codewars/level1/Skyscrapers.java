package coding.codewars.level1;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class Skyscrapers {

    private static final String digits = "1234567";
    private static final String rows = "ABCDEFG";
    private static final String cols = digits;

    private int[] clues;
    private Map<String, String> values = new HashMap<>();

    private String[] squares;
    private List<List<String>> unitList = new ArrayList<>();
    private Map<String, List<List<String>>> units = new HashMap<>();
    private Map<String, Set<String>> peers = new HashMap<>();

    private Map<Integer, List<String>> clueUnits = new HashMap<>();     // a list of squares that clues relates
    private Map<String, List<Integer>> squareClues = new HashMap<>();

    public Skyscrapers(int[] clues) {
        this.clues = clues;

        initSqaures();
        initUnitLists();
        initUnits();
        initPeers();
        initClueUnits();
        initSquareClues();
    }

    private void parseGrid() {
        for (String s : squares) values.put(s, digits);

        // assign initializing value calculated by the easy clues.
        for (int i = 0; i < 28; i++) {
            int clue = clues[i];
            if (clue == 1) {
                assign(values, clueUnits.get(i).get(0), "7");
            } else if (clue == 7) {
                for (int j = 0; j < 7; j++) {
                    String s = clueUnits.get(i).get(j);
                    assign(values, s, "" + (j + 1));
                }
            } else {
                for (int j = 0; j < clue - 1; j++) {
                    for (int k = 7 - clue + 2 + j; k <= 7; k++)
                        eliminate(values, clueUnits.get(i).get(j), "" + k);
                }
            }
        }

        System.out.println("parse grid done!");
//        display(values);
    }

    private Map<String, String> solve() {
        parseGrid();
        return search(values);
    }

    // used constrant propagation technique. refer to https://towardsdatascience.com/peter-norvigs-sudoku-solver-25779bb349ce
    private Map<String, String> search(Map<String, String> values) {
        // using depth-first search and propagation, try all possible values
        if (values == null) return null;
        if (isSolved(values)) return values;

        // chose the unfilled square s with the fewest possiblities
        int min = 8;
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
            Map<String, String> searched = search(assign(copied, ms, "" + d));
            if (searched != null) return searched;
        }

        return null;
    }

    private boolean isSolved(Map<String, String> values) {
        for (String value : values.values()) {
            if (value.length() != 1) return false;
        }

        for (int i = 0; i < clues.length; i++) {
            if (!validateClue(values, i)) return false;
        }

        return true;
    }

    private Map<String, String> assign(Map<String, String> values, String s, String d) {
        // valdiate rules with clues
        if (!validateClues(values, s)) {
//            System.out.printf("validation failed. s: %s, values: %s\n", s, values.get(s));
//            display(values);
            return null;
        }

        String otherValues = values.get(s).replace(d, "");
        for (char d2 : otherValues.toCharArray()) {
            if (eliminate(values, s, "" + d2) == null) return null;
        }

        return values;
    }

    private boolean validateClues(Map<String, String> values, String s) {
        for (int clueIndex : squareClues.get(s)) {
            if (!validateClue(values, clueIndex)) return false;
        }

        return true;
    }

    private boolean validateClue(Map<String, String> values, int clueIndex) {
        int clue = clues[clueIndex];
        if (clue == 0) return true;

        List<String> clueUnit = clueUnits.get(clueIndex);

        int count = 0;
        int maxHeight = 0;
        for (String us : clueUnit) {
            if (values.get(us).length() > 1) return true;   // if a row/column is not all set, stop validating
            int height = Integer.parseInt(values.get(us));
            if (height > maxHeight) {
                maxHeight = height;
                count++;
            }
        }

        return count == clue;
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
            List<String> unit = cross("" + r, cols);
            unitList.add(unit);
        }

        for (char c : cols.toCharArray()) {
            List<String> unit = cross(rows, "" + c);
            unitList.add(unit);
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
                for (String sqaure : unit) {
                    if (!sqaure.equals(s)) unitSet.add(sqaure);
                }
            }

            peers.put(s, unitSet);
        }
    }

    private void initClueUnits() {
        for (int i = 0; i < 7; i++) {
            clueUnits.put(i, cross(rows, "" + (i + 1)));
        }

        for (int i = 7; i < 14; i++) {
            clueUnits.put(i, cross(rows.toCharArray()[i - 7] + "", "7654321"));
        }

        for (int i = 14; i < 21; i++) {
            clueUnits.put(i, cross("GFEDCBA", cols.toCharArray()[20 - i] + ""));
        }

        for (int i = 21; i < 28; i++) {
            clueUnits.put(i, cross(rows.toCharArray()[27 - i] + "", cols));
        }
    }

    // indicate which square is affected by which clues
    private void initSquareClues() {
        for (String s : squares) {
            for (int i = 0; i < clueUnits.size(); i++) {
                if (clueUnits.get(i).contains(s)) {
                    List<Integer> clues = squareClues.getOrDefault(s, new ArrayList<>());
                    clues.add(i);
                    squareClues.put(s, clues);
                }
            }
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
        if (values == null) throw new IllegalArgumentException("values is null");

        System.out.printf("| %7s | ", "");
        for (int i = 0; i < 7; i++) {
            System.out.printf("%7s | ", clues[i]);
        }
        System.out.printf("%7s | \n", "");

        for (int i = 0; i < squares.length; i++) {
            if (i % 7 == 0) {
                System.out.printf("| %7s | ", clues[27 - (i / 7)]);
            }

            System.out.printf("%7s | ", values.get(squares[i]));

            if (i % 7 == 6)
                System.out.printf("%7s | \n", clues[((i - 6) / 7) + 7]);
        }

        System.out.printf("| %7s | ", "");
        for (int i = 20; i > 13; i--) {
            System.out.printf("%7s | ", clues[i]);
        }
        System.out.printf("%7s | \n", "");
        System.out.println();
    }

    public static int[][] solvePuzzle(int[] clues) {
        Skyscrapers sc = new Skyscrapers(clues);
        Map<String, String> values = sc.solve();

        int[][] solved = new int[7][7];
        for (int i = 0; i < sc.squares.length; i++) {
            solved[i / 7][i % 7] = Integer.parseInt(values.get(sc.squares[i]));
        }

        return solved;
    }

    public static void main(String[] args) {
        int[] clues = new int[]{7, 0, 0, 0, 2, 2, 3, 0, 0, 3, 0, 0, 0, 0, 3, 0, 3, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 4};

        Skyscrapers sc = new Skyscrapers(clues);
        System.out.println(Arrays.asList(sc.squares));
        System.out.println(sc.unitList);
        System.out.println(sc.units);
        System.out.println(sc.units.get("B2"));
        System.out.println(sc.peers.get("B2"));
        System.out.println(sc.clueUnits);

        System.out.println(sc.units.size());
        System.out.println(sc.squareClues);

        Map<String, String> solved = sc.solve();
        sc.display(solved);

        System.out.println(sc.validateClues(solved, "A1"));
    }
}
