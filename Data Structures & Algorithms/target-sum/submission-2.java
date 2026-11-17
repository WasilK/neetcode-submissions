
class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        // APPROACH:
        // Instead of assigning + or - signs directly to every element,
        // divide the array into two subsets:
        //
        // P = Sum of elements assigned a positive (+) sign
        // N = Sum of elements assigned a negative (-) sign
        //
        // Therefore:
        // P - N = target .......... (1)
        // P + N = sum ............. (2)
        //
        // Adding equations (1) and (2):
        // 2P = sum + target
        //
        // Hence:
        // P = (sum + target) / 2
        //
        // So, the problem becomes:
        // Count the number of subsets whose sum equals
        // (sum + target) / 2.
        //
        // This is a variation of the 0/1 Knapsack problem:
        // Count Subsets with Given Sum.

        // STEP 1: Calculate the total sum of the array.
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        // EDGE CASE 1:
        // The absolute value of target cannot exceed the total sum.
        //
        // Example:
        // nums = [1, 2, 3], sum = 6
        // A target of 8 or -8 is impossible.
        if (Math.abs(target) > sum) {
            return 0;
        }

        // EDGE CASE 2:
        // P = (sum + target) / 2 must be an integer.
        //
        // If (sum + target) is odd, no valid subset sum exists.
        if ((sum + target) % 2 != 0) {
            return 0;
        }

        // STEP 2: Convert Target Sum into Subset Sum.
        //
        // We now need to count the subsets whose sum
        // is equal to requiredSum.
        int requiredSum = (sum + target) / 2;

        int n = nums.length;

        // STEP 3: Create the DP table.
        //
        // dp[i][j] represents:
        // Number of ways to get sum j using the first i elements.
        //
        // Rows    = n + 1 (number of elements)
        // Columns = requiredSum + 1 (possible sums from 0 to requiredSum)
        //
        // We use 2D DP because each element can be selected only once.
        int[][] dp = new int[n + 1][requiredSum + 1];

        // STEP 4: BASE CASE
        //
        // dp[0][0] = 1 means:
        // There is exactly ONE way to make sum 0
        // using zero elements: select nothing (empty subset).
        //
        // All other dp[0][j] values are 0 because
        // we cannot form a positive sum without elements.
        dp[0][0] = 1;

        // STEP 5: Fill the DP table using tabulation.
        //
        // i represents the number of elements considered.
        // j represents the target subset sum we want to form.
        for (int i = 1; i <= n; i++) {

            for (int j = 0; j <= requiredSum; j++) {

                // CHOICE 1: EXCLUDE CURRENT ELEMENT
                //
                // We do not select nums[i - 1].
                //
                // The number of ways remains the same as
                // the previous row because we are not using
                // the current element.
                //
                // dp[i - 1][j] = Ways without selecting current element.
                dp[i][j] = dp[i - 1][j];

                // CHOICE 2: INCLUDE CURRENT ELEMENT
                //
                // We select nums[i - 1] in our subset.
                //
                // If we include this element, we need to
                // find the remaining sum:
                //
                // remainingSum = j - nums[i - 1]
                //
                // dp[i - 1][j - nums[i - 1]] gives the number
                // of ways to form that remaining sum.
                //
                // We use the previous row (i - 1) to ensure
                // that each element is used only once.
                if (j >= nums[i - 1]) {

                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }

                // FINAL DP TRANSITION:
                //
                // dp[i][j] =
                //      dp[i - 1][j]
                //      +
                //      dp[i - 1][j - nums[i - 1]]
                //
                // Total ways =
                // Ways excluding current element
                // +
                // Ways including current element.
                //
                // In the original Target Sum problem:
                // Exclude from positive subset = assign negative sign.
                // Include in positive subset = assign positive sign.
            }
        }

        // STEP 6: FINAL ANSWER
        //
        // dp[n][requiredSum] contains the number of subsets
        // whose sum equals requiredSum using all n elements.
        //
        // Every selected element gets a + sign.
        // Every remaining element gets a - sign.
        //
        // Therefore, this gives the number of valid
        // expressions that evaluate to target.
        return dp[n][requiredSum];
    }
}
