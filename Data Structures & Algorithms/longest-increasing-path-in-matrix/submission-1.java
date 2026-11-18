class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int dp[][] = new int[n + 1][m + 1];
        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < m + 1; j++){
                dp[i][j] = -1;
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                ans = Math.max(ans, func(i, j, matrix, dp));
            }
        }
        return ans;
    }
    int func(int i, int j, int matrix[][], int dp[][]){
        int up = 0, down = 0, left = 0, right = 0;
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(i > 0 && i < matrix.length){
            if(matrix[i - 1][j] > matrix[i][j]){
                up += func(i - 1, j, matrix, dp);
            }
        }
        if(i >= 0 && i < matrix.length - 1){
            if(matrix[i + 1][j] > matrix[i][j]){
                down += func(i + 1, j, matrix, dp);
            }
        }
        if(j > 0 && j < matrix[0].length){
            if(matrix[i][j - 1] > matrix[i][j]){
                left += func(i, j - 1, matrix, dp);
            }
        }
        if(j >= 0 && j < matrix[0].length - 1){
            if(matrix[i][j + 1] > matrix[i][j]){
                right += func(i, j + 1, matrix, dp);
            }
        }
        return dp[i][j] = 1 + Math.max(up, Math.max(down, Math.max(left, right)));
    }
}
