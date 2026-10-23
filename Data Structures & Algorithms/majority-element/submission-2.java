class Solution {
    public int majorityElement(int[] nums) {
        int curr = nums[0];
        int count = 0;

        for(int num : nums){
            if(count == 0){
                curr = num;
            }
            count += (curr == num) ? 1 : -1;
        }
        return curr;
    }
}