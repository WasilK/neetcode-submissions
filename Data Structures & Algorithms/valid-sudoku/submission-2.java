class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashMap<Integer, HashSet<Character>> rows = new HashMap<>();
        HashMap<Integer, HashSet<Character>> cols = new HashMap<>();
        HashMap<String, HashSet<Character>> squares = new HashMap<>();

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                String key = (i / 3) + "," +  (j / 3);
                if(board[i][j] == '.') continue;
                if(rows.computeIfAbsent(i, k -> new HashSet<>()).contains(board[i][j]) || 
                cols.computeIfAbsent(j, k -> new HashSet<>()).contains(board[i][j]) || 
                squares.computeIfAbsent(key, k -> new HashSet<>()).contains(board[i][j])){
                    return false;
                }
                rows.get(i).add(board[i][j]);
                cols.get(j).add(board[i][j]);
                squares.get(key).add(board[i][j]);
            }
        }
        return true;
    }
}
