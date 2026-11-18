class Solution {
    public int maxProfit(int[] prices) {
        int dp[][] = new int[prices.length][2];
        return func(0, 1, prices, dp);
    }
    int func(int idx, int buy, int prices[], int dp[][]){
        if(idx >= prices.length){
            return 0;
        }
        if(dp[idx][buy] != 0){
            return dp[idx][buy];
        }
        if(buy == 1){
            return dp[idx][buy] = Math.max(
                -prices[idx] + func(idx + 1, 0, prices, dp),
                func(idx + 1, 1, prices, dp)
            );
        }else{
            return dp[idx][buy] = Math.max(
                prices[idx] + func(idx + 2, 1, prices, dp),
                func(idx + 1, 0, prices, dp)
            );
        }
    }
}
