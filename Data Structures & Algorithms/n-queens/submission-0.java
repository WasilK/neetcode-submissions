class Solution {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> curr = new ArrayList<>();
        char board[][] = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        backtrack(board, curr, 0, n);
        return res;
    }
    void backtrack(char board[][], List<String> curr, int idx, int n){
        if(idx == n){
            res.add(new ArrayList<>(curr));
            return;
        }
        for(int i = 0; i < n; i++){
            if(!isPresent(board, idx, i)){
                board[idx][i] = 'Q';
                curr.add(new String(board[idx]));
                backtrack(board, curr, idx + 1, n);
                curr.remove(curr.size() - 1);
                board[idx][i] = '.';
            }
        }
    }
    boolean isPresent(char[][] board, int row, int col) {
    // Check upper column
    for (int i = row - 1; i >= 0; i--) {
        if (board[i][col] == 'Q') {
            return true;
        }
    }

    // Check upper-left diagonal
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') {
            return true;
        }
    }

    // Check upper-right diagonal
    for (int i = row - 1, j = col + 1;
         i >= 0 && j < board.length; i--, j++) {
        if (board[i][j] == 'Q') {
            return true;
        }
    }

    return false;
}
}
