class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int res[] = new int[queries.length];
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int arr[][] = new int[queries.length][2];
        for(int i = 0; i < queries.length; i++){
            arr[i][0] = queries[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int j = 0;
        for(int i = 0; i < arr.length; i++){
            int curr = arr[i][0];
            int idx = arr[i][1];
            while(j < intervals.length && intervals[j][0] <= curr){
                int len = intervals[j][1] - intervals[j][0] + 1;
                pq.offer(new int[]{len, intervals[j][1]});
                j++;
            }
            while(!pq.isEmpty() && pq.peek()[1] < curr){
                pq.poll();
            }
            if(pq.isEmpty()){
                res[idx] = -1;
            }else{
                res[idx] = pq.peek()[0];
            }
        }
        return res;
    }
}
