
class Solution {

    // Stores all unique combinations whose sum equals the original target.
    // Each inner list represents one valid combination.
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // curr stores the combination we are currently building.
        // Example: [1, 2, 5]
        List<Integer> curr = new ArrayList<>();

        // WHY SORT THE ARRAY?
        //
        // 1. Equal values become adjacent, so we can easily skip duplicates.
        // 2. Smaller values come first, allowing us to stop when a value
        //    exceeds the remaining target.
        //
        // Example:
        // [9, 2, 2, 4, 6, 1, 5]
        // becomes [1, 2, 2, 4, 5, 6, 9]
        Arrays.sort(candidates);

        // Start backtracking at index 0.
        //
        // idx = 0 means we can consider candidates starting from index 0.
        // target is the remaining sum needed.
        // curr is initially empty.
        backtrack(candidates, 0, target, curr);

        return res;
    }

    public void backtrack(int[] arr, int idx, int target,
                          List<Integer> curr) {

        // BASE CASE:
        // If target becomes 0, the sum of selected elements equals
        // the original target, so we have found a valid combination.
        if (target == 0) {

            // Make a copy because curr will change during backtracking.
            // Without copying, stored answers could be modified later.
            res.add(new ArrayList<>(curr));

            // Stop this recursive call because the target is reached.
            return;
        }

        // PICK / NOT-PICK APPROACH USING A FOR LOOP
        //
        // Explore every candidate starting from idx.
        //
        // idx = starting index of this recursive call.
        // i   = the candidate currently being considered.
        for (int i = idx; i < arr.length; i++) {

            // SKIP DUPLICATES AT THE SAME RECURSION LEVEL.
            //
            // i > idx ensures we only skip a duplicate when another
            // occurrence has already been considered at this level.
            //
            // arr[i] == arr[i - 1] checks whether the current value
            // is the same as the previous value.
            //
            // Because the array is sorted, duplicates are adjacent.
            //
            // Example: [1, 2, 2, 4]
            // If i points to the second 2 at this level, skip it.
            if (i > idx && arr[i] == arr[i - 1]) {
                continue;
            }

            // PRUNING:
            //
            // If the current value exceeds the remaining target,
            // no later value can help because the array is sorted
            // and all candidates are positive.
            //
            // Therefore, stop the loop instead of exploring
            // unnecessary recursive calls.
            if (arr[i] > target) {
                break;
            }

            // CHOICE 1: PICK THE CURRENT CANDIDATE.
            //
            // Add arr[i], NOT arr[idx], because i is the index
            // currently being explored by the for loop.
            curr.add(arr[i]);

            // RECURSION:
            //
            // i + 1: Move to the next index so the same array element
            //        cannot be reused.
            //
            // target - arr[i]: Subtract the selected value from
            //                  the remaining target.
            //
            // curr: Pass the current combination to the next call.
            backtrack(arr, i + 1, target - arr[i], curr);

            // BACKTRACK:
            //
            // Remove the element we just picked.
            // This restores curr to its previous state so the next
            // loop iteration can explore a different combination.
            curr.remove(curr.size() - 1);

            // The next iteration of the loop considers another
            // candidate at this same recursion level.
        }
    }
}
