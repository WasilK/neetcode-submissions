class Solution {
    public boolean search(int[] nums, int target) {
        int st = 0, en = nums.length - 1;
        while(st <= en){
            int mid = st + (en - st) / 2;
            if(nums[mid] == target){
                return true;
            }
            if(nums[st] == nums[mid] && nums[mid] == nums[en]){
                st++;
                en--;
            }
            else if(nums[mid] >= nums[st]){
                if((target >= nums[st] && target < nums[mid])){
                    en = mid - 1;
                }else{
                    st = mid + 1;
                }
            }else{
                if(target > nums[mid] && target <= nums[en]){
                    st = mid + 1;
                }else{
                    en = mid - 1;
                }
            }
        }
        return false;
    }
}