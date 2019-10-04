package coding.codewars.level1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("Duplicates")
public class Interpreter {

    private static final String SENTINEL = "$";
    private static final String PATTERN_NUMBER = "[0-9]*(\\.?[0-9]+)";
    private static final String PATTERN_IDENTIFIER = "[A-Za-z_][A-Za-z0-9_]*";
    private static final String FUNCTION = "fn";

    private Map<String, Function> functions = new HashMap<>();
    private Map<String, Double> variables = new HashMap<>();

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
        return node != null ? node.value() : null;
    }

    public Double input(List<String> expression) {
        tokens = new LinkedList<>(expression);
        Node node = parse();
        return node != null ? node.value() : null;
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

        if (tokens.peek().equals(FUNCTION)) {
            function();
            return null;
        } else {
            expression();
            if (!tokens.isEmpty()) throw new IllegalArgumentException();
            return operands.pop();
        }
    }

    private void expression() {
        factor();

        while (!tokens.isEmpty()) {
            if (!tokens.peek().matches("[-+*/%]")) break;

            String token = tokens.pop();
            pushOperator(token);
            factor();
        }

        while (!SENTINEL.equals(operators.peek())) {
            popOperator();
        }
    }

    private void factor() {
        String token = tokens.pop();
        if (token.matches(PATTERN_NUMBER)) {
            Node number = new Number(Double.valueOf(token));
            operands.push(number);
        } else if (functions.containsKey(token)) {
            Node call = functionCall(token);
            operands.push(call);
        } else if (token.matches(PATTERN_IDENTIFIER)) {
            if (!tokens.isEmpty() && tokens.peek().matches("=")) {
                tokens.pop();
                assignment(token);
            } else {
                Identifier identifier = new Identifier(token);
                operands.push(identifier);
            }
        } else if (token.equals("(")) {
            operators.push(SENTINEL);
            expression();
            String popped = tokens.pop();
            assert (popped.equals(")"));
            operators.pop();
        } else {
            throw new IllegalStateException("Invalid operand");
        }
    }

    private void assignment(String variableName) {
        operators.push(SENTINEL);
        expression();
        operators.pop();

        Node value = operands.pop();
        variables.put(variableName, value.value());
        operands.push(value);
    }

    private void function() {
        if (!tokens.pop().equals("fn")) throw new IllegalArgumentException();

        String fnName = tokens.pop();
        if (variables.containsKey(fnName)) throw new IllegalArgumentException();

        List<String> argumentNames = new ArrayList<>();
        while (!tokens.isEmpty()) {
            if (!tokens.peek().matches(PATTERN_IDENTIFIER)) break;
            argumentNames.add(tokens.pop());
        }

        if (tokens.isEmpty() || !tokens.pop().equals("=>")) throw new IllegalArgumentException();

        List<String> expression = new ArrayList<>();
        while (!tokens.isEmpty()) {
            expression.add(tokens.pop());
        }
        if (expression.isEmpty()) throw new IllegalArgumentException();
        functions.put(fnName, new Function(argumentNames, expression));
    }

    private Node functionCall(String fnName) {
        Function function = functions.get(fnName);
        List<String> arguments = new ArrayList<>();
        for (int i = 0; i < function.argumentNames.size(); i++) {
            operators.push(SENTINEL);
            expression();
            Node argument = operands.pop();
            arguments.add(String.valueOf(argument.value()));
            operators.pop();
        }

        return new FunctionCall(function, arguments);
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

    static class Function {

        private List<String> argumentNames;
        private List<String> expression;

        public Function(List<String> argumentNames, List<String> expression) {
            this.argumentNames = new ArrayList<>(new HashSet<>(argumentNames));
            this.expression = expression;

            if (this.argumentNames.size() != argumentNames.size()) throw new IllegalArgumentException();
            for (String name : argumentNames) {
                if (!expression.contains(name)) throw new IllegalArgumentException();
            }
        }

        @Override
        public String toString() {
            return "Function{" + "argumentNames=" + argumentNames + ", expression=" + expression + '}';
        }
    }

    static class FunctionCall implements Node {

        private Function function;
        private List<String> arguments = new ArrayList<>();

        public FunctionCall(Function function, List<String> arguments) {
            this.function = function;
            this.arguments = arguments;

            if (arguments.size() != function.argumentNames.size()) throw new IllegalArgumentException();
        }

        @Override
        public double value() {
            List<String> expression = new ArrayList<>(function.expression);
            for (int i = 0; i < arguments.size(); i++) {
                int index = expression.indexOf(function.argumentNames.get(i));
                expression.set(index, arguments.get(i));
            }

            Interpreter interpreter = new Interpreter();
            return interpreter.input(expression);
        }
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

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();

        interpreter.input("fn add x y => x + y * 3");
        System.out.println(interpreter.functions);
        System.out.println(interpreter.input("add 4 * 3 5 - 2"));

        interpreter.input("fn inc x => x + 1");
        System.out.println(interpreter.input("add inc 4 inc 4 * 3 - 2"));

//        List<String> argumentNames = Arrays.asList("x", "y");
//        List<String> expression = Arrays.asList("x", "+", "y", "*", "3");
//        Function function = new Function(argumentNames, expression);
//        FunctionCall call = new FunctionCall(function, Arrays.asList("1.0", "3.22"));
//        System.out.println(call.value());


        System.out.println(interpreter.input("x = y = 7"));
//        System.out.println(interpreter.input("1 + (1 * 2 + (3.22 - .101))"));
//        System.out.println(interpreter.input("_x = 1212"));
//        System.out.println(interpreter.input("y = 100 + _x"));
//        System.out.println(interpreter.input("y = 100 + (y - 110) * 2"));

    }
}
