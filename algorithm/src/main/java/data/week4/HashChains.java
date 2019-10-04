package data.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    private List<String>[] table;

    List<String> results = new ArrayList<>();

    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    HashChains() {

    }

    HashChains(int bucketCount) {
        this.bucketCount = bucketCount;
        this.table = new List[bucketCount];
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    public String processQuery(Query query) {
        String result = "";

        switch (query.type) {
            case "add":
                add(query.s);
                break;
            case "del":
                delete(query.s);
                break;
            case "find":
                result = find(query.s);
                break;
            case "check":
                result = printChain(query.ind);
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }

//        System.out.printf("query: %s, result: %s\n", query.type, result);
        if (!result.isEmpty()) results.add(result);
        return result;
    }

    private void doProcessQuery(Query query) {
        String result = processQuery(query);
        if (!result.isEmpty()) out.println(result);
    }

    private void add(String s) {
        int hashValue = hashFunc(s);
        List<String> chain = table[hashValue];

        if (chain != null && chain.contains(s)) return;

        if (chain == null) table[hashValue] = new ArrayList<>();
        table[hashValue].add(0, s);
    }

    private void delete(String s) {
        List<String> chain = findChain(s);
        if (chain != null && chain.contains(s)) chain.remove(s);
    }

    private String find(String s) {
        List<String> chain = findChain(s);
        return chain != null && chain.contains(s) ? "yes" : "no";
    }

    private String printChain(int index) {
        StringBuilder builder = new StringBuilder();
        List<String> chain = table[index];

        if (chain == null || chain.isEmpty()) return " ";

        for (String e : chain) {
            if (builder.length() > 0) builder.append(" ");
            builder.append(e);
        }

        return builder.toString();
    }

    private List<String> findChain(String s) {
        int hashValue = hashFunc(s);
        return table[hashValue];
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        this.table = new List[bucketCount];

        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            doProcessQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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
        new HashChains().processQueries();
    }

}
