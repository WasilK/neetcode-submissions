class Solution {
    public int maxProfit(int[] prices) {

        // Approach (Greedy):
        // We don't try to find the lowest buying price and highest selling price.
        // Instead, whenever today's price is higher than yesterday's,
        // we treat that increase as profit.
        //
        // Why?
        // Example:
        // Prices: 1 -> 2 -> 3 -> 4 -> 5
        //
        // Buying at 1 and selling at 5 gives:
        // Profit = 5 - 1 = 4
        //
        // Adding every increase also gives:
        // (2-1) + (3-2) + (4-3) + (5-4) = 4
        //
        // So collecting every positive difference is equivalent to
        // buying at the start of an increasing sequence and selling
        // at the end of it.
        //
        // Whenever the price falls, we simply don't add anything.
        // Conceptually, it's like selling before the drop and buying
        // again at the lower price.

        int profit = 0;

        // Start from the second day so we can compare it with the previous day.
        for (int i = 1; i < prices.length; i++) {

            // If today's price is higher than yesterday's,
            // this increase contributes to the maximum profit.
            if (prices[i] > prices[i - 1]) {

                // Add only the positive difference.
                profit += prices[i] - prices[i - 1];
            }
        }

        // Return the total profit earned from all increasing segments.
        return profit;
    }
}