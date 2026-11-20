
class Solution {
    public int[][] merge(int[][] intervals) {

        List<int[]> list = new ArrayList<>();

        // Edge case: no intervals
        if (intervals.length == 0) {
            return new int[0][];
        }

        // Step 1: Sort intervals based on starting point.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Start with the first interval.
        int[] current = intervals[0];

        // Step 3: Traverse the remaining intervals.
        for (int i = 1; i < intervals.length; i++) {

            // If current interval overlaps with the next interval
            if (intervals[i][0] <= current[1]) {

                // Merge them by extending the ending point.
                current[1] = Math.max(current[1], intervals[i][1]);

            } else {

                // No overlap: add the completed interval.
                list.add(current);

                // Start processing the next interval.
                current = intervals[i];
            }
        }

        // Add the last interval.
        list.add(current);

        return list.toArray(new int[list.size()][]);
    }
}
