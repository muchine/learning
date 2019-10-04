package algo.week4;

import java.util.*;
import java.io.*;

public class MajorityElement {

    int getMajorityElement(int[] a) {
        int medium = a.length / 2;

        List<Integer> sequence = new ArrayList<>();
        for (int e : a) {
            sequence.add(e);
        }

        Collections.sort(sequence);

        int value = sequence.get(0);
        int count = 0;

        for (int i = 0; i < sequence.size(); i++) {
            if (value == sequence.get(i))
                count++;
            else {
                count = 1;
                value = sequence.get(i);
            }

            if (count > medium) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        MajorityElement m = new MajorityElement();
        System.out.println(m.getMajorityElement(a));
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

