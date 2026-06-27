class Disjoint {
	int[] parent;
	int[] rank;
	
	Disjoint(int n) {
		parent = new int[n];
		rank = new int[n];
		
		for (int i = 0; i<n; i++) {
			parent[i] = i;
		}
	}
	
	int find(int node) {
		if (parent[node] == node) {
			return node;
		}
		return parent[node] = find(parent[node]);
	}
	void union (int u, int v) {
		int pu = find(u);
		int pv = find(v);
		
		if (pu == pv) {
			return;
		}
		if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		}
		else if (rank[pv] < rank[pu]) {
			parent[pv] = pu;
		}
		else {
			parent[pv] = pu;
			rank[pu]++;
		}
	}
}
class Solution {
    public static boolean isValid(int adjRow, int adjCol, int row, int col){
        return adjRow >= 0&& adjRow < row && adjCol >=0 && adjCol < col;
    }
	
	public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
		// Your code here
		Disjoint ds = new Disjoint(rows*cols);
		int [][] vis = new int[rows][cols];
		int count = 0;
		
		List<Integer> ans = new ArrayList<>();
		int len = operators.length;
		for (int i = 0; i<len; i++) {
			int r = operators[i][0];
			int c = operators[i][1];
			
			if (vis[r][c] == 1) {
				ans.add(count);
				continue;
			}
			vis[r][c] = 1;
			count++;
			
			int[] delR = {0, -1, 0, 1};
			int []delC = {-1, 0, 1, 0};
			
			for (int idx = 0; idx<4; idx++) {
				int adjRow = r + delR[idx];
				int adjCol = c + delC[idx];
				
				if (isValid(adjRow, adjCol, rows, cols)) {
					if (vis[adjRow][adjCol] == 1) {
						int nodeNo = r *cols + c;
						int adjNodeNo = adjRow*cols + adjCol;
						if (ds.find(nodeNo) != ds.find(adjNodeNo)) {
							count--;
							ds.union(adjNodeNo, nodeNo);
						}
					}
				}
			}
			ans.add(count);
		}
		return ans;
	}
}
