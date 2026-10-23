
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        /*
         * STEP 1: Make sure array 'a' is the smaller array.
         *
         * a = smaller array
         * b = larger array
         *
         * WHY?
         * We perform binary search only on the smaller array.
         * This minimizes the number of binary search iterations.
         *
         * If m = a.length and n = b.length,
         * Time Complexity = O(log(min(m, n)))
         */
        int a[] = nums1;
        int b[] = nums2;

        if (a.length > b.length) {
            a = nums2;
            b = nums1;
        }

        /*
         * STEP 2: Calculate the total number of elements.
         *
         * Example:
         * a = [1, 3]
         * b = [2, 4, 5]
         *
         * total = 2 + 3 = 5
         */
        int total = a.length + b.length;

        /*
         * STEP 3: Calculate how many elements must be
         * present in the LEFT partition.
         *
         * Formula:
         * left = (total + 1) / 2
         *
         * Integer division in Java discards the decimal part.
         *
         * If total = 5:
         * left = (5 + 1) / 2 = 3
         *
         * If total = 6:
         * left = (6 + 1) / 2 = 3
         *
         * Therefore:
         * - Odd total: left partition has one extra element.
         * - Even total: both partitions have equal elements.
         *
         * This makes the median easy to identify.
         */
        int left = (total + 1) / 2;

        /*
         * STEP 4: Initialize binary search boundaries.
         *
         * l = smallest possible partition position in array a.
         * r = largest possible partition position in array a.
         *
         * A partition position represents how many elements
         * we take from array a for the LEFT partition.
         *
         * For a = [1, 3]:
         *
         * Partition positions:
         *
         * Position 0: [] | [1, 3]
         * Position 1: [1] | [3]
         * Position 2: [1, 3] | []
         *
         * Therefore:
         * l = 0
         * r = a.length
         *
         * Notice that mid1 is a count of elements,
         * NOT an array index.
         */
        int l = 0;
        int r = a.length;

        /*
         * STEP 5: Search for the correct partition.
         *
         * Continue while there are possible partition
         * positions to check.
         */
        while (l <= r) {

            /*
             * mid1 = number of elements taken from array a
             * for the LEFT partition.
             *
             * Formula:
             * mid1 = (l + r) / 2
             *
             * This is the binary search midpoint.
             */
            int mid1 = (l + r) / 2;

            /*
             * mid2 = number of elements taken from array b
             * for the LEFT partition.
             *
             * Formula:
             * mid1 + mid2 = left
             *
             * Therefore:
             * mid2 = left - mid1
             *
             * WHY?
             * The total number of elements in the left
             * partition must always equal 'left'.
             */
            int mid2 = left - mid1;

            /*
             * STEP 6: Find the four boundary elements.
             *
             * l1 = largest element on the LEFT of array a.
             * r1 = smallest element on the RIGHT of array a.
             *
             * l2 = largest element on the LEFT of array b.
             * r2 = smallest element on the RIGHT of array b.
             *
             * The names mean:
             * l1 = left boundary of a
             * r1 = right boundary of a
             * l2 = left boundary of b
             * r2 = right boundary of b
             *
             * Example:
             * a = [1, 3, 8]
             *
             * If mid1 = 2:
             *
             * [1, 3 | 8]
             *       ^
             *
             * l1 = a[mid1 - 1] = a[1] = 3
             * r1 = a[mid1]     = a[2] = 8
             *
             * Boundary elements let us check the partition
             * without merging the arrays.
             */

            /*
             * If mid1 == 0, no elements from a are on the left.
             * There is no actual l1 element.
             *
             * Use Integer.MIN_VALUE as a negative infinity
             * placeholder so it never blocks a valid partition.
             *
             * Otherwise, use the element immediately before
             * the partition.
             */
            int l1 = mid1 > 0
                    ? a[mid1 - 1]
                    : Integer.MIN_VALUE;

            /*
             * If mid1 == a.length, all elements of a are
             * on the left, so there is no actual r1 element.
             *
             * Use Integer.MAX_VALUE as a positive infinity
             * placeholder so it never blocks a valid partition.
             *
             * Otherwise, use the element at the partition.
             */
            int r1 = mid1 < a.length
                    ? a[mid1]
                    : Integer.MAX_VALUE;

            /*
             * Apply the same boundary logic to array b.
             *
             * If mid2 == 0, the left partition contains
             * no elements from b.
             */
            int l2 = mid2 > 0
                    ? b[mid2 - 1]
                    : Integer.MIN_VALUE;

            /*
             * If mid2 == b.length, the right partition
             * contains no elements from b.
             */
            int r2 = mid2 < b.length
                    ? b[mid2]
                    : Integer.MAX_VALUE;

            /*
             * STEP 7: Check whether the partition is correct.
             *
             * Condition:
             *
             * l1 <= r2 AND l2 <= r1
             *
             * Why are these two conditions sufficient?
             *
             * Each input array is already sorted, so within
             * each array the left elements are <= right elements.
             *
             * We only need to check the cross-array boundaries:
             *
             * Largest left element from a <= smallest right
             * element from b.
             *
             * Largest left element from b <= smallest right
             * element from a.
             *
             * If both are true, every element in the combined
             * LEFT partition is <= every element in the
             * combined RIGHT partition.
             *
             * We have found the correct partition!
             */
            if (l1 <= r2 && l2 <= r1) {

                /*
                 * STEP 8: Calculate the median.
                 *
                 * The largest element on the left is:
                 *
                 * max(l1, l2)
                 *
                 * The smallest element on the right is:
                 *
                 * min(r1, r2)
                 */

                /*
                 * CASE 1: Even number of elements.
                 *
                 * Example:
                 * [1, 2, 3, 4]
                 *
                 * Median = (2 + 3) / 2.0 = 2.5
                 *
                 * Formula:
                 * median = (max(l1, l2) + min(r1, r2)) / 2
                 *
                 * IMPORTANT:
                 * Convert to double BEFORE adding the two
                 * boundary values. This avoids integer overflow.
                 */
                if (total % 2 == 0) {
                    return ((double) Math.max(l1, l2)
                            + Math.min(r1, r2)) / 2.0;
                }

                /*
                 * CASE 2: Odd number of elements.
                 *
                 * Example:
                 * [1, 2, 3, 4, 5]
                 *
                 * Median = 3
                 *
                 * Because the left partition has one extra
                 * element, its largest element is the median.
                 *
                 * Formula:
                 * median = max(l1, l2)
                 */
                else {
                    return (double) Math.max(l1, l2);
                }

            /*
             * STEP 9: Correct the partition using binary search.
             *
             * CASE 1:
             * l1 > r2
             *
             * The largest left element from a is greater
             * than the smallest right element from b.
             *
             * Too many elements from a are on the left.
             * Move the partition in a to the LEFT.
             *
             * Binary search update:
             * r = mid1 - 1
             */
            } else if (l1 > r2) {
                r = mid1 - 1;
            }

            /*
             * CASE 2:
             *
             * If the partition was not valid and l1 > r2
             * was false, then l2 > r1 must be true.
             *
             * The largest left element from b is greater
             * than the smallest right element from a.
             *
             * We need more elements from a in the left
             * partition, so move the partition in a RIGHT.
             *
             * Binary search update:
             * l = mid1 + 1
             */
            else {
                l = mid1 + 1;
            }
        }

        /*
         * This return should never be reached for valid,
         * sorted input arrays.
         *
         * The problem guarantees sorted arrays, so a valid
         * partition always exists.
         */
        return -1;
    }
}
