class Solution {
    public int findMin(int[] nums) {
        int st = 0, en = nums.length - 1;
        while(st < en){
            int mid = st + (en - st) / 2;
            if(nums[mid] > nums[en]){
                st = mid + 1;
            }else{
                en = mid;
            }
        }
        return nums[st];
    }
}
