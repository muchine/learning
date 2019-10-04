package coding.codewars.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MathEvaluator {

    private Stack<Op> operators = new Stack<>();
    private Stack<Node> operands = new Stack<>();
    private String[] tokens;

    private int next = 0;

    public double calculate(String expression) {
        char[] chars = expression.replace(" ", "").toCharArray();
        List<String> tokens = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            String c = String.valueOf(chars[i]);
            if ("0123456789.".contains(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    tokens.add(sb.toString());
                    sb = new StringBuilder();
                }
                tokens.add(c);
            }
        }

        if (sb.length() > 0) tokens.add(sb.toString());

        return calculate(tokens.toArray(new String[0]));
    }

    /**
     * used shunting yard algorithm: https://www.engr.mun.ca/~theo/Misc/exp_parsing.htm
     */
    public double calculate(String[] tokens) {
        this.tokens = tokens;
        Node node = parse();
        return node.calc();
    }

    private Node parse() {
        operators.clear();
        operands.clear();

        operators.add(Op.SENTINEL);

        processExpression();
        assert(next == tokens.length);

        return operands.pop();
    }

    private void processExpression() {
        processOperand();
        while (next < tokens.length && isBinaryOperator(next)) {
            pushOperator(createBinaryOp(next));
            next++;
            processOperand();
        }

        while (!Op.SENTINEL.equals(operators.peek())) {
            popOperator();
        }
    }

    private void processOperand() {
        if (isValue(next)) {
            Node constant = new Constant(Double.valueOf(tokens[next]));
            operands.push(constant);
            next++;
        } else if (tokens[next].equals("(")) {
            next++;
            operators.push(Op.SENTINEL);
            processExpression();

            assert (tokens[next].equals(")"));
            next++;
            operators.pop();
        } else if (isUnaryOperator(next)) {
            pushOperator(Op.NEGATE);
            next++;
            processOperand();
        } else {
            throw new IllegalStateException("Invalid operand");
        }
    }

    private boolean isValue(int next) {
        return !isOperator(next) && !"(".equals(tokens[next]);
    }

    private boolean isOperator(int next) {
        return isUnaryOperator(next) || isBinaryOperator(next);
    }

    private boolean isBinaryOperator(int next) {
        return "+-*/".contains(tokens[next]) && !isUnaryOperator(next);
    }

    private boolean isUnaryOperator(int next) {
        return "-".contains(tokens[next]) && (next == 0 || !isValue(next - 1));
    }

    private void popOperator() {
        if (Op.isBinary(operators.peek())) {
            Node t1 = operands.pop();
            Node t0 = operands.pop();
            Node op = createBinaryOperatorNode(operators.pop(), t0, t1);

            operands.push(op);
        } else {
            Node operand = operands.pop();
            Node op = createUnaryOperatorNode(operators.pop(), operand);

            operands.push(op);
        }
    }

    private void pushOperator(Op op) {
        while (precedence(operators.peek()) >= precedence(op)) {
            popOperator();
        }

        operators.push(op);
    }

    private Op createBinaryOp(int next) {
        if ("+".equals(tokens[next])) return Op.ADD;
        if ("-".equals(tokens[next])) return Op.SUBTRACT;
        if ("*".equals(tokens[next])) return Op.MULTIPLY;
        if ("/".equals(tokens[next])) return Op.DIVIDE;

        throw new IllegalArgumentException("Unsupported operator token");
    }

    private int precedence(Op op) {
        if (op.equals(Op.NEGATE)) return 3;
        if (op.equals(Op.MULTIPLY) || op.equals(Op.DIVIDE)) return 2;
        if (op.equals(Op.ADD) || op.equals(Op.SUBTRACT)) return 1;
        if (op.equals(Op.SENTINEL)) return 0;

        throw new IllegalArgumentException("Unsupported operator");
    }

    private Node createBinaryOperatorNode(Op operator, Node t0, Node t1) {
        if (operator.equals(Op.ADD))
            return new Add(t0, t1);
        else if (operator.equals(Op.SUBTRACT))
            return new Subtract(t0, t1);
        else if (operator.equals(Op.MULTIPLY))
            return new Multiply(t0, t1);
        else if (operator.equals(Op.DIVIDE))
            return new Divide(t0, t1);

        throw new IllegalArgumentException("Unsupported binary operator token");
    }

    private Node createUnaryOperatorNode(Op operator, Node operand) {
        if (operator.equals(Op.NEGATE)) {
            return new Negate(operand);
        }

        throw new IllegalArgumentException("Unsupported unary operator token");
    }

    interface Node {
        double calc();
    }

    class Constant implements Node {
        double value;

        Constant(double value) {
            this.value = value;
        }

        @Override
        public double calc() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    abstract class BinaryOperator implements Node {
        final Node left, right;

        BinaryOperator(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public double calc() {
            return calc(left, right);
        }

        protected abstract double calc(Node left, Node right);

        protected abstract String sign();

        @Override
        public String toString() {
            return sign() + "(" + left + ", " + right + ")";
        }
    }

    class Add extends BinaryOperator {
        Add(Node left, Node right) {
            super(left, right);
        }

        @Override
        protected double calc(Node left, Node right) {
            return left.calc() + right.calc();
        }

        @Override
        protected String sign() {
            return "+";
        }
    }

    class Subtract extends BinaryOperator {
        Subtract(Node left, Node right) {
            super(left, right);
        }

        @Override
        protected double calc(Node left, Node right) {
            return left.calc() - right.calc();
        }

        @Override
        protected String sign() {
            return "-";
        }
    }

    class Multiply extends BinaryOperator {
        Multiply(Node left, Node right) {
            super(left, right);
        }

        @Override
        protected double calc(Node left, Node right) {
            return left.calc() * right.calc();
        }

        @Override
        protected String sign() {
            return "*";
        }
    }

    class Divide extends BinaryOperator {
        Divide(Node left, Node right) {
            super(left, right);
        }

        @Override
        protected double calc(Node left, Node right) {
            return left.calc() / right.calc();
        }

        @Override
        protected String sign() {
            return "/";
        }
    }

    class Negate implements Node {
        final Node operand;

        Negate(Node operand) {
            this.operand = operand;
        }

        @Override
        public double calc() {
            return -operand.calc();
        }

        @Override
        public String toString() {
            return "-(" + operand + ")";
        }
    }

    enum Op {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, NEGATE, SENTINEL;

        static boolean isBinary(Op op) {
            return ADD.equals(op) || SUBTRACT.equals(op) || MULTIPLY.equals(op) || DIVIDE.equals(op);
        }
    }

    public static void main(String[] args) {
        System.out.println(new MathEvaluator().calculate("(4 + 2) * 3"));

//        Pattern divMul = Pattern.compile("(-?[0-9.]+)\\s*(/|\\*)\\s*(-?[0-9.]+)");
//        Matcher m = divMul.matcher("2 / 2 + 3 * 4.33 - -6");
//        System.out.println(m.find());
//        System.out.println(m.group(1));
//        System.out.println(m.group(2));
//        System.out.println(m.group(3));
//
//        System.out.println(m.find());
//        System.out.println(m.group(1));
//        System.out.println(m.group(2));
//        System.out.println(m.group(3));
    }

}
