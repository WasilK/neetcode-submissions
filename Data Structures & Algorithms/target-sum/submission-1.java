class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int total = 0;
        for(int num : nums){
            total += num;
        }
        int sum = (total + target) / 2;
        if(Math.abs(target) > total){
            return 0;
        }
        if((total + target) % 2 != 0){
            return 0;
        }
        int dp[][] = new int[n + 1][sum + 1];
        dp[0][0] = 1;
        for(int i = 1; i < n + 1; i++){
            for(int j = 0; j < sum + 1; j++){
                int val = nums[i - 1];
                if(val <= j){
                    dp[i][j] = dp[i - 1][j - val] + dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }
}
