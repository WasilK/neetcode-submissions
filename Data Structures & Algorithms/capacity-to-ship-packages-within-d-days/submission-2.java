class Solution {

    public int shipWithinDays(int[] weights, int days) {

        // res will store the minimum valid shipping capacity
        int res = 0;

        // -------------------------------
        // Binary Search Range
        // -------------------------------

        // st = minimum possible capacity
        // Why?
        // The ship must be able to carry the heaviest package.
        //
        // Example:
        // weights = [1, 2, 3, 4, 5]
        //
        // If capacity = 4, we cannot ship the package of weight 5.
        //
        // Therefore, the minimum possible capacity
        // must be the maximum weight in the array.
        int st = 0;

        // en = maximum possible capacity
        // Why?
        // In the worst case, the ship could carry ALL packages
        // in a single day.
        //
        // Therefore, the maximum capacity we ever need
        // is the sum of all package weights.
        //
        // Example:
        // weights = [1, 2, 3, 4, 5]
        //
        // Total weight = 15
        //
        // Capacity = 15 means we can ship everything
        // in one day.
        int en = 0;


        // Find:
        // 1. Maximum package weight -> st
        // 2. Total weight of all packages -> en
        for (int weight : weights) {

            // Find the heaviest package.
            // This becomes the minimum possible capacity.
            st = Math.max(st, weight);

            // Add all package weights.
            // This becomes the maximum possible capacity.
            en += weight;
        }


        // ---------------------------------
        // Binary Search for Minimum Capacity
        // ---------------------------------

        while (st <= en) {

            // Try a capacity in the middle
            // of our current search range.
            int mid = st + (en - st) / 2;

            // Initially, we need at least 1 day.
            int daysNeeded = 1;

            // Weight currently loaded on the ship
            // for the current day.
            int currentLoad = 0;


            // ---------------------------------
            // Check how many days are required
            // if ship capacity = mid
            // ---------------------------------

            for (int weight : weights) {

                // If adding this package would exceed
                // the ship's capacity...
                if (weight + currentLoad > mid) {

                    // We cannot put this package
                    // on the current day.
                    //
                    // So we start a new day.
                    daysNeeded++;

                    // The current day starts with
                    // this package.
                    currentLoad = 0;
                }

                // Add the package to the current day's load.
                currentLoad += weight;
            }


            // ---------------------------------
            // Decide which half to search
            // ---------------------------------

            if (daysNeeded > days) {

                // We need MORE days than allowed.
                //
                // This means our capacity (mid)
                // is too SMALL.
                //
                // So increase the capacity.
                st = mid + 1;

            } else {

                // We can ship all packages within
                // the required number of days.
                //
                // Therefore, mid is a VALID capacity.
                //
                // But we want the MINIMUM possible capacity,
                // so try something smaller.
                res = mid;
                en = mid - 1;
            }
        }


        // Return the minimum capacity that allows
        // all packages to be shipped within 'days'.
        return res;
    }
}