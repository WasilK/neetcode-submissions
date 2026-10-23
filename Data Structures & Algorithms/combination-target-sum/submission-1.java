class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<Integer> curr = new ArrayList<>();
        backtrack(nums, 0, target, curr);
        return res;
    }

    public void backtrack(int nums[], int i, int target, List<Integer> curr){
        if(target == 0){
            res.add(new ArrayList<>(curr));
            return;
        }
        if(target < 0 || i >= nums.length){
            return;
        }

        curr.add(nums[i]);
        backtrack(nums, i, target - nums[i], curr);
        curr.remove(curr.size() - 1);
        backtrack(nums, i + 1, target, curr);
    }
}
