package data.week6;

import java.io.*;
import java.util.*;

class Rope {

	Node root;

	Rope(String s) {
		Node current = null;
		for (int i = 0; i < s.length(); i++) {
			Node node = new Node(s.charAt(i), i + 1);

			if (current != null) {
				node.left = current;
				current.parent = node;
			}

			current = node;
		}

		this.root = current;
//		System.out.println("root size: " + root.size);
	}

	public void process(int i, int j, int k) {
//		System.out.println("------------------------------------");
//		System.out.printf("process i: %d, j: %d, k: %d\n", i, j, k);
//		System.out.println("------------------------------------");

		NodePair leftPair = split(root, i);
		Node left = leftPair.left;

		NodePair rightPair = split(leftPair.right, j - i + 1);
		Node middle = rightPair.left;
		Node right = rightPair.right;

//		System.out.println("left tree");
//		left.print(0);
//		System.out.println("middle tree");
//		middle.print(0);
//		System.out.println("right tree");
//		right.print(0);

		Node merged = merge(left, right);
		NodePair pair = split(merged, k);
		root = merge(merge(pair.left, middle), pair.right);
	}

	public void print() {
		print(root);
	}

	public void print(Node node) {
		System.out.println(stringify(node));
	}

	public String stringify() {
		return stringify(root);
	}

	public String stringify(Node node) {
		StringBuilder builder = new StringBuilder();

		Node current = node.minimum();
		while (current != null) {
//			System.out.println(current.c);
			builder.append(current.c);
			current = current.next();
		}

		return builder.toString();
	}

	Node find(Node root, int index) {
		if (root == null) return null;

		Node found = root.find(index + 1);
		splay(found);

		return found;
	}

	NodePair split(Node root, int index) {
		Node node = find(root, index);
		if (node == null) {
			return new NodePair(root, null);
		}

		Node previous = node.left;

		node.left = null;
		node.update();

		if (previous != null) {
			previous.parent = null;
			previous.update();
		}

		return new NodePair(previous, node);
	}

	Node merge(Node left, Node right) {
		if (left == null) return right;
		if (right == null) return left;

		Node min = right.minimum();

		root = splay(min);
		min.left = left;

		min.update();
		return min;
	}

	Node splay(Node node) {
		if (node == null) return null;

		while (node.parent != null) {
			if (node.parent.parent == null) {
				doSmallRotation(node);
				break;
			}
			doBigRotation(node);
		}

		return node;
	}

	private void doBigRotation(Node node) {
		if (node.parent.left == node && node.parent.parent.left == node.parent) {
			// Zig-zig
			doSmallRotation(node.parent);
			doSmallRotation(node);
		} else if (node.parent.right == node && node.parent.parent.right == node.parent) {
			// Zig-zig
			doSmallRotation(node.parent);
			doSmallRotation(node);
		} else {
			// Zig-zag
			doSmallRotation(node);
			doSmallRotation(node);
		}
	}

	private void doSmallRotation(Node node) {
		Node parent = node.parent;
		if (parent == null) return;

		Node grandparent = node.parent.parent;

		if (parent.left == node) {
			Node m = node.right;
			node.right = parent;
			parent.left = m;
		} else {
			Node m = node.left;
			node.left = parent;
			parent.right = m;
		}

		parent.update();
		node.update();

		node.parent = grandparent;
		if (grandparent != null) {
			if (grandparent.left == parent) {
				grandparent.left = node;
			} else {
				grandparent.right = node;
			}

			grandparent.update();
		}
	}

	class Node {
		char c;
		int size;

		Node left;
		Node right;
		Node parent;

		Node(char c, int size) {
			this.c = c;
			this.size = size;
			this.parent = parent;
		}

		void print(int depth) {
			System.out.printf("c: %s, depth: %d, size: %d\n", c, depth, size);
			if (left != null) left.print(depth + 1);
			if (right != null) right.print(depth + 1);
		}

		Node find(int index) {
			Node node = this;
			int k = index;
//			System.out.printf("index: %d\n", index);
//			print(0);

			while (node != null) {
				int size = node.left == null ? 0 : node.left.size;

				if (k == size + 1) {
					return node;
				} else if (k < size + 1) {
					node = node.left;
				} else if (k > size + 1) {
					node = node.right;
					k = k - size - 1;
				}
			}

			return null;
		}

		Node next() {
			return right != null ? right.minimum() : nextAncestor();
		}

		Node nextAncestor() {
			Node next = this;
			while (next != null) {
				if (next.parent != null && next.parent.left == next) return next.parent;
				next = next.parent;
			}

			return null;
		}

		Node minimum() {
			Node min = this;
			while (min.left != null) {
				min = min.left;
			}

			return min;
		}

		private void update() {
			int leftSize = left != null ? left.size : 0;
			int rightSize = right != null ? right.size : 0;
			this.size = 1 + leftSize + rightSize;

			if (left != null) left.parent = this;
			if (right != null) right.parent = this;
		}
	}

	class NodePair {
		Node left, right;

		NodePair(Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}

	static class FastScanner {
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

	public static void main( String[] args ) throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		Rope rope = new Rope(in.next());
		for (int q = in.nextInt(); q > 0; q--) {
			int i = in.nextInt();
			int j = in.nextInt();
			int k = in.nextInt();
			rope.process(i, j, k);
		}

		out.println(rope.stringify());
		out.close();
	}

}
