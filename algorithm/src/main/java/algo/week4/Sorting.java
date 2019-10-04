package algo.week4;

import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

    int[] partition3(int[] a, int l, int r) {
      int x = a[l];
      int m1 = l;
      int m2 = l;

      for (int i = l + 1; i <= r; i++) {
          if (a[i] < x) {
              // swap m2 and i
              m2++;
              swap(a, m2, i);

              // swap m2 and m1
              m1++;
              swap(a, m2, m1);
          } else if (a[i] == x) {
              m2++;
              swap(a, m2, i);
          }
      }

      swap(a, l, m1);

      int[] m = {m1, m2};
      return m;
    }

    private void swap(int[] sequence, int index1, int index2) {
        if (index1 == index2) return;

        int t = sequence[index1];
        sequence[index1] = sequence[index2];
        sequence[index2] = t;
    }

    int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int[] m = partition3(a, l, r);

        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        Sorting s = new Sorting();
        s.randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
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

