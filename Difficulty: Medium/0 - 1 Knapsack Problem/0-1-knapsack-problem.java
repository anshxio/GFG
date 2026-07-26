class Solution {
    public int knapsack(int W, int val[], int wt[]) {
        // code here
        int[] prev = new int[W+1];
        for(int w = wt[0]; w <= W; w++){
             prev[w] = val[0];
        }
        for(int i=1;  i< val.length; i++){
            for(int w = W; w >=0; w--){
                int notTake = 0 + prev[w];
                int take = Integer.MIN_VALUE;
                if(wt[i] <= w){
                    take = val[i] + prev[w -wt[i]];
                }
                prev[w] = Math.max(take,notTake);
            }
        }
        return prev[W];
    }
}
