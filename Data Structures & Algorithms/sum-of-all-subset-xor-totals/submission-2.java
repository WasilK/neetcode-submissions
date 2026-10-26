class Solution {
    int sum = 0;
    public int subsetXORSum(int[] nums) {
        int xor = 0;
        backtrack(nums, 0, xor);
        return sum;
    }
    void backtrack(int nums[], int i, int xor){
        if(i >= nums.length){
            sum += xor;
            return;
        }
        xor ^= nums[i];
        backtrack(nums, i + 1, xor);
        xor ^= nums[i];
        backtrack(nums, i + 1, xor);
    }
}