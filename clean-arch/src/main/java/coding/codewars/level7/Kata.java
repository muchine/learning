package coding.codewars.level7;

class Kata {
    public static int findShort(String s) {
        String[] tokens = s.split(" ");
        int minLength = Integer.MAX_VALUE;
        for (String token : tokens) {
            if (token.length() < minLength) minLength = token.length();
        }

        return minLength;
    }

}
