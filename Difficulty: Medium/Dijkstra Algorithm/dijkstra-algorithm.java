class Pair {
	int node;
	int distance;
	
	Pair(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}
class Solution {
	public int[] dijkstra(int V, int[][] edges, int src) {
		// code here
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b)-> a.distance - b.distance);
		// making an adj List
		ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
		for (int i = 0; i<V; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i<edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			int wt = edges[i][2];
			
			adj.get(u).add(new Pair(v, wt));
			adj.get(v).add(new Pair(u, wt));
		}
		int[] dist = new int[V];
		Arrays.fill(dist, (int)1e9);
		dist[src] = 0;
		pq.add(new Pair(src, 0));
		
		while (!pq.isEmpty()) {
			int nod = pq.peek().node;
			int wt = pq.peek().distance;
			pq.poll();
			
			for (Pair nbr: adj.get(nod)) {
				int adjnod = nbr.node;
				int edgeWt = nbr.distance;
				if (wt + edgeWt < dist[adjnod]) {
					dist[adjnod] = wt + edgeWt;
					pq.offer(new Pair(adjnod, dist[adjnod]));
				}
			}
		}
		return dist;
	}
}
