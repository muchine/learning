package coding.codewars.level4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Permutations
 * https://www.codewars.com/kata/permutations/train/java
 */
class Permutations {

    public List<String> perform(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        for (char c : s.toCharArray()) {
            result = permutate(result, "" + c);
        }

        return new ArrayList<>(result);
    }

    public Set<String> permutate(Set<String> set, String appended) {
        Set<String> result = new HashSet<>();
        for (String s : set) {
            for (int i = 0; i <= s.length(); i++) {
                result.add(s.substring(0, i) + appended + s.substring(i, s.length()));
            }
        }

        return result;
    }

    public static List<String> singlePermutations(String s) {
        return new Permutations().perform(s);
    }

    public static void main(String[] args) {
        String s = "";
        String a = "e";
        for (int i = 0; i <= s.length(); i++) {
            System.out.println(s.substring(0, i) + a + s.substring(i, s.length()));
        }
    }

}
