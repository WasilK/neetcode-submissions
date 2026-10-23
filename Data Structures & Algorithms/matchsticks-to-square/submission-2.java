


class Solution {
    public boolean makesquare(int[] matchsticks) {

        // Step 1: Calculate the total length of all matchsticks.
        int sum = Arrays.stream(matchsticks).sum();

        // Four equal sides are possible only if the total
        // length is divisible by 4.
        if (sum % 4 != 0) return false;

        // Each side must have this target length.
        int length = sum / 4;

        // Store the current length of each of the four sides.
        // Initially, all sides are empty: [0, 0, 0, 0].
        int sides[] = new int[4];

        // Sort matchsticks in ascending order, then reverse
        // to get descending order (largest stick first).
        // Placing large sticks first helps us reject
        // impossible arrangements earlier.
        Arrays.sort(matchsticks);
        reverse(matchsticks);

        // Start backtracking from the first matchstick (index 0).
        return backtrack(matchsticks, sides, 0, length);
    }

    boolean backtrack(int arr[], int sides[], int idx, int length) {

        // BASE CASE:
        // If every matchstick has been placed without exceeding
        // the target length, we have found a valid square.
        //
        // Why is this enough?
        // The total sum is exactly 4 * length, and each side
        // is at most length. Therefore, all four sides must
        // have length == length.
        if (idx == arr.length) {
            return true;
        }

        // Try placing the current matchstick on each of
        // the four sides.
        for (int i = 0; i < 4; i++) {

            // PRUNING:
            // Place the stick only if the side will not
            // exceed the required target length.
            if (sides[i] + arr[idx] <= length) {

                // CHOOSE:
                // Add the current matchstick to side i.
                sides[i] += arr[idx];

                // EXPLORE:
                // Recursively try placing the next matchstick.
                if (backtrack(arr, sides, idx + 1, length)) {
                    // A valid arrangement was found.
                    // No need to explore other possibilities.
                    return true;
                }

                // UNDO / BACKTRACK:
                // This placement did not lead to a solution.
                // Remove the stick to restore the previous state
                // before trying another side.
                sides[i] -= arr[idx];
            }

            // SYMMETRY PRUNING:
            // If side i is empty and placing the current stick
            // there failed, trying another empty side would
            // create an equivalent situation.
            // So stop trying other sides for this stick.
            if (sides[i] == 0) break;
        }

        // All four sides were tried, but none led to a solution.
        return false;
    }

    void reverse(int arr[]) {

        // Reverse the sorted array using two pointers.
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {

            // Swap the leftmost and rightmost elements.
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // Move both pointers toward the center.
            l++;
            r--;
        }
    }
}
