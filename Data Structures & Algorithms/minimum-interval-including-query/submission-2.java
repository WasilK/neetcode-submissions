
class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {

        // Stores the final answer for each query.
        // We use the original query index to place answers correctly.
        int res[] = new int[queries.length];

        // STEP 1: Sort intervals by their starting point.
        // This allows us to process intervals in increasing order
        // of their starting points as we move through sorted queries.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // STEP 2: Store each query along with its original index.
        // arr[i][0] = query value
        // arr[i][1] = original index of that query
        //
        // We need the original index because we will sort queries,
        // but our final answer must follow the original query order.
        int arr[][] = new int[queries.length][2];

        for(int i = 0; i < queries.length; i++){
            arr[i][0] = queries[i];
            arr[i][1] = i;
        }

        // STEP 3: Sort queries in increasing order.
        // Processing queries in sorted order allows us to
        // add intervals only once instead of checking all intervals
        // separately for every query.
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // STEP 4: Create a Min Heap.
        //
        // Each heap element contains:
        // [0] = interval length
        // [1] = interval ending point
        //
        // The heap is sorted by interval length.
        // Therefore, the smallest interval is always at the top.
        PriorityQueue<int []> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Points to the next interval that we haven't processed yet.
        int j = 0;

        // STEP 5: Process queries in sorted order.
        for(int i = 0; i < arr.length; i++){

            // Current query value.
            int curr = arr[i][0];

            // Original index of the current query.
            int idx = arr[i][1];

            // STEP 6: Add all intervals whose starting point
            // is less than or equal to the current query.
            //
            // Such intervals have the possibility of containing
            // the current query.
            //
            // We calculate their lengths and insert them into
            // the Min Heap.
            while(j < intervals.length && intervals[j][0] <= curr){

                int len = intervals[j][1] - intervals[j][0] + 1;

                // Store interval length and ending point.
                pq.offer(new int[]{len, intervals[j][1]});

                // Move to the next unprocessed interval.
                j++;
            }

            // STEP 7: Remove expired intervals from the heap.
            //
            // If an interval's ending point is smaller than
            // the current query, it cannot contain this query.
            //
            // We only need to remove expired intervals from
            // the top because the heap is ordered by length.
            //
            // Expired intervals elsewhere in the heap cannot
            // affect the current answer unless they reach the top.
            while(!pq.isEmpty() && pq.peek()[1] < curr){
                pq.poll();
            }

            // STEP 8: Find the answer for the current query.
            //
            // If the heap is empty, no valid interval contains
            // the current query.
            if(pq.isEmpty()){
                res[idx] = -1;
            }
            else{
                // The top of the Min Heap contains the smallest
                // valid interval for the current query.
                res[idx] = pq.peek()[0];
            }
        }

        // STEP 9: Return answers in their original query order.
        return res;
    }
}
