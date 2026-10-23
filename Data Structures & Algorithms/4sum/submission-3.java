class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        // Stores all unique quadruplets
        List<List<Integer>> res = new ArrayList<>();

        // Sorting is necessary because:
        // 1. Two pointer technique only works on sorted arrays.
        // 2. Makes skipping duplicates easy.
        Arrays.sort(nums);

        // First number of the quadruplet
        for (int i = 0; i < nums.length; i++) {

            // Skip duplicate values for i.
            // If current value is same as previous,
            // it would generate the same quadruplets again.
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Second number of the quadruplet
            for (int j = i + 1; j < nums.length; j++) {

                // Skip duplicate values for j.
                // We compare only within the current i.
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                // Remaining two numbers will be found
                // using the Two Pointer approach.
                int p = j + 1;
                int q = nums.length - 1;

                while (p < q) {

                    // Use long because adding four large integers
                    // may overflow an int.
                    long sum = (long) nums[i] + nums[j] + nums[p] + nums[q];

                    // Quadruplet found
                    if (sum == target) {

                        res.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));

                        // Move both pointers because
                        // current combination has already been used.
                        p++;
                        q--;

                        // Skip duplicate values for p.
                        // If next value is same as previous,
                        // it would produce the same quadruplet.
                        while (p < q && nums[p] == nums[p - 1])
                            p++;

                        // Standard implementation also skips duplicates
                        // from the right pointer.
                        // It is not strictly required for correctness
                        // if left duplicates are handled, but it avoids
                        // unnecessary comparisons.
                        //
                        // while (p < q && nums[q] == nums[q + 1])
                        //     q--;

                    }
                    // Sum is too large.
                    // Since array is sorted,
                    // move right pointer left to decrease the sum.
                    else if (sum > target) {
                        q--;
                    }
                    // Sum is too small.
                    // Move left pointer right to increase the sum.
                    else {
                        p++;
                    }
                }
            }
        }

        // Return all unique quadruplets.
        return res;
    }
}