package coding.codewars.level4;

import java.util.Arrays;

public class StripComments {

    public String perform(String text, String[] commentSymbols) {
        System.out.println("text: " + text + ", symbols: " + Arrays.asList(commentSymbols));
        StringBuilder sb = new StringBuilder();

        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            int minIndex = line.length();
            for (String symbol : commentSymbols) {
                int index = line.indexOf(symbol);
                if (index >= 0 && index < minIndex) minIndex = index;
            }

            sb.append(line.substring(0, minIndex).replaceAll("\\s+$",""));
            if (i < lines.length - 1) sb.append("\n");
        }

        return sb.toString();
    }


    public static String stripComments(String text, String[] commentSymbols) {
        return new StripComments().perform(text, commentSymbols);
    }

}
