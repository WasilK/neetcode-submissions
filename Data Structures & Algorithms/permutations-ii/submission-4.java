class Solution {

    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> curr = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, curr, new boolean[nums.length]);
        return new ArrayList<>(res);
    }

    public void backtrack(int nums[], int idx, List<Integer> curr, boolean pick[]){
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!pick[i]){
                curr.add(nums[i]);
                pick[i] = true;
                backtrack(nums, i + 1, curr, pick);
                pick[i] = false;
                curr.remove(curr.size() - 1);
            }
        }
    }
}