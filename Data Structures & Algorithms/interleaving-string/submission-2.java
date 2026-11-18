class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        Boolean dp[][] = new Boolean[s1.length() + 1][s2.length() + 1];
        return func(0, 0, 0, s1, s2, s3, dp);
    }
    boolean func(int i, int j, int k, String s1, String s2, String s3, Boolean dp[][]){
        if(k == s3.length()){
            return true;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        boolean takeS1 = false;
        boolean takeS2 = false; 
        if(i < s1.length() && s1.charAt(i) == s3.charAt(k)){
            takeS1 = func(i + 1, j, k + 1, s1, s2, s3, dp);
        }
        if(j < s2.length() && s2.charAt(j) == s3.charAt(k)){
            takeS2 = func(i, j + 1, k + 1, s1, s2, s3, dp);
        }
        return dp[i][j] = takeS1 || takeS2;
    }
}
