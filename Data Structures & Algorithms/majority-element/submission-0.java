class Solution {
    public int majorityElement(int[] nums) {
        int res = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(count == 0){
                res = nums[i];
            }
            count += (nums[i] == res) ? 1 : -1;
        }
        return res;
    }
}