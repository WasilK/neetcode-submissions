class Solution {
    public int maxProfit(int[] prices) {
        int buyPrice = Integer.MAX_VALUE;
        int maxP = 0;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] < buyPrice){
                buyPrice = prices[i];
            }
            int profit = prices[i] - buyPrice;
            maxP = Math.max(profit, maxP);
        }
        return maxP;
    }
}
