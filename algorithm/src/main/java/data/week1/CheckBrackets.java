package data.week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class CheckBrackets {

    static class Bracket {
        Bracket(char type, int position) {
            this.type = type;
            this.position = position;
        }

        boolean match(char c) {
            if (this.type == '[' && c == ']')
                return true;
            if (this.type == '{' && c == '}')
                return true;
            if (this.type == '(' && c == ')')
                return true;
            return false;
        }

        char type;
        int position;
    }

    public String check(String text) {
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position + 1));
            }

            if (next == ')' || next == ']' || next == '}') {
                if (opening_brackets_stack.isEmpty()) {
                    return String.valueOf(position + 1);
                }

                Bracket bracket = opening_brackets_stack.pop();
                if (!bracket.match(next)) {
                    return String.valueOf(position + 1);
                }
            }
        }

        if (opening_brackets_stack.isEmpty()) {
            return "Success";
        } else {
            Bracket b = opening_brackets_stack.pop();
            return String.valueOf(b.position);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        CheckBrackets cb = new CheckBrackets();
        System.out.println(cb.check(text));
    }
}
