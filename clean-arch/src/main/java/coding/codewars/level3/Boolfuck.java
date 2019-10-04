package coding.codewars.level3;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class Boolfuck {

    private char[] code;
    private Queue<Integer> inputBits = new LinkedList<>();
    private List<Integer> output = new ArrayList<>();

    private final Map<Integer, Integer> forwards = new HashMap<>();
    private final Map<Integer, Integer> backwards = new HashMap<>();

    private Node pointer = new Node();
    private int pointerCount = 0;

    public Boolfuck(String code, String input) {
        System.out.printf("code: %s, input: %s\n", code, input);

        initCode(code);
        initInput(input);
        inspectBrackets();

    }

    public String interpret() {
        int index = 0;
        while (index < code.length) {
            char c = code[index];
            if (c == '[') {
                index = pointer.value == 0 ? forwards.get(index) + 1 : index + 1;
            } else if (c == ']') {
                index = pointer.value != 0 ? backwards.get(index) + 1 : index + 1;
            } else {
                process(index);
                index++;
            }
//            System.out.printf("c: %c, index: %d, pointerCount: %d, pointer: %d\n", c, index, pointerCount, pointer.value);
        }

        return format();
    }

    private void process(int index) {
        char c = code[index];
        if (c == '>') {
            pointer = pointer.next();
            pointerCount++;
        } else if (c == '<') {
            pointer = pointer.prev();
            pointerCount--;
        } else if (c == '+') {
            pointer.flip();
        } else if (c == ';') {
            output.add(pointer.value);
        } else if (c == ',') {
            pointer.value = inputBits.isEmpty() ? 0 : inputBits.poll();
        }
    }

    private String format() {
        StringBuilder formatted = new StringBuilder();
        List<Integer> buffer = new ArrayList<>();

        for (int bit : output) {
            buffer.add(bit);
            if (buffer.size() == 8) {
                formatted.append(bitsToChar(buffer));
                buffer.clear();
            }
        }

        if (!buffer.isEmpty()) {
            for (int i = buffer.size(); i < 8; i++) buffer.add(0); // padding 0 to the last letter
            formatted.append(bitsToChar(buffer));
            buffer.clear();
        }

        return formatted.toString();
    }

    private char bitsToChar(List<Integer> buffer) {
        StringBuilder sb = new StringBuilder();
        for (int i = buffer.size() - 1; i >= 0; i--) sb.append(buffer.get(i));
        return (char) Integer.parseInt(sb.toString(), 2);
    }

    private void initInput(String input) {
        for (char c : input.toCharArray()) {
            char[] bits = Integer.toBinaryString(c).toCharArray();
            for (int i = bits.length - 1; i >= 0; i--) {
                inputBits.add(Integer.parseInt(String.valueOf(bits[i])));
            }

            for (int i = bits.length; i < 8; i++) {
                inputBits.add(0);   // add zero padding
            }
        }
    }

    private void initCode(String code) {
        List<Character> chars = code.chars()
                .mapToObj(c -> Character.valueOf((char) c))
                .filter(c -> "+,;<>[]".contains(c.toString()))
                .collect(Collectors.toList());
        this.code = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) this.code[i] = chars.get(i);
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

    public static String interpret(String code, String input) {
        return new Boolfuck(code, input).interpret();
    }

    public class Node {

        private int value = 0;
        private Node prev, next;

        void flip() {
            value = value == 0 ? 1 : 0;
        }

        Node next() {
            if (next == null) {
                next = new Node();
                next.prev = this;
            }
            return next;
        }

        Node prev() {
            if (prev == null) {
                prev = new Node();
                prev.next = this;
            }
            return prev;
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString('H'));
        System.out.println(Integer.toBinaryString('e'));
        System.out.println((char) Integer.parseInt("1100101", 2));
        Boolfuck bf = new Boolfuck("", "Codewars");
        System.out.println(bf.inputBits);
    }
}