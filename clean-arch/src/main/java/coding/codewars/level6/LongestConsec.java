package coding.codewars.level6;

class LongestConsec {

    public static String longestConsec(String[] strarr, int k) {
        String longest = "";
        for (int i = 0; i <= strarr.length - k; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < k; j++) {
                sb.append(strarr[i + j]);
            }

            String s = sb.toString();
            if (s.length() > longest.length()) longest = s;
        }

        return longest;
    }
}
