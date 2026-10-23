class Solution {
    public int subsetXORSum(int[] nums) {
        return findSubsets(nums, 0, 0);
    }
    public static int findSubsets(int nums[], int xor, int i){
        if(i == nums.length){
            return xor;
        }
        return findSubsets(nums, xor ^ nums[i], i + 1) + findSubsets(nums, xor, i + 1);
    }
}