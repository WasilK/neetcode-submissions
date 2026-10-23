class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        // Total number of elements
        int n = nums.length;

        // There will be (n-k+1) windows in total.
        // Example:
        // nums = [1,3,-1,-3,5], k = 3
        // Windows:
        // [1,3,-1]
        // [3,-1,-3]
        // [-1,-3,5]
        // Total = 5-3+1 = 3 windows
        int[] res = new int[n - k + 1];

        /*
         * Max Heap
         *
         * Each element is stored as:
         * {value, index}
         *
         * Why store index?
         * ----------------
         * Because while sliding the window, we need to know
         * whether an element is still inside the current window.
         *
         * Example:
         * nums = [1,3,-1,-3,5]
         *
         * If we only stored values:
         *      5
         *      3
         *      1
         *
         * We would never know whether 3 belongs to the current
         * window or an old window.
         *
         * Therefore we store:
         * {5,4}
         * {3,1}
         * {1,0}
         */
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Index used for storing answers
        int idx = 0;

        // Traverse every element
        for (int i = 0; i < n; i++) {

            // Insert current number along with its index
            pq.offer(new int[]{nums[i], i});

            /*
             * We can calculate the maximum only after the first
             * complete window has formed.
             *
             * Example:
             *
             * k = 3
             *
             * i = 0 -> only 1 element
             * i = 1 -> only 2 elements
             * i = 2 -> now first complete window
             *
             * Therefore:
             *
             * first window finishes at
             * i = k-1
             */
            if (i >= k - 1) {

                /*
                 * Remove all expired elements.
                 *
                 * --------------------------------------------------
                 * WHY i-k ?
                 * --------------------------------------------------
                 *
                 * Current window is:
                 *
                 * [ i-k+1 .......... i ]
                 *
                 * Example:
                 *
                 * i = 5
                 * k = 3
                 *
                 * Current window:
                 *
                 * [3,4,5]
                 *
                 * Valid indices:
                 * 3 4 5
                 *
                 * Expired indices:
                 * 0 1 2
                 *
                 * Notice:
                 *
                 * last expired index = 2
                 *
                 * and
                 *
                 * i-k
                 * = 5-3
                 * = 2
                 *
                 * Therefore every expired element satisfies
                 *
                 * index <= i-k
                 *
                 * NOT index <= i-k+1
                 *
                 * Because i-k+1 is actually the FIRST VALID INDEX.
                 *
                 * If we remove <= i-k+1,
                 * we'd remove valid elements too.
                 *
                 * Example:
                 *
                 * Window = [3,4,5]
                 *
                 * index 3 is inside the window.
                 *
                 * If condition becomes:
                 *
                 * index <= 3
                 *
                 * then index 3 would also be removed,
                 * which is WRONG.
                 */

                while (pq.peek()[1] <= i - k) {

                    /*
                     * Why WHILE and not IF?
                     *
                     * Heap may contain multiple expired elements.
                     *
                     * Example:
                     *
                     *          (9,1)
                     *         /     \
                     *     (8,2)   (7,3)
                     *       |
                     *     (6,7)
                     *
                     * Suppose current window starts from index 4.
                     *
                     * Expired:
                     * (9,1)
                     * (8,2)
                     * (7,3)
                     *
                     * Valid:
                     * (6,7)
                     *
                     * If we use IF,
                     * only (9,1) is removed.
                     *
                     * Heap top becomes
                     * (8,2)
                     *
                     * Still expired.
                     *
                     * Therefore we must keep removing until
                     * the top becomes valid.
                     */
                    pq.poll();
                }

                /*
                 * After removing all expired elements,
                 * heap top is guaranteed to be:
                 *
                 * 1. Maximum element
                 * 2. Inside current window
                 *
                 * Therefore it is the answer.
                 */
                res[idx++] = pq.peek()[0];

                /*
                 * Equivalent to:
                 *
                 * res[idx] = pq.peek()[0];
                 * idx++;
                 */
            }
        }

        return res;
    }
}