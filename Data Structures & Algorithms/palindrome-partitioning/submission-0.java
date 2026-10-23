class Solution {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        List<String> curr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack(s, curr, 0);
        return res;
    }

    void backtrack(String s, List<String> curr, int idx){
        if(idx >= s.length()){
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i = idx; i < s.length(); i++){
           String sub = s.substring(idx, i + 1);
           if(palindrome(sub)){
               curr.add(sub);
               backtrack(s, curr, i + 1);
               curr.remove(curr.size() - 1);
           }
        }
    }

    boolean palindrome(String s){
        int l = 0, r = s.length() - 1;
        while(l <= r){
            if(s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
