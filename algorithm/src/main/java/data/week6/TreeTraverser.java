package data.week6;

import java.util.*;
import java.io.*;

public class TreeTraverser {

	TreeNode node;

	public TreeTraverser() {}

	public TreeTraverser(int[] keys, int[] left, int[] right) {
		this.node = new TreeNode(keys, left, right);
		this.node.initializeNodes();
	}

	public List<Integer> printInOrder() {
		return node.inOrder();
	}

	public List<Integer> printPreOrder() {
		return node.preOrder();
	}

	public List<Integer> printPostOrder() {
		return node.postOrder();
	}

	public class TreeNode {
		int n;
		int[] key, left, right;

		Node[] nodes;
		Node root;

		TreeNode() {}

		TreeNode(int[] key, int[] left, int[] right) {
			this.n = key.length;
			this.key = key;
			this.left = left;
			this.right = right;
		}
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}

			initializeNodes();
		}

		Node initializeNodes() {
			this.nodes = new Node[n];

			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(key[i]);
			}

			for (int i = 0; i < n; i++) {
				if (left[i] > -1) {
					nodes[i].left = nodes[left[i]];
					nodes[left[i]].parent = nodes[i];
				}

				if (right[i] > -1) {
					nodes[i].right = nodes[right[i]];
					nodes[right[i]].parent = nodes[i];
				}
			}

//			System.out.println("initialized node....");
			this.root = nodes[0];
			return root;
		}

		void clearNodes() {
			for (int i = 0; i < n; i++) {
				nodes[i].done = false;
			}
		}

		List<Integer> inOrder() {
			clearNodes();
			ArrayList<Integer> result = new ArrayList<Integer>();

			Node next = root;
			while(true) {
//				System.out.printf("next key: %d\n", next.key);
				if (next.left != null && !next.left.done) {
					next = next.left;
					continue;
				}

				if (!next.done) {
					result.add(next.key);
					next.done = true;
				}

				if (next.right != null && !next.right.done) {
					next = next.right;
					continue;
				}

				if (next.key == root.key && root.isAllDone()) {
					break;
				}

				next = next.parent;
			}

			return result;
		}

		List<Integer> preOrder() {
			clearNodes();
			ArrayList<Integer> result = new ArrayList<Integer>();

			Node next = root;
			while(true) {
//				System.out.printf("next key: %d\n", next.key);
				if (!next.done) {
					result.add(next.key);
					next.done = true;
				}

				if (next.left != null && !next.left.done) {
					next = next.left;
					continue;
				}

				if (next.right != null && !next.right.done) {
					next = next.right;
					continue;
				}

				if (next.key == root.key && root.isAllDone()) {
					break;
				}

				next = next.parent;
			}

			return result;
		}

		List<Integer> postOrder() {
			clearNodes();
			ArrayList<Integer> result = new ArrayList<Integer>();

			Node next = root;
			while(true) {
//				System.out.printf("next key: %d\n", next.key);
				if (next.left != null && !next.left.done) {
					next = next.left;
					continue;
				}

				if (next.right != null && !next.right.done) {
					next = next.right;
					continue;
				}

				if (!next.done) {
					result.add(next.key);
					next.done = true;
				}

				if (next.key == root.key && root.isAllDone()) {
					break;
				}

				next = next.parent;
			}

			return result;
		}
	}

	class Node {

		int key;

		Node parent;

		Node left;

		Node right;

		boolean done = false;

		Node(int key) {
			this.key = key;
		}

		boolean isAllDone() {
			if (left != null && !left.done) return false;
			if (right != null && !right.done) return false;
			if (!done) return false;

			return true;
		}

	}

	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeNode tree = new TreeNode();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}

	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new TreeTraverser().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

}
