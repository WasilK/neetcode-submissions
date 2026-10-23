class Solution {
    public int splitArray(int[] nums, int k) {
        int l = 0, r = 0;
        for(int num : nums){
            l = Math.max(l, num);
            r += num;
        }
        int res = r;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(canSplit(nums, mid, k)){
                res = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return res;
    }
    public boolean canSplit(int nums[], int largest, int k){
        int subarray = 1, curr = 0;
        for(int num : nums){
            curr += num;
            if(curr > largest){
                subarray++;
                if(subarray > k) return false;
                curr = num;
            }
        }
        return true;
    }
}