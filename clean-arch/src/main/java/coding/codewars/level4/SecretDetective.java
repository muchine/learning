package coding.codewars.level4;

import java.util.*;

/**
 * Recover a secret string from random triplets
 * https://www.codewars.com/kata/recover-a-secret-string-from-random-triplets/train/java
 */
@SuppressWarnings("Duplicates")
public class SecretDetective {

    private List<Character>[] triplets;

    public String recoverSecret(char[][] triplets) {
//        print(triplets);

        this.triplets = new List[triplets.length];
        for (int i = 0; i < triplets.length; i++) {
            List<Character> chars = new ArrayList<>();
            for (char c : triplets[i]) chars.add(c);

            this.triplets[i] = chars;
//            System.out.printf("triplets[%s]: %s\n", i, this.triplets[i]);
        }

        StringBuilder sb = new StringBuilder();
        while (!isEmpty()) {
            char c = findFirstCharacter();
            sb.append(c);
        }

        return sb.toString();
    }

    private Character findFirstCharacter() {
        Set<Character> candidates = new HashSet<>();
        for (List<Character> triplet : triplets) {
            if (!triplet.isEmpty()) candidates.add(triplet.get(0));
        }

        for (char c : candidates) {
            if (isFirstCharacter(c)) {
                removeCharacter(c);
                return c;
            }
        }

        throw new IllegalStateException("First character not found.");
    }

    private boolean isFirstCharacter(Character c) {
        for (List<Character> triplet : triplets) {
            if (triplet.indexOf(c) > 0) return false;
        }

        return true;
    }

    private void removeCharacter(Character c) {
        for (List<Character> triplet : triplets) triplet.remove(c);
    }

    private boolean isEmpty() {
        for (List<Character> triplet : triplets) if (!triplet.isEmpty()) return false;
        return true;
    }

    private void print(char[][] triplets) {
        for (char[] row : triplets) {
            StringBuilder sb = new StringBuilder();
            for (char c : row) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(c);
            }

            System.out.println(sb.toString());
        }
    }

}
