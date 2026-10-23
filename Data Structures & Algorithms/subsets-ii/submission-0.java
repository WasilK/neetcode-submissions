class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        findSubsets(nums, 0, list, res);
        return res;
    }
    public void findSubsets(int nums[], int idx, List<Integer> list, List<List<Integer>> res){
        if(idx == nums.length){
            if(!res.contains(list)){
                res.add(new ArrayList<>(list));
            }
            return;
        }

        list.add(nums[idx]);
        findSubsets(nums, idx + 1, list, res);
        list.remove(list.size() - 1);
        findSubsets(nums, idx + 1, list, res);
    }
}
