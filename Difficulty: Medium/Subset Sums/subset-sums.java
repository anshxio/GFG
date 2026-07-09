class Solution {
    public ArrayList<Integer> subsetSums(int[] arr) {
        // code here
        ArrayList<Integer> subsetSum = new ArrayList<>();
        helper(0,0,arr,subsetSum);
        Collections.sort(subsetSum);
        return subsetSum;
    }
    public void helper(int indx, int sum, int[]arr,
    ArrayList<Integer> subsetSum){
        if(indx == arr.length){
            subsetSum.add(sum);
            return;
        }
        helper(indx+1,sum+arr[indx],arr,subsetSum);
        helper(indx+1,sum,arr,subsetSum);
    }
}