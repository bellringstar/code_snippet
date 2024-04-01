public class UnionFind {

	static class Node {
		private Node parent;
		private int depth;

		public boolean isConnected(Node o) {
			return root() == o.root();
		}

		public void merge(Node o) {
			if (isConnected(o)) return;

			Node root1 = root();
			Node root2 = o.root();

			if (root1.depth > root2.depth) {
				root2.parent = root1;
			} else if (root1.depth < root2.depth) {
				root1.parent = root2;
			} else {
				root2.parent = root1;
				root1.depth += 1;
			}
		}

		private Node root() {
			if (parent == null) return this;
			return parent.root();
		}
	}
}
