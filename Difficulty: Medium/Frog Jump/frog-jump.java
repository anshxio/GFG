class Solution {
    int minCost(int[] height) {
        // code here
        int n = height.length;
        int [] dp = new int[n+1];
        Arrays.fill(dp,-1);
        return jump(n-1, height,dp);
    }
    public static int jump(int index, int[] height, int []dp){
        if(index == 0){
            return 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        int left = jump(index-1, height, dp) + Math.abs(height[index] - height[index-1]);
        int right = Integer.MAX_VALUE;
        if(index > 1){
            right = jump(index-2, height, dp) + Math.abs(height[index] - height[index-2]);
        }
        return dp[index] = Math.min(left,right);
    }
}