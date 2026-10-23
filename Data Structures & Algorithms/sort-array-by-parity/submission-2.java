class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int st = 0, en = nums.length - 1;
        while(st < en){
            if(nums[st] % 2 != 0 && nums[en] % 2 == 0){
                int temp = nums[st];
                nums[st] = nums[en];
                nums[en] = temp;
                st++;
                en--;
            }else if(nums[st] % 2 == 0){
                st++;
            }else if(nums[en] % 2 != 0){
                en--;
            }
        }
        return nums;
    }
}