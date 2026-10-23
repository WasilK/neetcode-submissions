class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinations(n, k, 1, list, res);
        return res;
    }
    public void combinations(int n, int k, int i, List<Integer> list, List<List<Integer>> res){
        if(k == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        if(i > n || (n - i + 1) < k) return;
        list.add(i);
        combinations(n, k - 1, i + 1, list, res);
        list.remove(list.size() - 1);
        combinations(n, k, i + 1, list, res);
    }
}