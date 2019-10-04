package coding.programmers;

public class Sticker {

    public int solution(int[] sticker) {
        int answer = 0;

        Node[] nodes = new Node[sticker.length];
        for (int i = 0; i < sticker.length; i++) {
            nodes[i] = new Node(sticker[i]);
        }

        for (int i = 0; i < sticker.length; i++) {
            if (i + 2 < sticker.length)
                nodes[i].first = nodes[i + 2];

            if (i + 3 < sticker.length)
                nodes[i].second = nodes[i + 3];

            System.out.println("node: " + nodes[i]);
        }

        int firstSum = (int) nodes[0].sum();
        int secondSum = (int) nodes[1].sum();

        return Math.max(firstSum, secondSum);
    }

    class Node {

        int value;

        Node first;

        Node second;

        int sum = 0;

        Node(int value) {
            this.value = value;
        }

        long sum() {
            if (sum > 0) return sum;

            if (first == null && second == null) return value;

            long firstSum = 0;
            long secondSum = 0;

            if (first != null) firstSum = value + first.sum();
            if (second != null) secondSum = value + second.sum();

            sum = (int) Math.max(firstSum, secondSum);
            return sum;
        }

        @Override
        public String toString() {
            String firstValue = first != null ? String.valueOf(first.value) : "null";
            String secondValue = second != null ? String.valueOf(second.value) : "null";

            return "Node{" +
                    "value=" + value +
                    ", first=" + firstValue +
                    ", second=" + secondValue +
                    '}';
        }
    }

}


