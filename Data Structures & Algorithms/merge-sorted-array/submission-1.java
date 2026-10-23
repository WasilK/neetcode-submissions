class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Approach:
        // Since nums1 already has enough extra space at the end to hold nums2,
        // we merge the arrays from the BACK instead of the FRONT.
        //
        // Why from the back?
        // If we start filling from the front, we may overwrite elements of nums1
        // that we haven't compared yet.
        // By starting from the end, every position we fill is an empty/unused slot,
        // so no useful data is lost.
        //
        // Time Complexity : O(m + n)
        // Space Complexity: O(1)

        // k -> Index where the next largest element should be placed.
        int k = m + n - 1;

        // i -> Last valid element of nums1.
        // j -> Last element of nums2.
        int i = m - 1, j = n - 1;

        // Continue until every element of nums2 has been placed.
        // If nums2 becomes empty, nums1 is already in the correct position.
        while (j >= 0) {

            // If nums1 still has elements left and its current element
            // is larger than nums2's current element,
            // place nums1[i] at the end.
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            }

            // Otherwise, nums2[j] is greater (or nums1 has no elements left),
            // so place nums2[j] at the end.
            else {
                nums1[k--] = nums2[j--];
            }
        }

        // No need to copy remaining elements of nums1.
        // They are already in their correct positions.
    }
}