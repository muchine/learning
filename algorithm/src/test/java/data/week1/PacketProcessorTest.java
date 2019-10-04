package data.week1;

import data.week1.PacketProcessor;
import data.week1.Request;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PacketProcessorTest {

    @Test
    public void testSample1() {
        List<Request> requests = new ArrayList<Request>();
        doTest(requests, 1, null);
    }

    @Test
    public void testSample2() {
        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(0, 0));

        int[] expected = { 0 };

        doTest(requests, 1, expected);
    }

    @Test
    public void testSample3() {
        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(0, 1));
        requests.add(new Request(0, 1));

        int[] expected = { 0, -1 };

        doTest(requests, 1, expected);
    }

    @Test
    public void testSample4() {
        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(0, 1));
        requests.add(new Request(1, 2));

        int[] expected = { 0, 1 };

        doTest(requests, 1, expected);
    }

    @Test
    public void testSample5() {
        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(0, 0));
        requests.add(new Request(0, 2));
        requests.add(new Request(1, 1));
        requests.add(new Request(2, 1));

        int[] expected = { 0, 0, -1, 2 };

        doTest(requests, 1, expected);
    }

    @Test
    public void testSample6() {
        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(0, 2));
        requests.add(new Request(0, 0));
        requests.add(new Request(1, 1));
        requests.add(new Request(2, 1));

        int[] expected = { 0, -1, -1, 2 };

        doTest(requests, 1, expected);
    }

    @Test
    public void testDebugging1() {
        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(0, 0));
        requests.add(new Request(0, 0));

        int[] expected = { 0, 0 };

        doTest(requests, 1, expected);
    }

    private void doTest(List<Request> requests, int bufferSize, int[] expected) {
        PacketProcessor p = new PacketProcessor(requests, bufferSize);
        p.process();

        for (int i = 0; i < requests.size(); i++) {
            Request request = requests.get(i);
            System.out.printf("compare %dth requests...\n", i);
            assertEquals(expected[i], request.startTime);
        }
    }

}
