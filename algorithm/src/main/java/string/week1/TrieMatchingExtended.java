package string.week1;

import java.io.*;
import java.util.*;

public class TrieMatchingExtended implements Runnable {

	private final Node root = new Node(0);

	private int nodeIndex = 1;

	private char[] chars;

	List<Integer> solve(String text, int n, List <String> patterns) {
		List<Integer> positions = new ArrayList <Integer> ();

		for (String pattern : patterns) {
			buildTrie(pattern);
		}

		chars = text.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (match(i)) positions.add(i);
		}

		return positions;
	}

	private void buildTrie(String pattern) {
		Node node = root;
		for (char c : pattern.toCharArray()) {
			Node next = node.get(c);
			if (next == null) next = node.add(c, new Node(nodeIndex++));

			node = next;
		}

		node.add(Node.end, new Node(nodeIndex++));
	}

	private boolean match(int position) {
		int index = position;

		Node next = root;
		while(next != null && index < chars.length) {
			next = next.get(chars[index++]);
			if (next != null && next.isMatched()) return true;
		}

		return false;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	class Node {

		public static final char end = '$';

		private final Map<Character, Node> nodes = new HashMap<>();

		private final int index;

		public Node(int index) {
			this.index = index;
		}

		Node get(char c) {
			return nodes.get(c);
		}

		Node add(char c, Node node) {
			nodes.put(c, node);
			return node;
		}

		boolean isMatched() {
			return nodes.containsKey(end);
		}

	}

	public static void main (String [] args) {
		new Thread (new TrieMatchingExtended ()).start ();
	}
}
