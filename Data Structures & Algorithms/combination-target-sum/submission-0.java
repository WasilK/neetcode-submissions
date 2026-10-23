class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        findSum(nums, target, 0, list, res);
        return res;
    }
    public void findSum(int nums[], int target, int i, List<Integer> list, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        if(target < 0 || i >= nums.length){
            return;
        }
        list.add(nums[i]);
        findSum(nums, target - nums[i], i, list, res);
        list.remove(list.size() - 1);
        findSum(nums, target, i + 1, list, res);
    }
}
