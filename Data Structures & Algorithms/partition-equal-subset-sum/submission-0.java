class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int total = 0;
        for(int num : nums){
            total += num;
        }
        if(total % 2 != 0){
            return false;
        }
        int w = total / 2;
        int dp[][] = new int[n + 1][w + 1];
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < w + 1; j++){
                int val = nums[i - 1];
                if(val <= j){
                    dp[i][j] = Math.max(val + dp[i - 1][j - val], dp[i - 1][j]);
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int sum1 = dp[n][w];
        int sum2 = total - sum1;
        return sum1 == sum2;
    }
}
