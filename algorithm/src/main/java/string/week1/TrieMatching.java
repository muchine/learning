package string.week1;

import java.io.*;
import java.util.*;

public class TrieMatching implements Runnable {

	private final Node root = new Node(0);

	private int nodeIndex = 1;

	private char[] chars;

	List <Integer> solve(String text, List<String> patterns) {
		List<Integer> positions = new ArrayList <Integer> ();

		for (String pattern : patterns) {
			addTextToTrie(pattern);
		}

		chars = text.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (match(i)) positions.add(i);
		}

		return positions;
	}

	private void addTextToTrie(String pattern) {
		Node node = root;
		for (char c : pattern.toCharArray()) {
			Node next = node.get(c);
			if (next == null) next = node.add(c, new Node(nodeIndex++));

			node = next;
		}
	}

	private boolean match(int position) {
		int index = position;

		Node next = root;
		while(next != null && index < chars.length) {
			next = next.get(chars[index++]);
			if (next != null && next.isLeaf()) return true;
		}

		return false;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List<Integer> ans = solve(text, patterns);

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

		boolean isLeaf() {
			return nodes.isEmpty();
		}

	}

	public static void main (String [] args) {
		new Thread(new TrieMatching()).start();
	}
}
