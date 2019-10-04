package coding.codewars.level4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObservedPin {

    private String[] adjacents = new String[] { "08", "124", "1235", "236", "1457", "24568", "3569", "478", "05789", "689" };

    public List<String> find(String observed) {
        List<Integer> numbers = toIntList(observed);
        List<Node> roots = toIntList(adjacents[numbers.get(0)]).stream()
                .map(i -> new Node(i))
                .collect(Collectors.toList());

        if (numbers.size() > 1) addNodes(roots, numbers, 1);

        List<String> result = new ArrayList<>();
        for (Node root : roots) result.addAll(root.stringify());

        return result;
    }

    private void addNodes(List<Node> parents, List<Integer> numbers, int index) {
        if (index >= numbers.size()) return;

        List<Node> nodes = new ArrayList<>();

        for (Node p : parents) {
            List<Integer> variations = toIntList(adjacents[numbers.get(index)]);
            for (int n : variations) {
                Node node = new Node(n);
                p.next.add(node);
                nodes.add(node);
            }
        }

        addNodes(nodes, numbers, ++index);
    }

    private List<Integer> toIntList(String s) {
        List<Integer> numbers = new ArrayList<>();
        for (char c : s.toCharArray()) {
            numbers.add(Integer.parseInt("" + c));
        }

        return numbers;
    }

    public static List<String> getPINs(String observed) {
        System.out.println("observed: " + observed);
        return new ObservedPin().find(observed);
    }

    class Node {

        int value;

        Node(int value) {
            this.value = value;
        }

        List<Node> next = new ArrayList<>();

        List<String> stringify() {
            List<String> strings = new ArrayList<>();
            if (next.isEmpty()) {
                strings.add(value + "");
                return strings;
            }

            for (Node n : next) {
                strings.addAll(n.stringify());
            }

            return strings.stream().map(s -> value + "" + s).collect(Collectors.toList());
        }

    }
}
