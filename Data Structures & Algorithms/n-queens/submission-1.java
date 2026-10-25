class Solution {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char board[][] = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        boolean cols[] = new boolean[n];
        boolean diag1[] = new boolean[2 * n - 1];
        boolean diag2[] = new boolean[2 * n - 1];
        backtrack(board, 0, n, cols, diag1, diag2);
        return res;
    }
    void backtrack(char board[][], int row, int n, boolean cols[], boolean diag1[], boolean diag2[]){
        if(row == n){
            List<String> curr = new ArrayList<>();
            for(char r[] : board){
                curr.add(new String(r));
            }
            res.add(curr);
            return;
        }
        for(int col = 0; col < n; col++){
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if(cols[col] || diag1[d1] || diag2[d2]) continue;

            board[row][col] = 'Q';
            diag1[d1] = true;
            diag2[d2] = true;
            cols[col] = true;

            backtrack(board, row + 1, n, cols, diag1, diag2);

            
            board[row][col] = '.';
            diag1[d1] = false;
            diag2[d2] = false;
            cols[col] = false;
        }
    }
}
