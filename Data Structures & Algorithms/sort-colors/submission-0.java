class Solution {
    public void sortColors(int[] nums) {
        int oneP = 0;
        int zeroP = 0;
        int twoP = nums.length - 1;
        while(oneP <= twoP){
            if(nums[oneP] == 1){
                oneP++;
            }else if(nums[oneP] == 0){
                int temp = nums[zeroP];
                nums[zeroP] = nums[oneP];
                nums[oneP] = temp;
                oneP++;
                zeroP++;
            }else{
                int temp = nums[twoP];
                nums[twoP] = nums[oneP];
                nums[oneP] = temp;
                twoP--;
            }
        }
    }
}