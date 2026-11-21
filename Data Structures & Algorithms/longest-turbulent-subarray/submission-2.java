
class Solution {
    public int maxTurbulenceSize(int[] arr) {

        /*
         * PROBLEM:
         * Find the longest contiguous subarray where adjacent
         * comparisons alternate between greater and smaller.
         *
         * Example:
         * arr = [9, 4, 2, 10, 7, 8]
         *
         * Comparisons:
         * 9 > 4 > 2 < 10 > 7 < 8
         *
         * A turbulent subarray must follow either:
         *
         * Pattern 1: a < b > c < d
         * Pattern 2: a > b < c > d
         *
         * Answer = 5
         * Subarray = [4, 2, 10, 7, 8]
         *
         *
         * APPROACH: TWO-STATE DYNAMIC PROGRAMMING
         *
         * OBSERVATION:
         * We can extend a turbulent subarray only when the
         * current comparison is opposite to the previous one.
         *
         * Therefore, we maintain two states:
         *
         * odd:
         * Length of the longest turbulent subarray ending at
         * the current index whose last comparison is increasing.
         *
         * even:
         * Length of the longest turbulent subarray ending at
         * the current index whose last comparison is decreasing.
         *
         * Both start at 1 because a single element is always
         * a valid turbulent subarray.
         */

        int odd = 1;
        int even = 1;

        // Stores the maximum turbulent subarray length
        // encountered anywhere in the array.
        int maxSize = 1;

        /*
         * Start from index 1 because we compare the current
         * element with the previous element (i - 1).
         */
        for (int i = 1; i < arr.length; i++) {

            /*
             * CASE 1: CURRENT ELEMENT IS GREATER
             *
             * arr[i] > arr[i - 1]
             *
             * Current comparison is INCREASING.
             *
             * To maintain turbulence, the previous comparison
             * must have been DECREASING.
             *
             * Therefore, extend the previous even state.
             *
             * Example:
             * [9, 4, 10]
             *
             * Comparisons:
             * 9 > 4 < 10
             *
             * Previous decreasing subarray = [9, 4]
             * Its length = 2
             *
             * We add 10, so new length = 2 + 1 = 3.
             *
             * Hence:
             * odd = even + 1;
             *
             * We reset even to 1 because the current comparison
             * is increasing, not decreasing.
             */

            if (arr[i] > arr[i - 1]) {

                odd = even + 1;
                even = 1;

            }

            /*
             * CASE 2: CURRENT ELEMENT IS SMALLER
             *
             * arr[i] < arr[i - 1]
             *
             * Current comparison is DECREASING.
             *
             * To maintain turbulence, the previous comparison
             * must have been INCREASING.
             *
             * Therefore, extend the previous odd state.
             *
             * Example:
             * [9, 4, 10, 7]
             *
             * Comparisons:
             * 9 > 4 < 10 > 7
             *
             * Previous increasing-ending subarray:
             * [9, 4, 10]
             *
             * Its length = 3
             *
             * We add 7, so new length = 3 + 1 = 4.
             *
             * Hence:
             * even = odd + 1;
             *
             * We reset odd to 1 because the current comparison
             * is decreasing, not increasing.
             */

            else if (arr[i] < arr[i - 1]) {

                even = odd + 1;
                odd = 1;

            }

            /*
             * CASE 3: BOTH ELEMENTS ARE EQUAL
             *
             * arr[i] == arr[i - 1]
             *
             * Example:
             * [9, 4, 4, 10]
             *
             * Comparisons:
             * 9 > 4 = 4 < 10
             *
             * Equality breaks the turbulent pattern.
             *
             * Neither state can be extended.
             * Both states restart from length 1.
             */

            else {

                odd = 1;
                even = 1;

            }

            /*
             * UPDATE THE GLOBAL ANSWER
             *
             * At every index, we have two possible turbulent
             * subarray lengths:
             *
             * 1. odd  -> Last comparison was increasing.
             * 2. even -> Last comparison was decreasing.
             *
             * Take the larger of these two states and compare
             * it with the best answer found so far.
             */

            maxSize = Math.max(maxSize, Math.max(odd, even));
        }

        return maxSize;
    }
}


/*
 * DRY RUN:
 *
 * arr = [9, 4, 2, 10, 7, 8]
 *
 * Initially:
 * odd = 1, even = 1, maxSize = 1
 *
 * ------------------------------------------------
 * i = 1: 9 > 4
 *
 * even = odd + 1 = 2
 * odd = 1
 *
 * maxSize = 2
 *
 * ------------------------------------------------
 * i = 2: 4 > 2
 *
 * even = odd + 1 = 2
 * odd = 1
 *
 * maxSize = 2
 *
 * Notice: Two consecutive decreasing comparisons
 * cannot belong to the same turbulent subarray.
 *
 * ------------------------------------------------
 * i = 3: 2 < 10
 *
 * odd = even + 1 = 3
 * even = 1
 *
 * maxSize = 3
 *
 * Turbulent subarray: [4, 2, 10]
 *
 * ------------------------------------------------
 * i = 4: 10 > 7
 *
 * even = odd + 1 = 4
 * odd = 1
 *
 * maxSize = 4
 *
 * Turbulent subarray: [4, 2, 10, 7]
 *
 * ------------------------------------------------
 * i = 5: 7 < 8
 *
 * odd = even + 1 = 5
 * even = 1
 *
 * maxSize = 5
 *
 * Turbulent subarray: [4, 2, 10, 7, 8]
 *
 * ------------------------------------------------
 *
 * FINAL ANSWER = 5
 *
 *
 * COMPLEXITY ANALYSIS:
 *
 * Time Complexity: O(n)
 * We traverse the array exactly once.
 *
 * Space Complexity: O(1)
 * We only maintain three variables:
 * odd, even and maxSize.
 */
