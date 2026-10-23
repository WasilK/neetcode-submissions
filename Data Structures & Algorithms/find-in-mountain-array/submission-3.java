/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int len = mountainArr.length();

        // --------------------------------------------------
        // 1. Find the peak of the mountain
        // --------------------------------------------------

        int st = 0;
        int en = len - 1;

        while (st < en) {

            int m = st + (en - st) / 2;

            if (mountainArr.get(m) < mountainArr.get(m + 1)) {

                // We are on the increasing side.
                // Therefore, the peak must be on the right.
                st = m + 1;

            } else {

                // We are on the decreasing side.
                // Peak is at m or somewhere on the left.
                en = m;
            }
        }

        // When st == en, we have found the peak.
        int peak = st;


        // --------------------------------------------------
        // 2. Search in the increasing left half
        // --------------------------------------------------

        st = 0;
        en = peak;

        while (st <= en) {

            int m = st + (en - st) / 2;

            int value = mountainArr.get(m);

            if (value == target) {
                return m;
            }

            if (value < target) {

                // Target is bigger,
                // so move to the right.
                st = m + 1;

            } else {

                // Target is smaller,
                // so move to the left.
                en = m - 1;
            }
        }


        // --------------------------------------------------
        // 3. Search in the decreasing right half
        // --------------------------------------------------

        st = peak + 1;
        en = len - 1;

        while (st <= en) {

            int m = st + (en - st) / 2;

            int value = mountainArr.get(m);

            if (value == target) {
                return m;
            }

            if (value > target) {

                // Since this side is decreasing,
                // smaller values are on the right.
                st = m + 1;

            } else {

                // Target is bigger,
                // so move to the left.
                en = m - 1;
            }
        }

        // Target does not exist.
        return -1;
    }
}