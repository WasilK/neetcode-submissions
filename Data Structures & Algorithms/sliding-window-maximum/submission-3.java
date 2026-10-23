class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int res[] = new int[n - k + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);

        int idx = 0;
        for(int i = 0; i < n; i++){
            pq.offer(new int[]{nums[i], i});
            if(i >= k - 1){
                while(pq.peek()[1] <= i - k){
                    pq.poll();
                }
                res[idx++] = pq.peek()[0];
            }
        }
        return res;
    }
}
