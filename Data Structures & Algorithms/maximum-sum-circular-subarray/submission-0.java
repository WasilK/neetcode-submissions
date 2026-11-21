class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;

        int csMax = 0;
        int csMin = 0;

        int totalSum = 0;

        for(int num : nums){
            csMax += num;
            maxSum = Math.max(maxSum, csMax);
            if(csMax < 0){
                csMax = 0;
            }
            csMin += num;
            minSum = Math.min(minSum, csMin);
            if(csMin > 0){
                csMin = 0;
            }
            totalSum += num;
        }
        if(maxSum < 0){
            return maxSum;
        }
        int circularSum = totalSum - minSum;
        return Math.max(circularSum, maxSum);
    }
}