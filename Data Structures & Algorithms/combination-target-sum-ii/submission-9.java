class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> curr = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, curr);
        return res;
    }

    public void backtrack(int arr[], int idx, int target, List<Integer> curr){
        if(target == 0){
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i = idx; i < arr.length; i++){
            if(i > idx && arr[i] == arr[i - 1]){
                continue;
            }
            if(target < 0){
                break;
            }
            curr.add(arr[i]);
            backtrack(arr, i + 1, target - arr[i], curr);
            curr.remove(curr.size() - 1);
        }
    }
}
