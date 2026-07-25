class Solution {
    static int perfectSum(int[] arr, int target) {
        // code here
        int n = arr.length;
        int[][] dp = new int[n][target+1];;
        
        for(int i=0; i<n; i++){
            Arrays.fill(dp[i], -1);
        }
        return count(n-1,arr, target,dp);
    }
    static int count(int index, int[] arr, int target, int[][] dp){
        if(index == 0){
        if(target == 0 && arr[0] == 0)
            return 2;

        if(target == 0 || arr[0] == target)
            return 1;

        return 0;
    }
        if(dp[index][target] != -1){
            return dp[index][target];
        }
        int notPick = count(index-1,arr, target, dp);
        int pick = 0;
        if(arr[index] <= target){
            pick = count(index-1,arr,  target - arr[index],dp);
        }
        return dp[index][target] = notPick + pick;
    }
    
}