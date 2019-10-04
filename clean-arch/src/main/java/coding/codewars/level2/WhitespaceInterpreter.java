package coding.codewars.level2;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * refer to http://web.archive.org/web/20120417161536/http://yagni.com/whitespace/whitespace
 */
public class WhitespaceInterpreter {

    private static final String EMPTY_STACK = "2";
    private static final String UNCLEAN_TERMINATION = "3";
    private static final String MULTIPLE_LABEL = "3";
    private static final String END_OF_INPUT = "12";
    private static final String SUB_NOT_RETURN = "12";

    private final List<Token> tokens = new ArrayList<>();
    private BufferedReader br;

    private int programCounter = 0;
    private Stack<Integer> stack = new Stack();
    private Stack<Integer> callStack = new Stack();
    private Map<Integer, Integer> heap = new HashMap<>();

    private Map<String, Argument> codes = new HashMap<>();

    public WhitespaceInterpreter(String code, InputStream input) {
        if (code == null || code.isEmpty()) throw new IllegalArgumentException();
        if (input != null) {
            this.br = new BufferedReader(new InputStreamReader(input));
        }

        initCodes();
        tokenize(code);
    }

    public String execute() {
        try {
            StringBuilder sb = new StringBuilder();

            while (programCounter < tokens.size()) {
                Token token = tokens.get(programCounter);
                programCounter++;

                if (token.code.equals("ss")) {
                    stack.push(number(token.argument));
                } else if (token.code.equals("sts")) {
                    int value = stack.get(stack.size() - 1 - number(token.argument));
                    stack.push(value);
                } else if (token.code.equals("stn")) {
                    int n = number(token.argument);
                    if (n < 0 || n >= stack.size()) {
                        int value = pop();
                        stack.clear();
                        stack.push(value);
                    } else {
                        int value = pop();
                        for (int i = 0; i < n; i++) pop();
                        stack.push(value);
                    }
                } else if (token.code.equals("sns")) {
                    stack.push(stack.peek());
                } else if (token.code.equals("snt")) {
                    int v0 = pop();
                    int v1 = pop();
                    stack.push(v0);
                    stack.push(v1);
                } else if (token.code.equals("snn")) {
                    pop();
                } else if (token.code.equals("tsss")) {
                    int a = pop();
                    int b = pop();
                    stack.push(b + a);
                } else if (token.code.equals("tsst")) {
                    int a = pop();
                    int b = pop();
                    stack.push(b - a);
                } else if (token.code.equals("tssn")) {
                    int a = pop();
                    int b = pop();
                    stack.push(b * a);
                } else if (token.code.equals("tsts")) {
                    int a = pop();
                    int b = pop();
                    if (a == 0) throw new IllegalArgumentException();
                    stack.push(Math.floorDiv(b, a));
                } else if (token.code.equals("tstt")) {
                    int a = pop();
                    int b = pop();
                    if (a == 0) throw new IllegalArgumentException();
                    stack.push(Math.floorMod(b, a));
                } else if (token.code.equals("tts")) {
                    int a = pop();
                    int b = pop();
                    heap.put(b, a);
                } else if (token.code.equals("ttt")) {
                    stack.push(get(pop()));
                } else if (token.code.equals("tnss")) {
                    sb.append((char) (int) pop());
                } else if (token.code.equals("tnst")) {
                    sb.append(pop());
                } else if (token.code.equals("tnts")) {
                    if (br == null) throw new IllegalArgumentException(END_OF_INPUT);
                    int in = br.read();
                    if (in == -1) throw new IllegalArgumentException(END_OF_INPUT);
                    heap.put(pop(), (int) (char) in);
                } else if (token.code.equals("tntt")) {
                    if (br == null) throw new IllegalArgumentException(END_OF_INPUT);
                    String in = br.readLine();
                    if (in == null) throw new IllegalArgumentException(END_OF_INPUT);
                    heap.put(pop(), Integer.parseInt(in));
                } else if (token.code.equals("nss")) {
                    // do nothings
                } else if (token.code.equals("nst")) {
                    callStack.push(programCounter);
                    jump(token.argument);
                } else if (token.code.equals("nsn")) {
                    jump(token.argument);
                } else if (token.code.equals("nts")) {
                    if (pop() == 0) jump(token.argument);
                } else if (token.code.equals("ntt")) {
                    if (pop() < 0) jump(token.argument);
                } else if (token.code.equals("ntn")) {
                    programCounter = callStack.pop();
                } else if (token.code.equals("nnn")) {
                    break;
                }
            }

            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int pop() {
        if (stack.isEmpty()) throw new IllegalArgumentException(EMPTY_STACK);
        return stack.pop();
    }

    private int get(int value) {
        if (!heap.containsKey(value)) throw new IllegalArgumentException();
        return heap.get(value);
    }

    private void jump(String label) {
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.code.equals("nss") && token.argument.equals(label)) {
                programCounter = i;
                return;
            }
        }

        throw new IllegalArgumentException("Unknown label: " + label);
    }

    private int number(String argument) {
        String bits = argument.substring(1).replace("n", "").replace("s", "0").replace("t", "1");
        if (bits.length() == 0) return 0;

        int number = Integer.parseInt(bits, 2);
        return argument.startsWith("s") ? number : -number;
    }

    private void initCodes() {
        codes.put("ss", Argument.NUMBER);
        codes.put("sts", Argument.NUMBER);
        codes.put("stn", Argument.NUMBER);
        codes.put("sns", Argument.NONE);
        codes.put("snt", Argument.NONE);
        codes.put("snn", Argument.NONE);

        codes.put("tsss", Argument.NONE);
        codes.put("tsst", Argument.NONE);
        codes.put("tssn", Argument.NONE);
        codes.put("tsts", Argument.NONE);
        codes.put("tstt", Argument.NONE);

        codes.put("tts", Argument.NONE);
        codes.put("ttt", Argument.NONE);

        codes.put("tnss", Argument.NONE);
        codes.put("tnst", Argument.NONE);
        codes.put("tnts", Argument.NONE);
        codes.put("tntt", Argument.NONE);

        codes.put("nss", Argument.LABEL);
        codes.put("nst", Argument.LABEL);
        codes.put("nsn", Argument.LABEL);
        codes.put("nts", Argument.LABEL);
        codes.put("ntt", Argument.LABEL);
        codes.put("ntn", Argument.NONE);
        codes.put("nnn", Argument.NONE);
    }

    private void tokenize(String code) {
        System.out.println(code);
        while (!code.isEmpty()) {
            String op = findOperation(code);
            code = code.replaceFirst(op, "");

            String argument = findArgument(code, op);
            code = code.replaceFirst(argument, "");

            tokens.add(new Token(op, argument));
        }

        System.out.println(tokens);

        validateMultipleLabels();
        if (countToken("nst") > 0 && countToken("ntn") == 0 && countToken("nnn") == 0)
            throw new IllegalArgumentException(SUB_NOT_RETURN);
        if (countToken("nnn") == 0) throw new IllegalArgumentException(UNCLEAN_TERMINATION);
    }

    private void validateMultipleLabels() {
        List<Token> tokens = findTokens("nss");
        Set<String> labels = new HashSet<>();
        for (Token t : tokens) {
            if (labels.contains(t.argument)) throw new IllegalArgumentException(MULTIPLE_LABEL);
            labels.add(t.argument);
        }
    }

    private String findOperation(String code) {
        for (String op : codes.keySet()) {
            if (code.startsWith(op)) return op;
        }

        throw new IllegalArgumentException("12");
    }

    private String findArgument(String code, String op) {
        Argument arg = codes.get(op);
        if (arg == Argument.NONE) return "";

        String pattern = arg == Argument.LABEL ? "^[ts]*n" : "^[ts]{1}[ts]*n";
        Matcher m = Pattern.compile(pattern).matcher(code);
        if (!m.find()) throw new IllegalArgumentException();

        return m.group();
    }

    private List<Token> findTokens(String op) {
        List<Token> found = new ArrayList<>();
        for (Token token : tokens) {
            if (token.code.equals(op)) found.add(token);
            if (token.code.equals("nnn")) break;
        }

        return found;
    }

    private int countToken(String op) {
        return findTokens(op).size();
    }

    // transforms space characters to ['s','t','n'] chars;
    public static String unbleach(String code) {
        return code != null ? code.replace(' ', 's').replace('\t', 't').replace('\n', 'n') : null;
    }

    // solution
    public static String execute(String code, InputStream input) {
        return new WhitespaceInterpreter(unbleach(code.replaceAll("[^ \t\n]", "")), input).execute();
    }

    public static String execute(String code, InputStream input, OutputStream output) {
        try {
            String result = execute(code, input);
            if (output != null) {
                output.write(result.getBytes());
                output.flush();
            }
            return result;
        } catch (IllegalArgumentException e) {
            if (output != null) {
                try {
                    String message = e.getMessage();
                    if (message == null) message = "";
                    output.write(message.getBytes());
                    output.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    class Token {
        String code, argument;

        Token(String code, String argument) {
            this.code = code;
            this.argument = argument;
        }

        @Override
        public String toString() {
            String arg = codes.get(code) == Argument.NUMBER ? "" + number(argument) : argument;
            return "{" + code + ", " + arg + '}';
        }
    }

    enum Argument {
        LABEL, NUMBER, NONE
    }

    public static void main(String[] args) {
        try {
            WhitespaceInterpreter.execute("   \t\t\n   \t\t\n\t\n \t\t\n \t\n\n\n", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String code = "blahhhhssstarggggghhhssssstntnssnnn";
        System.out.println(code.replaceAll("[^stn]", ""));

//        String code = "ststst";
//        Matcher m = Pattern.compile("^sts").matcher(code);
//        System.out.println(m.find());
//        System.out.println(m.group());
//        System.out.println(code.replaceFirst(m.group(), ""));
    }

}
