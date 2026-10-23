class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        findPermutation(nums, 0, res);
        return res;
    }
    public void findPermutation(int nums[], int idx, List<List<Integer>> res){
        if(idx == nums.length){
            List<Integer> list = new ArrayList<>();
            for(int num : nums){
                list.add(num);
            }
            res.add(list);
            return;
        }
        for(int i = idx; i < nums.length; i++){
            swap(nums, idx, i);
            findPermutation(nums, idx + 1, res);
            swap(nums, idx, i);
        }
    }
    public void swap(int nums[], int st, int en){
        while(st <= en){
            int temp = nums[st];
            nums[st] = nums[en];
            nums[en] = temp;
            st++;
            en--;
        }
    }
}
