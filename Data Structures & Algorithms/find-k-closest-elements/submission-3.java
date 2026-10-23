class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        // We are NOT searching for x.
        // We are searching for the starting index of the window
        // of size k that contains the k closest elements.

        // Example:
        // arr = [1,2,3,4,5], k = 4
        //
        // Possible windows:
        // [1,2,3,4] -> start = 0
        // [2,3,4,5] -> start = 1
        //
        // Since the window size is fixed (k),
        // the last possible starting index is arr.length - k.

        int l = 0;
        int r = arr.length - k;

        // Binary search over all possible window starting positions.
        while (l < r) {

            // Middle starting index
            int mid = (l + r) / 2;

            /*
             We compare TWO possible windows:

             Window 1 starts at mid
             [arr[mid] ........ arr[mid+k-1]]

             Window 2 starts at mid+1
             [arr[mid+1] ...... arr[mid+k]]

             Notice that these two windows overlap.
             Only two elements are different:

             Window1 contains arr[mid]
             Window2 contains arr[mid+k]

             Every other element is common.

             Therefore, to decide which window is better,
             we only need to compare these two different elements.
            */

            /*
             Distance of left element from x

             x - arr[mid]
            */
            /*
             Distance of right element from x

             arr[mid+k] - x
            */

            if (x - arr[mid] > arr[mid + k] - x) {

                /*
                 Left element is farther from x than
                 the new right element.

                 Therefore,
                 Window starting at mid+1 is better.

                 So discard the left half including mid.

                 Move search to the right.
                */
                l = mid + 1;

            } else {

                /*
                 Left element is closer
                 OR both distances are equal.

                 When equal,
                 problem statement says choose smaller elements,
                 so we keep the left window.

                 Hence answer could still be mid,
                 so do NOT remove mid.

                 Move right boundary to mid.
                */
                r = mid;
            }
        }

        /*
         After binary search,
         l == r

         This is the starting index of the optimal window.
        */

        List<Integer> res = new ArrayList<>();

        // Collect the k elements of the best window.
        for (int i = l; i < l + k; i++) {
            res.add(arr[i]);
        }

        return res;
    }
}