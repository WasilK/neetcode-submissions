class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        findSum(candidates, target, 0, list, res);
        return res;
    }
    public void findSum(int nums[], int target, int st, List<Integer> list, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = st; i < nums.length; i++){

            if(i > st && nums[i] == nums[i - 1]) continue;

            if(nums[i] > target) break;

            list.add(nums[i]);
            findSum(nums, target - nums[i], i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
