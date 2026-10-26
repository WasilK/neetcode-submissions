
public class Solution {

    public int subsetXORSum(int[] nums) {

        // Start from index 0 with XOR total = 0.
        // Initially, we have selected no elements.
        return dfs(nums, 0, 0);
    }

    private int dfs(int[] nums, int i, int total) {

        // BASE CASE:
        // We have considered every element.
        // 'total' is the XOR value of the current subset.
        if (i == nums.length) {
            return total;
        }

        // CHOICE 1: INCLUDE nums[i]
        // XOR the current number into total and move to next index.
        int include = dfs(nums, i + 1, total ^ nums[i]);

        // CHOICE 2: EXCLUDE nums[i]
        // Keep total unchanged and move to next index.
        int exclude = dfs(nums, i + 1, total);

        // Add the XOR sums from both sets of possibilities.
        return include + exclude;
    }
}
