class Solution {
    public static int longestBitonicSequence(int n, int[] nums) {
        // code here
        int[] dp1 = new int[n];
        Arrays.fill(dp1, 1);

        for(int i =0; i < n; i++){
            for(int prev = 0; prev < i; prev++){
                if(nums[i] > nums[prev] && dp1[prev] + 1 > dp1[i]){
                    dp1[i] = dp1[prev] + 1;
                }
            }
        }
        
        int[] dp2 = new int[n];

        Arrays.fill(dp2, 1);

        for(int i =n-1; i >= 0; i--){
            for(int prev = n-1; prev > i; prev--){
                if(nums[i] > nums[prev] && dp2[prev] + 1 > dp2[i]){
                    dp2[i] = dp2[prev] + 1;
                }
            }
        }
        
        int maxi =0;
        for(int i =0; i<n; i++){
            if(dp1[i] > 1 && dp2[i] > 1){
                maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
            }
        }
        return maxi;
    }
}
