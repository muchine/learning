package data.week3;

import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private Worker[] queue;
    long[] assignedWorkers;
    long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public JobQueue() {}

    public JobQueue(int numWorkers, int[] jobs) {
        this.numWorkers = numWorkers;
        this.jobs = jobs;
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorkers[i] + " " + startTime[i]);
        }
    }

    public void assignJobs() {
        initializeWorkers();

        this.startTime = new long[jobs.length];
        this.assignedWorkers = new long[jobs.length];

        for (int i = 0; i < jobs.length; i++) {
            Worker currentWorker = queue[0];
            startTime[i] = currentWorker.time;
            assignedWorkers[i] = currentWorker.index;

//            System.out.printf("current worker time: %d, job time: %d\n", currentWorker.time, jobs[i]);
            currentWorker.time += jobs[i];
            siftDown(0);
        }
    }

    private void initializeWorkers() {
        queue = new Worker[numWorkers];
        for (int i = 0; i < numWorkers; i++) {
            queue[i] = new Worker(i);
        }
    }

    private void siftUp(int i) {
        while (i > 0 && queue[parent(i)].isLessThan(queue[i])) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    private void siftDown(int i) {
        int minIndex = i;

        int left = leftChild(i);
        if (left < queue.length && queue[left].isLessThan(queue[minIndex])) {
            minIndex = left;
        }

        int right = rightChild(i);
        if (right < queue.length && queue[right].isLessThan(queue[minIndex])) {
            minIndex = right;
        }

        if (i != minIndex) {
            swap(i, minIndex);
            siftDown(minIndex);
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Worker temp = queue[i];
        queue[i] = queue[j];
        queue[j] = temp;
    }

    static class Worker {

        private final int index;

        long time;

        public Worker(int index) {
            this.index = index;
        }

        public boolean isLessThan(Worker o) {
            return compareTo(o) < 0;
        }

        public long compareTo(Worker o) {
            if (this.time != o.time)
                return this.time - o.time;
            else
                return this.index - o.index;
        }

    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

}
