package coding.codewars.level5;

import java.util.*;

@SuppressWarnings("Duplicates")
public class Smallfuck {

    private final char[] code;
    private final char[] tape;
    private final Map<Integer, Integer> forwards = new HashMap<>();
    private final Map<Integer, Integer> backwards = new HashMap<>();

    private int pointer = 0;

    public Smallfuck(String code, String tape) {
        System.out.printf("code: %s, tape: %s\n", code, tape);
        this.code = code.toCharArray();
        this.tape = tape.toCharArray();

        inspectBrackets();
    }

    private void inspectBrackets() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < code.length; i++) {
            if (code[i] == '[') stack.push(i);
            if (code[i] == ']') {
                int open = stack.pop();
                forwards.put(open, i);
                backwards.put(i, open);
            }
        }
    }

    public String interpret() {
        int index = 0;
        while (index < code.length && 0 <= pointer && pointer < tape.length) {
            char c = code[index];
//            System.out.printf("c: %c\n", c);
            if (c == '[') {
                if (tape[pointer] == '0') {
                    index = forwards.get(index) + 1;
//                    System.out.printf("index: %d\n", index);
                } else {
                    index++;
                }
            } else if (c == ']') {
                if (tape[pointer] != '0') {
                    index = backwards.get(index) + 1;
//                    System.out.printf("index: %d\n", index);
                } else {
                    index++;
                }
            } else {
                process(index);
                index++;
            }
        }

        return new String(tape);
    }

    private void process(int index) {
        char c = code[index];
        if (c == '>') {
            pointer++;
        } else if (c == '<') {
            pointer--;
        } else if (c == '*') {
            if (tape[pointer] == '1') tape[pointer] = '0';
            else if (tape[pointer] == '0') tape[pointer] = '1';
        }
    }

    public static String interpreter(String code, String tape) {
        Smallfuck s = new Smallfuck(code, tape);
        return s.interpret();
    }
}