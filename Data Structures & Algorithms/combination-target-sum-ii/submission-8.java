
class Solution {

    // Stores all unique combinations whose sum equals target.
    // Each inner List<Integer> represents one valid combination.
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // Stores the combination we are currently building.
        // Example: [1, 2, 5]
        List<Integer> subset = new ArrayList<>();

        // WHY DO WE SORT?
        // 1. Equal values become adjacent, making duplicates easy to skip.
        // 2. Sorting enables optimizations because smaller values come first.
        //
        // Example:
        // [9, 2, 2, 4, 6, 1, 5]
        // becomes
        // [1, 2, 2, 4, 5, 6, 9]
        Arrays.sort(candidates);

        // Start exploring from index 0.
        // Initially, subset is empty and target is the remaining sum.
        backtrack(candidates, target, 0, subset);

        return res;
    }

    public void backtrack(int[] arr, int target, int idx,
                          List<Integer> subset) {

        // BASE CASE 1: We found a valid combination.
        //
        // target represents the remaining sum we need.
        // If target == 0, the selected elements add up to the
        // original target.
        //
        // IMPORTANT: Make a COPY of subset.
        // subset will be modified later during backtracking.
        // Without copying, previously stored answers could change.
        if (target == 0) {
            res.add(new ArrayList<>(subset));
            return;
        }

        // BASE CASE 2: Stop an invalid or exhausted branch.
        //
        // target < 0:
        // The selected elements have exceeded the target.
        // Since candidates are positive, adding more cannot help.
        //
        // idx >= arr.length:
        // There are no more elements available to select.
        if (target < 0 || idx >= arr.length) {
            return;
        }

        // -----------------------------------------
        // CHOICE 1: PICK THE CURRENT ELEMENT
        // -----------------------------------------

        // Add the current element to our combination.
        subset.add(arr[idx]);

        // Reduce the remaining target by the selected value.
        //
        // idx + 1 is important:
        // Each array element can be used only ONCE.
        //
        // Example:
        // If we pick arr[0], the next call starts at index 1,
        // so arr[0] cannot be reused.
        backtrack(arr, target - arr[idx], idx + 1, subset);

        // BACKTRACK: UNDO OUR PICK.
        //
        // Remove the last selected element to restore subset
        // to its previous state before exploring another choice.
        //
        // Example:
        // [1, 2] becomes [1] after removing 2.
        subset.remove(subset.size() - 1);

        // -----------------------------------------
        // CHOICE 2: SKIP THE CURRENT ELEMENT
        // -----------------------------------------

        // WHY DO WE NEED THIS WHILE LOOP?
        //
        // Combination Sum II must return UNIQUE combinations.
        // If the array contains duplicate values, skipping one
        // occurrence and then exploring another identical
        // occurrence can generate the same combination again.
        //
        // Sorting places duplicate values next to each other,
        // so we can skip all consecutive copies together.
        //
        // idx < arr.length - 1:
        // Ensures idx + 1 is a valid array index.
        //
        // arr[idx] == arr[idx + 1]:
        // Checks whether the next element has the same value.
        //
        // Keep incrementing idx while the values are equal.
        while (idx < arr.length - 1 && arr[idx] == arr[idx + 1]) {
            idx++;
        }

        // Skip the current value and all its adjacent duplicates.
        //
        // idx + 1 moves to the next DISTINCT value.
        //
        // Notice that subset and target are unchanged:
        // We did not select any element in this branch.
        backtrack(arr, target, idx + 1, subset);
    }
}
