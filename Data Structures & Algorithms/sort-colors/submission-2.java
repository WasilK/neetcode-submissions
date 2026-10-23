class Solution {
    public void sortColors(int[] nums) {
        int oneP = 0, zeroP = 0, twoP = nums.length - 1;

        while(oneP <= twoP){
            if(nums[oneP] == 0){
                swap(nums, zeroP, oneP);
                oneP++;
                zeroP++;
            }else if(nums[oneP] == 1){
                oneP++;
            }else{
                swap(nums, oneP, twoP);
                twoP--;
            }
        }
    }
    public void swap(int nums[], int st, int en){
        int temp = nums[st];
        nums[st] = nums[en];
        nums[en] = temp;
    }
}