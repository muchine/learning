package data.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingQueue;

public class TreeHeightChecker {

	TreeHeight th = new TreeHeight();

	public TreeHeightChecker() {}

	public TreeHeightChecker(int[] parents) {
		th.n = parents.length;
		th.parent = parents;
	}

	public int height() {
		th.buildTree();
		return th.computeHeight();
	}

	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}

	public class TreeHeight {
		int n;
		int parent[];

		Node nodes[];
		Node root;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}

			buildTree();
		}

		void buildTree() {
			this.nodes = new Node[n];
			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(i);
			}

			for (int i = 0; i < n; i++) {
				Node node = nodes[i];
				if (parent[i] == -1) {
					this.root = node;
				} else {
					Node parentNode = nodes[parent[i]];
					parentNode.addChild(node);
				}
			}
		}

		int computeHeight() {
			int maxHeight = 1;
			NodeHeight[] heights = new NodeHeight[n];

			for (int i = 0; i < n; i++) {
				heights[i] = new NodeHeight(nodes[i]);
			}

			Queue<NodeHeight> queue = new LinkedBlockingQueue<>();
			NodeHeight rootHeight = heights[root.index];
			rootHeight.height = 1;

			queue.add(rootHeight);

			while (!queue.isEmpty()) {
				NodeHeight currentHeight = queue.poll();

				for (Node child : currentHeight.node.children) {
					NodeHeight childHeight = heights[child.index];
					childHeight.height = currentHeight.height + 1;

					if (maxHeight < childHeight.height) maxHeight = childHeight.height;

					queue.add(childHeight);
				}
			}

			return maxHeight;
		}
	}

	class NodeHeight {

		Node node;

		int height;

		public NodeHeight(Node node) {
			this.node = node;
		}
	}

	class Node {

		private final int index;

		private final List<Node> children = new ArrayList<>();

		public Node(int index) {
			this.index = index;
		}

		void addChild(Node node) {
			children.add(node);
		}

		int height() {
			int maxHeight = 0;

			for (Node child : children) {
				int childHeight = child.height();
				if (childHeight > maxHeight) maxHeight = childHeight;
			}

			return maxHeight + 1;
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

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new TreeHeightChecker().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

}
