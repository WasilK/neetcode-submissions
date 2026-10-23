class Solution {
    public int findMin(int[] nums) {
        int bIdx = -1;
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i+1]){
                bIdx = i;
                break;
            }
        }
        if(bIdx == -1){
            return nums[0];
        }else{
            return nums[bIdx + 1];
        }
    }
}
