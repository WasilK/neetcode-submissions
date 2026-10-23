class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean used[] = new boolean[nums.length];
        findPermutation(nums, list, res, used);
        return res;
    }
    public void findPermutation(int nums[], List<Integer> list, List<List<Integer>> res, boolean used[]){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            if(!used[i]){
                list.add(nums[i]);
                used[i] = true;
                findPermutation(nums, list, res, used);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
}