package data.week3;

import org.junit.Assert;
import org.junit.Test;

public class JobQueueTest {

    @Test
    public void testSample1() {
        int[] jobs = { 1, 2, 3, 4, 5 };
        int[] workers = { 0, 1, 0, 1, 0, };
        int[] time = { 0, 0, 1, 2, 4 };

        doTest(2, jobs, workers, time);
    }

    @Test
    public void testSample2() {
        int[] jobs = new int[20];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i] = 1;
        }

        int[] workers = { 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3,0, 1, 2, 3,0, 1, 2, 3 };
        int[] time = { 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4 };

        doTest(4, jobs, workers, time);
    }

    @Test
    public void testSample3() {
        int[] jobs = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] workers = { 0, 1, 2, 0, 1, 2, 0, 1, 2, 0 };
        int[] time =    { 0, 0, 0, 1, 2, 3, 5, 7, 9, 12 };

        doTest(3, jobs, workers, time);
    }

    @Test(timeout = 4000)
    public void testMax() {
        int[] jobs = new int[100000];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i] = 10;
        }

        JobQueue q = new JobQueue(100000, jobs);
        q.assignJobs();
    }

    @Test
    public void testOverflow() {
        int[] jobs = new int[100000];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i] = 1000000000;
        }

        JobQueue q = new JobQueue(1, jobs);
        q.assignJobs();
    }

    private void doTest(int numWorkers, int[] jobs, int[] expectedWorker, int[] expectedTime) {
        JobQueue q = new JobQueue(numWorkers, jobs);
        q.assignJobs();

        for (int i = 0; i < jobs.length; i++) {
            if (q.assignedWorkers[i] != expectedWorker[i]) {
                System.out.printf("worker failed: i: %d, assigned: %d, expected: %d\n", i, q.assignedWorkers[i], expectedWorker[i]);
                Assert.fail();
            }
        }

        for (int i = 0; i < jobs.length; i++) {
            if (q.startTime[i] != expectedTime[i]) {
                System.out.printf("time failed: i: %d, start: %d, expected: %d\n", i, q.startTime[i], expectedTime[i]);
                Assert.fail();
            }
        }
    }

}