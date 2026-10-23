class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> subset = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, subset);
        return res;
    }

    public void backtrack(int arr[], int target, int idx, List<Integer> subset){
        if(target == 0){
            res.add(new ArrayList<>(subset));
            return;
        }
        if(target < 0 || idx >= arr.length){
            return;
        }
        subset.add(arr[idx]);
        backtrack(arr, target - arr[idx], idx + 1, subset);
        subset.remove(subset.size() - 1);
        while(idx < arr.length - 1 && arr[idx] == arr[idx + 1]){
            idx++;
        }
        backtrack(arr, target, idx + 1, subset);
    }
}
