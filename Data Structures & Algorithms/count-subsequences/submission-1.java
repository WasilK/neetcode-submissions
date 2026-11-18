class Solution {
    public int numDistinct(String s, String t) {
        int dp[][] = new int[s.length() + 1][t.length() + 1];
        for(int d[] : dp){
            Arrays.fill(d, -1);
        }
        return func(s, t, 0, 0, dp);
    }
    int func(String s, String t, int i ,int j, int dp[][]){
        if(j == t.length()){
            return 1;
        }
        if(i >= s.length()){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(s.charAt(i) == t.charAt(j)){
            return dp[i][j] = func(s, t, i + 1, j + 1, dp) + func(s, t, i + 1, j, dp);
        }else {
            return dp[i][j] = func(s, t, i + 1, j, dp);
        }
    }
}
