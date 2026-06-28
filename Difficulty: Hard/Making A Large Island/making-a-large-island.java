class Disjoint {
	int[] parent;
	int[] size;
	
	Disjoint(int n) {
		parent = new int[n];
		size = new int[n];
		
		for (int i = 0; i<n; i++) {
			parent[i] = i;
            size[i] = 1;
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
		if (size[pu] < size[pv]) {
			parent[pu] = pv;
            size[pv] += size[pu];
		}
		else {
			parent[pv] = pu;
			size[pu] += size[pv];
		}
	}
}
class Solution {
    public static boolean isValid(int adjRow, int adjcol, int n){
        return adjRow >=0 && adjRow< n && adjcol >=0 && adjcol<n;
    }
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        Disjoint ds = new Disjoint(n*n);
        for(int row=0; row<n; row++){
            for(int cols =0; cols<n; cols++){
                if(grid[row][cols] == 0) continue;

                int [] delR = {0,-1,0,1};
                int [] delC = {-1,0,1,0};

                for(int i =0; i<4; i++){
                    int adjRow = row + delR[i];
                    int adjCol = cols+ delC[i];

                    if(isValid(adjRow,adjCol,n) && grid[adjRow][adjCol] == 1){
                        int nodeNo = row*n + cols;
                        int adjNodeNo = adjRow*n + adjCol;
                        ds.union(nodeNo,adjNodeNo);
                    }
                }
            }
        }
        // step-2 
        int max =0;
        for(int row=0; row<n; row++){
            for(int cols =0; cols<n; cols++){
                if(grid[row][cols] == 1) continue;

                int [] delR = {0,-1,0,1};
                int [] delC = {-1,0,1,0};
                HashSet<Integer> components = new HashSet<>();

                for(int i =0; i<4; i++){
                    int adjRow = row + delR[i];
                    int adjCol = cols+ delC[i];

                    if(isValid(adjRow,adjCol, n)){
                        if(grid[adjRow][adjCol] == 1){
                            components.add(ds.find(adjRow*n + adjCol));
                        }
                    }
                }
                int totalSize =0;
                for(Integer parents: components ){
                    totalSize += ds.size[parents];
                }
                max = Math.max(max, totalSize  + 1);
            }
        }
        for(int cellNo =0; cellNo < n*n; cellNo++){
            max = Math.max(max, ds.size[ds.find(cellNo)]);
        }
        return max;
    }
}