class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int cs = 0;
        for(int num : nums){
            cs += num;
            maxSum = Math.max(maxSum, cs);
            if(cs < 0){
                cs = 0;
            }
        }
        return maxSum;
    }
}
