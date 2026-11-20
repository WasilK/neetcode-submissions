
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        // Stores the final merged intervals.
        // We use List<int[]> because the number of intervals
        // in the answer may be different from the input.
        List<int[]> list = new ArrayList<>();

        int n = intervals.length;
        int i = 0;

        // PHASE 1: Add all intervals that come BEFORE newInterval.
        //
        // If the ending point of an interval is smaller than
        // the starting point of newInterval, there is no overlap.
        //
        // Since intervals are already sorted, we can directly
        // add these intervals without modifying them.
        while(i < n && intervals[i][1] < newInterval[0]){

            list.add(intervals[i]);
            i++;
        }

        // PHASE 2: Merge all overlapping intervals.
        //
        // An overlap exists when the starting point of the
        // current interval is <= ending point of newInterval.
        //
        // We use <= because intervals touching at a boundary
        // are also merged in this problem.
        //
        // For every overlapping interval:
        //
        // 1. Update the starting point to the minimum starting value.
        // 2. Update the ending point to the maximum ending value.
        //
        // This expands newInterval to cover all overlapping intervals.
        while(i < n && intervals[i][0] <= newInterval[1]){

            newInterval[0] = Math.min(
                newInterval[0], intervals[i][0]
            );

            newInterval[1] = Math.max(
                newInterval[1], intervals[i][1]
            );

            i++;
        }

        // After merging all overlapping intervals,
        // add the final merged interval to the result.
        list.add(newInterval);

        // PHASE 3: Add all remaining intervals.
        //
        // These intervals come AFTER newInterval and
        // cannot overlap with it because the input is sorted.
        //
        // Therefore, we can directly add them.
        while(i < n){

            list.add(intervals[i]);
            i++;
        }

        // Convert List<int[]> into int[][] because
        // the method expects a 2D array as its return type.
        return list.toArray(new int[list.size()][]);
    }
}
