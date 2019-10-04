package algo;

import java.util.*;

public class PrimitiveCalculator {

    private final Map<Integer, List<Integer>> sequenceMap = new HashMap<>();

    private final int n;
    private final int[] cns;

    PrimitiveCalculator(int n) {
        this.n = n;
        this.cns = new int[n + 1];
    }

    public List<Integer> optimalSequence() {
        List<Integer> sequence = new ArrayList<>();

        if (n <= 0) return sequence;

        for (int i = 1; i <= n; i++) {
            int cn1 = Integer.MAX_VALUE;
            int cn2 = Integer.MAX_VALUE;
            int cn3 = Integer.MAX_VALUE;

            if (i % 3 == 0) {
                cn1 = cns[i / 3];
            }

            if (i % 2 == 0) {
                cn2 = cns[i / 2];
            }

            cn3 = cns[i - 1];

//            System.out.printf("i: %s, cn1: %s, cn2: %s, cn3: %s \n", i, cn1, cn2, cn3);
            cns[i] = Math.min(Math.min(cn1, cn2), cn3) + 1;
        }

//        Helper.printArray(cns);
        reconstruct(n, sequence);

        Collections.reverse(sequence);
        return sequence;
    }

    private void reconstruct(int i, List<Integer> sequence) {
        if (i == 0) return;

        sequence.add(i);

        if (i % 3 == 0 && cns[i / 3] + 1 == cns[i]) {
            reconstruct(i / 3, sequence);
        } else if (i % 2 == 0 && cns[i / 2] + 1 == cns[i]) {
            reconstruct(i / 2, sequence);
        } else {
            reconstruct(i - 1, sequence);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        PrimitiveCalculator pc = new PrimitiveCalculator(n);
        List<Integer> sequence = pc.optimalSequence();
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

