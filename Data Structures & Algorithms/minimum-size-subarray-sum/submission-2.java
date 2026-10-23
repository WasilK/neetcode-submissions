class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int j = 0, sum = 0;
        int len = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            while(sum >= target){
                len = Math.min(i - j + 1, len);
                sum -= nums[j];
                j++;
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}