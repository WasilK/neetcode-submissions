class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        findSubsets(nums, 0, list, res);
        return res;
    }
    public void findSubsets(int nums[], int idx, List<Integer> list, List<List<Integer>> res){
    
        res.add(new ArrayList<>(list));

        for(int i = idx; i < nums.length; i++){
            if(i > idx && nums[i] == nums[i - 1]) continue;

            list.add(nums[i]);
            findSubsets(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}
