package util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;

public class Helper {

    private static final NumberFormat formatter = new DecimalFormat("#0.00000");

    public static final void printArray(int[] a) {
        for (int e : a) System.out.print(e + " ");
        System.out.println();
    }

    public static final void printArray(long[] a) {
        for (long e : a) System.out.print(e + " ");
        System.out.println();
    }

    public static final void printArray(double[] a) {
        for (double e : a) System.out.print(String.format("%1$-12s", formatter.format(e)));
        System.out.println();
    }

    public static final void printArray(char[] a) {
        for (char e : a) System.out.print(e + " ");
        System.out.println();
    }

    public static final void printArray(int[][] a) {
        for (int[] e : a) printArray(e);
    }

    public static final void printArray(long[][] a) {
        for (long[] e : a) printArray(e);
    }

    public static final void printArray(Object[] a) {
        for (Object e : a) System.out.print(e + " ");
        System.out.println();
    }

    public static final int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static final void printCollection(String label, Collection<?> c) {
        System.out.print("[" + label + "]: ");

        if (c != null) {
            for (Object e : c)
                System.out.print(e + " ");
        }

        System.out.println();
    }

}
