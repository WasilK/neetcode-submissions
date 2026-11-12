class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int cs = 0;
        for(int num : nums){
            cs += num;
            if(cs < 0){
                cs = 0;
            }
            maxSum = Math.max(cs, maxSum);
        }
        if(maxSum == 0){
            maxSum = Integer.MIN_VALUE;
            for(int num : nums){
                maxSum = Math.max(num, maxSum);
            }
        }
        return maxSum;
    }
}
