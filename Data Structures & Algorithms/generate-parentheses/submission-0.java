class Solution {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        StringBuilder curr = new StringBuilder();
        backtrack(n, curr, 0, 0);
        return res;
    }

    public void backtrack(int n, StringBuilder curr, int open, int close){
        if(curr.length() ==  2*n){
            res.add(curr.toString());
            return;
        }
        if(open < n){
            curr.append('(');
            backtrack(n, curr, open + 1, close);
            curr.deleteCharAt(curr.length() - 1);
        }
        if(close < open){
            curr.append(')');
            backtrack(n, curr, open, close + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
