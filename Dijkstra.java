import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

	static class Node {
		int dest;
		int cost;

		public Node (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}

	public static int[] solution(int[][] graph, int start, int n) {
		List<Node>[] adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int[] edge : graph) {
			adj[edge[0]].add(new Node(edge[1], edge[2]));
		}

		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

		distance[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (distance[now.dest] < now.cost) continue;

			for (Node next : adj[now.dest]) {
				if (distance[next.dest] > now.cost + next.cost) {
					distance[next.dest] = now.cost + next.cost;
					pq.add(new Node(next.dest, distance[next.dest]));
				}
			}
		}

		return distance;
	}
}
