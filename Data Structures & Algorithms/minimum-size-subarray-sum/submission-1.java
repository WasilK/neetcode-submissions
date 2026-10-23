class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int len = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum >= target){
                    len = Math.min(len, j - i + 1);
                }
            }
        }
        if(len == Integer.MAX_VALUE){
            return 0;
        }else{
            return len;
        }
    }
}