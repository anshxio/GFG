class Solution {
    boolean graphColoring(int v, int[][] edges, int m) {
        // code here
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i=0; i< v; i++){
            adjList.add(new ArrayList<>());
        }
        for(int [] edge: edges){
            int U = edge[0];
            int V = edge[1];
            
            adjList.get(U).add(V);
            adjList.get(V).add(U);
        }
        int [] color = new int [v];
        return solve(0,adjList,color, m);
    }
    private boolean solve(int node, ArrayList<ArrayList<Integer>> adjList, int[]color, int m){
        if(node == adjList.size()){
            return true;
        }
        for(int cl = 1; cl <= m; cl++){
            if(isSafe(node,adjList, color, cl)){
                color[node] = cl;
                if(solve(node+1, adjList,color,m)){
                    return true;
                }
                // backtrack
                color[node] =0;
            }
            
        }
        return false;
    }
    private boolean isSafe(int node, ArrayList<ArrayList<Integer>>adjList, int[] color, int cl){
        for(int neighbour: adjList.get(node)){
            if(color[neighbour] == cl){
                return false;
            }
        }
        return true;
    }
}