class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int row = 0; row < 9; row++){
            HashSet<Character> set = new HashSet<>();
            for(int i = 0; i < 9; i++){
                if(board[row][i] == '.') continue;
                if(set.contains(board[row][i])) return false;
                set.add(board[row][i]);
            }
        }

        for(int col = 0; col < 9; col++){
            HashSet<Character> set = new HashSet<>();
            for(int j = 0; j < 9; j++){
                if(board[j][col] == '.') continue;
                if(set.contains(board[j][col])) return false;
                set.add(board[j][col]);
            }
        }

        for(int square = 0; square < 9; square++){
            HashSet<Character> set = new HashSet<>();
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    int row = (square / 3) * 3 + i;
                    int col = (square % 3) * 3 + j;
                    if (board[row][col] == '.') continue;
                    if(set.contains(board[row][col])) return false;
                    set.add(board[row][col]);
                }
            }
        }
        return true;
    }
}
