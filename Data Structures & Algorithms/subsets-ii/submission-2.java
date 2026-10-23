class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> curr = new ArrayList<>();
        backtrack(nums, 0, curr);
        return res;
    }

    public void backtrack(int nums[], int idx, List<Integer> curr){
        if(idx >= nums.length){
            if(!res.contains(curr)){
                res.add(new ArrayList<>(curr));
            }
            return;
        }
        curr.add(nums[idx]);
        backtrack(nums, idx + 1, curr);
        curr.remove(curr.size() - 1);
        backtrack(nums, idx + 1, curr);
    }
}
