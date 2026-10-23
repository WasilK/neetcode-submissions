class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> curr = new ArrayList<>();
        backtrack(n, 1, k, curr);
        return res;
    }

    public void backtrack(int n, int st, int k, List<Integer> curr){
        if(curr.size() == k){
            res.add(new ArrayList<>(curr));
            return;
        }
        
        for(int i = st; i <= n - (k - curr.size()) + 1; i++){
            curr.add(i);
            backtrack(n, i + 1, k, curr);
            curr.remove(curr.size() - 1);
        }
    }
}