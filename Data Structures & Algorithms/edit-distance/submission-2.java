class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int dp[][] = new int[n + 1][m + 1];
        for(int i = 1; i < n + 1; i++){
            dp[i][0] = i;
        }
        for(int j = 1; j < m + 1; j++){
            dp[0][j] = j;
        }
        dp[0][0] = 0;
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < m + 1; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], 
                    dp[i - 1][j - 1]));
                }
            }
        }
        return dp[n][m];
        // return func(word1, word2, 0, 0);
    }
    // int func(String word1, String word2, int i, int j){
    //     if(j == word2.length()){
    //         return word1.length() - i;
    //     }
    //     if(i == word1.length()){
    //         return word2.length() - j;
    //     }
    //     if(word1.charAt(i) == word2.charAt(j)){
    //         return func(word1, word2, i + 1, j + 1);
    //     }else {
    //         int insert = func(word1, word2, i, j + 1);
    //         int delete = func(word1, word2, i + 1, j);
    //         int replace = func(word1, word2, i + 1, j + 1);
    //         return 1 + Math.min(insert, Math.min(delete, replace));
    //     }
    // }
}
