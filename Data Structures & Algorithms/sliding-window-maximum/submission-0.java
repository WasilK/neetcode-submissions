class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int res[] = new int[n - k + 1];
        for(int i = 0; i < n - k + 1; i++){
            int max = nums[i];
            for(int j = i; j < k + i; j++){
               max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }
}
