class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> curr = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, curr, new boolean[nums.length]);
        return res;
    }

    public void backtrack(int nums[], List<Integer> curr, boolean pick[]){
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(pick[i]){
              continue;
            }
            if(i > 0 && nums[i] == nums[i - 1] && !pick[i - 1]) continue;
            
            curr.add(nums[i]);
            pick[i] = true;
            backtrack(nums, curr, pick);
            pick[i] = false;
            curr.remove(curr.size() - 1);
        }
    }
}