
class Solution {

    // Stores all the permutations we generate.
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        // curr = permutation we are currently building.
        List<Integer> curr = new ArrayList<>();

        // pick[i] tells us whether nums[i] is already
        // used in the current permutation.
        // Initially, every value is false.
        boolean[] pick = new boolean[nums.length];

        backtrack(nums, curr, pick);

        return res;
    }

    public void backtrack(int[] nums,
                          List<Integer> curr,
                          boolean[] pick) {

        // BASE CASE:
        // When curr contains all the numbers,
        // we have completed one permutation.
        if (curr.size() == nums.length) {

            // Store a copy because curr will change
            // during subsequent backtracking.
            res.add(new ArrayList<>(curr));
            return;
        }

        // Try every number as the next element.
        for (int i = 0; i < nums.length; i++) {

            // Only use a number if it hasn't already
            // been selected in the current permutation.
            if (!pick[i]) {

                // 1. CHOOSE:
                // Add this number to the current permutation.
                curr.add(nums[i]);

                // Mark this array position as used.
                pick[i] = true;

                // 2. EXPLORE:
                // Recursively choose the next element.
                backtrack(nums, curr, pick);

                // 3. UNDO:
                // Remove the last chosen number.
                curr.remove(curr.size() - 1);

                // Mark it as available again so it can
                // be used in a different permutation.
                pick[i] = false;
            }
        }
    }
}
