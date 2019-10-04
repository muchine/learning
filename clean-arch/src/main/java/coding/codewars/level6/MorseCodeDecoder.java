package coding.codewars.level6;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeDecoder {

    public static String decode(String morseCode) {
        System.out.println(morseCode);

        StringBuilder sb = new StringBuilder();
        String[] words = morseCode.trim().split("   ");
        for (String word : words) {
            if (sb.length() > 0) sb.append(" ");

            String[] characters = word.split(" ");
            for (String c : characters) {
                sb.append(MorseCode.get(c));
            }
        }

        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) {
        String decoded = MorseCodeDecoder.decode(".... . -.--   .--- ..- -.. .");
        System.out.println(decoded);
    }
}

class MorseCode {
    private static char[] english = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            ',', '.', '?' };

    private static String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--.." };

    static Map<String, String> codes = new HashMap<>();

    static {
        for (int i = 0; i < morse.length; i++) {
            codes.put(morse[i], "" + english[i]);
        }
    }

    static String get(String code) {
        return codes.get(code);
    }

}
