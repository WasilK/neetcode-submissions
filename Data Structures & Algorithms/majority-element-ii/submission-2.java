class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // We can have at most two elements which have occurences greater than n / 3.
        // Stores the final answer.
        List<Integer> res = new ArrayList<>();

        /*
         * This map stores the CURRENT CANDIDATES and their "votes".
         *
         * IMPORTANT:
         * These are NOT the actual frequencies.
         * They are just the remaining votes after cancellation.
         */
        HashMap<Integer, Integer> count = new HashMap<>();

        // ---------------- FIRST PASS ----------------
        // Find possible majority candidates.
        for (int num : nums) {

            // Increase the vote of the current number.
            count.put(num, count.getOrDefault(num, 0) + 1);

            /*
             * At most TWO numbers can appear more than n/3 times.
             *
             * If we ever have THREE different candidates,
             * cancel one vote from all of them.
             */
            if (count.size() > 2) {

                // Store surviving candidates after cancellation.
                HashMap<Integer, Integer> newCount = new HashMap<>();

                for (int key : count.keySet()) {

                    /*
                     * Decrease every candidate's vote by one.
                     *
                     * If its vote becomes zero,
                     * remove it completely.
                     *
                     * Why?
                     * We are cancelling one occurrence of
                     * three different numbers.
                     */
                    if (count.get(key) > 1) {
                        newCount.put(key, count.get(key) - 1);
                    }
                }

                // Replace old map with surviving candidates.
                count = newCount;
            }
        }

        // ---------------- SECOND PASS ----------------
        // The remaining keys are only POSSIBLE candidates.
        // Verify their real frequencies.
        for (int key : count.keySet()) {

            int freq = 0;

            // Count actual occurrences in the original array.
            for (int num : nums) {
                if (num == key)
                    freq++;
            }

            // Add only if it truly appears more than n/3 times.
            if (freq > nums.length / 3) {
                res.add(key);
            }
        }

        return res;
    }
}