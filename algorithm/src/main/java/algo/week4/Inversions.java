package algo.week4;

import java.util.*;

public class Inversions {

    long getNumberOfInversions(int[] a, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, left, ave);
        numberOfInversions += getNumberOfInversions(a, ave, right);

        numberOfInversions += merge(a, left, ave, right);

        return numberOfInversions;
    }

    long merge(int[] a, int left, int ave, int right) {
        long numberOfInversions = 0;
        if (left + 1 >= right) return numberOfInversions;

        int[] merged = new int[right - left];
        int k = left;
        int l = ave;

        for (int i = 0; i < merged.length; i++) {
            boolean isLeftEmpty = (k >= ave);
            int leftCount = isLeftEmpty ? 0 : ave - k;
            boolean isRightEmpty = (l >= right);
            int rightCount = isRightEmpty ? 0 : right - l;

            if (isRightEmpty || (!isLeftEmpty && a[k] <= a[l])) {
                merged[i] = a[k];
                k++;
            } else if ((isLeftEmpty) || (!isRightEmpty && a[k] > a[l])) {
                merged[i] = a[l];
                l++;
                if (!isLeftEmpty) {
                    numberOfInversions += leftCount;
                }
            } else {
                throw new RuntimeException("invalid condition");
            }
        }

        for (int i = 0; i < merged.length; i++) {
            a[left + i] = merged[i];
        }

        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];

        Inversions i = new Inversions();
        System.out.println(i.getNumberOfInversions(a,0, a.length));
    }
}

