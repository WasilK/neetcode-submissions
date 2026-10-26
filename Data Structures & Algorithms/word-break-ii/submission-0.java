class Solution {

    List<String> res;
    HashSet<String> set;

    public List<String> wordBreak(String s, List<String> wordDict) {
        res = new ArrayList<>();
        set = new HashSet<>(wordDict);
        List<String> curr = new ArrayList<>();
        backtrack(s, 0, set, curr);
        return res;
    }

    void backtrack(String s, int idx, HashSet<String> set, List<String> curr){
        if(idx == s.length()){
            res.add(String.join(" ", curr));
            return;
        }
        for(int i = idx; i < s.length(); i++){
            String w = s.substring(idx, i + 1);
            if(set.contains(w)){
                curr.add(w);
                backtrack(s, i + 1, set, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }
}