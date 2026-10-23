class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> curr = new ArrayList<>();
        backtrack(nums, 0, curr);
        return res;
    }

    public void backtrack(int nums[], int idx, List<Integer> curr){
        for(int i = idx; i < nums.length; i++){
            if(i > idx && nums[i] == nums[i - 1]) continue;
            curr.add(nums[i]);
            backtrack(nums, i + 1, curr);
            curr.remove(curr.size() - 1);
        }
        res.add(new ArrayList<>(curr));
        return;
    }
}
