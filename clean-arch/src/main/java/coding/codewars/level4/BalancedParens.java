package coding.codewars.level4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * All Balanced Parentheses
 * https://www.codewars.com/kata/5426d7a2c2c7784365000783
 */
public class BalancedParens {

    public List<String> perform(int n) {
        if (n == 0) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }

        Node root = new Node(n - 1, n, true);
        return new ArrayList<>(root.stringify());
    }

    class Node {
        int open, close;
        boolean opened;
        List<Node> children = new ArrayList<>();

        Node(int open, int close, boolean opened) {
            this.open = open;
            this.close = close;
            this.opened = opened;

            if (open > 0) children.add(new Node(open - 1, close, true));
            if (open < close) children.add(new Node(open, close - 1, false));
        }

        public Set<String> stringify() {
            String mark = opened ? "(" : ")";
            Set<String> result = new HashSet<>();

            if (children.isEmpty()) {
                result.add(mark);
                return result;
            }

            for(Node node : children) {
                for (String s : node.stringify()) {
                    result.add(mark + s);
                }
            }

            return result;
        }

    }

    public static List<String> balancedParens (int n) {
        return new BalancedParens().perform(n);
    }
}