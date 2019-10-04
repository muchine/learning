package coding.codewars.level4;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class SkyScrapers {

    private static final String digits = "1234";
    private static final String rows = "ABCD";
    private static final String cols = digits;

    private int[] clues;
    private Map<String, String> values = new HashMap<>();

    private String[] squares;
    private List<List<String>> unitList = new ArrayList<>();
    private Map<String, List<List<String>>> units = new HashMap<>();
    private Map<String, Set<String>> peers = new HashMap<>();

    private Map<Integer, List<String>> clueUnits = new HashMap<>();     // a list of squares that clues relates
    private Map<String, List<Integer>> squareClues = new HashMap<>();

    public SkyScrapers(int[] clues) {
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
        for (int i = 0; i < 16; i++) {
            int clue = clues[i];
            if (clue == 1) {
                assign(values, clueUnits.get(i).get(0), "4");
            } else if (clue == 4) {
                for (int j = 0; j < 4; j++) {
                    String s = clueUnits.get(i).get(j);
                    assign(values, s, "" + (j + 1));
                }
            } else {
                for (int j = 0; j < clue - 1; j++) {
                    for (int k = 4 - clue + 2 + j; k <= 4; k++)
                        eliminate(values, clueUnits.get(i).get(j), "" + k);
                }
            }
        }

        System.out.println("parse grid done!");
    }

    private Map<String, String> solve() {
        parseGrid();
        return search(values);
    }

    // used constraint propagation technique. refer to https://towardsdatascience.com/peter-norvigs-sudoku-solver-25779bb349ce
    private Map<String, String> search(Map<String, String> values) {
        // using depth-first search and propagation, try all possible values
        if (values == null) return null;
        if (isSolved(values)) return values;

        // chose the unfilled square s with the fewest possiblities
        int min = 5;
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
        if (!validateClues(values, s)) return null;

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
        for (int i = 0; i < 4; i++) {
            clueUnits.put(i, cross(rows, "" + (i + 1)));
        }

        for (int i = 4; i < 8; i++) {
            clueUnits.put(i, cross(rows.toCharArray()[i - 4] + "", "4321"));
        }

        for (int i = 8; i < 12; i++) {
            clueUnits.put(i, cross("DCBA", cols.toCharArray()[11 - i] + ""));
        }

        for (int i = 12; i < 16; i++) {
            clueUnits.put(i, cross(rows.toCharArray()[15 - i] + "", cols));
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

        System.out.printf("| %4s | ", "");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%4s | ", clues[i]);
        }
        System.out.printf("%4s | \n", "");

        for (int i = 0; i < squares.length; i++) {
            if (i % 4 == 0) {
                System.out.printf("| %4s | ", clues[15 - (i / 4)]);
            }

            System.out.printf("%4s | ", values.get(squares[i]));

            if (i % 4 == 3)
                System.out.printf("%4s | \n", clues[((i - 3) / 4) + 4]);
        }

        System.out.printf("| %4s | ", "");
        for (int i = 11; i > 7; i--) {
            System.out.printf("%4s | ", clues[i]);
        }
        System.out.printf("%4s | \n", "");
        System.out.println();
    }

    public static int[][] solvePuzzle(int[] clues) {
        SkyScrapers sc = new SkyScrapers(clues);
        Map<String, String> values = sc.solve();

        int[][] solved = new int[4][4];
        for (int i = 0; i < sc.squares.length; i++) {
            solved[i / 4][i % 4] = Integer.parseInt(values.get(sc.squares[i]));
        }

        return solved;
    }

    public static void main(String[] args) {
        int[] clues = new int []{ 2, 2, 1, 3,
                2, 2, 3, 1,
                1, 2, 2, 3,
                3, 2, 1, 3 };

        SkyScrapers sc = new SkyScrapers(clues);
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

