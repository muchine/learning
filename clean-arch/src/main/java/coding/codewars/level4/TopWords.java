package coding.codewars.level4;

import java.util.*;

public class TopWords {

    public static List<String> top3(String s) {
        Map<String, Integer> table = new HashMap<>();

        String[] tokens = s.split("[^a-zA-Z']");
        System.out.println(Arrays.asList(tokens));

        for (String token : tokens) {
            token = token.trim().toLowerCase();
            if (!isWord(token)) continue;

            Integer count = table.get(token);
            if (count == null) count = 0;

            table.put(token, count + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(table.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());

        List<String> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i == list.size()) break;
            result.add(list.get(i).getKey());
        }

        return result;
    }

    private static boolean isWord(String s) {
        return s.replace("'", "").length() > 0;
    }

}
