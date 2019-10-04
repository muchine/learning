package coding.codewars.level4;

public class TimeFormatter {

    public static String formatDuration(int seconds) {
        if (seconds == 0) return "now";

        int[] units = new int[] { 31536000, 86400, 3600, 60, 1 };
        int[] counts = new int[5];

        String[] formats = new String[] { "year", "day", "hour", "minute", "second" };

        for (int i = 0; i < units.length; i++) {
            int unit = units[i];
            counts[i] = seconds / unit;
            seconds = seconds % unit;
        }

        int lastUnit = -1;
        for (int i = 0; i < units.length; i++) {
            int index = units.length - i - 1;
            int count = counts[index];
            if (count > 0) {
                lastUnit = index;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < units.length; i++) {
            int count = counts[i];
            if (count > 0) {
                if (sb.length() > 0) {
                    sb.append(i == lastUnit ? " and " : ", ");
                }

                sb.append(count);
                sb.append(" ");
                sb.append(count > 1 ? formats[i] + "s" : formats[i]);
            }
        }

        return sb.toString();
    }
}
