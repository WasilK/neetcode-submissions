class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        findSubsets(nums, list, 0, result);
        return result;
    }
    public static void findSubsets(int nums[], List<Integer> list, int i, List<List<Integer>> result){
        if(i == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[i]);
        findSubsets(nums, list, i + 1, result);
        list.remove(list.size() - 1);
        findSubsets(nums, list, i + 1, result);
    }
}
