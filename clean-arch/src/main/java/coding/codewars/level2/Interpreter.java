package coding.codewars.level2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("Duplicates")
public class Interpreter {

    private static final String SENTINEL = "$";
    private static Map<String, Double> variables = new HashMap<>();

    private Stack<String> operators = new Stack<>();
    private Stack<Node> operands = new Stack<>();
    private Deque<String> tokens;

    /**
     * used shunting yard algorithm: https://www.engr.mun.ca/~theo/Misc/exp_parsing.htm
     */
    public Double input(String input) {
        System.out.println("input: " + input);
        tokens = tokenize(input);
        if (tokens.isEmpty()) return null;

        Node node = parse();
        return node.value();
    }

    private static Deque<String> tokenize(String input) {
        Deque<String> tokens = new LinkedList<>();
        Pattern pattern = Pattern.compile("=>|[-+*/%=\\(\\)]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)");
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }

    private Node parse() {
        operators.clear();
        operands.clear();

        operators.add(SENTINEL);

        processExpression();
        assert(tokens.isEmpty());

        return operands.pop();
    }

    private void processExpression() {
        processOperand();

        while (!tokens.isEmpty()) {
            if (tokens.peek().matches("\\)")) break;
            if (!tokens.peek().matches("[-+*/%=]")) {
                throw new IllegalArgumentException("Operator not found.");
            }

            String token = tokens.pop();

            pushOperator(token);
            processOperand();
        }

        while (!SENTINEL.equals(operators.peek())) {
            popOperator();
        }
    }

    private void processOperand() {
        String token = tokens.pop();
        if (token.matches("[0-9]*(\\.?[0-9]+)")) {
            Node number = new Number(Double.valueOf(token));
            operands.push(number);
        } else if (token.matches("[A-Za-z_][A-Za-z0-9_]*")) {
            Identifier identifier = new Identifier(token);
            operands.push(identifier);
        } else if (token.equals("(")) {
            operators.push(SENTINEL);
            processExpression();
            String popped = tokens.pop();
            assert (popped.equals(")"));
            operators.pop();
        } else {
            throw new IllegalStateException("Invalid operand");
        }
    }

    private void pushOperator(String operator) {
        while (precedence(operators.peek()) >= precedence(operator)) {
            popOperator();
        }

        operators.push(operator);
    }

    private void popOperator() {
        Node t1 = operands.pop();
        Node t0 = operands.pop();
        Node op = createOperatorNode(operators.pop(), t0, t1);

        operands.push(op);
    }

    private Node createOperatorNode(String operator, Node t0, Node t1) {
        switch (operator) {
            case "+":
                return new Add(t0, t1);
            case "-":
                return new Subtract(t0, t1);
            case "*":
                return new Multiply(t0, t1);
            case "/":
                return new Divide(t0, t1);
            case "%":
                return new Modular(t0, t1);
            case "=":
                return new Assign(t0, t1);
        }

        throw new IllegalArgumentException("Unsupported binary operator token");
    }

    private int precedence(String operator) {
        if ("*/%".contains(operator)) return 3;
        else if ("+-".contains(operator)) return 2;
        else if ("=".contains(operator)) return 1;
        else return 0;
    }

    interface Node {
        double value();
    }

    class Identifier implements Node {

        private final String id;

        Identifier(String id) {
            this.id = id;
        }

        @Override
        public double value() {
            if (!variables.containsKey(id))
                throw new IllegalArgumentException("ERROR: Invalid identifier. No variable with name " + id + " was found.");
            return variables.get(id);
        }
    }

    class Number implements Node {
        double value;

        Number(double value) {
            this.value = value;
        }

        @Override
        public double value() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    abstract class BinaryOperator implements Node {
        final Node left, right;
        final String sign;

        BinaryOperator(Node left, Node right, String sign) {
            this.left = left;
            this.right = right;
            this.sign = sign;
        }

        @Override
        public double value() {
            return value(left, right);
        }

        protected abstract double value(Node left, Node right);

        @Override
        public String toString() {
            return sign + "(" + left + ", " + right + ")";
        }
    }

    class Add extends BinaryOperator {
        Add(Node left, Node right) {
            super(left, right, "+");
        }

        @Override
        protected double value(Node left, Node right) {
            return left.value() + right.value();
        }
    }

    class Subtract extends BinaryOperator {
        Subtract(Node left, Node right) {
            super(left, right, "-");
        }

        @Override
        protected double value(Node left, Node right) {
            return left.value() - right.value();
        }
    }

    class Multiply extends BinaryOperator {
        Multiply(Node left, Node right) {
            super(left, right, "*");
        }

        @Override
        protected double value(Node left, Node right) {
            return left.value() * right.value();
        }
    }

    class Divide extends BinaryOperator {
        Divide(Node left, Node right) {
            super(left, right, "/");
        }

        @Override
        protected double value(Node left, Node right) {
            return left.value() / right.value();
        }
    }

    class Modular extends BinaryOperator {
        Modular(Node left, Node right) {
            super(left, right, "%");
        }

        @Override
        protected double value(Node left, Node right) {
            return left.value() % right.value();
        }
    }

    class Assign extends BinaryOperator {
        Assign(Node left, Node right) {
            super(left, right, "=");
        }

        @Override
        protected double value(Node left, Node right) {
            double value = right.value();
            variables.put(((Identifier) left).id, value);
            return value;
        }
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        System.out.println(interpreter.input("(5 + 2) * 3"));
//        System.out.println(interpreter.input("1 + (1 * 2 + (3.22 - .101))"));
//        System.out.println(interpreter.input("_x = 1212"));
//        System.out.println(interpreter.input("y = 100 + _x"));
//        System.out.println(interpreter.input("y = 100 + (y - 110) * 2"));

    }
}