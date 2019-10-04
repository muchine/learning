package data.week1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Request {

    int arrivalTime;
    int processTime;
    int startTime = -1;
    int endTime;

    boolean dropped;
    boolean finished;

    public Request(int arrivalTime, int processTime) {
        this.arrivalTime = arrivalTime;
        this.processTime = processTime;
    }

    public void setup(int currentTime) {
        this.startTime = currentTime;
        this.endTime = this.startTime + this.processTime;
    }

    public boolean isFinished(int currentTime) {
        return endTime <= currentTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "arrivalTime=" + arrivalTime +
                ", processTime=" + processTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", dropped=" + dropped +
                '}';
    }
}

class Buffer {

    private final int size;
    private final List<Request> queue = new ArrayList<>();

    public Buffer(int size) {
        this.size = size;
    }

    public boolean push(Request request) {
        if (queue.size() >= size) return false;

        int estimated = estimatedEndTime();
        request.setup(estimated == -1 ? request.arrivalTime : estimated);

        queue.add(request);
        return true;
    }

    public Request current() {
        return queue.isEmpty() ? null : queue.get(0);
    }

    public Request pop() {
        return queue.isEmpty() ? null : queue.remove(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private int estimatedEndTime() {
        if (queue.isEmpty()) return -1;

        return queue.get(queue.size() - 1).endTime;
    }

}

class PacketProcessor {

    private final List<Request> requests;

    private final Buffer buffer;

    private int currentIndex = 0;

    public PacketProcessor(List<Request> requests, int bufferSize) {
        this.requests = requests;
        this.buffer = new Buffer(bufferSize);
    }

    public void process() {
        if (requests.isEmpty()) return;

        int minArrivalTime = requests.get(0).arrivalTime;
        int maxArrivalTime = 2000000;

        for (int i = minArrivalTime; i <= maxArrivalTime; i++) {
            acceptRequests(i);

            if (currentIndex > requests.size() && buffer.isEmpty()) break;
        }
    }

    private void finishRequests(int currentTime) {
        while (true) {
            Request current = buffer.current();
            if (current == null || !current.isFinished(currentTime)) return;

            buffer.pop();
        }
    }

    private void acceptRequests(int currentTime) {
        while (true) {
            if (currentIndex >= requests.size()) return;

            Request request = requests.get(currentIndex);
            if (currentTime < request.arrivalTime) return;

            finishRequests(currentTime);
            addBuffer(request);
            currentIndex++;
        }
    }

    private boolean addBuffer(Request request) {
        boolean added = buffer.push(request);
        if (!added) request.dropped = true;

        return added;
    }

    private static List<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static void PrintResponses(List<Request> requests) {
        for (int i = 0; i < requests.size(); ++i) {
            Request response = requests.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.startTime);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();

        List<Request> requests = ReadQueries(scanner);
        PacketProcessor p = new PacketProcessor(requests, buffer_max_size);
        p.process();
        PrintResponses(requests);
    }
}
