
public class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        // res stores ALL the subsets we discover.
        // Example: [[1, 2], [1], [2], []]
        List<List<Integer>> res = new ArrayList<>();

        // subset represents the CURRENT subset being constructed.
        // We keep adding and removing elements from this same list.
        List<Integer> subset = new ArrayList<>();

        // Start DFS from index 0.
        // Initially, no elements have been selected.
        //
        // For nums = [1, 2, 3]:
        // dfs(nums, 0, [], res)
        //
        // At every index, we make two decisions:
        // 1. Include the current element.
        // 2. Exclude the current element.
        dfs(nums, 0, subset, res);

        // Once every decision path has been explored,
        // return all 2^n subsets.
        return res;
    }

    private void dfs(
            int[] nums,
            int i,
            List<Integer> subset,
            List<List<Integer>> res) {

        // =========================================
        // STEP 1: BASE CASE
        // =========================================
        //
        // i represents the index of the element we are deciding about.
        //
        // For nums = [1, 2, 3]:
        // i = 0 -> deciding about 1
        // i = 1 -> deciding about 2
        // i = 2 -> deciding about 3
        // i = 3 -> all elements have been considered
        //
        // Once i reaches nums.length, our current subset
        // is one complete answer.
        if (i >= nums.length) {

            // IMPORTANT:
            // Make a COPY of subset before adding it to res.
            //
            // subset is modified later during backtracking.
            // If we use res.add(subset), all saved answers
            // could refer to the same changing list.
            res.add(new ArrayList<>(subset));

            // Return to the previous recursive call.
            //
            // The previous call resumes from the line
            // immediately after its recursive call.
            return;
        }

        // =========================================
        // STEP 2: INCLUDE nums[i]
        // =========================================
        //
        // DECISION TREE:
        //
        //                     Current subset
        //                           |
        //                     /           \
        //                INCLUDE         EXCLUDE
        //
        // We explore INCLUDE first.
        //
        // Example:
        // subset = []
        // i = 0
        // nums[i] = 1
        //
        // After this statement:
        // subset = [1]
        subset.add(nums[i]);

        // =========================================
        // STEP 3: EXPLORE THE INCLUDE BRANCH
        // =========================================
        //
        // Move to the next index because we have already
        // decided whether to include the current element.
        //
        // Example:
        // dfs(nums, 1, [1], res)
        //
        // This recursive call explores ALL possibilities
        // that include 1 before we return here.
        //
        // Its execution is paused until the deeper calls
        // finish and return.
        dfs(nums, i + 1, subset, res);

        // =========================================
        // STEP 4: BACKTRACK
        // =========================================
        //
        // Once the INCLUDE branch is completely explored,
        // undo our previous choice.
        //
        // Remove the last element we added.
        //
        // Example:
        // Before removal: [1, 2, 3]
        // After removal:  [1, 2]
        //
        // Later, after the call for index 1 finishes,
        // removing 2 restores [1].
        //
        // This is BACKTRACKING:
        // make a choice -> explore -> undo the choice.
        subset.remove(subset.size() - 1);

        // =========================================
        // STEP 5: EXPLORE THE EXCLUDE BRANCH
        // =========================================
        //
        // The current element is now absent from subset.
        //
        // Example:
        // subset = [1, 2]
        // Current element = 3
        //
        // The INCLUDE branch already explored [1, 2, 3].
        // We removed 3, so subset is now [1, 2].
        //
        // This call explores [1, 2], where 3 is excluded.
        //
        // Notice:
        // We do NOT add nums[i] before this call.
        // That is how we exclude the current element.
        dfs(nums, i + 1, subset, res);

        // =========================================
        // STEP 6: RETURN TO THE PARENT CALL
        // =========================================
        //
        // After BOTH INCLUDE and EXCLUDE branches finish,
        // this dfs call is complete.
        //
        // Control returns to the caller, which either:
        // - backtracks another element, or
        // - finishes if it is the original call.
    }
}
