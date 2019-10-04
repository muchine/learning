package coding.codewars.level4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MorseCodeDecoder {

    public static String decodeBits(String bits) {
        int rate = transmissionRate(trim(bits));

        String dot = "";
        String space = "";
        String dash = "";
        for (int i = 0; i < rate; i++) {
            dot += "1"; space += "0";
            for (int j = 0; j < 3; j++) dash += "1";
        }

        return trim(bits).replace(dash, "-").replace(dot, ".").replace(space, " ");
    }

    public static String decodeMorse(String morseCode) {
        StringBuilder sb = new StringBuilder();
        for (String word :  morseCode.split("       ")) {
            if (sb.length() > 0) sb.append(" ");
            for (String letter : word.split("   ")) {
                sb.append(MorseCode.get(letter.replace(" ", "")));
            }
        }

        return sb.toString().toUpperCase();
    }

    private static int transmissionRate(String bits) {
        int minOne = Arrays.stream(bits.split("0"))
                .mapToInt(t -> t.length())
                .filter(length -> length > 0)
                .min()
                .orElse(Integer.MAX_VALUE);

        int minZero = Arrays.stream(bits.split("1"))
                .mapToInt(t -> t.length())
                .filter(length -> length > 0)
                .min()
                .orElse(Integer.MAX_VALUE);

        return Math.min(minOne, minZero);
    }

    private static String trim(String bits) {
        return bits.substring(bits.indexOf("1"), bits.lastIndexOf("1") + 1);
    }

    public static void main(String[] args) {
        System.out.println("01110".substring(1, 4));
        System.out.println("111".replace("111111111", "-").replace("111", "."));

//        String bits = "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011";
        String bits = "01110";
        System.out.println(transmissionRate(bits));

        String codes = decodeBits(bits);
        System.out.println("codes: " + codes);

        System.out.println(decodeMorse(codes));

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
